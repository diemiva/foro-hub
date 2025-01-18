package foro_hub.api_foro.domain.curso;

import foro_hub.api_foro.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public RegistroCursoResponse crearCurso(RegistroCursoRequest registroCursoRequest) {
        // Verificar si el curso ya existe
        if (cursoRepository.findByNombreEquals(registroCursoRequest.nombre()) != null) {
            throw new ValidacionException("El curso ya existe");
        }

        // Crear un nuevo curso
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(registroCursoRequest.nombre());
        nuevoCurso.setCategoria(registroCursoRequest.categoria());

        // Guardar el curso en la base de datos
        cursoRepository.save(nuevoCurso);

        return new RegistroCursoResponse(nuevoCurso);
    }
    public List<DatosCursoResponse> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(DatosCursoResponse::new)
                .collect(Collectors.toList());
    }

}