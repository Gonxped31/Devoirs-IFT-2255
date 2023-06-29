package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
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
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie, listeInteret);
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

    public void enregistrerRobot(String nomRobot, String type, String numeroSerie) {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        if (!(robot == null)){
            robot.setNom(nomRobot);
            robot.setType(type);
            this.utilisateurCourant.enregistrerRobot(robot);
        }this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        //return this.utilisateurCourant.enregistrerRobot(dataBaseController.retournerRobot(numeroSerie, nomRobot, type));
    }

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        return this.utilisateurCourant.afficherEtatRobot();
    }

    public void ajouterComposanteRobot(String composante, String numeroSerie, String pseudo){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        Composant comp = this.dataBaseController.retournerComposante(composante);
        if (!(robot == null) && !(comp == null)){
            robot.ajouterComposante(comp);
        }
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);

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

    public void allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Robot robot = this.utilisateurCourant.getRobot(numeroDeSerie);
        Tache tac = this.utilisateurCourant.getTache(tache);
        if ((robot != null) && (tac != null)){
            robot.allouerTache(tac);
        }
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean creerActivites(String pseudo, String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.creerActivites(nomActivite, dateDebut, dateFin, listeTache);
    }

    public boolean rejoindreActivite(String pseudo, Activite activite){
        return !(dataBaseController.rejoindreActivite(pseudo, activite) == null) ? this.utilisateurCourant.rejoindreActivite(activite) : false;
    }

    public boolean suivreUtilisateur(String pseudo,String nom){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        Utilisateur suivi = Utilisateur.trouverUtilisateur(nom, listeUtilisateurs);
        //FIX move to utilsateur 
        if (utilisateur.getPseudo() == null || suivi.getPseudo() == null){
            return false;
        }else{
            utilisateur.suivreUtilisateur(suivi);
            //suivi.getNotifs().add(utilisateur.getPseudo() + " vous a suivi!");
            return true;
        }
    }

    public void gererSuiveurs(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererSuiveurs();
    }

    public void gererInteret(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererInteret();
    }

    public LinkedList<Notification> voirNotifications(String pseudo){
        return this.utilisateurCourant.voirNotifications();
    }

    public void supprimerNotifs(String pseudo) {
        this.utilisateurCourant.getNotifs().clear();
    }

    public void voirProfilUtilisateurCourant()
    {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }

    public LinkedList<Notification> notifier() {
        return this.utilisateurCourant.notifier();
    }
}
