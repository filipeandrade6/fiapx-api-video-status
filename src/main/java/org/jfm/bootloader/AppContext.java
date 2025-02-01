package org.jfm.bootloader;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jfm.domain.ports.VideoRepository;
import org.jfm.domain.services.VideoCannonSyncService;
import org.jfm.domain.services.VideoShieldSyncService;
import org.jfm.domain.usecases.VideoCannonSyncUseCase;
import org.jfm.domain.usecases.VideoUseCase;
import org.jfm.domain.services.VideoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import software.amazon.awssdk.services.sqs.SqsClient;

@ApplicationScoped
public class AppContext {

  @Produces
  public VideoService VideoService(VideoRepository videoRepository) {
    return new VideoService(videoRepository);
  };
  
  @Produces
  public VideoShieldSyncService VideoShieldSyncService(
    SqsClient sqs,
    @ConfigProperty(name = "queue.receber.url") String queueUrl,
    VideoUseCase videoUseCase,
    VideoCannonSyncUseCase videoCannonSyncUseCase) {
    return new VideoShieldSyncService(
      sqs,
      queueUrl,
      videoUseCase, 
      videoCannonSyncUseCase
    );
  }

  @Produces
  public VideoCannonSyncService VideoCannonSyncService(
    SqsClient sqs,
    @ConfigProperty(name = "queue.enviar.url") String queueUrl
  ) {
    return new VideoCannonSyncService(
      sqs,
      queueUrl
    );
  }

}
