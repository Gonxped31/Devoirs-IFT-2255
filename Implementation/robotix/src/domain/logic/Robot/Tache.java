package domain.logic.Robot;

import java.util.ArrayList;

public class Tache {
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
}
