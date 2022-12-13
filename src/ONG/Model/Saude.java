package ONG.Model;

public class Saude {

    private int id;
    private Double peso;
    private boolean castrado;
    private String observacaoCastrado;
    private boolean vacinado;
    private String observacaoVacina;
    private boolean doente;
    private String observacaoDoenca;

    public Saude(int id, Double peso, boolean castrado, String observacaoCastrado, boolean vacinado, String observacaoVacina, boolean doente, String observacaoDoenca) {
        this.id = id;
        this.peso = peso;
        this.castrado = castrado;
        this.observacaoCastrado = observacaoCastrado;
        this.vacinado = vacinado;
        this.observacaoVacina = observacaoVacina;
        this.doente = doente;
        this.observacaoDoenca = observacaoDoenca;
    }

    public Saude() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getObservacaoCastrado() {
        return observacaoCastrado;
    }

    public void setObservacaoCastrado(String observacaoCastrado) {
        this.observacaoCastrado = observacaoCastrado;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    public String getObservacaoVacina() {
        return observacaoVacina;
    }

    public void setObservacaoVacina(String observacaoVacina) {
        this.observacaoVacina = observacaoVacina;
    }

    public boolean isDoente() {
        return doente;
    }

    public void setDoente(boolean doente) {
        this.doente = doente;
    }

    public String getObservacaoDoenca() {
        return observacaoDoenca;
    }

    public void setObservacaoDoenca(String observacaoDoenca) {
        this.observacaoDoenca = observacaoDoenca;
    }

}
