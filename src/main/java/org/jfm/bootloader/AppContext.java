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

}
