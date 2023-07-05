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

    public boolean authentificationFournisseur(String nomFournisseur, String mdp){
        Fournisseur f= this.dataBaseController.authentificatiFournisseur(nomFournisseur,mdp);
        this.fournisseurCourant= f;
        return f != null;
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

    public void ajouterComposante(String nom, String prix, String description, String typesComposants){
        //fournisseurCourant.ajouterComposante(composante, prix, description, typesComposants);
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.ajouterComposante(nom, prix,description,typesComposants);
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

    public boolean modifierPrixComposante(String nomComposante, String prix){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean bool = this.fournisseurCourant.modifierPrixComposante(nomComposante, prix);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return bool;
    }

    public boolean modifierDescriptionComposante(String nomComposante, String description){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean bool = this.fournisseurCourant.modifierDescriptionComposante(nomComposante, description);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return bool;
    }

    public void modifierProfile(String choix, String info){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.modifierProfile(choix, info);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
    }

    public String voirProfil() {
        return fournisseurCourant.getProfilFournisseur();
    }


}
