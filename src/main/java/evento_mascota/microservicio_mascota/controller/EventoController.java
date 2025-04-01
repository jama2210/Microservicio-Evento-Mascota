package evento_mascota.microservicio_mascota.controller;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController() {
        this.eventoService = new EventoService();
    }

    @GetMapping
    public List<EventoMascota> obtenerEventos() {
        return eventoService.obtenerEventos();
    }

    @GetMapping("/{id}")
    public EventoMascota obtenerEventoPorId(@PathVariable int id) {
        return eventoService.obtenerEventoPorId(id);
    }
}