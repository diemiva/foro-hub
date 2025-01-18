package foro_hub.api_foro.domain.respuesta;

import foro_hub.api_foro.domain.topico.Topico;
import foro_hub.api_foro.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private Boolean solucion;

    public Respuesta() {
    }

    public Respuesta(RegistroRespuestaRequest registro, Topico topico, Usuario usuario) {
        this.mensaje = registro.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuario;
        this.solucion = false;
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Topico getTopico() {
        return topico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}