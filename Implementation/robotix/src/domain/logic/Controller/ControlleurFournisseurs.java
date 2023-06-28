package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.TypesComposants;

import java.util.ArrayList;
import java.util.LinkedList;

public class ControlleurFournisseurs {
    private DbControleur dataBaseController = new DbControleur();
    private Fournisseur fournisseurCourant;
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
    /*        */

    public void ajouterRobot(Fournisseur fournisseur){
        this.fournisseurCourant.ajouterRobot();
        dataBaseController.ajouterRobot();
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

    public void notifier(Fournisseur fournisseur) {
        fournisseur.notifier();
    }

    public void voirProfilFournisseur()
    {
        System.out.println(this.fournisseurCourant.getProfilFournisseur());
    }
}
