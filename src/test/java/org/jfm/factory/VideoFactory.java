package org.jfm.factory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;
import org.jfm.domain.entities.enums.Status;

public class VideoFactory {
  
  public static Video montar() {
    Video video = new Video(UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"), Status.PROCESSANDO, Instant.now(), Instant.now(), UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d"));
    return video;
  }

  public static List<Video> montarLista() {
    List<Video> videos = new ArrayList<Video>(List.of(
      new Video(UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"), Status.PROCESSANDO, Instant.now(), Instant.now(), UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d")),
      new Video(UUID.fromString("4e1bb65c-b3c0-4229-964b-c10241b7aca4"), Status.CARREGADO, Instant.now(), Instant.now(), UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d"))
    ));
    return videos;
  }
}
