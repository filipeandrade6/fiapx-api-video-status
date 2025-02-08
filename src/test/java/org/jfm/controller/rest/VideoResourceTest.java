package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Video;
import org.jfm.domain.usecases.VideoUseCase;
import org.jfm.factory.VideoFactory;
import org.jfm.infra.repository.adaptersql.mapper.VideoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class VideoResourceTest {

  @Inject
  VideoResource videoResource;

  @InjectMocks
  VideoResource videoResourceMock;

  @Mock
  VideoUseCase videoUseCase;

  @Mock
  VideoMapper videoMapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  // BDD
  // Feature: Buscar um video específico pelo ID
  // Cenário: Buscar video por Id com sucesso
  @Test
  public void buscarPorIdTest() throws Exception {

    // Dado video com ID "23e52205-4d9d-41e6-a7f3-271af4f5316b" que existe no sistema
    UUID idVideo = UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b");
    Video video = VideoFactory.montar();

    Mockito.when(videoUseCase.buscarPorId(idVideo))
      .thenReturn(video);

    // Quando fizer requisição GET para "videos/{id}" com ID igual a "23e52205-4d9d-41e6-a7f3-271af4f5316b"
    // Então, a resposta tem status 200
    given()
      .contentType(ContentType.JSON)
      .when().pathParam("id", video.getId())
      .get("/videos/{id}")
      .then()
      .statusCode(200);
  }
}
