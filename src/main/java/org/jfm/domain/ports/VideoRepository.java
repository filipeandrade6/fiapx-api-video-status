package org.jfm.domain.ports;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;

public interface VideoRepository {
  public void criar(Video video);
  public Video buscarPorId(UUID id);
  public List<Video> buscarPorUsuario(UUID id);
  public void remover(UUID id);
}
