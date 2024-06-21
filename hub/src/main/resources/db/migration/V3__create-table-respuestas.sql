CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    creationDate DATETIME NOT NULL,
    solution TEXT NOT NULL,
    author BIGINT,
    topico BIGINT,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (author) REFERENCES usuarios(id),
    FOREIGN KEY (topico) REFERENCES topicos(id)
);