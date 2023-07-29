package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tache implements java.io.Serializable{
    private String nom;
    private ArrayList<Action> taches;
    private int dureeTache;

    @JsonCreator
    public Tache(
            @JsonProperty("nom") String nom,
            @JsonProperty("taches") ArrayList<Action> taches){
        this.nom = nom;
        this.taches = taches;
    }

    //Getters
    public String getNom() {
        return nom;
    }
    public ArrayList<Action> getTaches() {
        return taches;
    }
    public int getDureeTache() {
        return dureeTache;
    }

    //Setters
    public void setDureeTache(int dureeTache) {
        this.dureeTache = dureeTache;
    }

    @JsonIgnore
    public String getInfoTacheFormater(){
        return  "\nNom : " + this.nom +
                "\nListe d'action : " + taches.stream()
                .map(action -> action.getInfoActionFormater())
                .collect(Collectors.joining("\n"));
    }
}
