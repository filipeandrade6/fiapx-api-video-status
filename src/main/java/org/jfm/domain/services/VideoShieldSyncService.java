package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jfm.domain.entities.Video;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.SqsException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@ApplicationScoped
public class VideoShieldSyncService {
  
  @Inject
  SqsClient sqs;

  @Inject
  @ConfigProperty(name = "queue.receber.url")
  String queueUrl;

  @Inject
  @RestClient
  VideoService videoService;

  @Inject
  @RestClient
  VideoCannonSyncService videoCannonSyncService;

  static ObjectReader VIDEO_READER = new ObjectMapper().readerFor(Video.class);

  private static int NUMERO_MAXIMO_MENSAGENS = 20;
  private static int DURACAO_POLLING = 20;

  public VideoShieldSyncService(VideoService videoService, VideoCannonSyncService videoCannonSyncService) {
    this.videoService = videoService;
    this.videoCannonSyncService = videoCannonSyncService;
  }

  @PostConstruct
  public void startListening() {
    Executors.newSingleThreadExecutor().submit(this::receberMensagens);
  }

  public void receberMensagens() {
    while (true) {
      try {
        List<Message> mensagens = sqs.receiveMessage(ReceiveMessageRequest.builder()
          .queueUrl(queueUrl)
          .maxNumberOfMessages(NUMERO_MAXIMO_MENSAGENS)
          .waitTimeSeconds(DURACAO_POLLING)
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
    }
  }

  private void processarMensagem(Message mensagem) {
    System.out.println("Mensagem recebida: " + mensagem.body());

    Video video = mensagemToVideo(mensagem);

    Video videoBuscado = videoService.buscarPorId(video.getId());
    if (videoBuscado == null) {
      videoService.criar(video);
      return;
    } else {
      videoBuscado.setStatus(video.getStatus());
      videoBuscado.setDataAtualizacao(Instant.now());

      videoService.editar(videoBuscado);
    }

    if (video.getStatus() == Status.FALHA) {
      String mensagemEnviada = String.join(".", video.getId().toString(), video.getEmail());
      String mensagemId = videoCannonSyncService.enviarMensagem(mensagemEnviada);
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

  private Video mensagemToVideo(Message mensagem) {
    String[] bodyStrings = mensagem.body().split("\\.");
    Video video = new Video(UUID.fromString(bodyStrings[0]), Status.fromString(bodyStrings[1]), bodyStrings[2]);

    return video;
  }

}
