package ONG.Dao.Model;

import ONG.Model.Adotante;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdotanteDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Adotante adotante) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO adotante(nome, telefone, observacao) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, adotante.getNome());
            ps.setString(2, adotante.getTelefone());
            ps.setString(3, adotante.getObservacao());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Adotante adotante) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE adotante set nome = ?, telefone = ?, observacao = ?"
                + " WHERE idAdotante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, adotante.getNome());
            ps.setString(2, adotante.getTelefone());
            ps.setString(3, adotante.getObservacao());
            ps.setInt(4, adotante.getId());
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
        String sql = "DELETE FROM adotante WHERE (idAdotante = ? )";
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

    public Adotante select(int id) throws SQLException {
        Adotante adotanteRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from adotante where idAdotante = " + id);

        while (rs.next()) {
            adotanteRet = new Adotante(rs.getInt("idAdotante"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("observacao"));
        }
        conexao.desconectarBanco();
        return adotanteRet;
    }

    public int selectIdPorAdotante(Adotante adotante) throws SQLException {
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select idAdotante from adotante where "
                + "nome = " + "'" + adotante.getNome() + "'"
                + " and observacao = " + "'" + adotante.getObservacao() + "'"
                + " and telefone = " + "'" + adotante.getTelefone() + "'");

        while (rs.next()) {
            return rs.getInt("idAdotante");
        }
        conexao.desconectarBanco();
        return 0;
    }

    public ArrayList<Adotante> getadotantebyLista() throws SQLException {
        ArrayList<Adotante> adotanteRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from Adotante")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                adotanteRet.add(new Adotante(rs.getInt("idAdotante"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("observacao")));
            }
        }
        conexao.desconectarBanco();
        return adotanteRet;
    }
}
