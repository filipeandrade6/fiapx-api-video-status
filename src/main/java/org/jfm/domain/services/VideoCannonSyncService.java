package org.jfm.domain.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.SqsException;
import org.jfm.domain.usecases.VideoCannonSyncUseCase;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class VideoCannonSyncService implements VideoCannonSyncUseCase {

  SqsClient sqs;

  String queueUrl;

  public VideoCannonSyncService(SqsClient sqs, String queueUrl) {
    this.sqs = sqs;
    this.queueUrl = queueUrl;
  }

  @Override
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
