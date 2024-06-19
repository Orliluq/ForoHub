alter table usuarios add active tinyint;
update usuarios set active=1;