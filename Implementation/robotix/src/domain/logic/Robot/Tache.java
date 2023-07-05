package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tache implements java.io.Serializable{
    private String nom;
    private ArrayList<Action> actions;

    public Tache(String nom, ArrayList<Action> actions){
        this.nom = nom;
        this.actions = actions;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Action> getTaches() {
        return actions;
    }

    @JsonIgnore
    public String getInfoTacheFormater(){
        return  "Nom : " + this.nom +
                "Liste d'action : " + actions.stream()
                .map(action -> action.getInfoActionFormater())
                .collect(Collectors.joining("\n"));
    }
}
