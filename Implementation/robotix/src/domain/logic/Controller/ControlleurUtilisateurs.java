package domain.logic.Controller;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ControlleurUtilisateurs {

    private DbControleur dataBaseController = DbControleur.getDbControleur();
    private static ControlleurUtilisateurs controlleurUtilisateurs;
    //private ArrayList<Utilisateur> listeUtilisateurs = dataBaseController.recupererListeUtilisateur();
    //private ArrayList<Fournisseur> listeFournisseurs = dataBaseController.getListeFournisseurs();
    private Utilisateur utilisateurCourant;

    private ControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo, String mdp, String email,
                                    String numeroTelephone, String nomCompagnie, ArrayList<String> listeInteret) throws IOException {
        this.inscriptionUtilisateur(nom, prenom, adresse, pseudo, mdp, email, numeroTelephone, nomCompagnie, listeInteret);
    }

    public static ControlleurUtilisateurs getControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo,
                                                              String mdp, String email, String numeroTelephone,
                                                              String nomCompagnie, ArrayList<String> listeInteret) throws IOException {
        return controlleurUtilisateurs == null ? new ControlleurUtilisateurs(nom, prenom, adresse, pseudo, mdp, email,
                numeroTelephone, nomCompagnie, listeInteret) : controlleurUtilisateurs;
    }

    public ControlleurUtilisateurs() throws IOException {

    }

    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo,String mdp, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, Utilisateur.produireListeInteret(listeInteret));
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
        try {
            dataBaseController.verifierPseudo(pseudo);
        } catch (NullPointerException e){
            return true;
        }
        return false;
    }

    public boolean verifierEmail(String inputEmail) {
        return Utilisateur.verifierEmailUtilisateur(inputEmail);
    }

    public boolean verifierTelephone(String inputTelephone) {
        return Utilisateur.verifierTelephoneUtilisateur(inputTelephone);
    }


    /* Actions utilisateur */

    public void modifierProfile(String choix, String info) {
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.modifierProfile(choix, info);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
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

    public ArrayList<Robot> afficherEtatRobot(String pseudo) {
        return this.utilisateurCourant.afficherEtatRobot();
    }

    public boolean ajouterComposanteRobot(String composante, String numeroSerie, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = this.dataBaseController.retournerRobot(numeroSerie);
        Composant comp = this.dataBaseController.retournerComposante(composante);
        if (!(robot == null) && !(comp == null)){
            robot.ajouterComposante(comp);
            this.dataBaseController.ajouterUtilisateur(u);
            return true;
        } else {
            this.dataBaseController.ajouterUtilisateur(u);
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

    public ArrayList<String> creerTache(String nomTache, ArrayList<String> actions, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        ArrayList<String> actionsRestantes = u.creerTache(nomTache, actions);
        this.dataBaseController.ajouterUtilisateur(u);
        return actionsRestantes;
    }

    public String recuprerListeAction(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        String result = u.listeActions();
        if (result == null)
            return "";
        else
            return result;
    }

    public String recuprerListeTache(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        String result = u.listeTaches();
        if (result == null)
            return "";
        else
            return result;
    }

    public boolean allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        boolean bool = false;
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = u.getRobot(numeroDeSerie);
        Tache tac = u.getTache(tache);
        if ((robot != null) && (tac != null)){
            robot.allouerTache(tac);
            bool = true;
        }
        this.dataBaseController.ajouterUtilisateur(u);
        return bool;
    }

    public boolean creerActivites(String pseudo, String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache, ArrayList<String> listeInteret) throws ParseException {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        ArrayList<Tache> listeTac = u.getTacheEnListe(listeTache);
        ArrayList<Interet> listeInter = Utilisateur.produireListeInteret(listeInteret);
        boolean ressult = u.creerActivite(nomActivite, dateDebut, dateFin, listeTac, listeInter);
        this.dataBaseController.ajouterUtilisateur(u);
        return ressult;
    }

    public void rejoindreActivite(String pseudo, Activite activite){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        try {
            this.utilisateurCourant.rejoindreActivite(activite);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        } catch (NullPointerException e){

        }
    }

    public boolean suivreUtilisateur(String pseudoUtilisateurASuivre){
        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        Utilisateur aSuivre = this.dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
        this.dataBaseController.supprimerUtilisateur(aSuivre);
        try {
            aSuivre.etreSuivi(utilisateurCourant);
            this.utilisateurCourant.suivreUtilisateur(aSuivre);
            this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
            this.dataBaseController.ajouterUtilisateur(aSuivre);
        } catch (NullPointerException e) {
            return true;
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
        return this.utilisateurCourant.voirNotifications();
    }

    public void supprimerNotifs(String pseudo) {
        this.utilisateurCourant.getNotifs().clear();
    }

    public void voirProfilUtilisateurCourant()
    {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }

    public boolean[] notifier() {
        return this.utilisateurCourant.notifier();
    }
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
}
