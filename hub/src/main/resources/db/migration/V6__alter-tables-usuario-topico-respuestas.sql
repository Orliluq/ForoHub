ALTER TABLE respuestas
DROP COLUMN active;
ALTER TABLE usuarios
DROP COLUMN active;
ALTER TABLE topicos
DROP COLUMN active;

alter table usuarios add active tinyint;
update usuarios set active=1;
alter table topicos add active tinyint;
update topicos set active=1;
alter table respuestas add active tinyint;
update respuestas set active=1;