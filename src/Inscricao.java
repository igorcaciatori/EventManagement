public class Inscricao {

    private int id;
    private Evento evento;
    private Participante participante;

    public Inscricao(int id, Evento evento, Participante participante) {
        this(evento, participante);
        this.id = id;
    }
    public Inscricao(Evento evento, Participante participante) {
        this.evento = evento;
        this.participante = participante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvent() {
        return evento;
    }

    public void setEvent(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipant() {
        return participante;
    }

    public void setParticipant(Participante participante) {
        this.participante = participante;
    }
}
