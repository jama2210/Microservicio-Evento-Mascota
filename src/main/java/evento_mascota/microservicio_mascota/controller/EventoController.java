package evento_mascota.microservicio_mascota.controller;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel; 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public CollectionModel<EntityModel<EventoMascota>> buscarRegistro() {
        List<EventoMascota> eventos = eventoService.buscarRegistro();
        List<EntityModel<EventoMascota>> eventosModel = eventos.stream()
                .map(evento -> EntityModel.of(evento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarId(evento.getId())).withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).buscarRegistro());
        CollectionModel<EntityModel<EventoMascota>> collectionModel = CollectionModel.of(eventosModel, linkTo.withSelfRel());
        return collectionModel;
    }

    @GetMapping("/{id}")
    public EntityModel<EventoMascota> buscarId(@PathVariable Long id) {
        Optional<EventoMascota> evento = eventoService.buscarId(id);
        if (evento.isPresent()) {
            EntityModel<EventoMascota> entityModel = EntityModel.of(evento.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarId(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarRegistro()).withRel("eventos"));
            return entityModel;
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + id);
        }
    }

    // @PostMapping
    // public EntityModel<EventoMascota> createRegistro(@RequestBody EventoMascota mascota) {
    //     EventoMascota createdEvento = eventoService.createRegistro(mascota);
    //     EntityModel<EventoMascota> entityModel = EntityModel.of(createdEvento,
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarId(createdEvento.getId())).withSelfRel(),
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarRegistro()).withRel("eventos"));
    //     return entityModel;        
    // }

    @PostMapping
    public ResponseEntity<EntityModel<EventoMascota>> createRegistro(@RequestBody EventoMascota mascota) {
        EventoMascota createdEvento = eventoService.createRegistro(mascota);
        EntityModel<EventoMascota> entityModel = EntityModel.of(createdEvento,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarId(createdEvento.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarRegistro()).withRel("eventos"));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel); // Aquí aseguramos el 201 Created
    }

    @DeleteMapping("/{id}")
    public EntityModel<String> eliminarRegistro(@PathVariable Long id) {
        eventoService.eliminarRegistro(id);
        EntityModel<String> entityModel = EntityModel.of("Evento eliminado con ID: " + id,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarRegistro()).withRel("eventos"));
        return entityModel;
    }

    @PutMapping("/{id}")
    public EntityModel<EventoMascota> updateRegistro(@PathVariable Long id, @RequestBody EventoMascota update) {
        Optional<EventoMascota> eventoExistente = eventoService.buscarId(id);
        if (eventoExistente.isPresent()) {
            EventoMascota updatedEvento = eventoService.updateRegistro(id, update);
            EntityModel<EventoMascota> entityModel = EntityModel.of(updatedEvento,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarId(updatedEvento.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).buscarRegistro()).withRel("eventos"));
            return entityModel;
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + id);
        }
    }
    
    
}