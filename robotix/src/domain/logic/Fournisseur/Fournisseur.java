package domain.logic.Fournisseur;

import ClasseUtilisateur.Robot;
import domain.logic.Robot.TypeRobot;

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

    public void menu(scanner){
        System.out.println("1- Mettre a jour son profil");
        System.out.println("1- Ajouter un robot");
        System.out.println("2- Ajouter une composante");
        System.out.println("3- Retirer un robot");
        System.out.println("4- Retirer une composante");
        String decision = scanner.nextLine(); 
        switch (decision){
            case ("1"):
                Robot r = new Robot();
                mettreInventaireRobotAjour();
            }

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
