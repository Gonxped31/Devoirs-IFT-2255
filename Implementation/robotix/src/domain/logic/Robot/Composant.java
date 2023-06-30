package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Composant implements java.io.Serializable {
    private String nom;
    private String prix;
    private String description;
    private String typeComposant;

    public Composant(String nom, String prix, String description, String typeComposant){
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.typeComposant = typeComposant;
    }

    public Composant() {

    }


    public String getNom() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeComposant() {
        return typeComposant;
    }
    @JsonIgnore
    public String getInfoComposantFormater(){
        return "Nom :" + this.nom +
                "\nprix :" + this.prix +
                "\n description : " + this.description +
                "Type :" + this.typeComposant;
    }
}
