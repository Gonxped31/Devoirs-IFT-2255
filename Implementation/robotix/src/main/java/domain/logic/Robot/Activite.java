package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Représente une activité pouvant être organisée par un utilisateur.
 * Cette classe est sérialisable en JSON à l'aide de la bibliothèque Jackson.
 */
public class Activite implements java.io.Serializable{
    private String auteur;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList<Tache> listeDeTache = new ArrayList<>();
    private ArrayList<String> listeUtilisateurInsccrit = new ArrayList<>();
    private HashSet<Interet> listeInteretAssocie = new HashSet<>();
    private int points;
    private int dureeActivite;
    private ArrayList<Robot> listeRobotsInscrits = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Constructeur de la classe Activite.
     *
     * @param auteur             l'auteur de l'activité
     * @param nom                le nom de l'activité
     * @param dateDebut          la date de début de l'activité
     * @param dateFin            la date de fin de l'activité
     * @param listeDeTache       la liste des tâches associées à l'activité
     * @param listeInteretAssocie la liste des intérêts associés à l'activité
     */
    @JsonCreator
    public Activite(@JsonProperty("auteur") String auteur,
                    @JsonProperty("nom") String nom,
                    @JsonProperty("dateDebut") Date dateDebut,
                    @JsonProperty("dateFin") Date dateFin,
                    @JsonProperty("listeDeTache") ArrayList<Tache> listeDeTache,
                    @JsonProperty("listeInteretAssocie") HashSet<Interet> listeInteretAssocie){
        this.auteur = auteur;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeDeTache = listeDeTache;
        this.listeInteretAssocie = listeInteretAssocie;
        this.points = random.nextInt(50 - 10 + 1) + 10;
    }

    /**
     * Constructeur par défaut de la classe Activité.
     */
    public Activite(){

    }

    /**
     * Renvoie l'auteur de l'activité.
     *
     * @return l'auteur de l'activité
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * Renvoie le nom de l'activité.
     *
     * @return le nom de l'activité
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la date de début de l'activité.
     *
     * @return la date de début de l'activité
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Renvoie la date de fin de l'activité.
     *
     * @return la date de fin de l'activité
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Renvoie la liste des tâches associées à l'activité.
     *
     * @return la liste des tâches associées à l'activité
     */
    public ArrayList<Tache> getListeDeTache() {
        return listeDeTache;
    }


    /**
     * Renvoie la liste des intérêts associés à l'activité.
     *
     * @return la liste des intérêts associés à l'activité
     */
    public HashSet<Interet> getListeInteretAssocie() {
        return listeInteretAssocie;
    }

    /**
     * Renvoie la liste des utilisateurs inscrits à l'activité.
     *
     * @return la liste des utilisateurs inscrits à l'activité
     */
    public ArrayList<String> getListeUtilisateurInsccrit() {
        return listeUtilisateurInsccrit;
    }

    /**
     * Renvoie le nombre de points gagnés en participant à l'activité.
     *
     * @return le nombre de points gagnés en participant à l'activité
     */
    public int getPoints() {
        return points;
    }

    /**
     * Renvoie la durée de l'activité.
     *
     * @return la durée de l'activité
     */
    public int getDureeActivite() {
        return dureeActivite;
    }

    /**
     * Renvoie la liste des robots inscrits à l'activité.
     *
     * @return la liste des robots inscrits à l'activité
     */
    public ArrayList<Robot> getListeRobotsInscrits() {
        return listeRobotsInscrits;
    }


    /**
     * Définit le nom de l'activité.
     *
     * @param nom le nouveau nom de l'activité à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la date de début de l'activité.
     *
     * @param dateDebut la nouvelle date de début de l'activité à définir
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Définit la date de fin de l'activité.
     *
     * @param dateFin la nouvelle date de fin de l'activité à définir
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Définit la liste des tâches associées à l'activité.
     *
     * @param listeDeTache la nouvelle liste des tâches associées à l'activité à définir
     */
    public void setListeDeTache(ArrayList<Tache> listeDeTache) {
        this.listeDeTache = listeDeTache;
    }

    /**
     * Définit la liste des utilisateurs inscrits à l'activité.
     *
     * @param listeUtilisateurInsccrit la nouvelle liste des utilisateurs inscrits à l'activité à définir
     */
    public void setListeUtilisateurInsccrit(ArrayList<String> listeUtilisateurInsccrit) {
        this.listeUtilisateurInsccrit = listeUtilisateurInsccrit;
    }

    /**
     * Définit la liste des intérêts associés à l'activité.
     *
     * @param listeInteretAssocie la nouvelle liste des intérêts associés à l'activité à définir
     */
    public void setListeInteretAssocie(HashSet<Interet> listeInteretAssocie) {
        this.listeInteretAssocie = listeInteretAssocie;
    }

    /**
     * Définit la durée de l'activité.
     *
     * @param dureeActivite la nouvelle durée de l'activité à définir
     */
    public void setDureeActivite(int dureeActivite) {
        this.dureeActivite = dureeActivite;
    }

    /**
     * Méthode pour obtenir l'information de l'activité formatée.
     * Cette méthode est utilisée pour afficher l'information de l'activité dans un format spécifique.
     *
     * @return une chaîne de caractères représentant l'information de l'activité formatée
     */
    @JsonIgnore
    public String getInfoActiviteFormater(){
        return "\n\nNom : " + this.nom +
                "\nAuteur : " + this.auteur +
                "\nPoints gagnes : " + this.points +
                "\nDate de debut :" + this.dateDebut.toString() +
                "\nDate de fin : " + this.dateFin.toString() +
                "\nListe de tache : " + listeDeTache.stream()
                .map(tache -> tache.getInfoTacheFormater())
                .collect(Collectors.joining("\n"));

    }
}
