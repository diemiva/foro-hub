CREATE TABLE usuarios(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nombre VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         clave VARCHAR(300) NOT NULL,

                         PRIMARY KEY (id)
);