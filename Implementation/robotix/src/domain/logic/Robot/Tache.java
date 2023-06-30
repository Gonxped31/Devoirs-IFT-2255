package domain.logic.Robot;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tache {
    private String nom;
    private ArrayList<Action> actions;

    public Tache(String nom, ArrayList<Action> actions){
        this.nom = nom;
        this.actions = actions;
    }

    public Tache(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Action> getTaches() {
        return actions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public String getInfoTacheFormater(){
        return  "Nom : " + this.nom +
                "Liste d'action : " + actions.stream()
                .map(action -> action.getInfoActionFormater())
                .collect(Collectors.joining("\n"));
    }
}
