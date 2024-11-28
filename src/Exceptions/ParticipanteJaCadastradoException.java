package Exceptions;

public class ParticipanteJaCadastradoException extends RuntimeException {
    public ParticipanteJaCadastradoException() {
        super("Participante já cadastrado!");
    }
}
