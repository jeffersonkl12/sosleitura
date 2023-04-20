CREATE TABLE IF NOT EXISTS autor(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                nome varchar(30) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS genero(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                  nome VARCHAR(20) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS endereco( id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                     estado VARCHAR(30) NOT NULL,
                                     municipio VARCHAR(50) NOT NULL,
                                     bairro VARCHAR(50) NOT NULL,
                                     rua VARCHAR(50) NOT NULL,
                                     numero VARCHAR(4) NOT NULL CHECK (LENGTH(numero) = 4));

CREATE TABLE IF NOT EXISTS metodo_pagamento( id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                             metodo VARCHAR(20) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS usuario(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                   login VARCHAR(80) NOT NULL UNIQUE,
                                   nome VARCHAR(50) NOT NULL,
                                   senha VARCHAR(70) NOT NULL,
                                   status_user ENUM("NOVO","ATIVADO","BLOQUEADO","BANIDO") NOT NULL DEFAULT "NOVO",
                                   endereco BIGINT,
                                   FOREIGN KEY (endereco) REFERENCES endereco(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS pedido( id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                   data_pedido DATE NOT NULL DEFAULT (CURRENT_DATE()),
                                   pedido_status ENUM("ABERTO","CONFIRMADO","COMPLETADO","CANCELADO") NOT NULL DEFAULT "ABERTO",
                                   pagamento_status ENUM("NAO PAGO","FALHOU","EXPIRADO","PAGO","REEMBOLSADO") NOT NULL DEFAULT "NAO PAGO",
                                   entrega_status ENUM("NAO COMPRIDO", "ENVIO","ENVIADO","CHEGOU","COLETADO","DEVOLVIDO") NOT NULL DEFAULT "NAO COMPRIDO",
                                   usuario BIGINT NOT NULL,
                                   metodo_pagamento BIGINT NOT NULL,
                                   endereco_destino BIGINT NOT NULL,
                                   FOREIGN KEY (usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                   FOREIGN KEY (metodo_pagamento) REFERENCES metodo_pagamento(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                   FOREIGN KEY (endereco_destino) REFERENCES endereco(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS livro(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                nome VARCHAR(50) NOT NULL,
                                genero BIGINT NOT NULL,
                                resumo TEXT,
                                isbn CHAR(13) NOT NULL UNIQUE,
                                capa VARCHAR(20) DEFAULT "default.png",
                                autor BIGINT NOT NULL,
                                valor DOUBLE(5,2) UNSIGNED NOT NULL,
                                ano DATE DEFAULT (CURRENT_DATE()),
                                FOREIGN KEY(autor) REFERENCES autor(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY(genero) REFERENCES genero(id));

CREATE TABLE IF NOT EXISTS pedido_item( id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                        item BIGINT NOT NULL,
                                        qtd BIGINT UNSIGNED NOT NULL DEFAULT 1,
                                        pedido BIGINT NOT NULL,
                                        FOREIGN KEY(item) REFERENCES livro(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                        FOREIGN KEY(pedido) REFERENCES pedido(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE  IF NOT EXISTS role(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                  nome VARCHAR(30) NOT NULL UNIQUE);


CREATE TABLE IF NOT EXISTS user_role(usuario BIGINT NOT NULL,
                       role BIGINT NOT NULL,
                       PRIMARY KEY(usuario,role),
                       FOREIGN KEY (usuario) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE,
                       FOREIGN KEY (role) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE);
