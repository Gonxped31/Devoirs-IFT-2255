package domain.logic.Fournisseur;
import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur {
    String nom , adresse, email, numeroTelephone;
    TypeRobot typeRobotFabriquer;
    Type typeComposantesFabriquer;
    double capacite;
    String nomCompagnie;
    String pseudo;
    List<Robot> inventaireDeRobot=new ArrayList<>();
    List<Composant> inventaireComposant= new ArrayList<>();

    public Fournisseur(String nom, String adresse, String pseudo, String email, String numeroTelephone,
                       TypeRobot typeDeRobotFabriquer, Type typeComposantesFabriquer, double capacite ,String nomcompagnie){
        this.nom=nom;
        this.adresse=adresse;
        this.pseudo=pseudo;
        this.email=email;
        this.numeroTelephone=numeroTelephone;
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capacite=capacite;
        this.nomCompagnie=nomcompagnie;
    }

    public Fournisseur(){

    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return numeroTelephone;
    }

    public TypeRobot getTypeRobotFabriquer() {
        return typeRobotFabriquer;
    }

    public Type getTypeComposantesFabriquer() {
        return typeComposantesFabriquer;
    }

    public double getCapacite() {
        return capacite;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setTypeRobotFabriquer(TypeRobot typeRobotFabriquer) {
        this.typeRobotFabriquer = typeRobotFabriquer;
    }

    public void setTypeComposantesFabriquer(Type typeComposantesFabriquer) {
        this.typeComposantesFabriquer = typeComposantesFabriquer;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

    public void vendreUnComposant(Composant composant){
      inventaireComposant.remove(composant);
    }
    public void mettreInventaireRobotAjour(Robot robot, boolean modeAjout){
        if (modeAjout) {
            inventaireDeRobot.add(robot);
        } else {
            inventaireDeRobot.remove(robot);
        }
    }

    public void mettreInventaireComposantAjour( Composant composant, boolean modeAjout){
        if (modeAjout) {
            inventaireComposant.add(composant);
        } else {
            inventaireComposant.remove(composant);
        }
    }

    @Override
    public String toString() {
        return  "Fournisseur { " + '\n' +
                "Nom = " + getNom() + '\n' +
                "Adresse= " + getAdresse() + '\n' +
                "Email = " + getEmail() + '\n' +
                "Numéro de télephone = " + getTelephone() + '\n' +
                "Type de robots fabriqués = " + getTypeRobotFabriquer() + '\n' +
                "Capacité de fabrication = " + getCapacite() + '\n' +
                "}";
    }
}
