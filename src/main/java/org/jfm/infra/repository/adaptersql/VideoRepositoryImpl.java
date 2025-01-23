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
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideoRepositoryImpl implements VideoRepository {

  @Inject
  VideoMapper mapper;

  @Inject
  EntityManager entityManager;

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
      throw new EntityNotFoundException(ErrosSistemaEnum.ITEM_NOT_FOUND.getMessage());
    } catch (PersistenceException e) {
        throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public List<Video> buscarPorUsuario(UUID id) {
    try {
      TypedQuery<VideoEntity> query = entityManager.createNamedQuery("Video.findByUsuario", VideoEntity.class);
      query.setParameter("idUsuario", id);

      return query.getResultStream()
        .map(i -> mapper.toDomain(i)).collect(Collectors.toList());
    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
    // TODO: vai validar se o cliente existe?
  }

  @Override
  @Transactional
  public void remover(UUID id) {
    try {
      Query query = entityManager.createNamedQuery("Video.delete");
      query.setParameter("id", id);
      query.executeUpdate();
    } catch (PersistenceException e) {
      throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    }
  }

}
