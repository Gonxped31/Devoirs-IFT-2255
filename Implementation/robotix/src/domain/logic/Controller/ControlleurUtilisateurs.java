package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DbControleur dataBaseController = new DbControleur();
    //private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.recupererListeUtilisateur();
    //private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private Utilisateur utilisateurCourant;

    public ControlleurUtilisateurs() throws IOException {
    }

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie,this.utilisateurCourant.produireListeInteret(listeInteret));
        dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean authentification(String nom, String mdp, String type) {
        return true;
        //return dataBaseController.authentification(nom, mdp, type);
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

    public void modifierProfile(String pseudo, String choix, String info) {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.modifierProfile(choix, info);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean enregistrerRobot(String nomRobot, String type, String numeroSerie) {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        if (!(robot == null)){
            robot.setNom(nomRobot);
            robot.setType(type);
            this.utilisateurCourant.enregistrerRobot(robot);
        }this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
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
        this.utilisateurCourant.creerTache(nomTache, actions);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        boolean b = false;
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.utilisateurCourant.getRobot(numeroDeSerie);
        Tache tac = this.utilisateurCourant.getTache(tache);
        if ((robot != null) && (tac != null)){
            robot.allouerTache(tac);
            b = true;
        }
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return b;
    }

    public boolean creerActivites(String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache, ArrayList<String> listeInteret) throws ParseException {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        ArrayList<Tache> listeTac = this.utilisateurCourant.getTacheEnListe(listeTache);
        ArrayList<Interet> listeInter = this.utilisateurCourant.produireListeInteret(listeInteret);
        this.utilisateurCourant.creerActivite(nomActivite, dateDebut, dateFin, listeTac, listeInter);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
    }

    public void rejoindreActivite(String pseudo, Activite activite){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.rejoindreActivite(activite);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean suivreUtilisateur(String pseudoUtilisateurASuivre){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Utilisateur aSuivre = this.dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
        this.dataBaseController.supprimerUtilisateur(aSuivre);
        aSuivre.etreSuivi(utilisateurCourant);
        this.utilisateurCourant.suivreUtilisateur(aSuivre);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        this.dataBaseController.ajouterUtilisateur(aSuivre);
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
}
