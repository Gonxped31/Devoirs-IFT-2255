package domain.logic.Membre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Robot.*;





public class Utilisateur extends Membre implements java.io.Serializable{

    private ArrayList<Robot> listeRobot = new ArrayList<>();
    private ArrayList<Tache> listeTaches = new ArrayList<>();
    private ArrayList<Action> listeActions = new ArrayList<>();
    private ArrayList<Composant> composantesAchetes = new ArrayList<>();
    private HashSet<String> listeUtilisateursSuivi = new HashSet<>();
    private HashSet<Interet> listeInteret = new HashSet<>();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private ArrayList<Activite> listeActivitesRejoint = new ArrayList<>();
    private ArrayList<Activite> listeActivitesCreer = new ArrayList<>();
    private HashSet<String> listSuiveur = new HashSet<>();
    private String pseudo;
    private String prenom;
    private int point;
    private Tache tache;
    private Notification notification;
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
                       @JsonProperty("listeInteret") HashSet<Interet> listeInteret,
                       @JsonProperty("listeNotifications") ArrayList<Notification> listeNotifications,
                       @JsonProperty("listSuiveur") HashSet<String> listeSuiveur) {

        super(nom, adresse, email, numeroTelephone, nomCompagnie, mdp);
        this.setPseudo(pseudo);
        this.setPrenom(prenom);
        this.setListeInteret(listeInteret);
        this.setListeNotifications(listeNotifications);
        setListSuiveur(listeSuiveur);
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

    public void suivreUtilisateur(Utilisateur suivi){
        taillePrecedenteListeSuiveur = getListeUtilisateursSuivi().size();
        getListeUtilisateursSuivi().add(suivi.getPseudo());
    }

    public void etreSuivi(Utilisateur suiveur){
        getListSuiveur().add(suiveur.getPseudo());
    }

    public ArrayList<Notification> voirNotifications(){
        return getListeNotifications();
    }

    public void modifierProfile(String choix, String nouvelInfo){
        switch (choix.toLowerCase()) {
            case "nom" -> this.setNom(nouvelInfo);
            case "prenom" -> this.setPrenom(nouvelInfo);
            case "adresse" -> this.setAdresse(nouvelInfo);
            case "pseudo" -> this.setPseudo(nouvelInfo);
            case "email" -> this.setEmail(nouvelInfo);
            case "numerotelephone" -> this.setTelephone(nouvelInfo);
            case "nomcompagnie" -> this.setNomCompagnie(nouvelInfo);
            case "mdp" -> this.setMotDePasse(nouvelInfo);
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

    public void creerTache(String nom, ArrayList<String> stringActions){
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

    public boolean creerActivite(String nomActivite, String dateDebut, String dateFin, ArrayList<Tache> listeTache, HashSet<Interet> listeInterets) throws ParseException {
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
            if (listeRobot.get(i).getNumeroSerie().equals(robot.getNumeroSerie())) {
                listeRobot.get(i).ajouterComposante(composant);
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

    public void ajouterComposante(Composant composant){
        composantesAchetes.add(composant);
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

    public Set<String> getListSuiveur() {
        return listSuiveur;
    }

    public void setListSuiveur(HashSet<String> listSuiveur) {
        this.listSuiveur = listSuiveur;
    }

    public HashSet<Interet> getListeInteret() {
        return listeInteret;
    }

    public void setListeInteret(HashSet<Interet> listeInteret) {
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

    public static HashSet<Interet> produireListeInteret(ArrayList<String> listeInteret){
        HashSet<Interet> listeInter = new HashSet<>();
        for(String inter :listeInteret){
            listeInter.add(new Interet(inter));
        }
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

    public HashSet<String> getListeUtilisateursSuivi() {
        return listeUtilisateursSuivi;
    }

    public void setListeUtilisateursSuivi(HashSet<String> listeUtilisateursSuivi) {
        this.listeUtilisateursSuivi = listeUtilisateursSuivi;
    }

    public ArrayList<Notification> getListeNotifications() {
        return listeNotifications;
    }


    public void setListeNotifications( ArrayList<Notification> listeNotifications) {
        this.listeNotifications = listeNotifications;
    }

    public void addNotifs(String titre,String message, TypeNotification typeNotification){
        this.listeNotifications.add(new Notification(titre, message, typeNotification));
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

    public String voirListeUtilisateur() {
        StringBuilder sb = new StringBuilder();
        for (String u : this.listeUtilisateursSuivi) {
            sb.append(u).append("\n");
        }
        return sb.toString();
    }

    public boolean supprimerUtilisateurDeMaListe(String utilASupprimer) {
        for (String u : this.listeUtilisateursSuivi){
            if (u.equals(utilASupprimer)){
                this.listeUtilisateursSuivi.remove(u);
                return true;
            }
        }
        return false;
    }

    public void ajouterRobot(Robot robot) {
        System.out.println(listeRobot);
        listeRobot.add(robot);
        System.out.println(listeRobot);
    }

    public void desabonnerInteret(String choix) {
        Iterator<Interet> iterator = getListeInteret().iterator();
        while (iterator.hasNext()) {
            Interet i = iterator.next();
            if (i.getNom().equals(choix)) {
                iterator.remove(); // Safely remove the element using the iterator
            }
        }
    }

    public Composant retournerComposante(String nomComposante) {
        Composant resultat = null;
        for (Composant c: composantesAchetes) {
            if (c.getNom().equals(nomComposante.trim())){
                resultat = c;
            }
        }
        return resultat;
    }

    public Robot retrouverRobotNom(String nomRobot){
        return listeRobot.stream()
                .filter(robot -> robot.getNom().equals(nomRobot))
                .findFirst()
                .orElse(null);
    }

    public Robot retournerRobotNumeroSerie(String numeroSerie) {
        return listeRobot.stream()
                .filter(robot -> robot.getNumeroSerie().toString().equals(numeroSerie))
                .findFirst()
                .orElse(null);
    }
}