package ONG.Dao.Model;

import ONG.Model.Adocao;
import ONG.Model.Adotante;
import ONG.Model.Animal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class AdocaoDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Adocao adocao) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO adocao(dataAdocao, Animal_idAnimal, Adotante_idAdotante) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(adocao.getDataAdocao().getTime()));
            ps.setInt(2, adocao.getAnimal().getId());
            ps.setInt(3, adocao.getAdotante().getId());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Adocao adocao) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE adocao set dataAdocao = ?, Animal_idAnimal = ?, Adotante_idAdotante = ?"
                + " WHERE idAdocao = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(adocao.getDataAdocao().getTime()));
            ps.setInt(2, adocao.getAnimal().getId());
            ps.setInt(3, adocao.getAdotante().getId());
            ps.setInt(4, adocao.getId());
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
        String sql = "DELETE FROM adocao WHERE (idAdocao = ? )";
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

    public Adocao select(int id) throws SQLException {
        Adocao adocaoRet = null;
        try ( Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from adocao (idAdocao = " + id + ")")) {

            int idAnimal = rs.getInt("Animal_idAnimal");
            int idAdotante = rs.getInt("Adotante_idAdotante");

            AnimalDao animalDao = new AnimalDao();
            AdotanteDao adotanteDao = new AdotanteDao();

            Animal animal = animalDao.select(idAnimal);
            Adotante adotante = adotanteDao.select(idAdotante);

            adocaoRet = new Adocao(rs.getInt("idAdocao"),
                    rs.getDate("dataAdocao"),
                    animal,
                    adotante);
        }
        conexao.desconectarBanco();
        return adocaoRet;
    }

    public Adocao selectComIdAnimal(int id) throws SQLException {
        Adocao adocaoRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from adocao where Animal_idAnimal = " + id);

        while (rs.next()) {
            int idAnimal = rs.getInt("Animal_idAnimal");
            int idAdotante = rs.getInt("Adotante_idAdotante");

            AnimalDao animalDao = new AnimalDao();
            AdotanteDao adotanteDao = new AdotanteDao();

            Animal animal = animalDao.select(idAnimal);
            Adotante adotante = adotanteDao.select(idAdotante);

            adocaoRet = new Adocao(rs.getInt("idAdocao"),
                    rs.getDate("dataAdocao"),
                    animal,
                    adotante);
        }
        conexao.desconectarBanco();
        return adocaoRet;
    }

    public ArrayList<Adocao> getadocaobyLista() throws SQLException {
        ArrayList<Adocao> adocaoRet = new ArrayList<>();
        try (
                 Statement st = conexao.ConectarBanco().createStatement();  ResultSet rs = st.executeQuery(
                "select * from adocao")) {
            //rs.first(); //aponta para o primeiro da tabela

            while (rs.next()) {
                int idAnimal = rs.getInt("Animal_idAnimal");
                int idAdotante = rs.getInt("Adotante_idAdotante");

                AnimalDao animalDao = new AnimalDao();
                AdotanteDao adotanteDao = new AdotanteDao();

                Animal animal = animalDao.select(idAnimal);
                Adotante adotante = adotanteDao.select(idAdotante);

                adocaoRet.add(new Adocao(rs.getInt("idAdocao"),
                        rs.getDate("dataAdocao"),
                        animal,
                        adotante));
            }
        }
        conexao.desconectarBanco();
        return adocaoRet;
    }

}
