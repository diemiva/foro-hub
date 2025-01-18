CREATE TABLE respuestas (
                            id BIGINT NOT NULL AUTO_INCREMENT, -- Identificador único y autoincrementable
                            mensaje VARCHAR(255) NOT NULL, -- Mensaje de la respuesta
                            fecha_creacion DATETIME NOT NULL, -- Fecha y hora de creación
                            usuario_id BIGINT NOT NULL, -- ID del usuario que responde
                            topico_id BIGINT NOT NULL, -- ID del tópico al que pertenece la respuesta
                            solucion BOOLEAN NOT NULL DEFAULT FALSE, -- Indica si la respuesta es la solución (valor predeterminado: FALSE)

                            PRIMARY KEY (id), -- Establecer la columna 'id' como la clave primaria
                            CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id), -- Relación con la tabla 'usuarios'
                            CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id) -- Relación con la tabla 'topicos'
);