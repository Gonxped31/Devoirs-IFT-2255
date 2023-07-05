package domain.logic.Membre;
public abstract class Membre {
    private String nom;
    protected String adresse;
    protected String email;
    protected String numeroTelephone;
    protected String nomCompagnie;

    public Membre(String nom, String adresse, String email, String numeroTelephone, String nomCompagnie) {
        this.setNom(nom);
        this.adresse = adresse;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.nomCompagnie = nomCompagnie;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
