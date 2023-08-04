package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Représente un composant utilisé par un robot.
 * Cette classe est sérialisable en JSON.
 */
public class Composant implements java.io.Serializable {
    private String nom;
    private String prix;
    private String description;
    private String typeComposant;

    private static int numero=0;

    /**
     * Constructeur de la classe Composant.
     *
     * @param nom            le nom du composant
     * @param prix           le prix du composant
     * @param description    la description du composant
     * @param typeComposant  le type de composant
     */
    public Composant(String nom, String prix, String description, String typeComposant){
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.typeComposant = typeComposant;
        ++numero;
    }

    /**
     * Constructeur par défaut de la classe Composant.
     */
    public Composant() {

    }

    /**
     * Renvoie le nom du composant.
     *
     * @return le nom du composant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le prix du composant.
     *
     * @return le prix du composant
     */
    public String getPrix() {
        return prix;
    }

    /**
     * Renvoie la description du composant.
     *
     * @return la description du composant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Renvoie le type de composant.
     *
     * @return le type de composant
     */
    public String getTypeComposant() {
        return typeComposant;
    }

    /**
     * Renvoie le numéro du composant.
     *
     * @return le numéro du composant
     */
    public static int getNumero() {
        return numero;
    }


    /**
     * Définit le prix du composant.
     *
     * @param prix le nouveau prix du composant à définir
     */
    public void setPrix(String prix) {
        this.prix = prix;
    }

    /**
     * Définit la description du composant.
     *
     * @param description la nouvelle description du composant à définir
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode pour obtenir l'information du composant formatée.
     * Cette méthode est utilisée pour afficher l'information du composant dans un format spécifique.
     *
     * @return une chaîne de caractères représentant l'information du composant formatée
     */
    @JsonIgnore
    public String getInfoComposantFormater(){
        return "\n\nComposante " + ++numero + " :" +
                " \nNom : " + this.nom +
                " \nprix : " + this.prix +
                " \ndescription : " + this.description +
                " \nType : " + this.typeComposant;
    }

}
