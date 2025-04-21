package evento_mascota.microservicio_mascota.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity 
@Table(name = "EventoMascota")
public class EventoMascota {
    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha")
    private String fecha;

    @NotBlank(message = "El nombre del participante no puede estar vacío")
    @Column(name = "nombre_participante")
    private String nombre_participante;

    @NotBlank(message = "El tipo de mascota no puede estar vacío")
    @Column(name = "tipo_mascota")
    private String tipo_mascota;
    
}

