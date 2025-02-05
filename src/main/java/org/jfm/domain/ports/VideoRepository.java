package org.jfm.domain.ports;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;

public interface VideoRepository {
  public void criar(Video video);
  public List<Video> buscar();
  public Video buscarPorId(UUID id);
  public List<Video> buscarPorEmail(String email);
  public void editar(Video video);
  public void remover(Video video);
}
