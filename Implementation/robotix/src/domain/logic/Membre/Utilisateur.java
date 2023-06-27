package domain.logic.Membre;
import java.util.*;

import domain.logic.Robot.*;


//Faire import domain.logic.Fournisseur.Fournisseur;


public class Utilisateur extends Membre{
    private ArrayList<String> uuids = new ArrayList<>();
    private ArrayList<Robot> listeRobot = new ArrayList<>();
    private ArrayList<Tache> taches = new ArrayList<>();
    private ArrayList<Action> actions = new ArrayList<Action>();
    private ArrayList<Composant> composantesAchetes = new ArrayList<>();
    private Set<Utilisateur> listeUtilisateursSuivi = new HashSet<>();
    private ArrayList<Interet> listeInteret = new ArrayList<>();
    private ArrayList<String> notifs = new ArrayList<>();
    private ArrayList<String> listeActivitesRejoint = new ArrayList<>();

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
        return this.point;
    }

    public ArrayList<Robot> getListeRobot() {
        return this.listeRobot;
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

    public void setComposantesAchetes(ArrayList<Composant> composantesAchetes) {
        this.composantesAchetes = composantesAchetes;
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

    public void suivreUtilisateur(Utilisateur suivi){
        listeUtilisateursSuivi.add(suivi);
    }

    public ArrayList<String> voirNotifications(){
        return notifs;
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

    public boolean enregistrerRobot(String nomRobot,String numeroSerie, Utilisateur utilisateur, ArrayList<Fournisseur> listeFournisseur){
        boolean bool = false;
        Robot robot = verifierRobot(numeroSerie, listeFournisseur);
        if (robot != null){
            robot.setNom(nomRobot);
            utilisateur.listeRobot.add(robot);
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

    public ArrayList<Robot> afficherEtatRobot(Utilisateur utilisateur){
        return utilisateur.listeRobot;
    }

    public void creerAction(String nomAction, ArrayList<Composant> composantes){
        Action action = new Action(nomAction, composantes);
        actions.add(action);
    }

    public void creerTache(String nomTache, ArrayList<Action> actions){
        Tache tache = new Tache(nomTache, actions);
        taches.add(tache);
    }

    public boolean allouerTache(String robot, String tache){
        Robot rob = trouverRobot(robot);
        Tache tac = trouverTache(tache);
        if (rob.getNom().equals(null) || tac.getNom().equals(null)){
            return false;
        }
        else {
            rob.getTaches().add(tac);
            return true;
        }
    }

    private Tache trouverTache(String nom) {
        Tache resultat = null;
        for (Tache tache : taches) {
            if (tache.getNom().equals(nom)) {
                resultat = tache;
                break;
            }
        }
        return resultat;
    }

    public boolean creerActivites(String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache, ArrayList<Activite> listActivites) {
        int compteur = 0;
        for (Activite activite : listActivites) {
            if (nomActivite.equals(activite.getNom())){
                break;
            }
            compteur++;
        }

        if(compteur == listActivites.size()){
            Activite activite = new Activite(nomActivite, dateDebut, dateFin, listeTache, null, null);
        }
    }

    public boolean ajouterComposanteRobot(String nomComposant, String nomRobot) {
        boolean bool = false;
        Robot robot = trouverRobot(nomRobot);
        Composant composante = trouverComposante(nomComposant);
        if (robot != null && composante != null){
            robot.ajouterComposante(composante);
            bool = true;
        }
        return bool;
    }

    public Robot trouverRobot(String nom){
        Robot resultat = null;
        for (Robot robot : listeRobot) {
            if (robot.getNom().equals(nom)) {
                resultat = robot;
                break;
            }
        }
        return resultat;
    }

    public Composant trouverComposante(String composante){
        Composant composant = null;
        for (Composant composant1 : composantesAchetes) {
            if(composant1.getNom().equals(composante)){
                composant = composant1;
                break;
            }
        }
        return composant;
    }

    public int nombreDeRobot(){
        return listeRobot.size();
    }

}