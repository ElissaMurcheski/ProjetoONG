package ONG.Controller;

import ONG.View.ListaAnimaisDoados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorListaAnimaisDoados implements ActionListener {

    ListaAnimaisDoados listaAnimaisDoados = new ListaAnimaisDoados();

    public ControladorListaAnimaisDoados() {
        listaAnimaisDoados.setVisible(true);
        listaAnimaisDoados.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
