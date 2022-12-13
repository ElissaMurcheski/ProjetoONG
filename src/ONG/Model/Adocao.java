
package ONG.Model;

import java.util.Date;

public class Adocao {
    private int id;
    private Date dataAdocao;
    private Animal animal;
    private Adotante adotante; 

    public Adocao(int id, Date dataAdocao, Animal animal, Adotante adotante) {
        this.id = id;
        this.dataAdocao = dataAdocao;
        this.animal = animal;
        this.adotante = adotante;
    }

    public int getId() {
        return id;
    }
    
    public Date getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
    }
    
    
    
}
