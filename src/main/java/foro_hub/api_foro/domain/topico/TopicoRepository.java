package foro_hub.api_foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            select t from Topico t where t.titulo = :titulo and t.mensaje = :mensaje
            and t.curso.nombre = :nombreCurso
            """)
    Topico existeTopicoEnElCurso(@NotBlank String titulo, @NotBlank String mensaje, @NotBlank String nombreCurso);

    @Query("""
        select t.estado = 'ABIERTO'
        from Topico t
        where t.id = :idTopico
        """)
    boolean isEstadoAbierto(@NotNull Long idTopico);

}