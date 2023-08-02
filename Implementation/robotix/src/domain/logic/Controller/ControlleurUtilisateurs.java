package domain.logic.Controller;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.TypeNotification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

/**
 * Contrôleur pour la gestion des utilisateurs.
 */
public class ControlleurUtilisateurs {

    private DbControleur dataBaseController = DbControleur.getDbControleur();
    private static ControlleurUtilisateurs controlleurUtilisateurs;
    private Utilisateur utilisateurCourant;

    /**
     * Constructeur de la classe ControlleurUtilisateurs qui permet de créer un nouvel utilisateur et l'inscrire dans la base de données.
     *
     * @param nom            Le nom de l'utilisateur.
     * @param prenom         Le prénom de l'utilisateur.
     * @param adresse        L'adresse de l'utilisateur.
     * @param pseudo         Le pseudo de l'utilisateur.
     * @param mdp            Le mot de passe de l'utilisateur.
     * @param email          L'adresse e-mail de l'utilisateur.
     * @param numeroTelephone Le numéro de téléphone de l'utilisateur.
     * @param nomCompagnie    Le nom de la compagnie de l'utilisateur.
     * @param listeInteret    La liste des intérêts de l'utilisateur.
     * @throws IOException    En cas d'erreur d'entrée/sortie lors de l'accès aux fichiers.
     * @throws ParseException En cas d'erreur lors de la conversion de chaînes de caractères en dates.
     */
    private ControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo, String mdp, String email,
                                    String numeroTelephone, String nomCompagnie, ArrayList<String> listeInteret) throws IOException, ParseException {
        this.inscriptionUtilisateur(nom, prenom, adresse, pseudo, mdp, email, numeroTelephone, nomCompagnie, listeInteret);
    }

    /**
     * Méthode statique pour obtenir l'instance unique du ControlleurUtilisateurs ou en créer une nouvelle si elle n'existe pas encore.
     *
     * @param nom            Le nom de l'utilisateur.
     * @param prenom         Le prénom de l'utilisateur.
     * @param adresse        L'adresse de l'utilisateur.
     * @param pseudo         Le pseudo de l'utilisateur.
     * @param mdp            Le mot de passe de l'utilisateur.
     * @param email          L'adresse e-mail de l'utilisateur.
     * @param numeroTelephone Le numéro de téléphone de l'utilisateur.
     * @param nomCompagnie    Le nom de la compagnie de l'utilisateur.
     * @param listeInteret    La liste des intérêts de l'utilisateur.
     * @return L'instance unique du ControlleurUtilisateurs.
     * @throws IOException    En cas d'erreur d'entrée/sortie lors de l'accès aux fichiers.
     * @throws ParseException En cas d'erreur lors de la conversion de chaînes de caractères en dates.
     */
    public static ControlleurUtilisateurs getControlleurUtilisateurs(String nom, String prenom, String adresse, String pseudo,
                                                              String mdp, String email, String numeroTelephone,
                                                              String nomCompagnie, ArrayList<String> listeInteret) throws IOException, ParseException {
        return controlleurUtilisateurs == null ? new ControlleurUtilisateurs(nom, prenom, adresse, pseudo, mdp, email,
                numeroTelephone, nomCompagnie, listeInteret) : controlleurUtilisateurs;
    }

    /**
     * Constructeur par défaut de la classe ControlleurUtilisateurs.
     *
     * @throws IOException    En cas d'erreur d'entrée/sortie lors de l'accès aux fichiers.
     * @throws ParseException En cas d'erreur lors de la conversion de chaînes de caractères en dates.
     */
    public ControlleurUtilisateurs() throws IOException, ParseException {

    }

    /**
     * Inscription d'un nouvel utilisateur dans la base de données en utilisant les informations fournies.
     *
     * @param nom            Le nom de l'utilisateur.
     * @param prenom         Le prénom de l'utilisateur.
     * @param adresse        L'adresse de l'utilisateur.
     * @param pseudo         Le pseudo de l'utilisateur.
     * @param mdp            Le mot de passe de l'utilisateur.
     * @param courriel       L'adresse e-mail de l'utilisateur.
     * @param telephone      Le numéro de téléphone de l'utilisateur.
     * @param nomCompagnie   Le nom de la compagnie de l'utilisateur.
     * @param listeInteret   La liste des intérêts de l'utilisateur.
     */
    public void inscriptionUtilisateur(String nom, String prenom, String adresse, String pseudo,String mdp, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) {
        this.utilisateurCourant = new Utilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, Utilisateur.produireListeInteret(listeInteret), new ArrayList<Notification>());
        dataBaseController.ajouterUtilisateur(utilisateurCourant);
    }

    /**
     * Effectue l'authentification de l'utilisateur en vérifiant ses identifiants dans la base de données.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @param mdp    Le mot de passe de l'utilisateur.
     * @return Vrai si l'authentification est réussie, sinon faux.
     */
    public boolean authentification(String pseudo, String mdp) {
        Utilisateur u = dataBaseController.authentificatiUtilisateur(pseudo, mdp);
        if (u == null){
            return false;
        }
        this.utilisateurCourant = u;
        return true;
    }

    /* Code pour les vérifications */
    /**
     * Vérifie si un pseudo d'utilisateur existe dans la base de données des utilisateurs.
     *
     * @param pseudo Le pseudo de l'utilisateur à vérifier.
     * @return Vrai si le pseudo de l'utilisateur existe dans la base de données, sinon faux.
     */
    public boolean verifierPseudo(String pseudo) {
        return dataBaseController.verifierPseudo(pseudo);
    }


    /* Actions utilisateur */

    /**
     * Modifie le profil d'un utilisateur en utilisant les choix et les informations spécifiés.
     *
     * @param choix  Le choix de modification du profil (nom, prénom, adresse, etc.).
     * @param info   L'information à modifier.
     * @param pseudo Le pseudo de l'utilisateur dont le profil doit être modifié.
     */
    public void modifierProfile(String choix, String info, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        u.modifierProfile(choix, info);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Enregistre un robot pour un utilisateur en utilisant les informations spécifiées.
     *
     * @param nomRobot    Le nom du robot à enregistrer.
     * @param type        Le type du robot à enregistrer.
     * @param numeroSerie Le numéro de série du robot à enregistrer.
     * @param pseudo      Le pseudo de l'utilisateur pour lequel enregistrer le robot.
     * @return Vrai si l'enregistrement du robot est réussi, sinon faux.
     */
    public boolean enregistrerRobot(String nomRobot, String type, String numeroSerie, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        boolean bool=false;
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = this.dataBaseController.getCurentSoldRobot(numeroSerie);
        if (robot != null){
            robot.setNom(nomRobot);
            robot.setType(type);
            bool = u.enregistrerRobot(robot);
        }
        this.dataBaseController.ajouterUtilisateur(u);
        return bool;
    }

    /**
     * Récupère la liste des robots d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer la liste des robots.
     * @return La liste des robots de l'utilisateur.
     */
    public ArrayList<Robot> recupererListeRobot(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeRobot();
    }

    /**
     * Affiche l'état d'un robot spécifié par son numéro de série pour un utilisateur donné.
     *
     * @param numeroSeri Le numéro de série du robot pour lequel afficher l'état.
     * @param pseudo     Le pseudo de l'utilisateur.
     * @return L'état du robot sous forme d'une chaîne de caractères ou un message d'erreur s'il n'est pas trouvé.
     */
    public String afficherEtatRobot(String numeroSeri, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.afficherEtatRobot(numeroSeri);
    }

    /**
     * Ajoute une composante à un robot spécifié par son numéro de série pour un utilisateur donné.
     *
     * @param composante   Le nom de la composante à ajouter au robot.
     * @param numeroSerie  Le numéro de série du robot auquel ajouter la composante.
     * @param pseudo       Le pseudo de l'utilisateur.
     * @return Vrai si l'ajout de la composante au robot est réussi, sinon faux.
     */
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

    /**
     * Achète une composante spécifiée par son nom auprès d'un fournisseur pour un utilisateur donné.
     *
     * @param nomFournisseur Le nom du fournisseur auprès duquel acheter la composante.
     * @param nomComposante  Le nom de la composante à acheter.
     * @param pseudo         Le pseudo de l'utilisateur.
     * @return Vrai si l'achat de la composante est réussi, sinon faux.
     */
    public boolean acheterComposante(String nomFournisseur, String nomComposante, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        Composant composant = dataBaseController.acheterComposant(nomFournisseur, nomComposante);
        if (composant != null){
            u.ajouterComposante(composant);
            dataBaseController.ajouterUtilisateur(u);
            return true;
        } else {
            dataBaseController.ajouterUtilisateur(u);
            return false;
        }
    }

    /**
     * Crée une nouvelle action avec les informations spécifiées pour un utilisateur donné.
     *
     * @param nomAction    Le nom de l'action à créer.
     * @param composantes  La liste des noms de composantes associées à l'action.
     * @param duree        La durée de l'action.
     * @param pseudo       Le pseudo de l'utilisateur.
     */
    public void creerAction(String nomAction, ArrayList<String> composantes, String duree, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.creerAction(nomAction, composantes, duree);
        dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Affiche les métriques de la flotte de robots pour un utilisateur donné.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @return Le nombre de robots dans la flotte de l'utilisateur.
     */
    public int afficherMetriquesFlotte(String pseudo){
        return this.utilisateurCourant.nombreDeRobot();
    }

    /**
     * Crée une nouvelle tâche avec les informations spécifiées pour un utilisateur donné.
     *
     * @param nomTache Le nom de la tâche à créer.
     * @param actions  La liste des noms d'actions associées à la tâche.
     * @param pseudo   Le pseudo de l'utilisateur.
     */
    public void creerTache(String nomTache, ArrayList<String> actions, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        //this.dataBaseController.supprimerUtilisateur(u);
        u.creerTache(nomTache, actions);
        //this.dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Récupère la liste des actions d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer la liste des actions.
     * @return La liste des actions de l'utilisateur.
     */
    public ArrayList<Action> recuprerListeAction(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeActions();
    }

    /**
     * Récupère la liste des tâches d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer la liste des tâches.
     * @return La liste des tâches de l'utilisateur.
     */
    public ArrayList<Tache> recuprerListeTache(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.getListeTaches();
    }

    /**
     * Alloue une tâche à un robot spécifié par son numéro de série pour un utilisateur donné.
     *
     * @param pseudo       Le pseudo de l'utilisateur.
     * @param numeroDeSerie Le numéro de série du robot auquel allouer la tâche.
     * @param tache        Le nom de la tâche à allouer.
     */
    public void allouerTacheRobot(String pseudo, String numeroDeSerie, String tache){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        this.dataBaseController.supprimerUtilisateur(u);
        Robot robot = u.getRobot(numeroDeSerie);
        Tache tac = u.getTache(tache);
        robot.allouerTache(tac);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Crée une nouvelle activité pour un utilisateur donné avec les informations spécifiées.
     *
     * @param pseudo        Le pseudo de l'utilisateur.
     * @param nomActivite   Le nom de l'activité à créer.
     * @param dateDebut     La date de début de l'activité au format "yyyy-MM-dd".
     * @param dateFin       La date de fin de l'activité au format "yyyy-MM-dd".
     * @param listeTache    La liste des noms de tâches associées à l'activité.
     * @param listeInteret  La liste des noms d'intérêts associés à l'activité.
     * @return Vrai si la création de l'activité est réussie, sinon faux.
     * @throws ParseException En cas d'erreur lors de la conversion de chaînes de caractères en dates.
     */
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

    /**
     * Récupère la liste des activités disponibles dans la base de données.
     *
     * @return La liste des activités.
     */
    public ArrayList<Activite> recupererListeActivites(){
        return dataBaseController.recupererListeActivite();
    }

    /**
     * Permet à un utilisateur de rejoindre une activité donnée.
     *
     * @param pseudo      Le pseudo de l'utilisateur souhaitant rejoindre l'activité.
     * @param nomActivite Le nom de l'activité à rejoindre.
     * @return "true" si l'utilisateur a rejoint l'activité avec succès, "false" sinon.
     */
    public String rejoindreActivite(String pseudo, String nomActivite) {
        String result = "false";
        Utilisateur utilisateur = dataBaseController.retournerUtilisateur(pseudo);
        Activite activite = dataBaseController.retournerActivite(nomActivite);
        if (utilisateur != null && activite != null) {
            dataBaseController.supprimerUtilisateur(utilisateur);
            dataBaseController.supprimerActivite(activite);
            if (!utilisateur.rejoindreActivite(activite)){
                activite.getListeUtilisateurInsccrit().add(utilisateur.getPseudo());
                utilisateur.setPoint(utilisateur.getPoint() + activite.getPoints());
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

    /**
     * Ajoute des robots à une activité donnée pour un utilisateur spécifié par son pseudo.
     *
     * @param numeroSerieRobots La liste des numéros de série des robots à ajouter à l'activité.
     * @param nomActivite       Le nom de l'activité à laquelle ajouter les robots.
     * @param pseudo            Le pseudo de l'utilisateur.
     */
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

    /**
     * Affiche la liste des utilisateurs suivis par l'utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel afficher la liste des utilisateurs suivis.
     * @return La liste des utilisateurs suivis sous forme de chaîne de caractères.
     */
    public String voirListeUtilisateur(String pseudo){
        return dataBaseController.retournerUtilisateur(pseudo).voirListeUtilisateur();
    }

    /**
     * Supprime un utilisateur de la liste de suivi d'un autre utilisateur spécifié par son pseudo.
     *
     * @param pseudo         Le pseudo de l'utilisateur qui souhaite supprimer l'utilisateur de sa liste de suivi.
     * @param utilASupprimer Le pseudo de l'utilisateur à supprimer de la liste de suivi.
     * @return "true" si l'utilisateur a été supprimé de la liste de suivi avec succès, "false" sinon.
     */
    public boolean supprimerUtilisateurDeListeSuivi(String pseudo, String utilASupprimer){
        if (dataBaseController.retournerUtilisateur(pseudo).supprimerUtilisateurDeMaListe(utilASupprimer) == false){
            return false;
        }
        return true;
    }

    /**
     * Supprime la relation de suivi entre deux utilisateurs spécifiés par leur pseudo.
     *
     * @param pseudo                Le pseudo de l'utilisateur qui souhaite arrêter de suivre l'autre utilisateur.
     * @param pseudoUtilisateurASuivre Le pseudo de l'utilisateur à ne plus suivre.
     * @return "true" si la relation de suivi a été supprimée avec succès, "false" sinon.
     */
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

    /**
     * Supprime la relation de suivi entre deux utilisateurs spécifiés par leur pseudo.
     *
     * @param pseudo                Le pseudo de l'utilisateur qui souhaite arrêter de suivre l'autre utilisateur.
     * @param pseudoUtilisateurASuivre Le pseudo de l'utilisateur à ne plus suivre.
     * @return "true" si la relation de suivi a été supprimée avec succès, "false" sinon.
     */
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

    /**
     * Permet à un utilisateur de commencer à suivre un autre utilisateur spécifié par son pseudo.
     *
     * @param pseudo                Le pseudo de l'utilisateur qui souhaite commencer à suivre l'autre utilisateur.
     * @param pseudoUtilisateurASuivre Le pseudo de l'utilisateur à suivre.
     * @return "true" si la relation de suivi a été créée avec succès, "false" sinon.
     */
    public boolean etreSuivi(String pseudo, String pseudoUtilisateurASuivre){

        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            Utilisateur utilCourant = dataBaseController.retournerUtilisateur(pseudo);
            dataBaseController.supprimerUtilisateur(utilCourant);
            utilCourant.getListeUtilisateursSuivi().add(u);
            dataBaseController.ajouterUtilisateur(utilCourant);
        } catch (NullPointerException e){
            return false;
        }

        return true;
    }

    /**
     * Permet à un utilisateur de commencer à suivre un autre utilisateur spécifié par son pseudo.
     *
     * @param pseudo                Le pseudo de l'utilisateur qui souhaite commencer à suivre l'autre utilisateur.
     * @param pseudoUtilisateurASuivre Le pseudo de l'utilisateur à suivre.
     * @return "true" si la relation de suivi a été créée avec succès, "false" sinon.
     */
    public boolean suivreUtilisateur(String pseudo, String pseudoUtilisateurASuivre){
        //Aller chercher utilisateur suivi et APPEND utilisateurCOurant
        try {
            Utilisateur u = dataBaseController.retournerUtilisateur(pseudoUtilisateurASuivre);
            dataBaseController.supprimerUtilisateur(u);
            u.getListSuiveur().add(utilisateurCourant);
            dataBaseController.ajouterUtilisateur(u);
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }

    /**
     * Récupère la liste des notifications pour un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer les notifications.
     * @return La liste des notifications de l'utilisateur.
     */
    public ArrayList<Notification> voirNotifications(String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.voirNotifications();
    }

    /**
     * Ajoute une nouvelle notification à un utilisateur spécifié par son pseudo.
     *
     * @param nom       Le pseudo de l'utilisateur pour lequel ajouter la notification.
     * @param titre     Le titre de la notification.
     * @param message   Le message de la notification.
     * @param typeNotif Le type de la notification.
     */
    public void ajouterNotifs(String nom, String titre, String message, TypeNotification typeNotif){
        Utilisateur u = dataBaseController.retournerUtilisateur(nom);
        ArrayList<Notification> notifsCourantes = u.getNotifs();
        this.dataBaseController.supprimerUtilisateur(u);
        u.addNotifs(titre, message,  typeNotif);
        u.setListeNotifications(notifsCourantes);
        this.dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Supprime toutes les notifications d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur dont les notifications doivent être supprimées.
     */
    public void supprimerNotifs(String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.getNotifs().clear();
        dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Affiche le profil de l'utilisateur courant.
     */
    public void voirProfilUtilisateurCourant() {
       System.out.println(this.utilisateurCourant.getProfilUtilisateur());
    }

    /**
     * Permet à l'utilisateur courant de souscrire à un nouvel intérêt.
     *
     * @param nomInteret Le nom de l'intérêt à souscrire.
     * @return "true" si l'utilisateur a souscrit à l'intérêt avec succès, "false" sinon.
     */
    public boolean souscrireAunInteret(String nomInteret){
        Interet i= this.dataBaseController.souscrireAunInteret(nomInteret);
        if( i==null) {
            return false;
        }

        this.dataBaseController.supprimerUtilisateur(utilisateurCourant);
        this.utilisateurCourant.ajouterUnInteret(i);
        this.dataBaseController.ajouterUtilisateur(utilisateurCourant);
        return true;
    }

    /**
     * Ajoute un robot à l'utilisateur spécifié par son pseudo.
     *
     * @param pseudo      Le pseudo de l'utilisateur auquel ajouter le robot.
     * @param numeroSerie Le numéro de série du robot à ajouter.
     */
    public void ajouterRobot(String pseudo, String numeroSerie) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        Robot robot = dataBaseController.retournerRobot(numeroSerie);
        u.ajouterRobot(robot);
        dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Extrait les intérêts des utilisateurs qui ont souscrit à l'intérêt donné.
     *
     * @param interet Le nom de l'intérêt dont on souhaite extraire les utilisateurs.
     * @return "true" si l'intérêt a été extrait avec succès, "false" sinon.
     */
    public boolean extraireInteretsUtilisateurs(String interet) {
        return this.dataBaseController.extraireInterets(interet);
    }

    /**
     * Permet à un utilisateur de s'abonner à un nouvel intérêt.
     *
     * @param interet Le nom de l'intérêt auquel l'utilisateur souhaite s'abonner.
     * @param pseudo  Le pseudo de l'utilisateur souhaitant s'abonner à l'intérêt.
     */
    public void abonnerInteret(String interet, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.getListeInteret().add(new Interet(interet));
        dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Récupère la liste des intérêts d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer la liste des intérêts.
     * @return La liste des intérêts de l'utilisateur sous forme de chaîne de caractères.
     */
    public StringBuilder retournerInteretUtilisateur(String pseudo) {
        return dataBaseController.retournerListeInteretsUtilisateur(pseudo);
    }

    /**
     * Permet à un utilisateur de se désabonner d'un intérêt spécifié par son nom.
     *
     * @param choix  Le nom de l'intérêt dont l'utilisateur souhaite se désabonner.
     * @param pseudo Le pseudo de l'utilisateur souhaitant se désabonner de l'intérêt.
     */
    public void desabonnerInteret(String choix, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        dataBaseController.supprimerUtilisateur(u);
        u.desabonnerInteret(choix);
        dataBaseController.ajouterUtilisateur(u);
    }

    /**
     * Vérifie si un utilisateur spécifié par son pseudo suit un autre utilisateur spécifié par son nom.
     *
     * @param pseudo Le pseudo de l'utilisateur qui souhaite vérifier s'il suit l'autre utilisateur.
     * @param nom    Le nom de l'utilisateur suivi.
     * @return "true" si l'utilisateur suit l'autre utilisateur, "false" sinon.
     */
    public boolean existeDansListeSuivi(String pseudo, String nom) {
        return dataBaseController.existeDansListeSuivi(pseudo, nom);
    }

    /**
     * Retrouve un robot spécifié par son nom pour l'utilisateur spécifié par son pseudo.
     *
     * @param nomRobot Le nom du robot à retrouver.
     * @param pseudo   Le pseudo de l'utilisateur pour lequel retrouver le robot.
     * @return Le robot correspondant au nom spécifié, ou null s'il n'est pas trouvé.
     */
    public Robot retrouverRobotNom(String nomRobot, String pseudo) {
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.retrouverRobotNom(nomRobot);
    }

    /**
     * Retrouve un robot spécifié par son numéro de série pour l'utilisateur spécifié par son pseudo.
     *
     * @param numeroSerie Le numéro de série du robot à retrouver.
     * @param pseudo      Le pseudo de l'utilisateur pour lequel retrouver le robot.
     * @return Le robot correspondant au numéro de série spécifié, ou null s'il n'est pas trouvé.
     */
    public Robot retrouverRobotNumeroSerie(String numeroSerie, String pseudo){
        Utilisateur u = dataBaseController.retournerUtilisateur(pseudo);
        return u.retournerRobotNumeroSerie(numeroSerie);
    }
}
