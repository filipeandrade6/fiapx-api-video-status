package org.jfm.controller.rest;

import org.jfm.domain.entities.Video;
import org.jfm.domain.services.VideoCannonSyncService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cannons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CannonsResource {
  
  @Inject
  VideoCannonSyncService cannonService;

  @POST
  public Response enviarMensagem(Video video) throws Exception {
    // para teste
    String mensagemEnviada = String.join(".", video.getId().toString(), video.getEmail());
    String mensagemId = cannonService.enviarMensagem(mensagemEnviada);
    return Response.ok().entity(mensagemId).build();
  }

}
