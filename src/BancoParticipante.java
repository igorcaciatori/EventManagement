import Exceptions.ParticipanteInexistenteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoParticipante {

    Database database = new Database();

    public void adicionarParticipante(Participante participante) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO participante (nome, email) VALUES (?,?);");

            ps.setString(1, participante.getNome());
            ps.setString(2, participante.getEmail());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar participante: " + e.getMessage());
        }
    }

    public Participante buscarParticipantePorEmail(String email) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM participante WHERE email = ?;");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_participante");
                String nome = rs.getString("nome");
                String emailParticipante = rs.getString("email");
                return new Participante(id, nome, emailParticipante);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar participante: " + e.getMessage());
        }
        throw new ParticipanteInexistenteException();
    }

    public void removerParticipante(int id) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM participante WHERE id_participante = ?;");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao remover participante: " + e.getMessage());
        }
    }
}
