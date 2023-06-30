package domain.logic.Membre;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Interet implements java.io.Serializable{
    private String nom;
    @JsonCreator
    public Interet(@JsonProperty("nom") String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
