package ONG.Controller;

import ONG.Dao.Model.AdocaoDao;
import ONG.Dao.Model.AdotanteDao;
import ONG.Dao.Model.AnimalDao;
import ONG.Model.Adotante;
import ONG.Model.Animal;
import ONG.View.Adocao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorAdocao implements ActionListener {

    Adocao telaAdocao = new Adocao();
    Animal animal = new Animal();
    AdotanteDao adotanteDao = new AdotanteDao();
    AdocaoDao adocaoDao = new AdocaoDao();

    public ControladorAdocao(Animal animal) {
        this.animal = animal;
        telaAdocao.setVisible(true);
        telaAdocao.setLocationRelativeTo(null);

        telaAdocao.Salvar.addActionListener(this);

        popularCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int dia = Integer.parseInt(telaAdocao.Dia.getText());
            int mes = Integer.parseInt(telaAdocao.Mes.getText());
            int ano = Integer.parseInt(telaAdocao.Ano.getText());
            String dataEmTexto = dia + "/" + mes + "/" + ano;
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
            String nome = telaAdocao.Nome.getText();
            String telefone = telaAdocao.Telefone.getText();
            String observacao = telaAdocao.Observacao.getText();

            Adotante adotante = new Adotante(0, nome, telefone, observacao);
            adotanteDao.insert(adotante);
            adotante.setId(adotanteDao.selectIdPorAdotante(adotante));
            ONG.Model.Adocao adocao = new ONG.Model.Adocao(0, data, animal, adotante);
            adocaoDao.insert(adocao);
            new AnimalDao().inativarAnimal(animal.getId());

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControladorAdocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void popularCampos() {
        try {
            ONG.Model.Adocao adocao = adocaoDao.selectComIdAnimal(animal.getId());
            telaAdocao.AnimalDoacao.setText(animal.getNome());
            if (adocao != null) {
                Adotante adotante = adotanteDao.select(adocao.getAdotante().getId());
                LocalDate currentDate = LocalDate.parse(adocao.getDataAdocao().toString());
                telaAdocao.Dia.setText(String.valueOf(currentDate.getDayOfMonth()));
                telaAdocao.Mes.setText(String.valueOf(currentDate.getMonthValue()));
                telaAdocao.Ano.setText(String.valueOf(currentDate.getYear()));
                telaAdocao.Nome.setText(adotante.getNome());
                telaAdocao.Telefone.setText(adotante.getTelefone());
                telaAdocao.Observacao.setText(adotante.getObservacao());
                
                telaAdocao.Dia.enable(false);
                telaAdocao.Mes.enable(false);
                telaAdocao.Ano.enable(false);
                telaAdocao.Nome.enable(false);
                telaAdocao.Telefone.enable(false);
                telaAdocao.Observacao.enable(false);
                telaAdocao.Salvar.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
