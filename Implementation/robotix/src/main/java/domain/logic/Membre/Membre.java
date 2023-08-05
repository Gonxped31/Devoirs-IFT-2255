package domain.logic.Membre;

/**
 * Représente un membre abstrait.
 */
public abstract class Membre implements java.io.Serializable {
    private String motDePasse;
    private String nom;
    protected String adresse;
    protected String email;
    private String Telephone;
    protected String nomCompagnie;

    /**
     * Crée un nouveau membre avec les informations spécifiées.
     *
     * @param nom            Le nom du membre.
     * @param adresse        L'adresse du membre.
     * @param email          L'adresse email du membre.
     * @param numeroTelephone Le numéro de téléphone du membre.
     * @param nomCompagnie   Le nom de la compagnie du membre.
     * @param mdp            Le mot de passe du membre.
     */
    public Membre(String nom, String adresse, String email, String numeroTelephone, String nomCompagnie, String mdp) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.setTelephone(numeroTelephone);
        this.nomCompagnie = nomCompagnie;
        this.setMotDePasse(mdp);
    }

    /**
     * Renvoie le nom du membre.
     *
     * @return Le nom du membre.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le numéro de téléphone du membre.
     *
     * @return Le numéro de téléphone du membre.
     */
    public String getTelephone() {
        return Telephone;
    }

    /**
     * Renvoie l'adresse du membre.
     *
     * @return L'adresse du membre.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Renvoie l'adresse email du membre.
     *
     * @return L'adresse email du membre.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Renvoie le nom de la compagnie du membre.
     *
     * @return Le nom de la compagnie du membre.
     */
    public String getNomCompagnie() {
        return nomCompagnie;
    }

    /**
     * Renvoie le mot de passe du membre.
     *
     * @return Le mot de passe du membre.
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit le nom du membre.
     *
     * @param nom Le nouveau nom du membre.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le numéro de téléphone du membre.
     *
     * @param telephone Le nouveau numéro de téléphone du membre.
     */
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }


    /**
     * Définit le mot de passe du membre.
     *
     * @param motDePasse Le nouveau mot de passe du membre.
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Définit l'adresse email du membre.
     *
     * @param email La nouvelle adresse email du membre.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définit le nom de la compagnie du membre.
     *
     * @param nomCompagnie Le nouveau nom de la compagnie du membre.
     */
    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

    /**
     * Définit l'adresse du membre.
     *
     * @param adresse La nouvelle adresse du membre.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
