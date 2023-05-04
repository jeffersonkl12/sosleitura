CREATE TABLE IF NOT EXISTS token_usuario(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         usuario BIGINT NOT NULL,
                                         token VARCHAR(36) NOT NULL UNIQUE,
                                         data DATETIME NOT NULL,
                                         FOREIGN KEY(usuario) REFERENCES usuario(id) ON DELETE CASCADE);