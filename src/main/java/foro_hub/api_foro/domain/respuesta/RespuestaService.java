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

        if (topicoBuscado.get().getEstado().equals(Estado.CERRADO)) {
            throw new ValidacionException("No es posible generar una respuesta para este topico. El tópico está cerrado.");
        }

        Respuesta nuevaRespuesta = new Respuesta(respuesta, topicoBuscado.get(), usuarioAutenticado);
        respuestaRepository.save(nuevaRespuesta);
        return new RegistroRespuestaResponse(nuevaRespuesta);
    }

    public List<DatosRespuestaResponse> obtenerRespuestas(Long id) {
        List<Respuesta> respuestas = respuestaRepository.findByAutorId(id);
        return respuestas.stream()
                .map(r -> new DatosRespuestaResponse(r.getTopico().getTitulo(), r.getAutor().getNombre(), r.getMensaje()))
                .collect(Collectors.toList());
    }
}