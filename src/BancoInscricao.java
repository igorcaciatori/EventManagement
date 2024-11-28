import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoInscricao {

    Database database = new Database();

    public void inscreverParticipante(int eventoId, int participanteId) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO inscricao (id_evento, id_participante) VALUES (?,?);");

            ps.setInt(1, eventoId);
            ps.setInt(2, participanteId);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removerInscricao(int id) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM inscricao WHERE id_inscricao = ?;");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
