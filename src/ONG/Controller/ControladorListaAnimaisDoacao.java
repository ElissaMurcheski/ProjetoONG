package ONG.Controller;

import ONG.View.ListaAnimaisDoacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorListaAnimaisDoacao implements ActionListener {

    ListaAnimaisDoacao listaAnimaisDoacao = new ListaAnimaisDoacao();

    public ControladorListaAnimaisDoacao() {
        listaAnimaisDoacao.setVisible(true);
        listaAnimaisDoacao.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
