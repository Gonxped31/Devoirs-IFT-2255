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
                // utilisateur.enregistrerRobot(scanner);
                break;
            case 2 :
                // utilisateur.afficherEtatRobot(scanner);
                break;
            case 3 :
                // utilisateur.ajouterComposantes(scanner);
                break;
            case 4 :
                // utilisateur.ajouterAction(scanner);
                break;
            case 5 :
                // utilisateur.creerTaches(scanner);
                break;
            case 6 :
                // utilisateur.allouerTachesRobot(scanner);
                break;
            case 7 :
                // utilisateur.afficherMetriquesFlotte(scanner);
                break;
            case 8 :
                // utilisateur.participerActivites(scanner);
                break;
            case 9 :
                // utilisateur.voirActivitesMaintenues(scanner);
                break;
            case 10 :
                // utilisateur.gestionDesProblemes(scanner);
                break;
            case 11 :
                // utilisateur.trouverFournisseurs(scanner);
                break;
        }
    }

}
