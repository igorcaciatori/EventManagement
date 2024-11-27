package Exceptions;

public class EventoInexistenteException extends RuntimeException {
    public EventoInexistenteException() {
        super("Evento inexistente");
    }
}
