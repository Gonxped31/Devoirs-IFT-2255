package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Composant implements java.io.Serializable {
    private String nom;
    private String prix;
    private String description;
    private String typeComposant;

    private static int numero=0;

    public Composant(String nom, String prix, String description, String typeComposant){
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.typeComposant = typeComposant;
        ++numero;
    }

    public Composant() {

    }


    public String getNom() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeComposant() {
        return typeComposant;
    }
    @JsonIgnore
    public String getInfoComposantFormater(){
        return "\n\nComposante " + ++numero + " :" +
                " \nNom : " + this.nom +
                " \nprix : " + this.prix +
                " \ndescription : " + this.description +
                " \nType : " + this.typeComposant;
    }
}
