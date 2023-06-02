package domain.logic.Fournisseur;
import domain.logic.Robot.Robot;
import domain.logic.Robot.TypeRobot;
import java.util.List;

public class Fournisseur {
    String nom , adresse, email, telephone;
    TypeRobot typeRobotFabriquer;
    double capacite;
    List<Robot> inventaireDeRobot;
    private List<Composant> composants;

    public Fournisseur(String nom, String adresse, String email, String telephone, TypeRobot typeRobotFabriquer, double capacite) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        this.typeRobotFabriquer = typeRobotFabriquer;
        this.capacite = capacite;
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

    public void ajoutRobot() {

    }

    public void retierRobot() {

    }

    public void ajouterComposante() {

    }

    public void retirerComposante() {

    }

    public void vendreUnComposant(){
        //TODO
    }

    @Override
    public String toString() {
        return "Fournisseur {" + '\n' +
                "Nom = " + getNom() + '\n' +
                "Adresse= " + getAdresse() + '\n' +
                "Email = " + getEmail() + '\n' +
                "Numéro de télephone = " + getTelephone() + '\n' +
                "Type de robots fabriqués = " + getTypeRobotFabriquer() + '\n' +
                "Capacité de fabrication = " + getCapacite() + '\n' +
                '}';
    }
}
