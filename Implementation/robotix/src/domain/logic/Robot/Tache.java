package domain.logic.Robot;

import java.util.ArrayList;

public class Tache {
    private String nom;
    private ArrayList<Action> taches;

    public Tache(String nom, ArrayList<Action> taches){
        this.nom = nom;
        this.taches = taches;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Action> getTaches() {
        return taches;
    }
}
