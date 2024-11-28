package Exceptions;

public class ParticipanteInexistenteException extends RuntimeException {
    public ParticipanteInexistenteException() {
        super("Participante inexistente!");
    }
}
