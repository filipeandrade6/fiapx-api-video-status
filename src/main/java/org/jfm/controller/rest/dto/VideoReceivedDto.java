package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class VideoReceivedDto {
  @Schema(name = "queueMessage", example = "TODO", required = true) 
  public String queueMessage;

  public VideoReceivedDto() {}

  public VideoReceivedDto(String queueMessage) {
    this.queueMessage = queueMessage;
  }

  public String getQueueMessage() {
    return queueMessage;
  }

  public void setQueueMessage(String queueMessage) {
    this.queueMessage = queueMessage;
  }

}
