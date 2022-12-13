package ONG.Dao.Model;

import ONG.Model.Animal;
import ONG.Model.Especie;
import ONG.Model.Porte;
import ONG.Model.Raca;
import ONG.Model.Saude;
import ONG.Model.Sexo;
import ONG.Model.Status;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AnimalDao extends Conexao {

    private final Conexao conexao = new Conexao();

    public boolean insert(Animal animal) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "INSERT INTO animal(nome, tempoAproximadoVida, dataCadastro, Raca_idRaca, Especie_idEspecie, Sexo_idSexo, Porte_idPorte, Status_idStatus, Saude_idSaude)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, animal.getNome());
            ps.setDouble(2, animal.getTempoAproxVida());
            ps.setDate(3, new Date(animal.getDataCadastro().getTime()));
            ps.setInt(4, animal.getRaca().getId());
            ps.setInt(5, animal.getEspecie().getId());
            ps.setInt(6, animal.getSexo().getId());
            ps.setInt(7, animal.getPorte().getId());
            ps.setInt(8, animal.getStatus().getId());
            ps.setInt(9, animal.getSaude().getId());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public boolean update(Animal animal) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE animal set nome = ?, tempoAproximadoVida = ?, dataCadastro = ?, Raca_idRaca = ?,"
                + "Especie_idEspecie = ?, Sexo_idSexo = ?, Porte_idPorte = ?, Status_idStatus = ?, Saude_idSaude = ?"
                + " WHERE idAnimal = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, animal.getNome());
            ps.setDouble(2, animal.getTempoAproxVida());
            ps.setDate(3, new Date(animal.getDataCadastro().getTime()));
            ps.setInt(4, animal.getRaca().getId());
            ps.setInt(5, animal.getEspecie().getId());
            ps.setInt(6, animal.getSexo().getId());
            ps.setInt(7, animal.getPorte().getId());
            ps.setInt(8, animal.getStatus().getId());
            ps.setInt(9, animal.getSaude().getId());
            ps.setInt(10, animal.getId());
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
        String sql = "DELETE FROM animal WHERE (idAnimal = ? )";
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

    public Animal select(int id) throws SQLException {
        Animal animalRet = null;
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from animal where idAnimal = " + id);

        while (rs.next()) {
            int idSexo = rs.getInt("Sexo_idSexo");
            int idPorte = rs.getInt("Porte_idPorte");
            int idRaca = rs.getInt("Raca_idRaca");
            int idEspecie = rs.getInt("Especie_idEspecie");
            int idStatus = rs.getInt("Status_idStatus");
            int idSaude = rs.getInt("Saude_idSaude");

            SexoDao sexoDao = new SexoDao();
            PorteDao porteDao = new PorteDao();
            RacaDao racaDao = new RacaDao();
            EspecieDao especieDao = new EspecieDao();
            StatusDao statusDao = new StatusDao();
            SaudeDao saudeDao = new SaudeDao();

            Sexo sexo = sexoDao.select(idSexo);
            Porte porte = porteDao.select(idPorte);
            Raca raca = racaDao.select(idRaca);
            Especie especie = especieDao.select(idEspecie);
            Status status = statusDao.select(idStatus);
            Saude saude = saudeDao.select(idSaude);

            animalRet = new Animal(rs.getInt("idAnimal"),
                    rs.getString("nome"),
                    sexo,
                    porte,
                    rs.getDouble("tempoAproximadoVida"),
                    rs.getDate("dataCadastro"),
                    raca,
                    especie,
                    status,
                    saude);

        }
        conexao.desconectarBanco();
        return animalRet;
    }

    public void inativarAnimal(int id) {
        PreparedStatement ps = null;
        Connection con = ConectarBanco();
        String sql = "UPDATE animal set Status_idStatus = 2"
                + " WHERE idAnimal = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            desconectarBanco();
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
        }
    }

    public ArrayList<Animal> getAnimalAtivo() throws SQLException {
        ArrayList<Animal> animalRet = new ArrayList<>();
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from animal where Status_idStatus = 1");

        while (rs.next()) {
            int idSexo = rs.getInt("Sexo_idSexo");
            int idPorte = rs.getInt("Porte_idPorte");
            int idRaca = rs.getInt("Raca_idRaca");
            int idEspecie = rs.getInt("Especie_idEspecie");
            int idStatus = rs.getInt("Status_idStatus");
            int idSaude = rs.getInt("Saude_idSaude");

            Sexo sexo = new SexoDao().select(idSexo);
            Porte porte = new PorteDao().select(idPorte);
            Raca raca = new RacaDao().select(idRaca);
            Especie especie = new EspecieDao().select(idEspecie);
            Status status = new StatusDao().select(idStatus);
            Saude saude = new SaudeDao().select(idSaude);

            animalRet.add(new Animal(rs.getInt("idAnimal"),
                    rs.getString("nome"),
                    sexo,
                    porte,
                    rs.getDouble("tempoAproximadoVida"),
                    rs.getDate("dataCadastro"),
                    raca,
                    especie,
                    status,
                    saude));
        }
        conexao.desconectarBanco();
        return animalRet;
    }

    public ArrayList<Animal> getAnimalInativo() throws SQLException {
        ArrayList<Animal> animalRet = new ArrayList<>();
        Statement st = conexao.ConectarBanco().createStatement();
        ResultSet rs = st.executeQuery("select * from animal where Status_idStatus = 2");

        while (rs.next()) {
            int idSexo = rs.getInt("Sexo_idSexo");
            int idPorte = rs.getInt("Porte_idPorte");
            int idRaca = rs.getInt("Raca_idRaca");
            int idEspecie = rs.getInt("Especie_idEspecie");
            int idStatus = rs.getInt("Status_idStatus");
            int idSaude = rs.getInt("Saude_idSaude");

            Sexo sexo = new SexoDao().select(idSexo);
            Porte porte = new PorteDao().select(idPorte);
            Raca raca = new RacaDao().select(idRaca);
            Especie especie = new EspecieDao().select(idEspecie);
            Status status = new StatusDao().select(idStatus);
            Saude saude = new SaudeDao().select(idSaude);

            animalRet.add(new Animal(rs.getInt("idAnimal"),
                    rs.getString("nome"),
                    sexo,
                    porte,
                    rs.getDouble("tempoAproximadoVida"),
                    rs.getDate("dataCadastro"),
                    raca,
                    especie,
                    status,
                    saude));
        }
        conexao.desconectarBanco();
        return animalRet;
    }
}
