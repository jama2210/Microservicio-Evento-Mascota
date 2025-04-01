package evento_mascota.microservicio_mascota.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participante {
    private int id;
    private String nombre;
    private String tipoMascota;
}