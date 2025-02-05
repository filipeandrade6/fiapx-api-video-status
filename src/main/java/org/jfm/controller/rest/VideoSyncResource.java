// package org.jfm.controller.rest;

// import org.jfm.domain.entities.Video;
// import org.jfm.domain.services.VideoCannonSyncService;
// import org.jfm.domain.services.VideoShieldSyncService;

// import jakarta.inject.Inject;
// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/sync")
// public class VideoSyncResource {
  
//   @Inject
//   VideoCannonSyncService cannonService;

//   @Inject
//   VideoShieldSyncService shieldService;

//   @POST
//   @Path("/cannon")
//   @Consumes(MediaType.APPLICATION_JSON)
//   public Response enviarMensagem(Video video) throws Exception {
//     // para teste
//     String mensagemEnviada = String.join(".", video.getId().toString(), video.getEmail());
//     String mensagemId = cannonService.enviarMensagem(mensagemEnviada);
//     return Response.ok().entity(mensagemId).build();
//   }

//   @GET
//   @Path("/shield")
//   public Response receber() {
//     // para teste
//     shieldService.receberMensagens();
//     return Response.ok().build();
//   }

// }
