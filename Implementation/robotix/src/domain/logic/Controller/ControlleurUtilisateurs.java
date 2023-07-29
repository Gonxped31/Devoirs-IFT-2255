package domain.logic.Controller;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.TypeNotification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

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
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, Utilisateur.produireListeInteret(listeInteret), new ArrayList<Notification>());
        dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    public boolean authentification(String pseudo, String mdp) {
        Utilisateur u = dataBaseController.authentificatiUtilisateur(pseudo, mdp);
        if (u == null){
            return false;
        }
        this.utilisateurCourant = u;
        return true;
    }

    /* Code pour les vérifications */
    public boolean verifierPseudo(String pseudo) {
        try {
            dataBaseController.verifierPseudo(pseudo);
        } catch (NullPointerException e){
            return true;
        }
        return false;
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



    public boolean enregistrerRobot(String nomRobot, String type, String numeroSerie, String pseudo) {
        boolean bool=false;
        Utilisateur u = this.dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        System.out.println(robot);
        if (robot != null){
            bool = u.enregistrerRobot(nomRobot, robot, type);
        }
        this.dataBaseController.ajouterUtilisateur(u);
        return bool;
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
        try {
            this.utilisateurCourant.creerAction(nomAction, composantes, duree);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        } catch (NullPointerException e){

        }
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
        HashSet<Interet> listeInter = this.utilisateurCourant.produireListeInteret(listeInteret);
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

    public String voirListeUtilisateur(String pseudo){
        return dataBaseController.retournerUtilisateur(pseudo).voirListeUtilisateur();
    }

    public boolean supprimerUtilisateurDeListeSuivi(String pseudo, String utilASupprimer){
        if (dataBaseController.retournerUtilisateur(pseudo).supprimerUtilisateurDeMaListe(utilASupprimer) == false){
            return false;
        }
        return true;
    }

    public boolean supprimerEtreSuivi(String pseudo, String pseudoUtilisateurASuivre){
        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            Utilisateur utilCourant = dataBaseController.retournerUtilisateur(pseudo);
            dataBaseController.supprimerUtilisateur(utilCourant);
            utilCourant.getListeUtilisateursSuivi().remove(u);
            dataBaseController.ajouterUtilisateur(utilCourant);
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }

    public boolean suppriemrSuivreUtilisateur(String pseudo, String pseudoUtilisateurASuivre){
        //Aller chercher utilisateur suivi et APPEND utilisateurCOurant
        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            dataBaseController.supprimerUtilisateur(u);
            u.getListSuiveur().remove(utilisateurCourant);
            dataBaseController.ajouterUtilisateur(u);
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }


    //TODO
    public boolean etreSuivi(String pseudo, String pseudoUtilisateurASuivre){

        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            Utilisateur utilCourant = dataBaseController.retournerUtilisateur(pseudo);
            dataBaseController.supprimerUtilisateur(utilCourant);
            utilCourant.getListeUtilisateursSuivi().add(u);
            dataBaseController.ajouterUtilisateur(utilCourant);
        } catch (NullPointerException e){
            return false;
        }

        return true;
    }

    public boolean suivreUtilisateur(String pseudo, String pseudoUtilisateurASuivre){
        //Aller chercher utilisateur suivi et APPEND utilisateurCOurant
        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            dataBaseController.supprimerUtilisateur(u);
            u.getListSuiveur().add(utilisateurCourant);
            dataBaseController.ajouterUtilisateur(u);
        } catch (NullPointerException e){
            return false;
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
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.voirNotifications();
    }

    public void ajouterNotifs(String nom, String titre, String message, TypeNotification typeNotif){
        Utilisateur u = dataBaseController.retournerUtilisateur(nom);
        ArrayList<Notification> notifsCourantes = u.getNotifs();
        this.dataBaseController.supprimerUtilisateur(u);
        u.addNotifs(titre, message,  typeNotif);
        u.setListeNotifications(notifsCourantes);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    public void supprimerNotifs(String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.getNotifs().clear();
        dataBaseController.ajouterUtilisateur(u);
    }

    public void voirProfilUtilisateurCourant()
    {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }
    /*
    public boolean[] notifier() {
        return this.utilisateurCourant.notifier();
    }*/
    public boolean souscrireAunInteret(String nomInteret){
        Interet i= this.dataBaseController.souscrireAunInteret(nomInteret);
        if( i==null)
        {
            return false;
        }

        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.ajouterUnInteret(i);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
    }

    public void ajouterRobot(String pseudo, String numeroSerie) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        Robot robot = dataBaseController.retournerRobot(numeroSerie);
        u.ajouterRobot(robot);
        dataBaseController.ajouterUtilisateur(u);
    }

    public boolean extraireInteretsUtilisateurs(String interet) {
        return this.dataBaseController.extraireInterets(interet);
    }

    public void abonnerInteret(String interet, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.getListeInteret().add(new Interet(interet));
        dataBaseController.ajouterUtilisateur(u);
    }

    public StringBuilder retournerInteretUtilisateur(String pseudo) {
        return dataBaseController.retournerListeInteretsUtilisateur(pseudo);
    }

    public void desabonnerInteret(String choix, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.desabonnerInteret(choix);
        dataBaseController.ajouterUtilisateur(u);
    }

    public boolean existeDansListeSuivi(String pseudo, String nom) {
        return dataBaseController.existeDansListeSuivi(pseudo, nom);
    }
}
