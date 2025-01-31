package org.jfm.bootloader;

import org.jfm.domain.ports.VideoRepository;
import org.jfm.domain.services.VideoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class AppContext {

  @Produces
  public VideoService VideoService(VideoRepository videoRepository) {
    return new VideoService(videoRepository);
  };
  
  // TODO: por ser ApplicationScoped, é necessário configurar?
  // @Produces
  // public VideoShieldSyncService VideoShieldSyncService(
  //   @RestClient VideoService videoService,
  //   @RestClient VideoCannonSyncService videoCannonSyncService) {
  //   return new VideoShieldSyncService(videoService, videoCannonSyncService);
  // }

}
