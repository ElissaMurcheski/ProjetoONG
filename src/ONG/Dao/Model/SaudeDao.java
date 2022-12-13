package ONG.Dao.Model;

import ONG.Model.Saude;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SaudeDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Saude saude) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO saude(peso, castrado, observacaoCastrado, vacinado, observacaoVacina, doente, observacaoDoenca) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, saude.getPeso());
            ps.setBoolean(2, saude.isCastrado());
            ps.setString(3, saude.getObservacaoCastrado());
            ps.setBoolean(4, saude.isVacinado());
            ps.setString(5, saude.getObservacaoVacina());
            ps.setBoolean(6, saude.isDoente());
            ps.setString(7, saude.getObservacaoDoenca());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Saude saude) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE saude set peso = ?, castrado = ?, observacaoCastrado = ?, vacinado = ?, observacaoVacina = ?, doente = ?, observacaoDoenca = ?"
                + " WHERE idSaude = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, saude.getPeso());
            ps.setBoolean(2, saude.isCastrado());
            ps.setString(3, saude.getObservacaoCastrado());
            ps.setBoolean(4, saude.isVacinado());
            ps.setString(5, saude.getObservacaoVacina());
            ps.setBoolean(6, saude.isDoente());
            ps.setString(7, saude.getObservacaoDoenca());
            ps.setInt(8, saude.getId());
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
        String sql = "DELETE FROM saude WHERE (idSaude = ? )";
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

    public Saude select(int id) throws SQLException {
        Saude saudeRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from saude where idSaude = " + id);

        while (rs.next()) {
            saudeRet = new Saude(rs.getInt("idSaude"),
                    rs.getDouble("peso"),
                    rs.getBoolean("castrado"),
                    rs.getString("observacaoCastrado"),
                    rs.getBoolean("vacinado"),
                    rs.getString("observacaoVacina"),
                    rs.getBoolean("doente"),
                    rs.getString("observacaoDoenca"));

        }
        conexao.desconectarBanco();
        return saudeRet;
    }
    
    public int selectIdPorSaude(Saude saude) throws SQLException {
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select idSaude from saude where "
                + "peso = " + saude.getPeso()
                + " and castrado = " + saude.isCastrado()
                + " and observacaoCastrado = '" + saude.getObservacaoCastrado()
                + "' and vacinado = " + saude.isVacinado()
                + " and observacaoVacina = '" + saude.getObservacaoVacina()
                + "' and doente = " + saude.isDoente()
                + " and observacaoDoenca = '" + saude.getObservacaoDoenca() + "'"
        );

        while (rs.next()) {
            return rs.getInt("idSaude");

        }
        conexao.desconectarBanco();
        return 0;
    }

    public ArrayList<Saude> getSaudebyLista() throws SQLException {
        ArrayList<Saude> saudeRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from saude")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                saudeRet.add(new Saude(rs.getInt("idSaude"),
                        rs.getDouble("peso"),
                        rs.getBoolean("castrado"),
                        rs.getString("observacaoCastrado"),
                        rs.getBoolean("vacinado"),
                        rs.getString("observacaoVacina"),
                        rs.getBoolean("doente"),
                        rs.getString("observacaoDoenca")));
            }
        }
        conexao.desconectarBanco();
        return saudeRet;
    }

}
