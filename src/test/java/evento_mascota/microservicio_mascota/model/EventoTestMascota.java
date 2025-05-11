package evento_mascota.microservicio_mascota.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class EventoTestMascota {

    private Validator validator;

    public EventoTestMascota() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCrearEventoMascota() {
        EventoMascota evento = new EventoMascota(1L, "Competencia Canina", "2025-05-15", "Juan Pérez", "Perro");
        assertNotNull(evento);
        assertEquals(1L, evento.getId());
        assertEquals("Competencia Canina", evento.getNombre());
        assertEquals("2025-05-15", evento.getFecha());
        assertEquals("Juan Pérez", evento.getNombre_participante());
        assertEquals("Perro", evento.getTipo_mascota());
    }

    @Test
    public void testValidacionDeDatos() {
        EventoMascota evento = new EventoMascota(-1L, "", "", "", "");
        Set<ConstraintViolation<EventoMascota>> violations = validator.validate(evento);
        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size());
    }

    @Test
    public void testSettersAndGetters() {
        EventoMascota evento = new EventoMascota();
        evento.setId(2L);
        evento.setNombre("Feria de Mascotas");
        evento.setFecha("2025-06-20");
        evento.setNombre_participante("Ana García");
        evento.setTipo_mascota("Gato");

        assertEquals(2L, evento.getId());
        assertEquals("Feria de Mascotas", evento.getNombre());
        assertEquals("2025-06-20", evento.getFecha());
        assertEquals("Ana García", evento.getNombre_participante());
        assertEquals("Gato", evento.getTipo_mascota());
    }

    @Test
    public void testEqualsAndHashCode() {
        EventoMascota evento1 = new EventoMascota(1L, "Competencia Canina", "2025-05-15", "Juan Pérez", "Perro");
        EventoMascota evento2 = new EventoMascota(1L, "Competencia Canina", "2025-05-15", "Juan Pérez", "Perro");
        EventoMascota evento3 = new EventoMascota(2L, "Feria de Mascotas", "2025-06-20", "Ana García", "Gato");

        assertEquals(evento1, evento2);
        assertNotEquals(evento1, evento3);
        assertEquals(evento1.hashCode(), evento2.hashCode());
        assertNotEquals(evento1.hashCode(), evento3.hashCode());
    }

}

