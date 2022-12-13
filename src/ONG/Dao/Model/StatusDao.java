package ONG.Dao.Model;

import ONG.Model.Status;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StatusDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Status status) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO status(idStatus, nome) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, status.getId());
            ps.setString(2, status.getNome());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Status status) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE status set nome = ?"
                + " WHERE idStatus = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, status.getNome());
            ps.setInt(2, status.getId());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean delete(int id) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "DELETE FROM status WHERE (idStatus = ? )";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public Status select(int id) throws SQLException {
        Status statusRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from status where idStatus = " + id);

        while (rs.next()) {
            statusRet = new Status(rs.getInt("idStatus"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return statusRet;
    }

    public Status select(String nome) throws SQLException {
        Status statusRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from status where nome = '" + nome + "'");

        while (rs.next()) {
            statusRet = new Status(rs.getInt("idStatus"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return statusRet;
    }

    public ArrayList<Status> getstatusbyLista() throws SQLException {
        ArrayList<Status> statusRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from Status order by nome")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                statusRet.add(new Status(rs.getInt("idStatus"),
                        rs.getString("nome")));
            }
        }
        conexao.desconectarBanco();
        return statusRet;
    }
}
