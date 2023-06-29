package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;

import java.io.IOException;

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
    /*        */

    public void ajouterRobot(){
        this.fournisseurCourant.ajouterRobot();
        //dataBaseController.ajouterRobot();
    }

    public boolean retirerRobot(String numeroSerie) {
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean b = this.fournisseurCourant.retirerRobot(numeroSerie);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return b;
    }

    public void ajouterComposante(String composante, double prix, String description, String typesComposants, String nomFournisseur){
        //fournisseurCourant.ajouterComposante(composante, prix, description, typesComposants);
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.ajouterComposante();
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);

        //dataBaseController.ajouterComposanteFournisseur(composante, prix, description, typesComposants, nomFournisseur);
    }

    public boolean retirerComposante(String composante){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean c = this.fournisseurCourant.retirerComopsante(composante);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return c;
        //return fournisseur.retirerComopsante(composante);
    }

    public boolean[] notifier() {
        return fournisseurCourant.notifier();
    }

    public void voirProfilFournisseur()
    {
        System.out.println(this.fournisseurCourant.getProfilFournisseur());
    }

    public String voirProfil() {
        return fournisseurCourant.getProfilFournisseur();
    }
}
