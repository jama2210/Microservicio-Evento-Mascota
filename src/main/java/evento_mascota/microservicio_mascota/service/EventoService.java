package evento_mascota.microservicio_mascota.service;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import java.util.List;
import java.util.Optional;

public interface EventoService {
    EventoMascota createRegistro(EventoMascota mascota);
    List<EventoMascota> buscarRegistro();
    Optional<EventoMascota> buscarId(Long id);
    void eliminarRegistro(Long id);
    EventoMascota updateRegistro(Long id, EventoMascota update);
}