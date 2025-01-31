package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.entities.Video;
import org.jfm.domain.ports.VideoRepository;
import org.jfm.domain.usecases.VideoUseCase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import jakarta.inject.Inject;
import software.amazon.awssdk.services.sqs.SqsClient;

public class VideoService implements VideoUseCase {

  VideoRepository repository;

  // @Inject
  // SqsClient sqs;

  // @Inject
  // @ConfigProperty(name = "queue.url")
  // String queueUrl;

  // static ObjectReader VIDEO_READER = new ObjectMapper().readerFor(Video.class);

  // private static int MAX_NUMBER_MESSAGES = 10; 

  public VideoService(VideoRepository repository) {
    this.repository = repository;
  }

  @Override
  public UUID criar(Video video) {
    //TODO: ID do video já vem preenchido?
    repository.criar(video);

    return video.getId();
  }

  @Override
  public Video buscarPorId(UUID id) {
    return repository.buscarPorId(id);
  }

  @Override
  public List<Video> buscarPorEmail(String email) {
    return repository.buscarPorEmail(email);
  }

  @Override
  public void editar(Video video) {
    repository.editar(video);
  }

  @Override
  public void remover(UUID id) {
    Video video = repository.buscarPorId(id);
    repository.remover(video);
  }

  // @Override
  // public void criarPorMessagens() {
  //   List<Message> messagens = sqs.receiveMessage(m -> m.maxNumberOfMessages(MAX_NUMBER_MESSAGES).queueUrl(queueUrl)).messages();

  //   List<Video> videos = messagens.stream()
  //     .map(Message::body)
  //     .map(this::toVideo)
  //     .collect(Collectors.toList());
    
  //   repository.criarPorMessagens(videos);
  // }

  // private Video toVideo(String message) {
  //   Video video = null;
  //   try {
  //     video = VIDEO_READER.readValue(message);
  //   } catch (Exception e) {
  //     throw new RuntimeException(e);
  //   }
  //   return video;
  // }

//   private void deleteMessage(Message message) {
//     sqs.deleteMessage(DeleteMessageRequest.builder()
//         .queueUrl(queueUrl)
//         .receiptHandle(message.receiptHandle())
//         .build());
// }

}
