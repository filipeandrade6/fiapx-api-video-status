package org.jfm.infra.repository.adapterssql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jfm.domain.entities.Video;
import org.jfm.factory.VideoFactory;
import org.jfm.infra.repository.adaptersql.VideoRepositoryImpl;
import org.jfm.infra.repository.adaptersql.entities.VideoEntity;
import org.jfm.infra.repository.adaptersql.mapper.VideoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@QuarkusTest
public class VideoRepositoryImplTest {
  
  @Inject
  VideoRepositoryImpl repository;
  
  @InjectMocks
  VideoRepositoryImpl repositoryMock;

  @Mock
  EntityManager entityManager;

  @Mock
  VideoMapper videoMapper;

  @Mock
  Query query;

  @Mock
  TypedQuery<VideoEntity> typedQuery;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    Video video = new Video();
    when(videoMapper.toEntity(video)).thenReturn(new VideoEntity());

    repositoryMock.criar(video);

    verify(entityManager, times(1)).persist(any(VideoEntity.class));
  }

  @Test
  public void testEditar() {
    when(entityManager.createNamedQuery("Video.update")).thenReturn(query);
    Video video = VideoFactory.montar();

    repositoryMock.editar(video);

    verify(query, times(1)).setParameter("id", video.getId());
    verify(query, times(1)).setParameter("status", video.getStatus());
    verify(query, times(1)).setParameter("dataCriacao", video.getDataCriacao());
    verify(query, times(1)).setParameter("dataAtualizacao", video.getDataAtualizacao());
    verify(query, times(1)).setParameter("email", video.getEmail());
    verify(query, times(1)).executeUpdate();
  }

  @Test
  public void testRemover() {
    when(entityManager.createNamedQuery("Video.delete")).thenReturn(query);

    Video video = VideoFactory.montar();

    repositoryMock.remover(video);
    verify(query, times(1)).setParameter("id", video.getId());
    verify(query, times(1)).executeUpdate();
  }
}
