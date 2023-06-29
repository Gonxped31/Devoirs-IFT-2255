package domain.logic.Robot;

import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Activite {
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private static int numeroActivite = 0;
    private ArrayList<Tache> listeDeTache = new ArrayList<>();
    private ArrayList<Utilisateur> listeUtilisateurInsccrit = new ArrayList<>();
    private ArrayList<Interet> listeInteretAssocie = new ArrayList<>();

    public Activite(String nom, Date dateDebut, Date dateFin, ArrayList<Tache> listeDeTache, ArrayList<Interet> listeInteretAssocie){
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeDeTache = listeDeTache;
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


    public String getInfoActiviteFormater(){
        numeroActivite++;
        return numeroActivite+"-"+" Nom : " + this.nom +
                "\nDate de debut :" + this.dateDebut.toString() +
                "\nDate de fin : " + this.dateFin.toString() +
                "Liste de tache : " + listeDeTache.stream()
                .map(tache -> tache.getInfoTacheFormater())
                .collect(Collectors.joining("\n"));
    }
}
