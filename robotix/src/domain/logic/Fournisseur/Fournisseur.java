package domain.logic.Fournisseur;

import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;

import java.util.List;

public class Fournisseur{
    String nom , adresse, email, telephone;
    TypeRobot typeRobotFabriquer;
    double capacite;
    List<Robot> inventaireDeRobot;
    private List<Composant> composants;

    public Fournisseur(String nom, String adresse, String email, String numeroTelephone, TypeRobot typeDeRobotFabriquer, double capacite) {
        //TODO
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public TypeRobot getTypeRobotFabriquer() {
        return typeRobotFabriquer;
    }

    public double getCapacite() {
        return capacite;
    }

    public List<Robot> getInventaireDeRobot() {
        return inventaireDeRobot;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTypeRobotFabriquer(TypeRobot typeRobotFabriquer) {
        this.typeRobotFabriquer = typeRobotFabriquer;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public void setInventaireDeRobot(List<Robot> inventaireDeRobot) {
        this.inventaireDeRobot = inventaireDeRobot;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    public void ajoutRobot() {

    }

    public void retierRobot() {

    }

    public void ajoutComposante() {

    }

    public void retirerComposante() {

    }

    public void vendreUnComposant(){
        //TODO
    }
}
