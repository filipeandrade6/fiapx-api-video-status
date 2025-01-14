package org.jfm.domain.ports;

import java.util.UUID;

import org.jfm.domain.entities.Video;

public interface VideoRepository {
  public Video buscarPorId(UUID id);
}
