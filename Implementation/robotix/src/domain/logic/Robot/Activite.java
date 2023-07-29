package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Activite implements java.io.Serializable{
    private String auteur;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList<Tache> listeDeTache = new ArrayList<>();
    private ArrayList<String> listeUtilisateurInsccrit = new ArrayList<>();
    private ArrayList<Interet> listeInteretAssocie = new ArrayList<>();
    private int points;
    private int dureeActivite;
    private ArrayList<Robot> listeRobotsInscrits = new ArrayList<>();
    private final Random random = new Random();

    public Activite () {

    }

    @JsonCreator
    public Activite(@JsonProperty("auteur") String auteur,
                    @JsonProperty("nom") String nom,
                    @JsonProperty("dateDebut") Date dateDebut,
                    @JsonProperty("dateFin") Date dateFin,
                    @JsonProperty("listeDeTache") ArrayList<Tache> listeDeTache,
                    @JsonProperty("listeInteretAssocie") ArrayList<Interet> listeInteretAssocie){
        this.auteur = auteur;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeDeTache = listeDeTache;
        this.listeInteretAssocie = listeInteretAssocie;
        this.points = random.nextInt(50 - 10 + 1) + 10;
    }

    //Getters
    public String getAuteur() {
        return auteur;
    }
    public String getNom() {
        return nom;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public ArrayList<Tache> getListeDeTache() {
        return listeDeTache;
    }
    public ArrayList<Interet> getListeInteretAssocie() {
        return listeInteretAssocie;
    }
    public ArrayList<String> getListeUtilisateurInsccrit() {
        return listeUtilisateurInsccrit;
    }
    public int getPoints() {
        return points;
    }
    public int getDureeActivite() {
        return dureeActivite;
    }

    public ArrayList<Robot> getListeRobotsInscrits() {
        return listeRobotsInscrits;
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setListeDeTache(ArrayList<Tache> listeDeTache) {
        this.listeDeTache = listeDeTache;
    }
    public void setListeUtilisateurInsccrit(ArrayList<String> listeUtilisateurInsccrit) {
        this.listeUtilisateurInsccrit = listeUtilisateurInsccrit;
    }
    public void setListeInteretAssocie(ArrayList<Interet> listeInteretAssocie) {
        this.listeInteretAssocie = listeInteretAssocie;
    }
    public void setDureeActivite(int dureeActivite) {
        this.dureeActivite = dureeActivite;
    }

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
    public void rejoindreActivvite(Utilisateur u){

    }
}
