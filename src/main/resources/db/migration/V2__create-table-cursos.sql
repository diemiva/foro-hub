CREATE TABLE cursos(
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nombre VARCHAR(255) NOT NULL UNIQUE,
                       categoria VARCHAR(255) NOT NULL,

                       PRIMARY KEY (id)
);