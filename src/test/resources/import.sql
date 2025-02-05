-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- INSERT INTO clientes (id, nome, cpf, email, ativo) VALUES ('63a59178-39f8-4a28-a2c7-989a57ca7b54', 'FILIPE ANDRADE', '123.123.123-12', 'filipe@email.com', TRUE);
INSERT INTO videos (id, status, data_criacao, data_atualizacao, email) VALUES
('23e52205-4d9d-41e6-a7f3-271af4f5316b', 2, Now(), Now(), 'exemplo@exemplo.com');

INSERT INTO videos (id, status, data_criacao, data_atualizacao, email) VALUES
('4e1bb65c-b3c0-4229-964b-c10241b7aca4', 3, Now(), Now(), 'exemplo@exemplo.com');
