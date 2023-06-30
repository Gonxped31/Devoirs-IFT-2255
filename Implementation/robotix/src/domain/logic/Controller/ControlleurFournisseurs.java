package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Notification;
import domain.logic.Robot.Robot;

import java.io.IOException;
import java.util.LinkedList;

public class ControlleurFournisseurs {
    private DbControleur dataBaseController = new DbControleur();
    private Fournisseur fournisseurCourant;
    public ControlleurFournisseurs() throws IOException {
    }

    public boolean authentificationFournisseur(String connexion, String membre){
        //TODO
        return true;
        //return Fournisseur.authentification(connexion, listeFournisseurs);
    }

    public void inscriptionFournisseur(String inputNom, String inputAdresse, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                       String inputTypeComposantes, String inputCapacite, String inputCompagnie){

        dataBaseController.ajouterFournisseur(new Fournisseur(inputNom, inputAdresse, inputCourriel,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie));
    }

    /* Code pour les vérifications */
    public boolean verifierNom(String inputNom) {
        return dataBaseController.verifierNomFournissuer(inputNom);
    }
    public boolean verifierEmail(String inputEmail) {
        return Fournisseur.verifierEmailFournisseur(inputEmail);
    }
    public boolean verifierTelephone(String inputTelephone) {
        return Fournisseur.verifierTelephoneFournisseur(inputTelephone);
    }

    public void ajouterRobot(){
        //this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.ajouterRobot();
        //this.dataBaseController.ajouterFournisseur(fournisseurCourant);

        //dataBaseController.ajouterRobot();
    }

    public boolean retirerRobot(String nomRobot) {
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean resultat = this.fournisseurCourant.retirerRobot(nomRobot);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return resultat;
    }

    public void ajouterComposante(String composante, double prix, String description, String typesComposants, String nomFournisseur){
        //fournisseurCourant.ajouterComposante(composante, prix, description, typesComposants);
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.ajouterComposante();
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        //dataBaseController.ajouterComposanteFournisseur(composante, prix, description, typesComposants, nomFournisseur);
    }

    public boolean retirerComposante(String composante, String nomFournisseur){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean resultat = this.fournisseurCourant.retirerComopsante(composante);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return resultat;
        //return fournisseur.retirerComopsante(composante);
    }

    public boolean modifierPrixComposante(String composante, Double nouveauPrix){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean resultat = this.fournisseurCourant.modifierPrixComposante(composante, nouveauPrix);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return resultat;
    }

    public boolean modifierDescriptionComposante(String composante, String nouvelleDescription){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean resultat = this.fournisseurCourant.modifierDescriptionComposante(composante, nouvelleDescription);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return resultat;
    }

    public void modifierProfile(String choix, String info){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.modifierProfile(choix, info);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
    }

    public LinkedList<Notification> notifier() {
        return this.fournisseurCourant.notifier();
    }

    public void voirProfilFournisseur(String nomFournisseur) {
        System.out.println(this.fournisseurCourant.getProfilFournisseur());
    }

    public String voirProfil() {
        return fournisseurCourant.getProfilFournisseur();
    }
}
