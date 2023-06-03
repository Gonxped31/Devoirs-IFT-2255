package domain.logic.Fournisseur;

import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur{
    String nom;
    String adresse;
    String email;
    String numeroTelephone;
    TypeRobot typeRobotFabriquer;
    double capacite;
    String nomCompagnie;
    String pseudo;
    List<Robot> inventaireDeRobot=new ArrayList<>();
    List<String> inventaireComposant= new ArrayList<>();

    public Fournisseur(String nom, String adresse, String pseudo, String email, String numeroTelephone,
                       TypeRobot typeDeRobotFabriquer, double capacite ,String nomcompagnie){
        this.nom=nom;
        this.adresse=adresse;
        this.pseudo=pseudo;
        this.email=email;
        this.numeroTelephone=numeroTelephone;
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.capacite=capacite;
        this.nomCompagnie=nomcompagnie;
    }
    public void vendreUnComposant(String composant){
      inventaireComposant.remove(composant);
    }
    public void mettreInventaireRobotAjour(Robot robot, boolean modeAjout){
        if (modeAjout) {
            inventaireDeRobot.add(robot);
        } else {
            inventaireDeRobot.remove(robot);
        }
    }

    public void mettreInventaireComposantAjour( String composant, boolean modeAjout){
        if (modeAjout) {
            inventaireComposant.add(composant);
        } else {
            inventaireComposant.remove(composant);
        }
    }
}
