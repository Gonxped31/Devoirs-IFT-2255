package domain.logic.Controller;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ControlleurUtilisateurs {

    private DbControleur dataBaseController = new DbControleur();
    //private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.recupererListeUtilisateur();
    //private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private Utilisateur utilisateurCourant;

    public ControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo, String mdp, String email, String numeroTelephone, String nomCompagnie, ArrayList<String> listeInteret) throws IOException {
        this.inscriptionUtilisateur(nom, prenom, adresse, pseudo, mdp, email, numeroTelephone, nomCompagnie, listeInteret);
    }

    public ControlleurUtilisateurs() throws IOException {

    }

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo,String mdp, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, Utilisateur.produireListeInteret(listeInteret));
        dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean authentification(String pseudo, String mdp) {
        Utilisateur u = dataBaseController.authentificatiUtilisateur(pseudo, mdp);
        if (u.equals(null)){
            return false;
        }
        this.utilisateurCourant = u;
        return true;
    }

    /* Code pour les vérifications */
    public boolean verifierPseudo(String pseudo) {
        return dataBaseController.verifierPseudo(pseudo);
    }

    public boolean verifierEmail(String inputEmail) {
        return Utilisateur.verifierEmailUtilisateur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return Utilisateur.verifierTelephoneUtilisateur(inputTelephone);
    }


    /* Actions utilisateur */

    public void modifierProfile(String choix, String info) {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.modifierProfile(choix, info);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }



    public boolean enregistrerRobot(String nomRobot, String type, String numeroSerie) {
        boolean bool=false;
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        if (!(robot == null)){
            robot.setNom(nomRobot);
            robot.setType(type);
            try {
                bool = this.utilisateurCourant.enregistrerRobot(robot);
            } catch (NullPointerException e){
                return true;
            }
        }this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return bool;
        //return this.utilisateurCourant.enregistrerRobot(dataBaseController.retournerRobot(numeroSerie, nomRobot, type));
    }

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        return this.utilisateurCourant.afficherEtatRobot();
    }

    public boolean ajouterComposanteRobot(String composante, String numeroSerie, String pseudo){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        Composant comp = this.dataBaseController.retournerComposante(composante);
        if (!(robot == null) && !(comp == null)){
            robot.ajouterComposante(comp);
        }
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
        //return !(dataBaseController.ajouterComposanteRobot(numeroSerie, composante, pseudo) == null) ? this.utilisateurCourant.ajouterComposanteRobot(composante, dataBaseController.retournerRobot(numeroSerie)):false;
    }

    public void creerAction(String nomAction, ArrayList<String> composantes, String duree){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.creerAction(nomAction, composantes, duree);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public int afficherMetriquesFlotte(String pseudo){
        return this.utilisateurCourant.nombreDeRobot();
    }

    public void creerTache(String nomTache, ArrayList<Action> actions){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);

        try{
            this.utilisateurCourant.creerTache(nomTache, actions);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        } catch (NullPointerException e){

        }
    }

    public boolean allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        boolean b = false;
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        try {
        Robot robot = this.utilisateurCourant.getRobot(numeroDeSerie);
        Tache tac = this.utilisateurCourant.getTache(tache);
        if ((robot != null) && (tac != null)){
            robot.allouerTache(tac);
            b = true;
        }
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return b;}catch (NullPointerException e){
            return true;
        }
        //return b;
    }

    public boolean creerActivites(String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache, ArrayList<String> listeInteret) throws ParseException {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        try {
        ArrayList<Tache> listeTac = this.utilisateurCourant.getTacheEnListe(listeTache);
        ArrayList<Interet> listeInter = this.utilisateurCourant.produireListeInteret(listeInteret);
        this.utilisateurCourant.creerActivite(nomActivite, dateDebut, dateFin, listeTac, listeInter);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;} catch (NullPointerException e){
            return true;
        }
    }

    public void rejoindreActivite(String pseudo, Activite activite){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        try {
            this.utilisateurCourant.rejoindreActivite(activite);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        } catch (NullPointerException e){

        }
    }

    public boolean suivreUtilisateur(String pseudoUtilisateurASuivre){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Utilisateur aSuivre = this.dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
        this.dataBaseController.supprimerUtilisateur(aSuivre);
        try {
            aSuivre.etreSuivi(utilisateurCourant);
            this.utilisateurCourant.suivreUtilisateur(aSuivre);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
            this.dataBaseController.ajouterUtilisateur(aSuivre);
        } catch (NullPointerException e) {
            return true;
        }
        return true;
    }

    //TODO
    /*
    public void gererSuiveurs(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererSuiveurs();
    }

    public void gererInteret(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererInteret();
    }*/

    public ArrayList<Notification> voirNotifications(String pseudo){
        return this.utilisateurCourant.voirNotifications();
    }

    public void supprimerNotifs(String pseudo) {
        this.utilisateurCourant.getNotifs().clear();
    }

    public void voirProfilUtilisateurCourant()
    {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }

    public boolean[] notifier() {
        return this.utilisateurCourant.notifier();
    }



    public  String recupererListeInteret(){
       return this.dataBaseController.recupererListeInteret();
    }
    public boolean souscrireAunInteret(String nomInteret) {
        boolean check = false;
        Interet i = this.dataBaseController.souscrireAunInteret(nomInteret);
        if (i == null) {
            return check;
        }

        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.ajouterUnInteret(i);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
    }


}