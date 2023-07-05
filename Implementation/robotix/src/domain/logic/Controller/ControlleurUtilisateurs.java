package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import domain.logic.Robot.TypesComposants;
import domain.logic.Robot.Action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.getListeUtilisateurs();
    private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private Utilisateur utilisateurCourant;

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie, listeInteret);
        dataBaseController.getListeUtilisateurs().add(utilisateurCourant);
    }

    public boolean authentification(String nom, String mdp, String type) {
        return dataBaseController.authentification(nom, mdp, type);
    }

    /* Code pour les vérifications */
    public boolean verifierPseudo(String pseudo) {
        return dataBaseController.verifierPseudoUtilisateur(pseudo);
    }

    public boolean verifierEmail(String inputEmail) {
        return Utilisateur.verifierEmailUtilisateur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return Utilisateur.verifierTelephoneUtilisateur(inputTelephone);
    }


    /* Actions utilisateur */

    public void modifierProfile(String pseudo, String choix, String info) {
        this.utilisateurCourant.modifierProfile(choix, info);
        dataBaseController.modifierProfile(pseudo);
    }

    public boolean enregistrerRobot(String pseudo, String nomRobot, String type, String numeroSerie) {
        return this.utilisateurCourant.enregistrerRobot(dataBaseController.retournerRobot(numeroSerie, nomRobot, type));
    }

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        return this.utilisateurCourant.afficherEtatRobot();
    }

    public boolean ajouterComposanteRobot(Composant composante, String numeroSerie, String pseudo){
        return !(dataBaseController.ajouterComposanteRobot(numeroSerie, composante, pseudo) == null) ? this.utilisateurCourant.ajouterComposanteRobot(composante, dataBaseController.retournerRobot(numeroSerie)):false;
    }

    public void creerAction(String pseudo, String nomAction, ArrayList<TypesComposants> composantes, String duree){
        dataBaseController.creerAction(nomAction, composantes);
        this.utilisateurCourant.creerAction(nomAction, composantes, duree);
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerAction(nomAction, composantes);
    }

    public int afficherMetriquesFlotte(String pseudo){
        return this.utilisateurCourant.nombreDeRobot();
    }

    public void creerTache(String pseudo, String nomTache, ArrayList<Action> actions){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerTache(nomTache, actions);
    }

    public boolean allouerTacheRobot(String pseudo, String robot, String tache){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.allouerTache(robot, tache);
    }

    public boolean creerActivites(String pseudo, String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.creerActivites(nomActivite, dateDebut, dateFin, listeTache);
    }

    public void rejoindreActivite(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.rejoindreActivite();
    }

    public boolean suivreUtilisateur(String pseudo,String nom){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        Utilisateur suivi = Utilisateur.trouverUtilisateur(nom, listeUtilisateurs);
        //FIX move to utilsateur 
        if (utilisateur.getPseudo() == null || suivi.getPseudo() == null){
            return false;
        }else{
            utilisateur.suivreUtilisateur(suivi);
            suivi.getNotifs().add(utilisateur.getPseudo() + " vous a suivi!");
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

    public ArrayList<String> voirNotifications(String pseudo){
        return this.utilisateurCourant.voirNotifications();
    }

    public void supprimerNotifs(String pseudo) {
        this.utilisateurCourant.getNotifs().clear();
    }

    public void voirProfilUtilisateurCourant()
    {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }
}
