package evento_mascota.microservicio_mascota.controller;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.service.EventoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private  EventoService eventoService;

    @GetMapping
    public List<EventoMascota> buscarRegistro() {
        return eventoService.buscarRegistro();
    }

    @GetMapping("/{id}")
    public Optional<EventoMascota> buscarId(@PathVariable Long id) {
        return eventoService.buscarId(id);
    }

    @PostMapping
    public EventoMascota createRegistro(@RequestBody EventoMascota mascota) {
        return eventoService.createRegistro(mascota);
        
    }

    @DeleteMapping("/{id}")
    public void eliminarRegistro(@PathVariable Long id) {
        eventoService.eliminarRegistro(id);
    }

    @PutMapping("/{id}")
    public EventoMascota updateRegistro(@PathVariable Long id, @RequestBody EventoMascota update) {
        return eventoService.updateRegistro(id, update);
    }
    
    
}