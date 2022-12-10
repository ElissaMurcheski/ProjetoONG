package ONG.Dao.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/projeto_ong?allowPublicKeyRetrieval=true&useSSL=false";
    // + "//jdbc:mysql://localhost:3306/";
    private Connection con = null;
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Connection ConectarBanco() {
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println(e);
            // JOptionPane.showMessageDialog(null, "error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void desconectarBanco() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
