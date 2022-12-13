package ONG.Dao.Model;

import ONG.Model.Especie;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EspecieDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Especie especie) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO especie(idEspecie, nome) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, especie.getId());
            ps.setString(2, especie.getNome());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Especie especie) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE especie set nome = ?"
                + " WHERE idEspecie = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, especie.getNome());
            ps.setInt(2, especie.getId());
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
        String sql = "DELETE FROM especie WHERE (idEspecie = ? )";
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

    public Especie select(int id) throws SQLException {
        Especie especieRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from especie where idEspecie = " + id);
        while (rs.next()) {
            especieRet = new Especie(rs.getInt("idEspecie"),
                    rs.getString("nome"));
        }

        conexao.desconectarBanco();
        return especieRet;
    }

    public Especie select(String nome) throws SQLException {
        Especie especieRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from especie where nome = '" + nome + "'");

        while (rs.next()) {
            especieRet = new Especie(rs.getInt("idEspecie"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return especieRet;
    }

    public ArrayList<Especie> getEspeciebyLista() throws SQLException {
        ArrayList<Especie> especieRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from especie order by nome")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                especieRet.add(new Especie(rs.getInt("idEspecie"),
                        rs.getString("nome")));
            }
        }
        conexao.desconectarBanco();
        return especieRet;
    }

}
