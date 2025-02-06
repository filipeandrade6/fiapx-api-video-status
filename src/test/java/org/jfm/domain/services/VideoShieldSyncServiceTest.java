// package org.jfm.domain.services;

// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import org.jfm.domain.entities.Video;
// import org.jfm.domain.entities.enums.Status;
// import org.jfm.domain.exceptions.SqsException;
// import org.jfm.domain.usecases.VideoCannonSyncUseCase;
// import org.jfm.domain.usecases.VideoUseCase;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;

// import io.quarkus.test.InjectMock;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;
// import software.amazon.awssdk.services.sqs.SqsClient;
// import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
// import software.amazon.awssdk.services.sqs.model.Message;
// import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
// import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

// @QuarkusTest
// public class VideoShieldSyncServiceTest {

//   private SqsClient sqsClientMock;
//   private VideoUseCase videoUseCase;
//   private VideoCannonSyncUseCase cannonUseCase;
//   private VideoShieldSyncService service;

//   private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/test-queue";

//   @BeforeEach
//   void setUp() {
//     sqsClientMock = mock(SqsClient.class);
//     service = new VideoShieldSyncService(sqsClientMock, queueUrl, videoUseCase, cannonUseCase);
//   }

//   @Test
//   public void receberMensagemTest() {

//     List<Message> mensagens = List.of(
//       Message.builder()
//           .messageId("msg-1")
//           .receiptHandle("handle-1")
//           .body("23e52205-4d9d-41e6-a7f3-271af4f5316b.SOLICITADO.email1@example.com")
//           .build(),
//       Message.builder()
//           .messageId("msg-2")
//           .receiptHandle("handle-2")
//           .body("e1ea5f84-be64-4589-a6bc-5894fd0018db.FALHA.email2@example.com")
//           .build()
//     );

//     // String expectedMessageId = "12345";

//     // ReceiveMessageResponse responseMock = ReceiveMessageResponse.builder()
//     //   .messages(mensagens)
//     //   .build();

//     when(sqsClientMock.receiveMessage(any(ReceiveMessageRequest.class))
//       .messages())
//       .thenReturn(mensagens);

//     service.receberMensagens();

//     verify(sqsClientMock, times(1)).receiveMessage(any(ReceiveMessageRequest.class)); 
//   }
// }
