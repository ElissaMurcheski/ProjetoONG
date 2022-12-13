package ONG.Dao.Model;

import ONG.Model.Sexo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SexoDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Sexo sexo) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO sexo(idSexo, nome) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sexo.getId());
            ps.setString(2, sexo.getNome());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Sexo sexo) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE sexo set nome = ?"
                + " WHERE idSexo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sexo.getNome());
            ps.setInt(2, sexo.getId());
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
        String sql = "DELETE FROM sexo WHERE (idSexo = ? )";
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

    public Sexo select(int id) throws SQLException {
        Sexo sexoRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from sexo where idSexo = " + id);

        while (rs.next()) {
            sexoRet = new Sexo(rs.getInt("idSexo"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return sexoRet;

    }

    public Sexo select(String nome) throws SQLException {
        Sexo sexoRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from sexo where nome = '" + nome + "'");

        while (rs.next()) {
            sexoRet = new Sexo(rs.getInt("idSexo"),
                    rs.getString("nome"));
        }
        conexao.desconectarBanco();
        return sexoRet;
    }

    public ArrayList<Sexo> getSexoByLista() throws SQLException {
        ArrayList<Sexo> sexoRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from sexo order by nome")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                sexoRet.add(new Sexo(rs.getInt("idSexo"),
                        rs.getString("nome")));
            }
        }
        conexao.desconectarBanco();
        return sexoRet;
    }
}
