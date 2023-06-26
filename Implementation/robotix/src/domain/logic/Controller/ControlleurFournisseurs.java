package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Robot.Composant;

import java.util.ArrayList;
import java.util.LinkedList;

public class ControlleurFournisseurs {
    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    public ArrayList<Fournisseur> getListeFournisseurs() {
        return listeFournisseurs;
    }

    public boolean authentificationFournisseur(String connexion, String membre){
        return Fournisseur.authentification(connexion, listeFournisseurs);
    }

    public void inscriptionFournisseur(String inputNom, String inputAdresse, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                       String inputTypeComposantes, String inputCapacite, String inputCompagnie){

        dataBaseController.ajouterFournisseur(new Fournisseur(inputNom, inputAdresse, inputCourriel,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie));
    }


    /* Code pour les vérifications */
    public boolean verifierNom(String inputNom) {
        return Fournisseur.verifierNomFournisseur(inputNom, listeFournisseurs);
    }

    public boolean verifierEmail(String inputEmail) {
        return Fournisseur.verifierEmailFournisseur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return Fournisseur.verifierTelephoneFournisseur(inputTelephone);
    }

    public ArrayList<Fournisseur> trouverFournisseur(String info){
        return Fournisseur.trouverFournisseur(info, listeFournisseurs);
    }

    public void ajouterRobot(Fournisseur fournisseur, String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<Composant> composantes,
                             LinkedList<String> action, LinkedList<String> taches, LinkedList<String> activites, String numeroSerie){
        fournisseur.ajouterRobot(nom, X, Y, vitesse, batterie, cpu, memoire, composantes, action, taches, activites, numeroSerie);
    }

    public boolean retirerRobot(String nomRobot, Fournisseur fournisseur) {
        return fournisseur.retirerRobot(nomRobot);
    }

    public void ajouterComposante(String composante, double prix, String description, String typesComposants, Fournisseur fournisseur){
        fournisseur.ajouterComposante(composante, prix, description, typesComposants);
    }

    public boolean retirerComposante(String composante, Fournisseur fournisseur){
        return fournisseur.retirerComopsante(composante);
    }

}
