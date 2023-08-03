package domain.logic.Membre;
public abstract class Membre implements java.io.Serializable {
    private String motDePasse;
    private String nom;
    protected String adresse;
    protected String email;
    private String Telephone;
    protected String nomCompagnie;

    public Membre(String nom, String adresse, String email, String numeroTelephone, String nomCompagnie, String mdp) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.setTelephone(numeroTelephone);
        this.nomCompagnie = nomCompagnie;
        this.setMotDePasse(mdp);
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
