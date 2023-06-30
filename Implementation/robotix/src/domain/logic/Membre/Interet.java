package domain.logic.Membre;
public class Interet implements java.io.Serializable{
    private String nom;

    public Interet(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
