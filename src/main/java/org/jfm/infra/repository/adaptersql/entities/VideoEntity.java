package org.jfm.infra.repository.adaptersql.entities;

import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
@NamedQueries({
  @NamedQuery(name = "Video.findById", query = "SELECT v FROM VideoEntity v WHERE v.id = :id")
})
public class VideoEntity {
  @Id
  private UUID id;
  private Status status;
  private UUID idUsuario;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public UUID getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(UUID idUsuario) {
    this.idUsuario = idUsuario;
  }

}
