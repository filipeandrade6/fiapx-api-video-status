package org.jfm.infra.repository.adaptersql.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Video;
import org.jfm.infra.repository.adaptersql.entities.VideoEntity;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface VideoMapper {
  Video toDomain(VideoEntity video);

  VideoEntity toEntity(Video video);
}
