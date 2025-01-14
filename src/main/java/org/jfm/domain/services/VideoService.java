package org.jfm.domain.services;

import java.util.UUID;

import org.jfm.domain.entities.Video;
import org.jfm.domain.ports.VideoRepository;
import org.jfm.domain.usecases.VideoUseCase;

public class VideoService implements VideoUseCase{
  VideoRepository repository;

  public VideoService(VideoRepository repository) {
    this.repository = repository;
  }

  @Override
  public Video buscarPorId(UUID id) {
    return repository.buscarPorId(id);
  }
}
