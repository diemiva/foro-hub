CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT, -- Identificador único y autoincrementable
                         titulo VARCHAR(255) NOT NULL UNIQUE, -- Título único del tópico
                         mensaje VARCHAR(255) NOT NULL UNIQUE, -- Mensaje único del tópico
                         fecha_creacion DATETIME NOT NULL, -- Fecha y hora de creación
                         estado VARCHAR(255) NOT NULL, -- Estado del tópico
                         usuario_id BIGINT NOT NULL, -- ID del usuario que crea el tópico
                         curso_id BIGINT NOT NULL, -- ID del curso asociado
                         fecha_modificacion DATETIME NOT NULL DEFAULT NOW(), -- Fecha y hora de la última modificación

                         PRIMARY KEY (id), -- Establecer la columna 'id' como la clave primaria
                         CONSTRAINT fk_topicos_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id), -- Relación con la tabla 'usuarios'
                         CONSTRAINT fk_topicos_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id) -- Relación con la tabla 'cursos'
);
