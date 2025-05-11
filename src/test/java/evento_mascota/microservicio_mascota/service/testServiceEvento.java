package evento_mascota.microservicio_mascota.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import evento_mascota.microservicio_mascota.model.EventoMascota;
import evento_mascota.microservicio_mascota.repository.EventoRepository;
import java.util.Optional;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class testServiceEvento {
    @InjectMocks
    private ServiceImplementation serviceImplementation;

    @Mock
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setUp() {
        eventoRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        eventoRepository.deleteAll();
    }

    @Test
    public void saveEvento() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("nombre1");
        eventoMascota.setFecha("2023-10-01");
        eventoMascota.setNombre_participante("participante1");
        eventoMascota.setTipo_mascota("perro");

        when(eventoRepository.save(any(EventoMascota.class))).thenReturn(eventoMascota);

        EventoMascota resultado = serviceImplementation.createRegistro(eventoMascota);

        assertEquals(eventoMascota.getNombre(), resultado.getNombre());
        assertEquals(eventoMascota.getFecha(), resultado.getFecha());
        assertEquals(eventoMascota.getNombre_participante(), resultado.getNombre_participante());
        assertEquals(eventoMascota.getTipo_mascota(), resultado.getTipo_mascota());
    }
    

    @Test
    public void findEventoById() {
        EventoMascota eventoMascota = new EventoMascota(3L, "evento3", "2023-10-03", "participante3", "conejo");
        when(eventoRepository.findById(3L)).thenReturn(Optional.of(eventoMascota));

        Optional<EventoMascota> encontrado = serviceImplementation.buscarId(3L);
        assertTrue(encontrado.isPresent());
        assertEquals("evento3", encontrado.get().getNombre());
        assertEquals("2023-10-03", encontrado.get().getFecha());

        when(eventoRepository.findById(4L)).thenReturn(Optional.empty());
        Optional<EventoMascota> noEncontrado = serviceImplementation.buscarId(4L);
        assertTrue(noEncontrado.isEmpty());
    }
}