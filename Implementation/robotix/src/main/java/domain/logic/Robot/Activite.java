package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Activite implements java.io.Serializable{
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private static int numeroActivite = 0;
    private ArrayList<Tache> listeDeTache = new ArrayList<>();
    private ArrayList<Utilisateur> listeUtilisateurInsccrit = new ArrayList<>();
    private ArrayList<Interet> listeInteretAssocie = new ArrayList<>();

    public Activite () {

    }
    @JsonCreator
    public Activite(
            @JsonProperty("nom") String nom,
            @JsonProperty("dateDebut") Date dateDebut,
            @JsonProperty("dateFin") Date dateFin,
            @JsonProperty("listeDeTache") ArrayList<Tache> listeDeTache,
            @JsonProperty("listeInteretAssocie") ArrayList<Interet> listeInteretAssocie
    ){
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeDeTache = listeDeTache;
        this.listeInteretAssocie = listeInteretAssocie;
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

    public ArrayList<Utilisateur> getListeUtilisateurInsccrit() {
        return listeUtilisateurInsccrit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public static void setNumeroActivite(int numeroActivite) {
        Activite.numeroActivite = numeroActivite;
    }

    public void setListeDeTache(ArrayList<Tache> listeDeTache) {
        this.listeDeTache = listeDeTache;
    }

    public void setListeUtilisateurInsccrit(ArrayList<Utilisateur> listeUtilisateurInsccrit) {
        this.listeUtilisateurInsccrit = listeUtilisateurInsccrit;
    }

    public void setListeInteretAssocie(ArrayList<Interet> listeInteretAssocie) {
        this.listeInteretAssocie = listeInteretAssocie;
    }
    @JsonIgnore
    public String getInfoActiviteFormater(){
        numeroActivite++;
        return numeroActivite+"-"+" Nom : " + this.nom +
                "\nDate de debut :" + this.dateDebut.toString() +
                "\nDate de fin : " + this.dateFin.toString() +
                "Liste de tache : " + listeDeTache.stream()
                .map(tache -> tache.getInfoTacheFormater())
                .collect(Collectors.joining("\n"));
    }
    public void rejoindreActivvite(Utilisateur u){

    }
}
