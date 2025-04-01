package evento_mascota.microservicio_mascota.service;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.model.Participante;
import evento_mascota.microservicio_mascota.exception.EventoNotFound;
import java.util.ArrayList;
import java.util.List;

public class EventoService {
    private final List<EventoMascota> eventos = new ArrayList<>();

    public EventoService() {
        eventos.add(new EventoMascota(1, "Feria de Mascotas", "2025-04-20", new ArrayList<>()));
        eventos.add(new EventoMascota(2, "Competencia de Agilidad", "2025-05-15", new ArrayList<>()));
        eventos.add(new EventoMascota(3, "Exhibición Canina", "2025-06-10", new ArrayList<>()));

        eventos.get(0).getParticipantes().add(new Participante(1, "Carlos", "Perro"));
        eventos.get(1).getParticipantes().add(new Participante(2, "María", "Gato"));
        eventos.get(2).getParticipantes().add(new Participante(3, "Luis", "Conejo"));
    }

    public List<EventoMascota> obtenerEventos() {
        return eventos;
    }

    public EventoMascota obtenerEventoPorId(int id) {
        return eventos.stream()
                .filter(evento -> evento.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EventoNotFound((int) id));
    }
}