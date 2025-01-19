package foro_hub.api_foro.domain.respuesta;

import foro_hub.api_foro.domain.ValidacionException;
import foro_hub.api_foro.domain.topico.Estado;
import foro_hub.api_foro.domain.topico.Topico;
import foro_hub.api_foro.domain.topico.TopicoRepository;
import foro_hub.api_foro.domain.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RespuestaService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    public RegistroRespuestaResponse crearRespuesta(Long topicoId, @Valid RegistroRespuestaRequest respuesta, Usuario usuarioAutenticado) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(topicoId);
        if (topicoBuscado.isEmpty()) {
            throw new ValidacionException("No existe el tópico al que hace referencia");
        }

        // Verificar si el tópico está cerrado
        if (topicoBuscado.get().getEstado().equals(Estado.CERRADO)) {
            throw new ValidacionException("No es posible generar una respuesta para este tópico. El tópico está cerrado.");
        }

        // Crear y guardar la nueva respuesta
        Respuesta nuevaRespuesta = new Respuesta(respuesta, topicoBuscado.get(), usuarioAutenticado);
        respuestaRepository.save(nuevaRespuesta);

        // Cambiar el estado del tópico a cerrado
        Topico topico = topicoBuscado.get();
        topico.setEstado(Estado.CERRADO);
        topicoRepository.save(topico); // Guardar el tópico actualizado

        return new RegistroRespuestaResponse(nuevaRespuesta);
    }

    public List<DatosRespuestaResponse> obtenerRespuestas(Long id) {
        List<Respuesta> respuestas = respuestaRepository.findByAutorId(id);
        return respuestas.stream()
                .map(r -> new DatosRespuestaResponse(r.getTopico().getTitulo(), r.getAutor().getNombre(), r.getMensaje()))
                .collect(Collectors.toList());
    }

    public RegistroRespuestaResponse modificarRespuesta(Long id, @Valid RegistroRespuestaRequest respuesta, Usuario usuarioAutenticado) {
        Respuesta respuestaExistente = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));

        // Verificar si el usuario autenticado es el autor de la respuesta
        if (!respuestaExistente.getAutor().getId().equals(usuarioAutenticado.getId())) {
            throw new ValidacionException("No tiene permisos para modificar esta respuesta.");
        }

        respuestaExistente.setMensaje(respuesta.mensaje());
        respuestaRepository.save(respuestaExistente);
        return new RegistroRespuestaResponse(respuestaExistente);
    }

    public void eliminarRespuesta(Long id, Usuario usuarioAutenticado) {
        Respuesta respuestaExistente = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));

        // Verificar si el usuario autenticado es el autor de la respuesta
        if (!respuestaExistente.getAutor().getId().equals(usuarioAutenticado.getId())) {
            throw new ValidacionException("No tiene permisos para eliminar esta respuesta.");
        }

        respuestaRepository.deleteById(id);
    }
    public DatosRespuestaResponse obtenerRespuestaPorId(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));
        return new DatosRespuestaResponse(respuesta);
    }

}