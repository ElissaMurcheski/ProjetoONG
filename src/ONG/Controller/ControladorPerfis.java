package ONG.Controller;

import ONG.View.Perfis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPerfis implements ActionListener {

    Perfis telaPerfis = new Perfis();

    public ControladorPerfis() {
        telaPerfis.setVisible(true);
        telaPerfis.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
