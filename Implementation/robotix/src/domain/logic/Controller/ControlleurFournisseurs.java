package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Robot.Composant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class ControlleurFournisseurs {
    private DbControleur dataBaseController = new DbControleur();
    private Fournisseur fournisseurCourant;

    public ControlleurFournisseurs(String nom, String mdp,  String adresse, String email, String numeroTelephone,
                                   String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie) throws IOException {
      this.fournisseurCourant= new Fournisseur(nom, mdp, adresse, email, numeroTelephone,typeDeRobotFabriquer,typeComposantesFabriquer,capacite,nomcompagnie);
    }
    public ControlleurFournisseurs() throws IOException {}

    public boolean authentificationFournisseur(String connexion, String membre){
        //TODO
        return true;
        //return Fournisseur.authentification(connexion, listeFournisseurs);
    }

    public void inscriptionFournisseur(String inputNom, String mdp, String inputAdresse, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                       String inputTypeComposantes, String inputCapacite, String inputCompagnie){

        dataBaseController.ajouterFournisseur(new Fournisseur(inputNom,mdp, inputAdresse, inputCourriel,
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
    public UUID ajouterRobot(ArrayList<ArrayList<String>> nomsCoposantAajouter){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        ArrayList<Composant> composants =this.fournisseurCourant.produireComposant(nomsCoposantAajouter);
       UUID uuid= this.fournisseurCourant.ajouterRobot(composants);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        //dataBaseController.ajouterRobot();
        return uuid;
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
