import Exceptions.*;
import java.sql.*;

public class BancoEvento {

    Database database = new Database();

    public void adicionarEvento(Evento evento) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO evento (nome, local, data, descricao) " +
                            "VALUES (?,?,?,?);");

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setString(3, evento.getData());
            ps.setString(4, evento.getDescricao());
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar evento: " + e.getMessage());
        }
    }

    public Evento buscarEventoPorNome(String nome) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM evento WHERE nome = ?;");

            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_evento");
                String nomeEvento = rs.getString("nome");
                String lugar = rs.getString("lugar");
                String data = rs.getString("data");
                String descricao = rs.getString("descricao");

                return new Evento(id, nomeEvento, lugar, data, descricao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar evento por nome: " + e.getMessage());
        }
        throw new EventoInexistenteException();
    }

    public void removerEvento(int id) {
        try (Connection con = database.getConexao()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM evento WHERE id_evento = ?;");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao remover evento: " + e.getMessage());
        }
    }
}
