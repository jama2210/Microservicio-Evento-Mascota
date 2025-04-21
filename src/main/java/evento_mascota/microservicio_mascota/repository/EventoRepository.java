package evento_mascota.microservicio_mascota.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import evento_mascota.microservicio_mascota.model.EventoMascota;


@Repository
public interface EventoRepository extends JpaRepository<EventoMascota, Long> {
    
    
} 
