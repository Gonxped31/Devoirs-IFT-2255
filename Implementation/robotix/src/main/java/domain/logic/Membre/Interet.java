package domain.logic.Membre;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Représente un intérêt.
 */
public class Interet implements java.io.Serializable{
    private String nom;

    /**
     * Constructeur de la classe.
     * Crée un nouvel objet Interet avec le nom spécifié.
     *
     * @param nom Le nom de l'intérêt.
     */
    @JsonCreator
    public Interet(@JsonProperty("nom") String nom){
        this.nom = nom;
    }

    /**
     * Renvoie le nom de l'intérêt.
     *
     * @return Le nom de l'intérêt.
     */
    public String getNom() {
        return nom;
    }
}