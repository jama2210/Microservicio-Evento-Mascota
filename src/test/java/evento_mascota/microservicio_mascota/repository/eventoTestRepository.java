package evento_mascota.microservicio_mascota.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import evento_mascota.microservicio_mascota.model.EventoMascota;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class eventoTestRepository {
    @Autowired
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
    public void saveFactura() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("nombre1");
        eventoMascota.setFecha("2023-10-01");
        eventoMascota.setNombre_participante("participante1");
        eventoMascota.setTipo_mascota("perro");

        EventoMascota resultado = eventoRepository.save(eventoMascota);

        assertNotNull(resultado);
        assertEquals(eventoMascota.getNombre(), resultado.getNombre());
        assertEquals(eventoMascota.getFecha(), resultado.getFecha());
        assertEquals(eventoMascota.getNombre_participante(), resultado.getNombre_participante());
        assertEquals(eventoMascota.getTipo_mascota(), resultado.getTipo_mascota());
        
    }

    @Test
    public void findById() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("nombre2");
        eventoMascota.setFecha("2023-10-02");
        eventoMascota.setNombre_participante("participante2");
        eventoMascota.setTipo_mascota("gato");
        EventoMascota guardado = eventoRepository.save(eventoMascota);
        Optional<EventoMascota> resultado = eventoRepository.findById(guardado.getId());
        assertTrue(resultado.isPresent());
    }

    @Test
    public void deleteEvento() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("eventoEliminar");
        eventoMascota.setFecha("2023-10-03");
        eventoMascota.setNombre_participante("participante3");
        eventoMascota.setTipo_mascota("conejo");

        eventoRepository.save(eventoMascota);
        eventoRepository.deleteById(eventoMascota.getId());

        Optional<EventoMascota> resultado = eventoRepository.findById(eventoMascota.getId());

        assertFalse(resultado.isPresent());
    }

    @Test
    public void updateEvento() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setNombre("eventoActualizar");
        eventoMascota.setFecha("2023-10-04");
        eventoMascota.setNombre_participante("participante4");
        eventoMascota.setTipo_mascota("pez");

        EventoMascota guardado = eventoRepository.save(eventoMascota);
        guardado.setNombre("eventoActualizado");
        eventoRepository.save(guardado);

        Optional<EventoMascota> resultado = eventoRepository.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("eventoActualizado", resultado.get().getNombre());
    }

    @Test
    public void findAllEventos() {
        EventoMascota evento1 = new EventoMascota();
        evento1.setNombre("evento1");
        evento1.setFecha("2023-10-05");
        evento1.setNombre_participante("participante5");
        evento1.setTipo_mascota("tortuga");

        EventoMascota evento2 = new EventoMascota();
        evento2.setNombre("evento2");
        evento2.setFecha("2023-10-06");
        evento2.setNombre_participante("participante6");
        evento2.setTipo_mascota("hamster");

        eventoRepository.save(evento1);
        eventoRepository.save(evento2);

        List<EventoMascota> eventos = eventoRepository.findAll();

        assertEquals(2, eventos.size());
    }

}
