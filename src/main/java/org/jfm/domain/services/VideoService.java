package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;
import org.jfm.domain.ports.VideoRepository;
import org.jfm.domain.usecases.VideoUseCase;

public class VideoService implements VideoUseCase {

  VideoRepository repository;

  public VideoService(VideoRepository repository) {
    this.repository = repository;
  }

  @Override
  public UUID criar(Video video) {
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

}
