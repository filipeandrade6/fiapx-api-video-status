\connect "fiapx-videos";

CREATE TABLE "public"."videos" (
  "id" uuid NOT NULL,
  "status" smallint,
  "data_criacao" timestamptz(6),
  "data_atualizacao" timestamptz(6),
  "email" character varying(255),
  CONSTRAINT "videos_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO videos (id, status, data_criacao, data_atualizacao, email) VALUES
('23e52205-4d9d-41e6-a7f3-271af4f5316b', 2, Now(), Now(), 'exemplo@exemplo.com');

INSERT INTO videos (id, status, data_criacao, data_atualizacao, email) VALUES
('4e1bb65c-b3c0-4229-964b-c10241b7aca4', 3, Now(), Now(), 'exemplo@exemplo.com');
