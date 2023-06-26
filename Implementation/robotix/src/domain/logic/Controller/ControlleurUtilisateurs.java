package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.getListeUtilisateurs();
    private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie) {
        dataBaseController.getListeUtilisateurs().add(new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie));
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
        return utilisateur.enregistrerRobot(nomRobot, numeroSerie, listeFournisseurs);
    }

    public void afficherEtatRobot(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.afficherEtatRobot();
    }

    public void ajouterComposanteRobot(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.ajouterComposanteRobot();
    }

    public void creerAction(String pseudo){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerAction();
    }

    public void ajouterActionRobot(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.ajouterActionRobot();
    }

    public void afficherMetriquesFlotte(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.afficherMetriquesFlotte();
    }

    public void creerTache(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerTache();
    }

    public void allouerTacheRobot(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.allouerTacheRobot();
    }

    public void creerActivites(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.creerActivites();
    }

    public void rejoindreActivite(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.rejoindreActivite();
    }

    public void suivreUtilisateur(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.suivreUtilisateur();
    }

    public void gereSuiveurs(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gereSuiveurs();
    }

    public void gererInteret(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.gererInteret();
    }

    public void notifications(){
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);
        utilisateur.notifications();
    }
}
