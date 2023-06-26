package domain.logic.Controller;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateurs;
import domain.logic.Menu.Menu;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class ControlleurUtilisateurs {

    private DataBaseController dataBaseController = new DataBaseController();
    private ArrayList<Utilisateurs> listeUtilisateurs = dataBaseController.getListeUtilisateurs();
    private final Utilisateurs util = new Utilisateurs(null, null, null, null, null, null, null);

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo, String courriel, String telephone, String nomCompagnie){
        dataBaseController.getListeUtilisateurs().add(new Utilisateurs(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie));
    }

    public boolean authentification(String connexion, String membre){
        return util.authentification(connexion, listeUtilisateurs);
    }

    /* Code pour les vérifications */
    public String verifierNom(String inputNom) {
        return util.verifierPseudoUtilisateur(inputNom, listeUtilisateurs);
    }

    public boolean verifierEmail(String inputEmail) {
        return util.verifierEmailUtilisateur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return util.verifierTelephoneUtilisateur(inputTelephone);
    }

    public Utilisateurs trouverUtilisateur(String pseudo){
        Utilisateurs utilisateur = new Utilisateurs(null, null, null, null, null, null, null);
        for (Utilisateurs utilisateurs : dataBaseController.getListeUtilisateurs()) {
            if (utilisateurs.getPseudo().equals(pseudo)) {
                utilisateur = utilisateurs;
                break;
            }
        }
        return utilisateur;
    }

    public void actionsUtilisateurs(Scanner scanner, int action, String pseudo){
        // TROUVER UN MOYEN DE TROUVER UN UTILISATEUR JSUTE UNE FOIS.
        Utilisateurs utilisateur = trouverUtilisateur(pseudo);

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
