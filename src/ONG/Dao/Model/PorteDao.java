package ONG.Dao.Model;

import ONG.Model.Porte;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PorteDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Porte porte) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO porte(idPorte, nome) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, porte.getId());
            ps.setString(2, porte.getNome());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Porte porte) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE porte set nome = ?"
                + " WHERE idPorte = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, porte.getNome());
            ps.setInt(2, porte.getId());
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
        String sql = "DELETE FROM porte WHERE (idPorte = ? )";
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

    public Porte select(int id) throws SQLException {
        Porte porteRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from porte where idPorte = " + id);

        while (rs.next()) {
            porteRet = new Porte(rs.getInt("idPorte"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return porteRet;
    }

    public Porte select(String nome) throws SQLException {
        Porte porteRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from porte where nome = '" + nome + "'");

        while (rs.next()) {
            porteRet = new Porte(rs.getInt("idPorte"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return porteRet;
    }

    public ArrayList<Porte> getPortebyLista() throws SQLException {
        ArrayList<Porte> porteRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from porte order by nome")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                porteRet.add(new Porte(rs.getInt("idPorte"),
                        rs.getString("nome")));
            }
        }
        conexao.desconectarBanco();
        return porteRet;
    }
}
