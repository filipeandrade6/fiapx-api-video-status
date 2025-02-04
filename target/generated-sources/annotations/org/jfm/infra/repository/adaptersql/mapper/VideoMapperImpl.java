package org.jfm.infra.repository.adaptersql.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.jfm.domain.entities.Video;
import org.jfm.infra.repository.adaptersql.entities.VideoEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T20:56:29-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.5 (Ubuntu)"
)
@ApplicationScoped
public class VideoMapperImpl implements VideoMapper {

    @Override
    public Video toDomain(VideoEntity video) {
        if ( video == null ) {
            return null;
        }

        Video video1 = new Video();

        video1.setId( video.getId() );
        video1.setStatus( video.getStatus() );
        if ( video.getDataCriacao() != null ) {
            video1.setDataCriacao( video.getDataCriacao().toInstant() );
        }
        if ( video.getDataAtualizacao() != null ) {
            video1.setDataAtualizacao( video.getDataAtualizacao().toInstant() );
        }
        video1.setEmail( video.getEmail() );

        return video1;
    }

    @Override
    public VideoEntity toEntity(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoEntity videoEntity = new VideoEntity();

        videoEntity.setId( video.getId() );
        videoEntity.setStatus( video.getStatus() );
        if ( video.getDataCriacao() != null ) {
            videoEntity.setDataCriacao( Date.from( video.getDataCriacao() ) );
        }
        if ( video.getDataAtualizacao() != null ) {
            videoEntity.setDataAtualizacao( Date.from( video.getDataAtualizacao() ) );
        }
        videoEntity.setEmail( video.getEmail() );

        return videoEntity;
    }
}
