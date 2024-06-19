create table usuarios (
    id bigint not null auto_increment,
    username varchar(100)not null unique,
    password varchar(300)not null,
    name varchar(100)not null,
    email varchar(100)not null unique,
    primary key(id)
);