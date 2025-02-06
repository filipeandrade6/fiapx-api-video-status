package org.jfm.controller.rest;

import org.jfm.domain.entities.Video;
import org.jfm.domain.services.VideoCannonSyncService;
import org.jfm.domain.services.VideoShieldSyncService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/shields")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShieldsResource {

  @Inject
  VideoShieldSyncService shieldService;

  @GET
  public Response receber() {
    // para teste
    shieldService.receberMensagens();
    return Response.ok().build();
  }

}
