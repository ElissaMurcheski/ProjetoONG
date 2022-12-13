package ONG.Dao.Model;

import ONG.Model.Raca;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RacaDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Raca raca) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO raca (idRaca, nome) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, raca.getId());
            ps.setString(2, raca.getNome());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Raca raca) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE raca set nome = ?"
                + " WHERE idRaca = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, raca.getNome());
            ps.setInt(2, raca.getId());
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
        String sql = "DELETE FROM raca WHERE (idRaca = ? )";
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

    public Raca select(int id) throws SQLException {
        Raca racaRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from raca where idRaca = " + id);
        while (rs.next()) {
            racaRet = new Raca(rs.getInt("idRaca"),
                    rs.getString("nome"));
        }

        conexao.desconectarBanco();
        return racaRet;
    }

    public Raca select(String nome) throws SQLException {
        Raca racaRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from raca where nome = '" + nome + "'");

        while (rs.next()) {
            racaRet = new Raca(rs.getInt("idRaca"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return racaRet;
    }

    public ArrayList<Raca> getRacabyLista() throws SQLException {
        ArrayList<Raca> racaRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from raca order by nome")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                racaRet.add(new Raca(rs.getInt("idRaca"),
                        rs.getString("nome")));
            }
        }
        conexao.desconectarBanco();
        return racaRet;
    }

}
