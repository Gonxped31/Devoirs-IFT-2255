package domain.logic.Fournisseur;

import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;

import java.util.List;

public class Fournisseur{
    private String nom;
    private String adresse;
    private String email;
    private String numeroTelephone;
    private TypeRobot typeRobotFabriquer;
    private double capacite;
    private String nomcompagnie;
    private List<Robot> inventaireDeRobot;

    private List<Composant> composants;
    public Fournisseur(String nom, String adresse, String email, String numeroTelephone,TypeRobot typeDeRobotFabriquer, double capacite){
        //TODO
    }
    public void vendreUnComposant(){
        //TODO
    }
}
