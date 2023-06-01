package domain.logic.Fournisseur;

import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;

import java.util.List;

public class Fournisseur{
    String nom;
    String adresse;
    String email;
    String numeroTelephone;
    TypeRobot typeRobotFabriquer;
    double capacite;
    String nomcompagnie;
    List<Robot> inventaireDeRobot;

    private List<Composant> composants;
    public Fournisseur(String nom, String adresse, String email, String numeroTelephone,TypeRobot typeDeRobotFabriquer, double capacite){
        //TODO
    }
    public void vendreUnComposant(){
        //TODO
    }
}
