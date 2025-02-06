package org.jfm.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@QuarkusTest
public class VideoCannonSyncServiceTest {
  
  private SqsClient sqsClientMock;
  private VideoCannonSyncService service;

  private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/test-queue";

  @BeforeEach
  void setUp() {
    sqsClientMock = mock(SqsClient.class);
    service = new VideoCannonSyncService(sqsClientMock, queueUrl);
  }

  @Test
  public void enviarMensagemTest() {
    // Arrange
    String mensagem = "23e52205-4d9d-41e6-a7f3-271af4f5316b.exemplo@exemplo.com";
    String expectedMessageId = "12345";

    SendMessageResponse responseMock = SendMessageResponse.builder()
      .messageId(expectedMessageId)
      .build();

    when(sqsClientMock.sendMessage(any(SendMessageRequest.class))).thenReturn(responseMock);

    // Act
    String messagemId = service.enviarMensagem(mensagem);

    // Assert
    assertEquals(expectedMessageId, messagemId);
    verify(sqsClientMock, times(1)).sendMessage(any(SendMessageRequest.class));
  }
}
