package domain.logic.Membre;
import java.util.*;

import domain.logic.Robot.Robot;

//Faire import domain.logic.Fournisseur.Fournisseur;


public class Utilisateur extends Membre{
    private LinkedList<String> uuids = new LinkedList<>();
    private ArrayList<Robot> listeRobot = new ArrayList<>();
    private LinkedList<String> taches = new LinkedList<>();
    private ArrayList<String> actions = new ArrayList<>(); 
    private LinkedList<String> composantesAchetes = new LinkedList<>();
    private Set<Utilisateur> listeUtilisateursSuivi = new HashSet<>();
    private List<Interet> listeInteret = new ArrayList<>();
    private Set<Utilisateur> listSuiveur = new HashSet<>();
    private String pseudo;
    private String prenom;
    private int point;



    public Utilisateur(String nom, String prenom, String adresse, String pseudo, String email, String numeroTelephone, String nomCompagnie){
        super(nom, adresse, email, numeroTelephone, nomCompagnie);
        this.pseudo = pseudo;
        this.prenom = prenom;
    }

    public String getPseudo(){
        return pseudo;
    }
    public int getPoint() {
        return point;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNumeroTelephone(String numeroTelephone){
        this.numeroTelephone = numeroTelephone;
    }

    public void setNomCompagnie(String nomCompagnie){
        this.nomCompagnie = nomCompagnie;
    }

    public static boolean authentification(String connexion, ArrayList<Utilisateur> listeUtilisateurs) {
        boolean authentification = false;
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getPseudo().equals(connexion)) {
                authentification = true;
                break;
            }
        }
        return authentification;
    }

    public static boolean verifierPseudoUtilisateur(String pseudo, ArrayList<Utilisateur> listeUtilisateurs) {
        boolean bool = false;
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (!utilisateur.getPseudo().equals(pseudo)) {
                bool = true;
                break;
            }
            break;
        }
        return bool;
    }

    public static boolean verifierEmailUtilisateur(String inputEmail) {
        return inputEmail.contains("@");
    }

    public static boolean verifierTelephoneUtilisateur(String inputTelephone) {
        return  inputTelephone.length() == 10;
    }

    public static Utilisateur trouverUtilisateur(String pseudo, ArrayList<Utilisateur> listeUtilisateurs){
        Utilisateur utilisateur = new Utilisateur(null, null, null, null, null, null, null);
        for (Utilisateur utilisateurs : listeUtilisateurs) {
            if (utilisateurs.getPseudo().equals(pseudo)) {
                utilisateur = utilisateurs;
                break;
            }
        }
        return utilisateur;
    }

    public void modifierProfile(String choix, String nouvelInfo, Utilisateur utilisateur){
        switch (choix.toLowerCase()) {
            case "nom" :
                utilisateur.setNom(nouvelInfo);
            case "prenom" :
                utilisateur.setPrenom(nouvelInfo);
            case "adresse" :
                utilisateur.setAdresse(nouvelInfo);
            case "pseudo" :
                utilisateur.setPseudo(nouvelInfo);
            case "email" :
                utilisateur.setEmail(nouvelInfo);
            case "numerotelephone" :
                utilisateur.setNumeroTelephone(nouvelInfo);
            case "nomcompagnie" :
                utilisateur.setNomCompagnie(nouvelInfo);
        }

    }

    public boolean enregistrerRobot(String nomRobot,String numeroSerie, ArrayList<Fournisseur> listeFournisseur){
        boolean bool = false;
        Robot robot = verifierRobot(numeroSerie, listeFournisseur);
        if (robot != null){
            robot.setNom(nomRobot);
            listeRobot.add(robot);
            bool = true;
        }
        return bool;
    }

    public Robot verifierRobot(String numeroSerie, ArrayList<Fournisseur> listeFournisseur){
        Robot robot = null;
        int nbRobot = 0;
        for (Fournisseur fournisseur: listeFournisseur) {
            for (Robot robot1 :  fournisseur.getInventaireDeRobot()) {
                if (robot1.getNumeroSerie().toString().equals(numeroSerie)){
                    robot = robot1;
                    fournisseur.getInventaireDeRobot().remove(robot1);
                    break;
                }
                nbRobot++;
            }
            if (nbRobot < fournisseur.getInventaireDeRobot().size()){
                break;
            }
        }
        return robot;
    }

    public void creerAction(String pseudo, ){

    }
}