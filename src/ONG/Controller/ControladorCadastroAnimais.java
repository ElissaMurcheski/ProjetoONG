package ONG.Controller;

import ONG.Dao.Model.AnimalDao;
import ONG.Dao.Model.EspecieDao;
import ONG.Dao.Model.PorteDao;
import ONG.Dao.Model.RacaDao;
import ONG.Dao.Model.SaudeDao;
import ONG.Dao.Model.SexoDao;
import ONG.Dao.Model.StatusDao;
import ONG.Model.Animal;
import ONG.Model.Especie;
import ONG.Model.Porte;
import ONG.Model.Raca;
import ONG.Model.Saude;
import ONG.Model.Sexo;
import ONG.Model.Status;
import ONG.View.CadastroAnimais;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorCadastroAnimais implements ActionListener {

    Animal animal = new Animal();
    CadastroAnimais telaCadastroAnimais = new CadastroAnimais();

    RacaDao racaDao = new RacaDao();
    EspecieDao especieDao = new EspecieDao();
    SexoDao sexoDao = new SexoDao();
    PorteDao porteDao = new PorteDao();
    StatusDao statusDao = new StatusDao();
    SaudeDao saudeDao = new SaudeDao();
    AnimalDao animalDao = new AnimalDao();

    public ControladorCadastroAnimais(CadastroAnimais cadastroAnimais, Animal animal) throws SQLException {
        this.animal = animal;
        telaCadastroAnimais = cadastroAnimais;
        telaCadastroAnimais.setVisible(true);
        telaCadastroAnimais.setLocationRelativeTo(null);
        telaCadastroAnimais.jButtonSalvar.addActionListener(this);
        telaCadastroAnimais.jButtonDeletar.addActionListener(this);

        popularCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if ("Deletar".equals(e.getActionCommand())) {
                if (jaExiste()) {
                    animalDao.delete(animal.getId());
                    saudeDao.delete(animal.getSaude().getId());
                }
            } else {
                // Criando nova instancia de saude com os dados que o usuario escreveu
                Saude newSaude = getNewSaude();
                if (jaExiste()) {
                    saudeDao.update(newSaude);
                } else {
                    saudeDao.insert(newSaude);
                }

                // Criando nova instancia de animal com os dados que o usuario escreveu
                Animal newAnimal = getNewAnimal(newSaude);
                if (jaExiste()) {
                    animalDao.update(newAnimal);
                } else {
                    newAnimal.getSaude().setId(saudeDao.selectIdPorSaude(newSaude));
                    animalDao.insert(newAnimal);
                }
            }
            new ControladorListaAnimaisDoacao();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControladorCadastroAnimais.class.getName()).log(Level.SEVERE, null, ex);
        }
        telaCadastroAnimais.dispose();
    }

    private boolean jaExiste() {
        return animal.getId() > 0 && animal.getSaude().getId() > 0;
    }

    private Saude getNewSaude() {
        int idSaude = jaExiste() ? animal.getSaude().getId() : 0;
        boolean castrado = telaCadastroAnimais.CastradoSim.isSelected();
        String observacaoCastrado = telaCadastroAnimais.ObservacaoCastrado.getText();
        boolean vacinado = telaCadastroAnimais.VacinasSim.isSelected();
        String observacaoVacinado = telaCadastroAnimais.ObservacaoVacinas.getText();
        boolean doente = telaCadastroAnimais.DoencaSim.isSelected();
        String observacaoDoente = telaCadastroAnimais.ObservacaoDoencas.getText();
        Double peso = Double.parseDouble(telaCadastroAnimais.Peso.getText());

        return new Saude(idSaude, peso, castrado, observacaoCastrado, vacinado, observacaoVacinado, doente, observacaoDoente);
    }

    private Animal getNewAnimal(Saude saude) throws SQLException, ParseException {
        int idAnimal = Integer.parseInt(telaCadastroAnimais.idAnimal.getText());
        String nome = telaCadastroAnimais.Nome.getText();
        Double tempoAproximadoVida = Double.parseDouble(telaCadastroAnimais.TempoVida.getText());
        int dia = Integer.parseInt(telaCadastroAnimais.Dia.getText());
        int mes = Integer.parseInt(telaCadastroAnimais.Mes.getText());
        int ano = Integer.parseInt(telaCadastroAnimais.Ano.getText());
        String dataEmTexto = dia + "/" + mes + "/" + ano;
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
        String racaSelecionada = telaCadastroAnimais.Racas.getSelectedItem().toString();
        String especieSelecionada = telaCadastroAnimais.Especies.getSelectedItem().toString();
        String sexoSelecionado = telaCadastroAnimais.Sexo.getSelectedItem().toString();
        String porteSelecionado = telaCadastroAnimais.Portes.getSelectedItem().toString();
        String statusSelecionado = telaCadastroAnimais.Status.getSelectedItem().toString();

        Raca raca = racaDao.select(racaSelecionada);
        Especie especie = especieDao.select(especieSelecionada);
        Sexo sexo = sexoDao.select(sexoSelecionado);
        Porte porte = porteDao.select(porteSelecionado);
        Status status = statusDao.select(statusSelecionado);

        return new Animal(idAnimal, nome, sexo, porte, tempoAproximadoVida, data, raca, especie, status, saude);
    }

    private void popularCampos() throws SQLException {
        especieDao.getEspeciebyLista().forEach(especie -> {
            telaCadastroAnimais.Especies.addItem(especie.getNome());
        });
        racaDao.getRacabyLista().forEach(raca -> {
            telaCadastroAnimais.Racas.addItem(raca.getNome());
        });
        sexoDao.getSexoByLista().forEach(sexo -> {
            telaCadastroAnimais.Sexo.addItem(sexo.getNome());
        });
        porteDao.getPortebyLista().forEach(porte -> {
            telaCadastroAnimais.Portes.addItem(porte.getNome());
        });
        statusDao.getstatusbyLista().forEach(status -> {
            telaCadastroAnimais.Status.addItem(status.getNome());
        });
        if (jaExiste()) {
            // Animal
            LocalDate currentDate = LocalDate.parse(animal.getDataCadastro().toString());
            telaCadastroAnimais.idAnimal.setText(String.valueOf(animal.getId()));
            telaCadastroAnimais.Dia.setText(String.valueOf(currentDate.getDayOfMonth()));
            telaCadastroAnimais.Mes.setText(String.valueOf(currentDate.getMonthValue()));
            telaCadastroAnimais.Ano.setText(String.valueOf(currentDate.getYear()));
            telaCadastroAnimais.Nome.setText(animal.getNome());
            telaCadastroAnimais.Especies.setSelectedItem(animal.getEspecie().getNome());
            telaCadastroAnimais.Racas.setSelectedItem(animal.getRaca().getNome());
            telaCadastroAnimais.Sexo.setSelectedItem(animal.getSexo().getNome());
            telaCadastroAnimais.Portes.setSelectedItem(animal.getPorte().getNome());
            telaCadastroAnimais.Status.setSelectedItem(animal.getStatus().getNome());
            telaCadastroAnimais.TempoVida.setText(animal.getTempoAproxVida().toString());

            // Saude
            if (animal.getSaude().isCastrado()) {
                telaCadastroAnimais.CastradoSim.setSelected(true);
            } else {
                telaCadastroAnimais.CastradoNao.setSelected(true);
            }
            if (animal.getSaude().isVacinado()) {
                telaCadastroAnimais.VacinasSim.setSelected(true);
            } else {
                telaCadastroAnimais.VacinasNao.setSelected(true);
            }
            if (animal.getSaude().isDoente()) {
                telaCadastroAnimais.DoencaSim.setSelected(true);
            } else {
                telaCadastroAnimais.DoencaNao.setSelected(true);
            }
            telaCadastroAnimais.ObservacaoCastrado.setText(animal.getSaude().getObservacaoCastrado());
            telaCadastroAnimais.ObservacaoVacinas.setText(animal.getSaude().getObservacaoVacina());
            telaCadastroAnimais.ObservacaoDoencas.setText(animal.getSaude().getObservacaoDoenca());
            telaCadastroAnimais.Peso.setText(animal.getSaude().getPeso().toString());
        }
    }
}
