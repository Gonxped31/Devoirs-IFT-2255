package domain.logic.Controller;
import domain.logic.Membre.Utilisateur;

import java.util.ArrayList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.getListeUtilisateurs();

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie){
        dataBaseController.getListeUtilisateurs().add(new Utilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie));
    }

    public boolean authentification(String connexion, String membre){
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

    public void actionsUtilisateurs(Scanner scanner, int action, String pseudo){
        // TROUVER UN MOYEN DE TROUVER UN UTILISATEUR JSUTE UNE FOIS.
        Utilisateur utilisateur = Utilisateur.trouverUtilisateur(pseudo, listeUtilisateurs);

        switch (action) {
            case 1 :
                utilisateur.modifierProfile();
                break;
            case 2 :
                utilisateur.enregistrerRobot();
                break;
            case 3 :
                utilisateur.afficherEtatRobot();
                break;
            case 4 :
                utilisateur.ajouterComposanteRobot();
                break;
            case 5 :
                utilisateur.creerAction();
                break;
            case 6 :
                utilisateur.ajouterActionRobot();
                break;
            case 7 :
                utilisateur.afficherMetriquesFlotte();
                break;
            case 8 :
                utilisateur.creerTache();
                break;
            case 9 :
                utilisateur.allouerTacheRobot();
                break;
            case 10 :
                utilisateur.creerActivites();
                break;
            case 11 :
                utilisateur.rejoindreActivite();
                break;
            case 12 :
                utilisateur.suivreUtilisateur();
                break;
            case 13 :
                utilisateur.gereSuiveurs();
                break;
            case 14 :
                utilisateur.gererInteret();
                break;
            case 15 :
                utilisateur.notifications();
    }

}

}
