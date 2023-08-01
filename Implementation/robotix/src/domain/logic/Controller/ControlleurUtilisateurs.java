package domain.logic.Controller;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.TypeNotification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

public class ControlleurUtilisateurs {

    private DbControleur dataBaseController = DbControleur.getDbControleur();
    private static ControlleurUtilisateurs controlleurUtilisateurs;
    //private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.recupererListeUtilisateur();
    //private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private Utilisateur utilisateurCourant;

    private ControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo, String mdp, String email,
                                    String numeroTelephone, String nomCompagnie, ArrayList<String> listeInteret) throws IOException, ParseException {
        this.inscriptionUtilisateur(nom, prenom, adresse, pseudo, mdp, email, numeroTelephone, nomCompagnie, listeInteret);
    }

    public static ControlleurUtilisateurs getControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo,
                                                              String mdp, String email, String numeroTelephone,
                                                              String nomCompagnie, ArrayList<String> listeInteret) throws IOException, ParseException {
        return controlleurUtilisateurs == null ? new ControlleurUtilisateurs(nom, prenom, adresse, pseudo, mdp, email,
                numeroTelephone, nomCompagnie, listeInteret) : controlleurUtilisateurs;
    }

    public ControlleurUtilisateurs() throws IOException, ParseException {

    }

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo,String mdp, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, Utilisateur.produireListeInteret(listeInteret), new ArrayList<Notification>(), new HashSet<String>());
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
        return dataBaseController.verifierPseudo(pseudo);
    }


    /* Actions utilisateur */

    public void modifierProfile(String choix, String info, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        u.modifierProfile(choix, info);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    public boolean enregistrerRobot(String nomRobot, String type, String numeroSerie, String pseudo) {
        boolean bool=false;
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        if (robot != null){
            bool = u.enregistrerRobot(nomRobot ,robot, type);
        }
        this.dataBaseController.ajouterUtilisateur(u);
        return bool;
    }

    public ArrayList<Robot> recupererListeRobot(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeRobot();
    }

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        return this.utilisateurCourant.afficherEtatRobot();
    }

    public boolean ajouterComposanteRobot(String composante, String numeroSerie, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        Composant comp = u.retournerComposante(composante);
        if (robot != null && comp != null){
            robot.ajouterComposante(comp);
            u.ajouterComposanteRobot(comp, robot);
            this.dataBaseController.ajouterUtilisateur(u);
            return true;
        } else {
            this.dataBaseController.ajouterUtilisateur(u);
            return false;
        }
    }

    public boolean acheterComposante(String nomFournisseur, String nomComposante, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        Composant composant = dataBaseController.achatComposante(nomFournisseur, nomComposante);
        if (composant != null){
            u.ajouterComposante(composant);
            dataBaseController.ajouterUtilisateur(u);
            return true;
        } else {
            dataBaseController.ajouterUtilisateur(u);
            return false;
        }
    }

    public void creerAction(String nomAction, ArrayList<String> composantes, String duree, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.creerAction(nomAction, composantes, duree);
        dataBaseController.ajouterUtilisateur(u);
    }

    public int afficherMetriquesFlotte(String pseudo){
        return this.utilisateurCourant.nombreDeRobot();
    }

    public void creerTache(String nomTache, ArrayList<String> actions, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        //this.dataBaseController.supprimerUtilisateur(u);
        u.creerTache(nomTache, actions);
        //this.dataBaseController.ajouterUtilisateur(u);
    }

    public ArrayList<Action> recuprerListeAction(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeActions();
    }

    public ArrayList<Tache> recuprerListeTache(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeTaches();
    }

    public void allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = u.getRobot(numeroDeSerie);
        Tache tac = u.getTache(tache);
        robot.allouerTache(tac);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    public boolean creerActivites(String pseudo, String nomActivite, String dateDebut, String dateFin,
                                  ArrayList<String> listeTache, ArrayList<String> listeInteret) throws ParseException {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        ArrayList<Tache> listeTac = u.getTacheEnListe(listeTache);
        HashSet<Interet> listeInter = Utilisateur.produireListeInteret(listeInteret);
        boolean ressult = u.creerActivite(nomActivite, dateDebut, dateFin, listeTac, listeInter);
        this.dataBaseController.ajouterActivite(u.getActiviteCree(nomActivite));
        this.dataBaseController.ajouterUtilisateur(u);
        return ressult;
    }

    public ArrayList<Activite> recupererListeActivites(){
        return dataBaseController.recupererListeActivite();
    }

    public String rejoindreActivite(String pseudo, String nomActivite) {
        String result = "false";
        Utilisateur utilisateur = dataBaseController.retournerUtilisateur(pseudo);
        Activite activite = dataBaseController.retournerActivite(nomActivite);
        if (utilisateur != null && activite != null) {
            dataBaseController.supprimerUtilisateur(utilisateur);
            dataBaseController.supprimerActivite(activite);
            if (!utilisateur.rejoindreActivite(activite)){
                activite.getListeUtilisateurInsccrit().add(utilisateur.getPseudo());
                dataBaseController.ajouterActivite(activite);
                dataBaseController.ajouterUtilisateur(utilisateur);
                result = "true";
            } else {
                dataBaseController.ajouterActivite(activite);
                dataBaseController.ajouterUtilisateur(utilisateur);
                result = "true2";
            }
        }

        return result;
    }

    public void ajouterRobotActivite(ArrayList<String> numeroSerieRobots, String nomActivite ,String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        Activite activite = dataBaseController.retournerActivite(nomActivite);
        dataBaseController.supprimerUtilisateur(u);
        dataBaseController.supprimerActivite(activite);
        ArrayList<Robot> robots = new ArrayList<>();
        numeroSerieRobots.forEach(num -> robots.add(u.retournerRobotNumeroSerie(num)));
        int iterations = robots.size();
        for (int i = 0; i < iterations; i++) {
            Robot robot = robots.get(0);
            if (robot != null){
                activite.getListeRobotsInscrits().add(robot);
                robot.getActivites().add(activite.getNom());
            }
        }
        dataBaseController.ajouterActivite(activite);
        dataBaseController.ajouterUtilisateur(u);
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
            utilCourant.getListeUtilisateursSuivi().add(u.getPseudo());
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
            Utilisateur utilCourant = dataBaseController.retournerUtilisateur(pseudo);
            dataBaseController.supprimerUtilisateur(u);
            u.getListSuiveur().add(utilCourant.getPseudo());
            utilCourant.getListeUtilisateursSuivi().add(u.getPseudo());
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

    public Robot retrouverRobotNom(String nomRobot, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.retrouverRobotNom(nomRobot);
    }

    public Robot retrouverRobotNumeroSerie(String numeroSerie, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.retournerRobotNumeroSerie(numeroSerie);
    }
}
