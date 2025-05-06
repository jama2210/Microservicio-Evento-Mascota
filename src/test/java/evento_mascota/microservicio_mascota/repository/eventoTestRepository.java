package evento_mascota.microservicio_mascota.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import evento_mascota.microservicio_mascota.model.EventoMascota;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
}
