CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    date DATETIME NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE', 'RESOLVED') NOT NULL,
    author_id BIGINT,
    course VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (author_id) REFERENCES usuarios(id)
);