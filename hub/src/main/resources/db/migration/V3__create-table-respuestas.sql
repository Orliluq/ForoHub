create table respuestas (
    id bigint not null auto_increment,
    solution varchar(1000)not null,
    author int not null,
    topico int not null,
    date datetime not null,
    primary key(id)
);