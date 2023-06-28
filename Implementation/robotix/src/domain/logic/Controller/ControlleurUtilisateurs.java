package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import domain.logic.Robot.Action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.getListeUtilisateurs();
    private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        dataBaseController.getListeUtilisateurs().add(new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie, listeInteret));
    }

    public boolean authentification(String connexion, String membre) {
        return Utilisateur.authentification(connexion, listeUtilisateurs);
    }

    /* Code pour les vérifications */
    public boolean verifierPseudo(String pseudo) {
        return Utilisateur.verifierPseudoUtilisateur(pseudo, listeUtilisateurs);
    }

    public boolean verifierEmail(String inputEmail) {
        return Utilisateur.verifierEmailUtilisateur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return Utilisateur.verifierTelephoneUtilisateur(inputTelephone);
    }


    /* Actions utilisateur */

    public void modifierProfile(String pseudo, String choix, String info) {
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.modifierProfile(choix, info, utilisateur);
    }

    public boolean enregistrerRobot(String pseudo, String nomRobot, String numeroSerie) {
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.enregistrerRobot(nomRobot, numeroSerie, utilisateur, listeFournisseurs);
    }

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.afficherEtatRobot(utilisateur);
    }

    public boolean ajouterComposanteRobot(String nomComposante, String nomRobot, String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.ajouterComposanteRobot(nomComposante, nomRobot);
    }

    public void creerAction(String pseudo, String nomAction, ArrayList<Composant> composantes){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerAction(nomAction, composantes);
    }

    public int afficherMetriquesFlotte(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.nombreDeRobot();
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
        if (utilisateur.getPseudo() == null || suivi.getPseudo() == null){
            return false;
        }else{
            utilisateur.suivreUtilisateur(suivi);
            suivi.getNotifs().add(utilisateur.getPseudo() + " vous a suivi!");
            return true;
        }
    }

    public void gereSuiveurs(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gereSuiveurs();
    }

    public void gererInteret(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererInteret();
    }

    public ArrayList<String> voirNotifications(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        return utilisateur.voirNotifications();
    }
}
