package foro_hub.api_foro.domain.topico;


import foro_hub.api_foro.domain.ValidacionException;
import foro_hub.api_foro.domain.curso.Curso;
import foro_hub.api_foro.domain.curso.CursoRepository;
import foro_hub.api_foro.domain.usuarios.Usuario;
import foro_hub.api_foro.domain.usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public RegistroTopicoResponse registrarTopico(@Valid RegistroTopicoRequest datos, Usuario usuarioAutenticado) {

        Curso curso = cursoRepository.findByNombreEquals(datos.nombreCurso());
        Topico nuevoTopico = topicoRepository.existeTopicoEnElCurso(datos.titulo(), datos.mensaje(), datos.nombreCurso());
        if (nuevoTopico != null) {
            throw new ValidacionException("Ya existe un tópico en el foro");
        }

        nuevoTopico = new Topico(datos, usuarioAutenticado, curso);
        topicoRepository.save(nuevoTopico);
        return new RegistroTopicoResponse(nuevoTopico);
    }

    public List<DetalleTopicoResponse> listarTopicos() {
        return topicoRepository.findAll().stream().map(DetalleTopicoResponse::new).collect(Collectors.toList());
    }

    public DetalleTopicoResponse obtenerTopico(Long id) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isPresent()) {
            return new DetalleTopicoResponse(topicoBuscado.get());
        } else {
            throw new ValidacionException("No existe el topico");
        }
    }

    public ModificacionTopicoResponse actualizarTopico(Long id, ModificacionTopicoRequest datosModificacion, Usuario usuarioAutenticado) {
        if (datosModificacion.titulo() == null && datosModificacion.mensaje() == null) {
            throw new ValidacionException("No se enviaron datos para realizar modificación");
        }
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if(topicoBuscado.isPresent()){
            Topico topico = topicoBuscado.get();
            if (Objects.equals(topico.getAutor().getId(), usuarioAutenticado.getId())){
                topico.actualizarTopico(datosModificacion);
                return new ModificacionTopicoResponse(topico);
            } else {
                throw new ValidacionException("No tiene permisos para modificar este tópico.");
            }
        } else {
            throw new ValidacionException("No existe el topico que desea modificar");
        }
    }

    public void eliminarTopico(Long id, Usuario usuarioAutenticado) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isPresent()) {
            if(Objects.equals(topicoBuscado.get().getAutor().getId(), usuarioAutenticado.getId())) {
                topicoRepository.deleteById(id);
            }
            else {
                throw new ValidacionException("No tiene permisos para eliminar este tópico.");
            }
        } else {
            throw new ValidacionException("No existe el topico que desea eliminar");
        }
    }
}