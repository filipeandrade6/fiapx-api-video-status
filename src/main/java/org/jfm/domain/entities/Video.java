package org.jfm.domain.entities;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

public class Video {

  private UUID id;
  private Status status;
  private Instant dataCriacao;
  private Instant dataAtualizacao;
  private UUID idUsuario;

  public Video() {
    super();
  }
  
  public Video(UUID id, Status status, Instant dataCriacao, Instant dataAtualizacao, UUID idUsuario) {
    this.id = id;
    this.status = status;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
    this.idUsuario = idUsuario;
  }

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

  public Instant getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Instant dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Instant getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(Instant dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  public UUID getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(UUID idUsuario) {
    this.idUsuario = idUsuario;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Video other = (Video) obj;
    if (id == null) {
        if (other.id != null)
          return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
