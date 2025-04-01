package evento_mascota.microservicio_mascota.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNotFound extends RuntimeException{
    public EventoNotFound(int id) {
        super("El evento N° " + id + " no fue encontrado");
    }
}
