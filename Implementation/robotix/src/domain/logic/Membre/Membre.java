package domain.logic.Membre;
public abstract class Membre {
    private String motDePasse;
    private String nom;
    protected String adresse;
    protected String email;
    protected String numeroTelephone;
    protected String nomCompagnie;

    public Membre(String nom, String adresse, String email, String numeroTelephone, String nomCompagnie, String mdp) {
        this.setNom(nom);
        this.adresse = adresse;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.nomCompagnie = nomCompagnie;
        this.motDePasse = mdp;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
