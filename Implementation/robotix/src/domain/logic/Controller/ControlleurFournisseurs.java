package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateurs;
import domain.logic.Robot.Robot;

import java.util.ArrayList;
import java.util.LinkedList;

public class ControlleurFournisseurs {
    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private final Fournisseur fournis = new Fournisseur(null, null, null, null, null, null, null, null);

    public ArrayList<Fournisseur> getListeFournisseurs() {
        return listeFournisseurs;
    }

    public boolean authentificationFournisseur(String connexion, String membre){
        return fournis.authentification(connexion, listeFournisseurs);
    }

    public void inscriptionFournisseur(String inputNom, String inputAdresse, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                       String inputTypeComposantes, String inputCapacite, String inputCompagnie){

        dataBaseController.ajouterFournisseur(new Fournisseur(inputNom, inputAdresse, inputCourriel,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie));
    }


    /* Code pour les vérifications */
    public String verifierNom(String inputNom) {
        return fournis.verifierNomFournisseur(inputNom, listeFournisseurs);
    }

    public boolean verifierEmail(String inputEmail) {
        return fournis.verifierEmailFournisseur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return fournis.verifierTelephoneFournisseur(inputTelephone);
    }

    public ArrayList<Fournisseur> trouverFournisseur(String info){
        return fournis.trouverFournisseur(info, listeFournisseurs);
    }

    public Robot trouverRobot(String nom, Fournisseur fournisseur){
        Robot robots = new Robot(null, 0, 0, 0, 0, 0, 0, null, null, null, null, null);
        for (Robot robot : fournisseur.getInventaireDeRobot()) {
            if (robot.getNom().equals(nom)) {
                robots = robot;
                break;
            }
        }
        return robots;
    }

    public void ajouterRobot(Fournisseur fournisseur, String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<String> composantes,
                             LinkedList<String> action, LinkedList<String> taches, LinkedList<String> activites, String numeroSerie){
        Robot robot = new Robot(nom, X, Y, vitesse, batterie, cpu, memoire, composantes, action, taches, activites, numeroSerie);
        fournisseur.ajouterRobot(robot, fournisseur);
    }

    public void retirerRobot(String nomRobot, Fournisseur fournisseur) {
        fournisseur.retirerRobot(nomRobot, fournisseur);
    }

    public void ajouterComposante(String composante, Fournisseur fournisseur){
        fournisseur.ajouterComposante(composante, fournisseur);
    }

    public void retirerComposante(String composante, Fournisseur fournisseur){
        fournisseur.retirerComopsante(composante, fournisseur);
    }

}
