package foro_hub.api_foro.domain.curso;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNombreEquals(@NotBlank String nombre);
}