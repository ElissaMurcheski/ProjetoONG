package ONG.Model;

import java.util.Date;

public class Animal {

    private int id;
    private String nome;
    private Sexo sexo;
    private Porte porte;
    private Double tempoAproxVida;
    private Date dataCadastro;
    private Raca raca;
    private Especie especie;
    private Status status;
    private Saude saude;

    public Animal() {}

    public Animal(int id, String nome, Sexo sexo, Porte porte, Double tempoAproxVida, Date dataCadastro, Raca raca, Especie especie, Status status, Saude saude) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.porte = porte;
        this.tempoAproxVida = tempoAproxVida;
        this.dataCadastro = dataCadastro;
        this.raca = raca;
        this.especie = especie;
        this.status = status;
        this.saude = saude;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Double getTempoAproxVida() {
        return tempoAproxVida;
    }

    public void setTempoAproxVida(Double tempoAproxVida) {
        this.tempoAproxVida = tempoAproxVida;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Saude getSaude() {
        return saude;
    }

    public void setSaude(Saude saude) {
        this.saude = saude;
    }

}
