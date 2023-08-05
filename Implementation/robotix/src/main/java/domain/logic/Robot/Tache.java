package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Représente une tâche à effectuer par un robot, composée d'une liste d'actions.
 * Cette classe est sérialisable en JSON.
 */
public class Tache implements java.io.Serializable{
    private String nom;
    private ArrayList<Action> taches;
    private int dureeTache;

    /**
	 * Constructeur de la classe Tache.
	 * @param nom Le nom de la tâche.
	 * @param taches La liste d'actions composant la tâche.
	 */
    @JsonCreator
    public Tache(
            @JsonProperty("nom") String nom,
            @JsonProperty("taches") ArrayList<Action> taches){
        this.nom = nom;
        this.taches = taches;
    }

    /**
     * Renvoie le nom de la tâche.
     *
     * @return le nom de la tâche.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la liste des actions composant la tâche.
     *
     * @return la liste des actions composant la tâche.
     */
    public ArrayList<Action> getTaches() {
        return taches;
    }

    /**
     * Renvoie la durée de la tâche.
     *
     * @return la durée de la tâche.
     */
    public int getDureeTache() {
        return dureeTache;
    }

    /**
     * Définit la durée de la tâche.
     *
     * @param dureeTache la durée de la tâche à définir.
     */
    public void setDureeTache(int dureeTache) {
        this.dureeTache = dureeTache;
    }

    /**
     * Renvoie une chaîne de caractères formatée représentant les informations de la tâche.
     *
     * @return une chaîne de caractères formatée représentant les informations de la tâche.
     */
    @JsonIgnore
    public String getInfoTacheFormater(){
        return  "\nNom : " + this.nom +
                "\nListe d'action : " + taches.stream()
                .map(action -> action.getInfoActionFormater())
                .collect(Collectors.joining("\n"));
    }
}
