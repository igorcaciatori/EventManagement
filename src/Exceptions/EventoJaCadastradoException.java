package Exceptions;

public class EventoJaCadastradoException extends RuntimeException {
    public EventoJaCadastradoException() {
        super("Evento já cadastrado!");
    }
}
