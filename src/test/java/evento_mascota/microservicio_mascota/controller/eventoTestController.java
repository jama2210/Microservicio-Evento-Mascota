package evento_mascota.microservicio_mascota.controller;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.service.EventoService;

import java.util.stream.Collectors;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;


@WebMvcTest(EventoController.class)
public class eventoTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @Test
    public void testGetAllEventos() throws Exception {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("evento1");
        eventoMascota.setFecha("2023-10-01");
        eventoMascota.setNombre_participante("participante1");
        eventoMascota.setTipo_mascota("perro");

        List<EventoMascota> eventos = List.of(eventoMascota);

        List<EntityModel<EventoMascota>> eventoModels = eventos.stream()
                .map(evento -> EntityModel.of(evento))
                .collect(Collectors.toList());

        when(eventoService.buscarRegistro()).thenReturn(eventos);

        mockMvc.perform(get("/eventos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.eventoMascotaList[0].nombre", Matchers.is("evento1")))
                .andExpect(jsonPath("$._embedded.eventoMascotaList[0].fecha", Matchers.is("2023-10-01")))
                .andExpect(jsonPath("$._embedded.eventoMascotaList[0].nombre_participante", Matchers.is("participante1")))
                .andExpect(jsonPath("$._embedded.eventoMascotaList[0].tipo_mascota", Matchers.is("perro")));
    }
}
