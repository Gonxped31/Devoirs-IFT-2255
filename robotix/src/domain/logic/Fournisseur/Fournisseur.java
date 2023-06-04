package domain.logic.Fournisseur;

import domain.logic.Robot.Robot;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur{
    public String nom;
    public String adresse;
    public String email;
    public String numeroTelephone;
    public String typeRobotFabriquer;
    public String capacite;
    public String nomCompagnie;
    public String pseudo;
    public List<Robot> inventaireDeRobot=new ArrayList<>();
    public List<String> inventaireComposant= new ArrayList<>();

    public Fournisseur(String nom, String adresse, String pseudo, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String capacite ,String nomcompagnie){
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
