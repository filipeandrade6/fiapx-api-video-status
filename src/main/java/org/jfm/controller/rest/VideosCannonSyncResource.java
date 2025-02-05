package org.jfm.controller.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jfm.domain.entities.Video;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Path("/sync/cannon")
@Produces(MediaType.TEXT_PLAIN)
public class VideosCannonSyncResource {

  private static final Logger LOGGER = Logger.getLogger(VideosCannonSyncResource.class);

  @Inject
  SqsClient sqs;

  @ConfigProperty(name = "queue.url")
  String queueUrl;

  static ObjectWriter VIDEO_WRITER = new ObjectMapper().writerFor(Video.class);

  @POST
  @Path("/shoot")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response sendMessage(Video video) throws Exception {
      String message = VIDEO_WRITER.writeValueAsString(video);
      SendMessageResponse response = sqs.sendMessage(m -> m.queueUrl(queueUrl).messageBody(message));
      LOGGER.infov("Fired Video[{0}, {1}}]", video.getId(), video.getEmail());
      return Response.ok().entity(response.messageId()).build();
  }

}
