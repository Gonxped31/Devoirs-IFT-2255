package domain.logic.Membre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import domain.logic.Robot.*;


//Faire import domain.logic.Fournisseur.Fournisseur;


public class Utilisateur extends Membre{
    private ArrayList<String> uuids = new ArrayList<>();
    private ArrayList<Robot> listeRobot = new ArrayList<>();
    private ArrayList<Tache> listeTaches = new ArrayList<>();
    private ArrayList<Action> listeActions = new ArrayList<>();
    private ArrayList<Composant> composantesAchetes = new ArrayList<>();
    private Set<Utilisateur> listeUtilisateursSuivi = new HashSet<>();
    private ArrayList<Interet> listeInteret = new ArrayList<>();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private ArrayList<Activite> listeActivitesRejoint = new ArrayList<>();
    private ArrayList<Activite> listeActivitesCreer = new ArrayList<>();
    private Set<Utilisateur> listSuiveur = new HashSet<>();
    private String pseudo;
    private String prenom;
    private int point;
    private Tache tache;
    private Notification notification = new Notification();
    private int taillePrecedenteListeSuiveur;
    private LocalDate dateActuelle = LocalDate.now();
    private long joursRestants;

    public Utilisateur(String nom, String prenom, String adresse, String pseudo, String email, String numeroTelephone, String nomCompagnie, ArrayList<Interet> listeInteret){
        super(nom, adresse, email, numeroTelephone, nomCompagnie);
        this.pseudo = pseudo;
        this.setPrenom(prenom);
        this.setListeInteret(listeInteret);
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

    public ArrayList<Notification> getNotifs(){
        return listeNotifications;
    }
 
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom){
        super.setNom(nom);
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
        Utilisateur utilisateur = new Utilisateur(null, null, null, null, null, null, null, null);
        for (Utilisateur utilisateurs : listeUtilisateurs) {
            if (utilisateurs.getPseudo().equals(pseudo)) {
                utilisateur = utilisateurs;
                break;
            }
        }
        return utilisateur;
    }

    public void suivreUtilisateur(Utilisateur suivi){
        taillePrecedenteListeSuiveur = listeUtilisateursSuivi.size();
        listeUtilisateursSuivi.add(suivi);
    }

    public void etreSuivi(Utilisateur suiveur){
        listSuiveur.add(suiveur);
    }

    public ArrayList<Notification> voirNotifications(){
        return listeNotifications;
    }



    public boolean[] notifier(){
        boolean[] tabBoolean = new boolean[7];
        boolean NotifierEtatRobot;
        boolean NotifierBatterieRobot;
        boolean NotifiercCPURobot;
        boolean NotifierNouvelleActivite;
        boolean NotifierNouveauAbonne;
        boolean NotifierNouveauParticipant;
        boolean NotifierSensibilisation;

        NotifierEtatRobot = verifierEtatRobot();
        NotifierBatterieRobot = verifierBatterieRobot();
        NotifiercCPURobot = verifierCPURobot();
        NotifierNouvelleActivite = verifierNouvelleActivite();
        NotifierNouveauAbonne = verifierNouveauAbonne();
        NotifierNouveauParticipant = verifierNouveauParticipant();
        NotifierSensibilisation = verifierDateLimiteActivite();

        tabBoolean[0] = NotifierEtatRobot;
        tabBoolean[1] = NotifierBatterieRobot;
        tabBoolean[2] = NotifiercCPURobot;
        tabBoolean[3] = NotifierNouvelleActivite;
        tabBoolean[4] = NotifierNouveauAbonne;
        tabBoolean[5] = NotifierNouveauParticipant;
        tabBoolean[6] = NotifierSensibilisation;

        return tabBoolean;
    }

    private boolean verifierEtatRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : listeRobot) {
            if (robot.getVitesse() == 0 || robot.getMemoire() == 0) {
                DoitEtreNotifie = true;
                notification.setTitre("MAUVAIS FONCTIONNEMENT");
                notification.setMesssage("Le robot " + robot.getNom() + " éprouve un problème de fonctionnement.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }

    private boolean verifierBatterieRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : listeRobot) {
            if (robot.getBatterie() >= 20) {
                DoitEtreNotifie = true;
                notification.setTitre("BATTERIE FAIBLE");
                notification.setMesssage("La batterie du robot " + robot.getNom() + " est à " + robot.getBatterie() + "%.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }

    private boolean verifierCPURobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : listeRobot) {
            if (robot.getCpu() >= 100) {
                DoitEtreNotifie = true;
                notification.setTitre("SURCHARGE CPU");
                notification.setMesssage("Le CPU du robot " + robot.getNom() + " est surchagé");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }

    private boolean verifierNouvelleActivite() {
        boolean DoitEtreNotifie = false;

        /*for (Activite activite : listeInteret) {
            if (robot.getCpu() >= 100) {
                DoitEtreNotifie = true;
                notification.setTitre("SURCHARGE CPU");
                notification.setMesssage("Le CPU du robot " + robot.getNom() + " est surchagé");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }*/
        return DoitEtreNotifie;
    }

    private boolean verifierNouveauAbonne() {
        boolean DoitEtreNotifie = false;

        if (listSuiveur.size() > taillePrecedenteListeSuiveur) {
            DoitEtreNotifie = true;
            notification.setTitre("NOUVEAU ABONNÉ");
            notification.setMesssage("Un nouvel utilisateur suit votre profil");
            notification.setTypeNotification(TypeNotification.NOUVEAU_ABONNE);
            listeNotifications.add(notification);
        }
        return DoitEtreNotifie;
    }

    private boolean verifierNouveauParticipant() {
        boolean DoitEtreNotifie = false;

        /*if (listeActivitesRejoint.size() > taillePrecedenteListeSuiveur) {
            DoitEtreNotifie = true;
            notification.setTitre("NOUVEAU PARTICIPANT");
            notification.setMesssage("Un nouvel utilisateur joint une de vos activités");
            notification.setTypeNotification(TypeNotification.NOUVEAU_PARTICIPANT);
            listeNotifications.add(notification);
        }*/
        return DoitEtreNotifie;
    }

    private boolean verifierDateLimiteActivite() {
        boolean DoitEtreNotifie = false;

        for (Activite activite : listeActivitesRejoint) {
            joursRestants = ChronoUnit.DAYS.between(dateActuelle, (Temporal) activite.getDateDebut());

            // Vérifiez si la date de l'activité est dans les trois jours à venir
            if (joursRestants >=0 && joursRestants <=3) {
                DoitEtreNotifie = true;
                notification.setTitre("RAPPEL D'UNE DE VOS ACTIVITÉS");
                notification.setMesssage("Il ne reste que " + joursRestants + " avant le début de l'activité " + activite.getNom());
                notification.setTypeNotification(TypeNotification.SENSIBILISATION);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }

    public void modifierProfile(String choix, String nouvelInfo){
        switch (choix.toLowerCase()) {
            case "nom" :
                this.setNom(nouvelInfo);
            case "prenom" :
                this.setPrenom(nouvelInfo);
            case "adresse" :
                this.setAdresse(nouvelInfo);
            case "email" :
                this.setEmail(nouvelInfo);
            case "numerotelephone" :
                this.setNumeroTelephone(nouvelInfo);
            case "nomcompagnie" :
                this.setNomCompagnie(nouvelInfo);
        }

    }

    public boolean enregistrerRobot(Robot robot){//String nomRobot, String numeroSerie, ArrayList<Fournisseur> listeFournisseur){
        boolean bool = false;
        //Robot robot = verifierNumeroSerieRobot(numeroSerie, listeFournisseur);
        if (robot != null){
            robot.setNom(robot.getNom());
            this.listeRobot.add(robot);
            bool = true;
        }
        return bool;
    }

    public void creerTache(String nom, ArrayList<Action> actions){
        listeTaches.add(new Tache(nom, actions));
    }

    public Robot getRobot(String numeroDeSerie){
        return listeRobot.stream()
                .filter(r -> r.getNumeroSerie().equals(numeroDeSerie))
                .findFirst()
                .orElse(null);
    }

    public Tache getTache(String nom){
        return listeTaches.stream()
                .filter(t -> t.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    public Robot verifierNumeroSerieRobot(String numeroSerie, ArrayList<Fournisseur> listeFournisseur){
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

    public ArrayList<Robot> afficherEtatRobot(){
        return this.listeRobot;
    }

    public void creerActivite(String nomActivite, String dateDebut, String dateFin, ArrayList<Tache> listeTache, ArrayList<Interet> listeInterets) throws ParseException {
        listeActivitesCreer.add(new Activite(nomActivite, new SimpleDateFormat("dd/MM/yyyy").parse(dateDebut), new SimpleDateFormat("dd/MM/yyyy").parse(dateFin), listeTache, listeInterets));
    }

    public void creerAction(String nomAction, ArrayList<String> composantes, String duree){
        Action action = new Action(nomAction, composantes, duree);
        listeActions.add(action);
   /* public void creerTache(String nomTache, ArrayList<Action> actions){
        Tache tache = new Tache(nomTache, actions);
        listeTaches.add(tache);
    }
        taches.add(tache);
    }*/
    /*public boolean allouerTache(String robot, String tache){
        Robot rob = trouverRobot(robot);
        Tache tac = trouverTache(tache);
        if (rob.getNom().equals(null) || tac.getNom().equals(null)){
            return false;
        }
        else {
            rob.getTaches().add(tac);
            return true;
        }
    }*/

    /*private Tache trouverTache(String nom) {
        Tache resultat = null;
        for (Tache tache : listeTaches) {
            if (tache.getNom().equals(nom)) {
                resultat = tache;
                break;
            }
        }
        return resultat;
    }*/

    /*public boolean creerActivites(String nomActivite, String dateDebut, String dateFin, ArrayList<String> listeTache, ArrayList<Activite> listActivites) {
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
    }*/
}

    public void ajouterComposanteRobot(Composant composant, Robot robot) {
        for (int i = 0; i < listeRobot.size(); i++) {
            if (listeRobot.get(i).getNumeroSerie().equals(robot.getNumeroSerie())) {
                listeRobot.get(i).ajouterComposante(composant);
            }
        }
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
        for (Composant composant1 : getComposantesAchetes()) {
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

    public ArrayList<Composant> getComposantesAchetes() {
        return composantesAchetes;
    }

    public String getProfilUtilisateur(){
        return "Nom :" + this.getNom() + "\n Prenom :" + this.getPrenom() +
                "\n pseudo :" + pseudo + "\n adresse courriel : " +
                this.email + "\nTelephone : " + this.numeroTelephone +
                "\nInteret : " + this.getListeInteret().stream()
                .map(i -> i.getNom()).collect(Collectors.joining(","))+
                "\nNombre de point :" + this.point +
                "\nNombre de suiveur : " + this.getListSuiveur().size();
    }

    public String getPrenom() {
        return prenom;
    }

    public Set<Utilisateur> getListSuiveur() {
        return listSuiveur;
    }

    public void setListSuiveur(Set<Utilisateur> listSuiveur) {
        this.listSuiveur = listSuiveur;
    }


    public void rejoindreActivite(Activite activite) {
        listeActivitesRejoint.add(activite);
    }
    public ArrayList<Interet> getListeInteret() {
        return listeInteret;
    }

    public void setListeInteret(ArrayList<Interet> listeInteret) {
        this.listeInteret = listeInteret;
    }

    public ArrayList<Tache> getTacheEnListe(ArrayList<String> listeTache) {
         ArrayList<Tache> listeDeTaches = new ArrayList<Tache>();
         for(String tac : listeTache){
             listeDeTaches.add(listeTaches.stream()
                     .filter(t -> t.getNom().equals(tac))
                     .findFirst()
                     .orElse(null));
         }
         return listeDeTaches;
    }

    public ArrayList<Interet> produireListeInteret(ArrayList<String> listeInteret){
        ArrayList<Interet> listeInter = new ArrayList<>();
        for(String inter :listeInteret){
            listeInter.add(new Interet(inter));
        }
        return listeInter;
    }

}