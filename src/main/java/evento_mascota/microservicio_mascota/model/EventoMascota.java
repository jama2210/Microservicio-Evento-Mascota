package evento_mascota.microservicio_mascota.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class EventoMascota {
    private int id;
    private String nombre;
    private String fecha;
    private List<Participante> participantes;
}