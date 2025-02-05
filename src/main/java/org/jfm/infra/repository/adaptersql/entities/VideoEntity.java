package org.jfm.infra.repository.adaptersql.entities;

import java.util.Date;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
@NamedQueries({
  @NamedQuery(
    name = "Video.findById", 
    query = "SELECT v FROM VideoEntity v WHERE v.id = :id"),
  @NamedQuery(
    name = "Video.findAll", 
    query = "SELECT v FROM VideoEntity v"),
  @NamedQuery(
    name = "Video.findByEmail", 
    query = "SELECT v FROM VideoEntity v WHERE v.email = :email"),
  @NamedQuery(
    name = "Video.update", 
    query = "UPDATE VideoEntity v SET v.status = :status, v.data_atualizacao = :dataAtualizacao WHERE v.id = :id"),
  @NamedQuery(
    name = "Video.delete", 
    query = "DELETE FROM VideoEntity v WHERE v.id = :id")
})
public class VideoEntity {

  @Id
  private UUID id;
  private Status status;
  @Column(name = "data_criacao")
  private Date dataCriacao;
  @Column(name = "data_atualizacao")
  private Date dataAtualizacao;
  private String email;

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

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Date getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(Date dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 

}
