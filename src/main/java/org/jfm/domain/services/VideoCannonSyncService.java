package org.jfm.domain.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.SqsException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@ApplicationScoped
public class VideoCannonSyncService {

  @Inject
  SqsClient sqs;

  @Inject
  @ConfigProperty(name = "queue.enviar.url")
  String queueUrl;

  public String enviarMensagem(String messageBody) {
    try {
      SendMessageRequest request = SendMessageRequest.builder()
        .queueUrl(queueUrl)
        .messageBody(messageBody)
        .build();
      
      SendMessageResponse response = sqs.sendMessage(request);
      System.out.println("messageId: " + response.messageId());

      return response.messageId();
    } catch (Exception e) {
      throw new SqsException(ErrosSistemaEnum.FALHA_COMUNICACAO.getMessage());
    }
  }

}
