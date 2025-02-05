package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;

public interface VideoUseCase {
  public UUID criar(Video video);
  public Video buscarPorId(UUID id);
  public List<Video> buscarPorEmail(String email);
  public List<Video> buscar();
  public void editar(Video video);
  public void remover(UUID id);
}
