package org.jfm.infra.repository.adaptersql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Video;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.VideoRepository;
import org.jfm.infra.repository.adaptersql.entities.VideoEntity;
import org.jfm.infra.repository.adaptersql.mapper.VideoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideoRepositoryImpl implements VideoRepository {

  @Inject
  EntityManager entityManager;
  
  @Inject
  VideoMapper mapper;

  @Override
  @Transactional
  public void criar(Video video) {
    try {
      entityManager.persist(mapper.toEntity(video));

    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public Video buscarPorId(UUID id) {
    try {
      TypedQuery<VideoEntity> query = entityManager.createNamedQuery("Video.findById", VideoEntity.class);
      query.setParameter("id", id);

      return mapper.toDomain(query.getSingleResult());
    } catch(NoResultException e) {
      return null;
    } catch (PersistenceException e) {
        throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public List<Video> buscar() {
    try {
      TypedQuery<VideoEntity> query = entityManager.createNamedQuery("Video.findAll", VideoEntity.class);

      return query.getResultStream().map(i -> mapper.toDomain(i)).collect(Collectors.toList());
    } catch(NoResultException e) {
      return null;
    } catch (PersistenceException e) {
        throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public List<Video> buscarPorEmail(String email) {
    try {
      TypedQuery<VideoEntity> query = entityManager.createNamedQuery("Video.findByEmail", VideoEntity.class);
      query.setParameter("email", email);

      return query.getResultStream()
        .map(i -> mapper.toDomain(i)).collect(Collectors.toList());
    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public void editar(Video video) {
    try {
      Query query = entityManager.createNamedQuery("Video.update");
      query.setParameter("id", video.getId());
      query.setParameter("status", video.getStatus());
      query.setParameter("dataCriacao", video.getDataCriacao());
      query.setParameter("dataAtualizacao", video.getDataAtualizacao());
      query.setParameter("email", video.getEmail());

      query.executeUpdate();
    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public void remover(Video video) {
    try {
      Query query = entityManager.createNamedQuery("Video.delete");
      query.setParameter("id", video.getId());
      query.executeUpdate();
    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

}
