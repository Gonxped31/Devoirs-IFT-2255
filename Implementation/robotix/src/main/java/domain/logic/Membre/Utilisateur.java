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

/**
 * Classe représentant un utilisateur de l'application qui gere des robots.
 * Ce dernier peut acheter et enregistrer des robots auprès de différent fournisseur.
 */
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
    private Set<Utilisateur> listSuiveur = new HashSet<>();
    private String pseudo;
    private String prenom;
    private int point;
    private Tache tache;
    private Notification notification;
    private int taillePrecedenteListeSuiveur;



    /**
     * Constructeur pour la classe Utilisateur.
     *
     * @param nom                Le nom de l'utilisateur.
     * @param prenom             Le prénom de l'utilisateur.
     * @param adresse            L'adresse de l'utilisateur.
     * @param pseudo             Le pseudo de l'utilisateur.
     * @param mdp                Le mot de passe de l'utilisateur.
     * @param email              L'adresse email de l'utilisateur.
     * @param numeroTelephone    Le numéro de téléphone de l'utilisateur.
     * @param nomCompagnie       Le nom de la compagnie de l'utilisateur.
     * @param listeInteret       Une collection de centres d'intérêt de l'utilisateur, représentée par un HashSet d'objets Interet.
     * @param listeNotifications Une liste des notifications de l'utilisateur, représentée par un ArrayList d'objets Notification.
     */
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
                       @JsonProperty("listeNotifications") ArrayList<Notification> listeNotifications) {

        super(nom, adresse, email, numeroTelephone, nomCompagnie, mdp);
        this.setPseudo(pseudo);
        this.setPrenom(prenom);
        this.setListeInteret(listeInteret);
        this.setListeNotifications(listeNotifications);
        this.point = 0;
    }

    /**
     * Récupère le pseudo de l'utilisateur.
     *
     * @return Le pseudo de l'utilisateur.
     */
    public String getPseudo(){
        return pseudo;
    }

    /**
     * Récupère le nombre de points de l'utilisateur.
     *
     * @return Le nombre de points de l'utilisateur.
     */
    public int getPoint() {
        return point;
    }

    /**
     * Récupère la liste des robots associés à l'utilisateur.
     *
     * @return Une ArrayList contenant les robots associés à l'utilisateur.
     */
    public ArrayList<Robot> getListeRobot() {
        return listeRobot;
    }

    /**
     * Récupère la liste des notifications de l'utilisateur.
     *
     * @return Une ArrayList contenant les notifications de l'utilisateur.
     */
    public ArrayList<Notification> getNotifs(){
        return listeNotifications;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère la liste des utilisateurs qui suivent l'utilisateur actuel.
     *
     * @return Un ensemble (Set) contenant les utilisateurs qui suivent l'utilisateur actuel.
     */
    public Set<Utilisateur> getListSuiveur() {
        return listSuiveur;
    }

    /**
     * Récupère la liste des activités auxquelles l'utilisateur s'est joint.
     *
     * @return Une ArrayList contenant les activités auxquelles l'utilisateur s'est joint.
     */
    public ArrayList<Activite> getListeActivitesRejoint() {
        return listeActivitesRejoint;
    }

    /**
     * Récupère la liste des activités créées par l'utilisateur.
     *
     * @return Une ArrayList contenant les activités créées par l'utilisateur.
     */
    public ArrayList<Activite> getListeActivitesCreer() {
        return listeActivitesCreer;
    }

    /**
     * Récupère la tâche associée à l'utilisateur.
     *
     * @return La tâche associée à l'utilisateur.
     */
    public Tache getTache() {
        return tache;
    }

    /**
     * Récupère la notification associée à l'utilisateur.
     *
     * @return La notification associée à l'utilisateur.
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * Récupère la liste des tâches associées à l'utilisateur.
     *
     * @return Une ArrayList contenant les tâches associées à l'utilisateur.
     */
    public ArrayList<Tache> getListeTaches() {
        return listeTaches;
    }

    /**
     * Récupère la liste des actions associées à l'utilisateur.
     *
     * @return Une ArrayList contenant les actions associées à l'utilisateur.
     */
    public ArrayList<Action> getListeActions() {
        return listeActions;
    }

    /**
     * Récupère la liste des utilisateurs suivis par l'utilisateur actuel.
     *
     * @return Un ensemble (Set) contenant les utilisateurs suivis par l'utilisateur actuel.
     */
    public HashSet<String> getListeUtilisateursSuivi() {
        return listeUtilisateursSuivi;
    }

    /**
     * Récupère la liste des notifications de l'utilisateur.
     *
     * @return Une ArrayList contenant les notifications de l'utilisateur.
     */
    public ArrayList<Notification> getListeNotifications() {
        return listeNotifications;
    }



    /**
     * Modifie le pseudo de l'utilisateur.
     *
     * @param pseudo Le nouveau pseudo de l'utilisateur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Modifie le nom de l'utilisateur.
     *
     * @param nom Le nouveau nom de l'utilisateur.
     */
    public void setNom(String nom){
        super.setNom(nom);
    }

    /**
     * Modifie le prénom de l'utilisateur.
     *
     * @param prenom Le nouveau prénom de l'utilisateur.
     */
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    /**
     * Modifie l'adresse de l'utilisateur.
     *
     * @param adresse La nouvelle adresse de l'utilisateur.
     */
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    /**
     * Modifie l'adresse email de l'utilisateur.
     *
     * @param email La nouvelle adresse email de l'utilisateur.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Modifie le numéro de téléphone de l'utilisateur.
     *
     * @param numeroTelephone Le nouveau numéro de téléphone de l'utilisateur.
     */
    public void setTelephone(String numeroTelephone){
        super.setTelephone(numeroTelephone);
    }

    /**
     * Modifie le nom de la compagnie de l'utilisateur.
     *
     * @param nomCompagnie Le nouveau nom de la compagnie de l'utilisateur.
     */
    public void setNomCompagnie(String nomCompagnie){
        this.nomCompagnie = nomCompagnie;
    }

    /**
     * Modifie la liste des composants achetés par l'utilisateur.
     *
     * @param composantesAchetes La nouvelle liste des composants achetés par l'utilisateur.
     */
    public void setComposantesAchetes(ArrayList<Composant> composantesAchetes) {
        this.composantesAchetes = composantesAchetes;
    }

    /**
     * Permet à l'utilisateur de suivre un autre utilisateur en ajoutant celui-ci à sa liste de suiveurs.
     *
     * @param suivi L'utilisateur à suivre.
     */
    public void suivreUtilisateur(Utilisateur suivi){
        taillePrecedenteListeSuiveur = getListeUtilisateursSuivi().size();
        getListeUtilisateursSuivi().add(suivi.getPseudo());
    }

    /**
     * Modifie la liste des utilisateurs qui suivent l'utilisateur actuel.
     *
     * @param listSuiveur La nouvelle liste des utilisateurs qui suivent l'utilisateur actuel.
     */
    public void setListSuiveur(Set<Utilisateur> listSuiveur) {
        this.listSuiveur = listSuiveur;
    }

    /**
     * Modifie la liste des centres d'intérêt de l'utilisateur.
     *
     * @param listeInteret La nouvelle liste des centres d'intérêt de l'utilisateur, représentée par un HashSet d'objets Interet.
     */
    public void setListeInteret(HashSet<Interet> listeInteret) {
        this.listeInteret = listeInteret;
    }

    /**
     * Modifie la liste des robots associés à l'utilisateur.
     *
     * @param listeRobot La nouvelle liste des robots associés à l'utilisateur.
     */
    public void setListeRobot(ArrayList<Robot> listeRobot) {
        this.listeRobot = listeRobot;
    }

    /**
     * Modifie la liste des tâches associées à l'utilisateur.
     *
     * @param listeTaches La nouvelle liste des tâches associées à l'utilisateur.
     */
    public void setListeTaches(ArrayList<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    /**
     * Modifie la liste des actions associées à l'utilisateur.
     *
     * @param listeActions La nouvelle liste des actions associées à l'utilisateur.
     */
    public void setListeActions(ArrayList<Action> listeActions) {
        this.listeActions = listeActions;
    }

    /**
     * Modifie la liste des utilisateurs suivis par l'utilisateur actuel.
     *
     * @param listeUtilisateursSuivi La nouvelle liste des utilisateurs suivis par l'utilisateur actuel.
     */
    public void setListeUtilisateursSuivi(HashSet<String> listeUtilisateursSuivi) {
        this.listeUtilisateursSuivi = listeUtilisateursSuivi;
    }

    /**
     * Modifie la liste des notifications de l'utilisateur.
     *
     * @param listeNotifications La nouvelle liste des notifications de l'utilisateur.
     */
    public void setListeNotifications( ArrayList<Notification> listeNotifications) {
        this.listeNotifications = listeNotifications;
    }

    /**
     * Modifie la liste des activités auxquelles l'utilisateur s'est joint.
     *
     * @param listeActivitesRejoint La nouvelle liste des activités auxquelles l'utilisateur s'est joint.
     */
    public void setListeActivitesRejoint(ArrayList<Activite> listeActivitesRejoint) {
        this.listeActivitesRejoint = listeActivitesRejoint;
    }

    /**
     * Modifie la liste des activités créées par l'utilisateur.
     *
     * @param listeActivitesCreer La nouvelle liste des activités créées par l'utilisateur.
     */
    public void setListeActivitesCreer(ArrayList<Activite> listeActivitesCreer) {
        this.listeActivitesCreer = listeActivitesCreer;
    }

    /**
     * Modifie le nombre de points de l'utilisateur.
     *
     * @param point Le nouveau nombre de points de l'utilisateur.
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * Modifie la tâche associée à l'utilisateur.
     *
     * @param tache La nouvelle tâche associée à l'utilisateur.
     */
    public void setTache(Tache tache) {
        this.tache = tache;
    }

    /**
     * Modifie la notification associée à l'utilisateur.
     *
     * @param notifs La nouvelle notification associée à l'utilisateur.
     */
    public void setNotifs(ArrayList<Notification> notifs) {
        this.notification = notification;
    }

    /**
     * Ajoute un utilisateur à la liste de suiveurs de l'utilisateur actuel.
     *
     * @param suiveur L'utilisateur qui souhaite suivre l'utilisateur actuel.
     */
    public void etreSuivi(Utilisateur suiveur){
        getListSuiveur().add(suiveur);
    }

    /**
     * Renvoie la liste des notifications de l'utilisateur.
     *
     * @return Une ArrayList contenant les notifications de l'utilisateur.
     */
    public ArrayList<Notification> voirNotifications(){
        return getListeNotifications();
    }

    /**
     * Modifie les informations du profil de l'utilisateur en fonction du choix spécifié.
     *
     * @param choix      Le champ à modifier (nom, prénom, adresse, pseudo, email, numéro de téléphone, nom de compagnie, mot de passe).
     * @param nouvelInfo La nouvelle valeur à attribuer au champ spécifié.
     */
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

    /**
     * Enregistre un robot dans la liste des robots de l'utilisateur.
     *
     * @param robot Le robot à enregistrer.
     * @return true si le robot existe déjà dans la liste, sinon false.
     */
    public boolean enregistrerRobot(Robot robot){
        boolean robotExiste=false;
        for( Robot r : listeRobot)
        {
            if(r.getNumero()==robot.getNumero())
            {
                robotExiste=true;
            }
        }
        if (!robotExiste ) {
            listeRobot.add(robot);
        }
        return  robotExiste;
    }

    /**
     * Renvoie une chaîne de caractères contenant la liste des robots disponibles sous la forme "nom : numéro de série".
     *
     * @return Une chaîne de caractères contenant la liste des robots disponibles.
     */
    public String listeRobot(){
        String result = "";
        for (Robot robot: listeRobot) {
            if (robot.estDisponible()){
                result += "\n-" + robot.getNom() + " : " + robot.getNumeroSerie().toString();
            }
        }
        return result;
    }

    /**
     * Crée une nouvelle tâche et l'ajoute à la liste des tâches de l'utilisateur.
     * La tâche est créée à partir du nom et de la liste des noms d'actions fournis.
     * La durée totale de la tâche est calculée en fonction de la durée de chaque action.
     *
     * @param nom           Le nom de la nouvelle tâche.
     * @param stringActions La liste des noms d'actions qui composent la tâche.
     */
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

    /**
     * Renvoie un robot de la liste des robots de l'utilisateur en fonction de son numéro de série.
     *
     * @param numeroDeSerie Le numéro de série du robot recherché.
     * @return Le robot correspondant au numéro de série spécifié, ou null s'il n'est pas trouvé.
     */

    public Robot getRobot(String numeroDeSerie){
        return listeRobot.stream()
                .filter(r -> r.getNumeroSerie().toString().equals(numeroDeSerie))
                .findFirst()
                .orElse(null);
    }

    /**
     * Renvoie une tâche de la liste des tâches de l'utilisateur en fonction de son nom.
     *
     * @param nom Le nom de la tâche recherchée.
     * @return La tâche correspondant au nom spécifié, ou null si elle n'est pas trouvée.
     */
    public Tache getTache(String nom){
        return listeTaches.stream()
                .filter(t -> t.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * Renvoie une chaîne de caractères contenant la liste des informations formatées des actions de l'utilisateur.
     *
     * @return Une chaîne de caractères contenant la liste des actions de l'utilisateur.
     */
    public String listeActions(){
        return listeActions.stream()
                        .map(Action::getInfoActionFormater)
                        .collect(Collectors.joining("\n\n"));
    }

    /**
     * Renvoie une chaîne de caractères contenant la liste des informations formatées des tâches de l'utilisateur.
     *
     * @return Une chaîne de caractères contenant la liste des tâches de l'utilisateur.
     */
    public String listeTaches(){
        return listeTaches.stream()
                .map(Tache::getInfoTacheFormater)
                .collect(Collectors.joining("\n\n"));
    }

    /**
     * Vérifie si un robot avec le numéro de série spécifié appartient à l'un des fournisseurs de la liste fournie.
     * Si le robot est trouvé dans l'inventaire d'un fournisseur, il est retiré de cet inventaire.
     *
     * @param numeroSerie      Le numéro de série du robot à vérifier.
     * @param listeFournisseur La liste des fournisseurs à vérifier.
     * @return Le robot avec le numéro de série spécifié s'il est trouvé, sinon null.
     */
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

    /**
     * Affiche l'état du robot correspondant au numéro de série spécifié.
     *
     * @param numSeri Le numéro de série du robot dont l'état doit être affiché.
     * @return Une chaîne de caractères contenant l'état du robot, ou un message indiquant que le robot n'a pas été trouvé.
     */
    public String afficherEtatRobot(String numSeri){
        return  this.listeRobot.stream().filter(r-> r.getNumeroSerie().toString().trim().equals(numSeri.trim()))
                .findFirst()
                .map(Robot::afficherEtatRobot)
                .orElse("Robot non trouver, veuiller verifier le numero de serie");
    }

    /**
     * Crée une nouvelle activité et l'ajoute à la liste des activités créées par l'utilisateur.
     *
     * @param nomActivite   Le nom de la nouvelle activité.
     * @param dateDebut     La date de début de l'activité au format "yyyy-MM-dd".
     * @param dateFin       La date de fin de l'activité au format "yyyy-MM-dd".
     * @param listeTache    La liste des tâches associées à l'activité.
     * @param listeInterets La liste des centres d'intérêt associés à l'activité.
     * @return true si l'activité a été créée avec succès, false si une activité avec le même nom existe déjà.
     * @throws ParseException Si une erreur se produit lors de la conversion des dates de chaînes de caractères en objets Date.
     */
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

    /**
     * Rejoint une activité spécifiée en l'ajoutant à la liste des activités rejointes par l'utilisateur.
     * Si l'activité est déjà rejointe, elle n'est pas ajoutée à la liste.
     *
     * @param activite L'activité à rejoindre.
     * @return true si l'activité a déjà été rejointe, false si elle a été ajoutée à la liste des activités rejointes.
     */
    public boolean rejoindreActivite(Activite activite) {
        Activite activite1 = getActiviteRejoint(activite.getNom());
        if (activite1 == null){
            listeActivitesRejoint.add(activite);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Renvoie une activité créée par l'utilisateur en fonction de son nom.
     *
     * @param nom Le nom de l'activité recherchée.
     * @return L'activité correspondant au nom spécifié, ou null si elle n'est pas trouvée.
     */
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

    /**
     * Renvoie une activité rejointe par l'utilisateur en fonction de son nom.
     *
     * @param nom Le nom de l'activité recherchée.
     * @return L'activité correspondant au nom spécifié, ou null si elle n'est pas trouvée.
     */
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

    /**
     * Vérifie si une activité avec le nom spécifié existe déjà dans la liste des activités créées par l'utilisateur.
     *
     * @param nomActivite Le nom de l'activité à vérifier.
     * @return true si une activité avec le même nom existe déjà, sinon false.
     */
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


    /**
     * Crée une nouvelle action et l'ajoute à la liste des actions de l'utilisateur.
     *
     * @param nomAction   Le nom de la nouvelle action.
     * @param composantes La liste des composantes associées à l'action.
     * @param duree       La durée de l'action.
     */
    public void creerAction(String nomAction, ArrayList<String> composantes, String duree){
        listeActions.add(new Action(nomAction, composantes, duree));
    }

    /**
     * Ajoute une composante à un robot spécifié en fonction de son numéro de série.
     *
     * @param composant La composante à ajouter au robot.
     * @param robot     Le robot auquel ajouter la composante.
     */
    public void ajouterComposanteRobot(Composant composant, Robot robot) {
        for (int i = 0; i < getListeRobot().size(); i++) {
            if (listeRobot.get(i).getNumeroSerie().equals(robot.getNumeroSerie())) {
                listeRobot.get(i).ajouterComposante(composant);
            }
        }
    }

    /**
     * Recherche et renvoie un robot de la liste des robots de l'utilisateur en fonction de son nom.
     *
     * @param nom Le nom du robot recherché.
     * @return Le robot correspondant au nom spécifié, ou null s'il n'est pas trouvé.
     */
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

    /**
     * Recherche et renvoie une composante de la liste des composantes achetées par l'utilisateur en fonction de son nom.
     *
     * @param composante Le nom de la composante recherchée.
     * @return La composante correspondant au nom spécifié, ou null si elle n'est pas trouvée.
     */
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

    /**
     * Renvoie le nombre de robots dans la liste des robots de l'utilisateur.
     *
     * @return Le nombre de robots dans la liste.
     */
    public int nombreDeRobot(){
        return getListeRobot().size();
    }

    /**
     * Renvoie la liste des composantes achetées par l'utilisateur.
     *
     * @return Une ArrayList contenant les composantes achetées par l'utilisateur.
     */
    public ArrayList<Composant> getComposantesAchetes() {
        return composantesAchetes;
    }

    /**
     * Ajoute une composante à la liste des composantes achetées par l'utilisateur.
     *
     * @param composant La composante à ajouter.
     */
    public void ajouterComposante(Composant composant){
        composantesAchetes.add(composant);
    }

    /**
     * Renvoie le profil de l'utilisateur sous forme de chaîne de caractères avec les informations clés.
     *
     * @return Une chaîne de caractères contenant les informations clés du profil de l'utilisateur.
     */
    @JsonIgnore
    public String getProfilUtilisateur(){
        return "Nom: " + this.getNom() + "\nPrenom: " + this.getPrenom() +
                "\npseudo: " + getPseudo() + "\nadresse courriel: " +
                this.email + "\nTelephone: " + this.getTelephone() +
                "\nInteret: " + this.getListeInteret().stream()
                .map(i -> i.getNom()).collect(Collectors.joining(","))+
                "\nNombre de point  " + this.getPoint() +
                "\nNombre de suiveur: " + this.getListSuiveur().size();
    }

    /**
     * Renvoie la liste des centres d'intérêt de l'utilisateur.
     *
     * @return Un HashSet contenant les centres d'intérêt de l'utilisateur.
     */
    public HashSet<Interet> getListeInteret() {
        return listeInteret;
    }

    /**
     * Renvoie une liste de tâches associées à l'utilisateur en fonction d'une liste de noms de tâches.
     *
     * @param listeTache La liste des noms de tâches recherchées.
     * @return Une ArrayList contenant les tâches correspondant aux noms spécifiés.
     */
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

    /**
     * Crée une liste de centres d'intérêt à partir d'une liste de noms de centres d'intérêt fournie.
     *
     * @param listeInteret La liste des noms de centres d'intérêt.
     * @return Un HashSet contenant les centres d'intérêt créés à partir des noms spécifiés.
     */
    public static HashSet<Interet> produireListeInteret(ArrayList<String> listeInteret){
        HashSet<Interet> listeInter = new HashSet<>();
        for(String inter :listeInteret){
            listeInter.add(new Interet(inter));
        }
        return listeInter;
    }

    /**
     * Ajoute une nouvelle notification à la liste des notifications de l'utilisateur.
     *
     * @param titre            Le titre de la notification.
     * @param message          Le message de la notification.
     * @param typeNotification Le type de la notification.
     */
    public void addNotifs(String titre,String message, TypeNotification typeNotification){
        this.listeNotifications.add(new Notification(titre, message, typeNotification));
    }

    /**
     * Ajoute un nouveau centre d'intérêt à la liste des centres d'intérêt de l'utilisateur.
     *
     * @param i Le centre d'intérêt à ajouter.
     */
    public void ajouterUnInteret(Interet i){
        this.listeInteret.add(i);
    }

    /**
     * Renvoie une chaîne de caractères contenant la liste des pseudonymes des utilisateurs suivis par l'utilisateur actuel.
     *
     * @return Une chaîne de caractères contenant la liste des pseudonymes des utilisateurs suivis.
     */
    public ArrayList<String>  voirListeUtilisateur() {
        ArrayList<String> liste = new ArrayList<>();
        liste.addAll(this.listeUtilisateursSuivi);
        return liste;
    }

    /**
     * Supprime un utilisateur de la liste des utilisateurs suivis par l'utilisateur actuel.
     *
     * @param utilASupprimer Le pseudonyme de l'utilisateur à supprimer de la liste des suivis.
     * @return true si l'utilisateur a été supprimé avec succès, false s'il n'a pas été trouvé dans la liste.
     */
        public boolean supprimerUtilisateurDeMaListe(String utilASupprimer) {
            for (String u : this.listeUtilisateursSuivi){
                if (u.equals(utilASupprimer)){
                    this.listeUtilisateursSuivi.remove(u);
                    return true;
                }
            }
            return false;
        }

    /**
     * Ajoute un robot à la liste des robots de l'utilisateur.
     *
     * @param robot Le robot a ajouter à la liste.
     */
    public void ajouterRobot(Robot robot) {
        System.out.println(listeRobot);
        listeRobot.add(robot);
        System.out.println(listeRobot);
    }

    /**
     * Désabonne l'utilisateur d'un centre d'intérêt spécifié en le supprimant de sa liste de centres d'intérêt.
     *
     * @param choix Le nom du centre d'intérêt à désabonner.
     */
    public void desabonnerInteret(String choix) {
        Iterator<Interet> iterator = getListeInteret().iterator();
        while (iterator.hasNext()) {
            Interet i = iterator.next();
            if (i.getNom().equals(choix)) {
                iterator.remove();
            }
        }
    }

    /**
     * Renvoie la composante de la liste des composantes achetées par l'utilisateur en fonction de son nom.
     *
     * @param nomComposante Le nom de la composante recherchée.
     * @return La composante correspondant au nom spécifié, ou null si elle n'est pas trouvée.
     */
    public Composant retournerComposante(String nomComposante) {
        Composant resultat = null;
        for (Composant c: composantesAchetes) {
            if (c.getNom().equals(nomComposante.trim())){
                resultat = c;
            }
        }
        return resultat;
    }

    /**
     * Retrouve un robot de la liste des robots de l'utilisateur en fonction de son nom.
     *
     * @param nomRobot Le nom du robot recherché.
     * @return Le robot correspondant au nom spécifié, ou null s'il n'est pas trouvé.
     */
    public Robot retrouverRobotNom(String nomRobot){
        return listeRobot.stream()
                .filter(robot -> robot.getNom().equals(nomRobot))
                .findFirst()
                .orElse(null);
    }

    /**
     * Renvoie un robot de la liste des robots de l'utilisateur en fonction de son numéro de série.
     *
     * @param numeroSerie Le numéro de série du robot recherché.
     * @return Le robot correspondant au numéro de série spécifié, ou null s'il n'est pas trouvé.
     */
    public Robot retournerRobotNumeroSerie(String numeroSerie) {
        return listeRobot.stream()
                .filter(robot -> robot.getNumeroSerie().toString().equals(numeroSerie))
                .findFirst()
                .orElse(null);
    }
}