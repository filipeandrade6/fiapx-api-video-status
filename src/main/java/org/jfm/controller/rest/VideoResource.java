package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jfm.domain.entities.Video;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.VideoUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/videos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Video", description = "Operações relacionada a videos")
public class VideoResource {

  @Inject
  VideoUseCase useCase;

  @Operation(summary = "Buscar video por ID", description = "Busca o video por ID")
  @APIResponses(value = {
    @APIResponse(responseCode = "200", description = "Sucesso", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Video.class))
    }),
    @APIResponse(responseCode = "404", description = "Video não encontrado", content = {
      @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Video não encontrado"))
    })
  })
  @GET
  @Path("/{id}")
  public Response buscarPorId(
    @PathParam("id") @Parameter(description = "ID do video", example = "TODO") 
    UUID id) {
    if (id == null) {
      throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
    }

    Video video = useCase.buscarPorId(id);
    return Response.status(Response.Status.OK).entity(video).build();
  }

  @Operation(summary = "Buscar lista de videos por ID de usuário", description = "Busca lista de videos por ID de usuário")
  @APIResponses(value = {
    @APIResponse(responseCode = "200", description = "Sucesso", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Video.class))
    }),
    @APIResponse(responseCode = "404", description = "Video não encontrado", content = {
      @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Video não encontrado"))
    })
  })
  @GET
  public Response buscar() {
    List<Video> videos = useCase.buscar();
    return Response.status(Response.Status.OK).entity(videos).build();
  }

  @GET
  @Path("/email/{email}")
  public Response buscarEmail(
    @PathParam("email") @Parameter(description = "Email do video cadastrado", example = "exemplo@exemplo.com")
    String email) {
    
    List<Video> videos = useCase.buscarPorEmail(email);
    return Response.status(Response.Status.OK).entity(videos).build();
  }

}
