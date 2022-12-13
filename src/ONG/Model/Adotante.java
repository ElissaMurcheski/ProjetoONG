package ONG.Model;

public class Adotante {

    private int id;
    private String nome;
    private String telefone;
    private String observacao;

    public Adotante(int id, String nome, String telefone, String observacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
