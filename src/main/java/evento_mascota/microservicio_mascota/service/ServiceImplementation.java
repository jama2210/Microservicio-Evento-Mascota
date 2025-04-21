package evento_mascota.microservicio_mascota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.repository.EventoRepository;

@Service
public class ServiceImplementation implements EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public EventoMascota createRegistro(EventoMascota mascota) {
        return eventoRepository.save(mascota);
    }

    @Override
    public List<EventoMascota> buscarRegistro() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<EventoMascota> buscarId(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public void eliminarRegistro(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public EventoMascota updateRegistro(Long id, EventoMascota update) {
        if(eventoRepository.existsById(id)) {
            update.setId(id);
            return eventoRepository.save(update);
        } else {return null;}
    }
}
