package org.jfm.domain.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;

import org.jfm.domain.entities.Video;
import org.jfm.factory.VideoFactory;
import org.jfm.infra.repository.adaptersql.VideoRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class VideoServiceTest {

  @Inject
  VideoService service;

  @InjectMocks
  VideoService serviceMock;

  @Mock
  VideoRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    Video video = VideoFactory.montar();
    video.setId(UUID.randomUUID());

    serviceMock.criar(video);

    verify(repository, times(1)).criar(video);
  }

  @Test
  public void testBuscarPorId() {
    Video video = VideoFactory.montar();

    Assertions.assertEquals(service.buscarPorId(video.getId()), video);
  }

  @Test
  public void testBuscarPorIdUsuario() {
    List<Video> videos = VideoFactory.montarLista();

    Assertions.assertEquals(service.buscarPorIdUsuario(UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d")), videos);
  }

  @Test
  public void testRemover() {
    Video video = VideoFactory.montar();
    when(repository.buscarPorId(video.getId())).thenReturn(video);

    serviceMock.remover(video.getId());

    verify(repository, times(1)).remover(video);
  }
}

