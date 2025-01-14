package org.jfm.domain.usecases;

import java.util.UUID;

import org.jfm.domain.entities.Video;

public interface VideoUseCase {
  public Video buscarPorId(UUID id);
}
