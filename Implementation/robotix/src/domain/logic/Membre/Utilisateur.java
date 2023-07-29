package domain.logic.Membre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Robot.*;

public class Utilisateur extends Membre implements java.io.Serializable{

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

    @JsonCreator
    public Utilisateur(@JsonProperty("nom") String nom,
                       @JsonProperty("prenom") String prenom,
                       @JsonProperty("adresse") String adresse,
                       @JsonProperty("pseudo") String pseudo,
                       @JsonProperty("mdp") String mdp,
                       @JsonProperty("email") String email,
                       @JsonProperty("numeroTelephone") String numeroTelephone,
                       @JsonProperty("nomCompagnie") String nomCompagnie,
                       @JsonProperty("listeInteret") ArrayList<Interet> listeInteret) {

        super(nom, adresse, email, numeroTelephone, nomCompagnie, mdp);
        this.setPseudo(pseudo);
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
        return getListeNotifications();
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

    public void setTelephone(String numeroTelephone){
        super.setTelephone(numeroTelephone);
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

    public void suivreUtilisateur(Utilisateur suivi){
        taillePrecedenteListeSuiveur = getListeUtilisateursSuivi().size();
        getListeUtilisateursSuivi().add(suivi);
    }

    public void etreSuivi(Utilisateur suiveur){
        getListSuiveur().add(suiveur);
    }

    public ArrayList<Notification> voirNotifications(){
        return getListeNotifications();
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

        for (Robot robot : getListeRobot()) {
            if (robot.getVitesse() == 0 || robot.getMemoire() == 0) {
                DoitEtreNotifie = true;
                getNotification().setTitre("MAUVAIS FONCTIONNEMENT");
                getNotification().setMesssage("Le robot " + robot.getNom() + " éprouve un problème de fonctionnement.");
                getNotification().setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                getListeNotifications().add(getNotification());
            }
        }
        return DoitEtreNotifie;
    }

    private boolean verifierBatterieRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : getListeRobot()) {
            if (robot.getBatterie() >= 20) {
                DoitEtreNotifie = true;
                getNotification().setTitre("BATTERIE FAIBLE");
                getNotification().setMesssage("La batterie du robot " + robot.getNom() + " est à " + robot.getBatterie() + "%.");
                getNotification().setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                getListeNotifications().add(getNotification());
            }
        }
        return DoitEtreNotifie;
    }

    private boolean verifierCPURobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : getListeRobot()) {
            if (robot.getCpu() >= 100) {
                DoitEtreNotifie = true;
                getNotification().setTitre("SURCHARGE CPU");
                getNotification().setMesssage("Le CPU du robot " + robot.getNom() + " est surchagé");
                getNotification().setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                getListeNotifications().add(getNotification());
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

        if (getListSuiveur().size() > taillePrecedenteListeSuiveur) {
            DoitEtreNotifie = true;
            getNotification().setTitre("NOUVEAU ABONNÉ");
            getNotification().setMesssage("Un nouvel utilisateur suit votre profil");
            getNotification().setTypeNotification(TypeNotification.NOUVEAU_ABONNE);
            getListeNotifications().add(getNotification());
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

        for (Activite activite : getListeActivitesRejoint()) {
            joursRestants = ChronoUnit.DAYS.between(dateActuelle, (Temporal) activite.getDateDebut());

            // Vérifiez si la date de l'activité est dans les trois jours à venir
            if (joursRestants >=0 && joursRestants <=3) {
                DoitEtreNotifie = true;
                getNotification().setTitre("RAPPEL D'UNE DE VOS ACTIVITÉS");
                getNotification().setMesssage("Il ne reste que " + joursRestants + " avant le début de l'activité " + activite.getNom());
                getNotification().setTypeNotification(TypeNotification.SENSIBILISATION);
                getListeNotifications().add(getNotification());
            }
        }
        return DoitEtreNotifie;
    }

    public void modifierProfile(String choix, String nouvelInfo){
        switch (choix.toLowerCase()) {
            case "nom" -> this.setNom(nouvelInfo);
            case "pseudo" -> this.setPseudo(nouvelInfo);
            case "prenom" -> this.setPrenom(nouvelInfo);
            case "adresse" -> this.setAdresse(nouvelInfo);
            case "email" -> this.setEmail(nouvelInfo);
            case "numerotelephone" -> this.setTelephone(nouvelInfo);
            case "nomcompagnie" -> this.setNomCompagnie(nouvelInfo);
        }

    }

    public boolean enregistrerRobot(String nomRobot, Robot robot, String type){//, String numeroSerie, ArrayList<Fournisseur> listeFournisseur){
        robot.setNom(nomRobot);
        robot.setType(type);

       boolean robotExiste= this.listeRobot.stream()
               .anyMatch(r-> robot.getNumeroSerie().equals(robot.getNumeroSerie()));
       if (!robotExiste ) {
           listeRobot.add(robot);
       }
        return  robotExiste;
    }
    public String listeRobot(){
        String result = "";
        for (Robot robot: listeRobot) {
            if (robot.estDisponible()){
                result += "\n-" + robot.getNom() + " : " + robot.getNumeroSerie().toString();
            }
        }
        return result;
    }

    public ArrayList<String> creerTache(String nom, ArrayList<String> stringActions){
        Map<String, Action> map = new HashMap<>();
        int dureeTache = 0;
        listeActions.forEach(action -> map.put(action.getNomAction(), action));
        ArrayList<String> actionList = new ArrayList<>(map.keySet());
        ArrayList<Action> actions = new ArrayList<>();
        int numberOfActions = stringActions.size();
        for (int i = 0; i < numberOfActions; i++) {
            String act = stringActions.get(0);
            if (actionList.contains(act.trim())){
                Action action = map.get(act);
                dureeTache += Integer.parseInt(action.getDuree());
                actions.add(action);
                stringActions.remove(0);
            }
        }

        if (actions.size() == numberOfActions){
            Tache tache = new Tache(nom, actions);
            tache.setDureeTache(dureeTache);
            listeTaches.add(tache);
        }
        return stringActions;
    }

    public Robot getRobot(String numeroDeSerie){
        return listeRobot.stream()
                .filter(r -> r.getNumeroSerie().toString().equals(numeroDeSerie))
                .findFirst()
                .orElse(null);
    }

    public Tache getTache(String nom){
        return listeTaches.stream()
                .filter(t -> t.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    public String listeActions(){
        return listeActions.stream()
                        .map(Action::getInfoActionFormater)
                        .collect(Collectors.joining("\n\n"));
    }

    public String listeTaches(){
        return listeTaches.stream()
                .map(Tache::getInfoTacheFormater)
                .collect(Collectors.joining("\n\n"));
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
        return this.getListeRobot();
    }

    public boolean creerActivite(String nomActivite, String dateDebut, String dateFin, ArrayList<Tache> listeTache, ArrayList<Interet> listeInterets) throws ParseException {
        if (!verifierExistenceActivite(nomActivite)){
            int dureeActivitee = listeTache.stream()
                    .mapToInt(Tache::getDureeTache)
                    .sum();
            Activite activite = new Activite(pseudo, nomActivite, new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut),
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateFin), listeTache, listeInterets);
            activite.setDureeActivite(dureeActivitee);
            listeActivitesCreer.add(activite);
            return true;
        } else {
            return false;
        }
    }
    public boolean rejoindreActivite(Activite activite) {
        Activite activite1 = getActiviteRejoint(activite.getNom());
        if (activite1 == null){
            listeActivitesRejoint.add(activite);
            return false;
        } else {
            return true;
        }
    }
    public Activite getActiviteCree(String nom){
        Activite activite1 = null;
        for (Activite activite: listeActivitesCreer) {
            if (activite.getNom().equals(nom)){
                activite1 = activite;
                break;
            }
        }
        return activite1;
    }

    public Activite getActiviteRejoint(String nom){
        Activite activite1 = null;
        for (Activite activite: listeActivitesRejoint) {
            if (activite.getNom().equals(nom)){
                activite1 = activite;
                break;
            }
        }
        return activite1;
    }

    private boolean verifierExistenceActivite(String nomActivite){
        boolean result = false;
        for (Activite activite1 : listeActivitesCreer) {
            if (activite1.getNom().equals(nomActivite)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void creerAction(String nomAction, ArrayList<String> composantes, String duree){
        listeActions.add(new Action(nomAction, composantes, duree));
    }

    public void ajouterComposanteRobot(Composant composant, Robot robot) {
        for (int i = 0; i < getListeRobot().size(); i++) {
            if (getListeRobot().get(i).getNumeroSerie().equals(robot.getNumeroSerie())) {
                getListeRobot().get(i).ajouterComposante(composant);
            }
        }
    }

    public Robot trouverRobot(String nom){
        Robot resultat = null;
        for (Robot robot : getListeRobot()) {
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
        return getListeRobot().size();
    }

    public ArrayList<Composant> getComposantesAchetes() {
        return composantesAchetes;
    }
    @JsonIgnore
    public String getProfilUtilisateur(){
        return "Nom :" + this.getNom() + "\n Prenom :" + this.getPrenom() +
                "\n pseudo :" + getPseudo() + "\n adresse courriel : " +
                this.email + "\nTelephone : " + this.getTelephone() +
                "\nInteret : " + this.getListeInteret().stream()
                .map(i -> i.getNom()).collect(Collectors.joining(","))+
                "\nNombre de point :" + this.getPoint() +
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



    public ArrayList<Interet> getListeInteret() {
        return listeInteret;
    }

    public void setListeInteret(ArrayList<Interet> listeInteret) {
        this.listeInteret = listeInteret;
    }

    public ArrayList<Tache> getTacheEnListe(ArrayList<String> listeTache) {
         ArrayList<Tache> listeDeTaches = new ArrayList<Tache>();
         listeTache.forEach(tac -> {
             listeDeTaches.add(getListeTaches().stream()
                     .filter(t -> t.getNom().equals(tac))
                     .findFirst()
                     .orElse(null));
         });

         return listeDeTaches;
    }

    public static ArrayList<Interet> produireListeInteret(ArrayList<String> listeInteret){
        ArrayList<Interet> listeInter = new ArrayList<>();
        listeInteret.forEach(inter -> listeInter.add(new Interet(inter)));
        return listeInter;
    }

    public void setListeRobot(ArrayList<Robot> listeRobot) {
        this.listeRobot = listeRobot;
    }

    public ArrayList<Tache> getListeTaches() {
        return listeTaches;
    }

    public void setListeTaches(ArrayList<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public ArrayList<Action> getListeActions() {
        return listeActions;
    }

    public void setListeActions(ArrayList<Action> listeActions) {
        this.listeActions = listeActions;
    }

    public Set<Utilisateur> getListeUtilisateursSuivi() {
        return listeUtilisateursSuivi;
    }

    public void setListeUtilisateursSuivi(Set<Utilisateur> listeUtilisateursSuivi) {
        this.listeUtilisateursSuivi = listeUtilisateursSuivi;
    }

    public ArrayList<Notification> getListeNotifications() {
        return listeNotifications;
    }

    public void setListeNotifications(ArrayList<Notification> listeNotifications) {
        this.listeNotifications = listeNotifications;
    }

    public ArrayList<Activite> getListeActivitesRejoint() {
        return listeActivitesRejoint;
    }

    public void setListeActivitesRejoint(ArrayList<Activite> listeActivitesRejoint) {
        this.listeActivitesRejoint = listeActivitesRejoint;
    }

    public ArrayList<Activite> getListeActivitesCreer() {
        return listeActivitesCreer;
    }

    public void setListeActivitesCreer(ArrayList<Activite> listeActivitesCreer) {
        this.listeActivitesCreer = listeActivitesCreer;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Notification getNotification() {
        return notification;
    }


    public void setNotifs(ArrayList<Notification> notifs) {
        this.notification = notification;
    }
    public void ajouterUnInteret(Interet i){
        this.listeInteret.add(i);
    }

}