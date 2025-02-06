package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.entities.Video;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.SqsException;
import org.jfm.domain.usecases.VideoCannonSyncUseCase;
import org.jfm.domain.usecases.VideoShieldSyncUseCase;
import org.jfm.domain.usecases.VideoUseCase;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public class VideoShieldSyncService implements VideoShieldSyncUseCase {
  SqsClient sqs;

  // @ConfigProperty(name = "SQS.AWS.RECEBER")
  String queueUrl;

  VideoUseCase videoUseCase;

  VideoCannonSyncUseCase videoCannonSyncUseCase;

  private static int NUMERO_MAXIMO_MENSAGENS = 10;
  private static int DURACAO_POLLING = 20;

  public VideoShieldSyncService(
    SqsClient sqs,
    String queueUrl,
    VideoUseCase videoUseCase, 
    VideoCannonSyncUseCase videoCannonSyncUseCase
  ) {
    this.videoUseCase = videoUseCase;
    this.videoCannonSyncUseCase = videoCannonSyncUseCase;
    this.sqs = sqs;
    this.queueUrl = queueUrl;
  }

  @PostConstruct
  public void startListening() {
    System.out.println("Start Listening");
    Executors.newSingleThreadExecutor().submit(this::receberMensagens);
  }

  @Override
  public void receberMensagens() {
    System.out.println("receberMensagens");
    // while (true) {
      try {
        List<Message> mensagens = sqs.receiveMessage(ReceiveMessageRequest.builder()
          .queueUrl(queueUrl)
          .maxNumberOfMessages(NUMERO_MAXIMO_MENSAGENS)
          // .waitTimeSeconds(DURACAO_POLLING)
          .build()).messages();

        for (Message mensagem : mensagens) {
          processarMensagem(mensagem);
          deletarMensagem(mensagem);
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        throw new SqsException(ErrosSistemaEnum.FALHA_COMUNICACAO.getMessage());
      }
    // }
  }

  private void processarMensagem(Message mensagem) {
    System.out.println("Mensagem recebida: " + mensagem.body());

    Status videoStatus = mensagemVideoStatus(mensagem);
    UUID videoId = mensagemVideoId(mensagem);

    Video videoBuscado = videoUseCase.buscarPorId(videoId);

    if (videoBuscado == null && videoStatus == Status.SOLICITADO) {
      System.out.println("video nao encontrado, mensagemId: " + mensagem.messageId());
      // // exemplo 1: "uuid.estado.email";
      Video video = new Video(videoId, videoStatus, desmembrarMensagem(mensagem)[2]);
      videoUseCase.criar(video);
      return;
    } else if (videoBuscado != null && videoStatus != Status.SOLICITADO) {
      System.out.println("video encontrado, mensagemId: " + mensagem.messageId());
      // // exemplo 2: "uuid.estado"
      // // exemplo 3: "uuid.falha.descricaoDaFalha"
      videoBuscado.setStatus(videoStatus);
      videoBuscado.setDataAtualizacao(Instant.now());

      videoUseCase.editar(videoBuscado);
    }
 
    if (videoStatus == Status.FALHA) {
      // // exemplo 3: "uuid.falha.descricaoDaFalha"
      String mensagemEnviada = String.join(".", videoId.toString(), videoBuscado.getEmail(), desmembrarMensagem(mensagem)[2]);
      String mensagemId = videoCannonSyncUseCase.enviarMensagem(mensagemEnviada);

      // TODO: como saber se mensagem nao foi enviada?
      if (mensagemId == null || mensagemId.isBlank()) {
        throw new SqsException(ErrosSistemaEnum.FALHA_COMUNICACAO.getMessage());
      }
    }
  }

  private void deletarMensagem(Message mensagem) {
    sqs.deleteMessage(DeleteMessageRequest.builder()
          .queueUrl(queueUrl)
          .receiptHandle(mensagem.receiptHandle())
          .build());
  }

  private String[] desmembrarMensagem(Message mensagem) {
    return mensagem.body().split("\\.");
  }

  private UUID mensagemVideoId(Message mensagem) {
    return UUID.fromString(desmembrarMensagem(mensagem)[0]);
  }

  private Status mensagemVideoStatus(Message mensagem) {
    return Status.fromString(desmembrarMensagem(mensagem)[1]);
  }

}
