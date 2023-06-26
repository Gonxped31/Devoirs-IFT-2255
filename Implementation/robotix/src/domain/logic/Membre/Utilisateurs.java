package domain.logic.Membre;
import java.io.IOException;
import java.util.*;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

//Faire import domain.logic.Fournisseur.Fournisseur;


public class Utilisateurs extends Membre{
    private LinkedList<String> uuids = new LinkedList<>();
    private ArrayList<Robot> listeRobot = new ArrayList<>();
    private LinkedList<String> taches = new LinkedList<>();
    private LinkedList<String> composantesAchetes = new LinkedList<>();
    private Set<Utilisateurs> listeUtilisateursSuivi = new HashSet<>();
    private List<Interet> listeInteret = new ArrayList<>();
    private Set<Utilisateurs> listSuiveur = new HashSet<>();
    private String pseudo;
    private int point;



    public Utilisateurs(String nom, String prenom, String adresse, String pseudo, String email, String numeroTelephone, String nomCompagnie){
        super(nom, adresse, email, numeroTelephone, nomCompagnie);
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }
    public int getPoint() {
        return point;
    }

    public boolean authentification(String connexion, ArrayList<Utilisateurs> listeUtilisateurs) {
        boolean authentification = false;
        for (Utilisateurs utilisateur : listeUtilisateurs) {
            if (utilisateur.getPseudo().equals(connexion)) {
                authentification = true;
                break;
            }
        }
        return authentification;
    }

    public String verifierPseudoUtilisateur(String pseudo, ArrayList<Utilisateurs> listeFournisseurs) {
        String message = "";
        for (Utilisateurs utilisateur : listeFournisseurs) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                message = "Ce pseudo existe déjà. Veuillez saisir un autre pseudo: ";
                break;
            }
        }
        return message;
    }

    public boolean verifierEmailUtilisateur(String inputEmail) {
        return inputEmail.contains("@");

    }

    public boolean verifierTelephoneUtilisateur(String inputTelephone) {
        return  inputTelephone.length() == 10;
    }
    
}