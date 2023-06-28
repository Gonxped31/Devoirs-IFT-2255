package domain.logic.Robot;

public class Composant {
    private String nom;
    private Double prix;
    private String description;
    private String typeComposant;

    public Composant(String nom, Double prix, String description, String typeComposant){
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.typeComposant = typeComposant;
    }


    public String getNom() {
        return nom;
    }

    public Double getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeComposant() {
        return typeComposant;
    }
}
