package domain.logic.Membre;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import java.util.UUID;

import java.util.*;

/**
 * Cette classe represente un fournisseur de l'applicatiom.
 * Un fournisseur a la possibilite de vendre des robots ainsi que des composantes a des utilisateurs.
 */
public class Fournisseur extends Membre implements java.io.Serializable{
    private String typeRobotFabriquer;
    private String typeComposantesFabriquer;
    private String capaciteProductionComposantes;
    private LinkedList<Robot> inventaireDeRobot = new LinkedList<>();
    private LinkedList<Composant> inventaireComposant = new LinkedList<>();
    //private Notification notification = new Notification();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private int taillePrecedenteInventaireComposantes;

    /**
	 * Constructeur de la classe Fournisseur.
	 * @param nom Le nom du fournisseur.
	 * @param mdp Le mot de passe du fournisseur.
	 * @param adresse L'adresse du fournisseur.
	 * @param email L'adresse email du fournisseur.
	 * @param numeroTelephone Le numéro de téléphone du fournisseur.
	 * @param typeDeRobotFabriquer Le type de robot que le fournisseur fabrique.
	 * @param typeComposantesFabriquer Le type de composants que le fournisseur fabrique.
	 * @param capacite La capacité de production de composants du fournisseur.
	 * @param nomcompagnie Le nom de la compagnie du fournisseur.
	 */
    @JsonCreator
    public Fournisseur(@JsonProperty("nom") String nom, @JsonProperty("mdp") String mdp,
                       @JsonProperty("adresse") String adresse, @JsonProperty("email") String email,
                       @JsonProperty("numeroTelephone") String numeroTelephone,
                       @JsonProperty("typeDeRobotFabriquer") String typeDeRobotFabriquer,
                       @JsonProperty("typeComposantesFabriquer") String typeComposantesFabriquer,
                       @JsonProperty("capacite") String capacite,
                       @JsonProperty("nomcompagnie") String nomcompagnie) {

        super(nom, adresse, email, numeroTelephone, nomcompagnie, mdp);
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capaciteProductionComposantes =capacite;
    }

    /**
     * Renvoie le nom de la compagnie du fournisseur.
     *
     * @return Le nom de la compagnie du fournisseur.
     */
    public String getNomCompagnie() {
        return this.nomCompagnie;
    }

    /**
     * Renvoie l'inventaire des robots du fournisseur.
     *
     * @return L'inventaire des robots du fournisseur sous forme de LinkedList.
     */
    public LinkedList<Robot> getInventaireDeRobot() {
        return inventaireDeRobot;
    }

    /**
     * Renvoie l'inventaire des composants du fournisseur.
     *
     * @return L'inventaire des composants du fournisseur sous forme de LinkedList.
     */
    public LinkedList<Composant> getInventaireComposant() {
        return inventaireComposant;
    }

    /**
     * Renvoie la capacité de production de composants du fournisseur.
     *
     * @return La capacité de production de composants du fournisseur.
     */
    public String getCapaciteProductionComposantes() {
        return this.capaciteProductionComposantes;
    }

    /**
     * Renvoie le type de composants que le fournisseur fabrique.
     *
     * @return Le type de composants que le fournisseur fabrique.
     */
    public String getTypeComposantesFabriquer() {
        return this.typeComposantesFabriquer;
    }

    /**
     * Renvoie le type de robot que le fournisseur fabrique.
     *
     * @return Le type de robot que le fournisseur fabrique.
     */
    public String getTypeRobotFabriquer() {
        return this.typeRobotFabriquer;
    }

    /**
     * Renvoie l'adresse email du fournisseur.
     *
     * @return L'adresse email du fournisseur.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Renvoie l'adresse du fournisseur.
     *
     * @return L'adresse du fournisseur.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Renvoie le nom du fournisseur.
     *
     * @return Le nom du fournisseur.
     */
    public String getNom() {
        return super.getNom();
    }

    /**
	 * Renvoie le numéro de téléphone du fournisseur.
	 * @return Le numéro de téléphone du fournisseur.
	 */
    @JsonProperty("telephone")
    public String getNumeroTelephone() {
        return this.getTelephone();
    }

    /**
	 * Renvoie la liste des notifications du fournisseur.
	 * @return La liste des notifications du fournisseur sous forme de ArrayList.
	 */
    @JsonProperty("listeNotifications")
    public ArrayList<Notification> getNotifs(){
        return this.listeNotifications;
    }

    /**
     * Renvoie la liste des notifications du fournisseur.
     *
     * @return La liste des notifications du fournisseur sous forme de ArrayList.
     */
    public ArrayList<Notification> getListeNotifications() {
        return this.listeNotifications;
    }


    /**
     * Met à jour l'inventaire des robots du fournisseur.
     *
     * @param inventaireDeRobot Le nouvel inventaire des robots du fournisseur sous forme de LinkedList.
     */
    public void setInventaireDeRobot(LinkedList<Robot> inventaireDeRobot) {
        this.inventaireDeRobot = inventaireDeRobot;
    }

    /**
     * Met à jour l'inventaire des composants du fournisseur.
     *
     * @param inventaireComposant Le nouvel inventaire des composants du fournisseur sous forme de LinkedList.
     */
    public void setInventaireComposant(LinkedList<Composant> inventaireComposant) {
        this.inventaireComposant = inventaireComposant;
    }


    /**
     * Met à jour la capacité de production de composants du fournisseur.
     *
     * @param capaciteProductionComposantes La nouvelle capacité de production de composants du fournisseur.
     */
    public void setCapaciteProductionComposantes(String capaciteProductionComposantes) {
        this.capaciteProductionComposantes = capaciteProductionComposantes;
    }

    /**
     * Met à jour la liste des notifications du fournisseur.
     *
     * @param listeNotifications La nouvelle liste des notifications du fournisseur sous forme de ArrayList.
     */
    public void setListeNotifications(ArrayList<Notification> listeNotifications) {
        this.listeNotifications = listeNotifications;
    }

    /**
     * Ajoute un nouveau robot à l'inventaire du fournisseur avec les composants spécifiés.
     *
     * @param composants La liste des composants du robot à ajouter.
     * @param typeRobot  Le type du robot à ajouter.
     * @return L'identifiant unique (UUID) du robot ajouté.
     */
    public UUID ajouterRobot(ArrayList<Composant> composants, String typeRobot) {
        Robot r= new Robot("", 0, 0, 0, 100, 32, 32, new LinkedList<>(), "", new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        for( Composant c :composants){
            r.ajouterComposante(c);
            r.setType(typeRobot);
        }
        inventaireDeRobot.add(r);
        return   r.getNumeroSerie();
    }

    /**
     * Retire un robot de l'inventaire du fournisseur en utilisant son numéro de série.
     *
     * @param numeroSerie Le numéro de série du robot à retirer.
     * @return True si le robot a été retiré avec succès, sinon False s'il n'a pas été trouvé.
     */
    public boolean retirerRobot(String numeroSerie) {
        Robot robot = inventaireDeRobot.stream()
                .filter(r -> r.getNumeroSerie().toString().trim().equals(numeroSerie.trim()))
                .findFirst()
                .orElse(null);

        boolean robotExiste = robot != null;

        if (robotExiste) {
            inventaireDeRobot.remove(robot);
        }

        return robotExiste;
    }

    /**
     * Ajoute une nouvelle composante à l'inventaire du fournisseur.
     *
     * @param nom           Le nom de la nouvelle composante.
     * @param prix          Le prix de la nouvelle composante.
     * @param description   La description de la nouvelle composante.
     * @param typeComposant Le type de la nouvelle composante.
     */
    public void ajouterComposante(String nom, String prix, String description, String typeComposant) {
        taillePrecedenteInventaireComposantes = inventaireComposant.size();
        inventaireComposant.add(new Composant(nom,prix,description,typeComposant.toUpperCase()));
    }

    /**
     * Retire une composante de l'inventaire du fournisseur en utilisant son nom.
     *
     * @param nom Le nom de la composante à retirer.
     * @return True si la composante a été retirée avec succès, sinon False si elle n'a pas été trouvée.
     */
    public boolean retirerComopsante(String nom) {
        boolean bool = false;
        int nbComposantes = 0;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(nom)) {
                this.inventaireComposant.remove(composant);
                ++nbComposantes;
                break;
            }
        }
        if (nbComposantes != 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * Modifie le prix d'une composante de l'inventaire du fournisseur en utilisant son nom.
     *
     * @param composante   Le nom de la composante à modifier.
     * @param nouveauPrix  Le nouveau prix de la composante.
     * @return True si le prix de la composante a été modifié avec succès, sinon False si la composante n'a pas été trouvée.
     */
    public boolean modifierPrixComposante(String composante, String nouveauPrix){
        boolean bool = false;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(composante)){
                composant.setPrix(nouveauPrix);
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * Modifie la description d'une composante de l'inventaire du fournisseur en utilisant son nom.
     *
     * @param composante          Le nom de la composante à modifier.
     * @param nouvelleDescription La nouvelle description de la composante.
     * @return True si la description de la composante a été modifiée avec succès, sinon False si la composante n'a pas été trouvée.
     */
    public boolean modifierDescriptionComposante(String composante, String nouvelleDescription){
        boolean bool = false;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(composante)){
                composant.setDescription(nouvelleDescription);
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * Modifie les informations du profil du fournisseur.
     *
     * @param choix Le champ à modifier (nom, adresse, email, numeroTelephone, nomcompagnie, capaciteproduction, mdp).
     * @param info  La nouvelle information à définir pour le champ spécifié.
     */
    public void modifierProfile(String choix, String info){
        switch (choix.toLowerCase()) {
            case "nom" -> this.setNom(info);
            case "addresse" -> this.setAdresse(info);
            case "email" -> this.setEmail(info);
            case "numerotelephone" -> this.setTelephone(info);
            case "nomcompagnie" -> this.setNomCompagnie(info);
            case "capaciteproduction" -> this.setCapaciteProductionComposantes(info);
            case "mdp" -> this.setMotDePasse(info);
        }
    }

    /**
     * Renvoie une représentation formatée du profil du fournisseur, incluant ses informations personnelles et l'état de son inventaire.
     *
     * @return Une chaîne de caractères représentant le profil du fournisseur.
     */
    @JsonIgnore
    public String getProfilFournisseur(){
        return "Nom: " + super.getNom() + "\nAdresse courriel: " +
                this.email + "\nTelephone: " + this.getTelephone() +
                "\nAddresse: " + this.getAdresse() +
                "\nType de robot fabriquer: " + this.typeRobotFabriquer +
                "\nType de composant fabriquer: " + this.typeComposantesFabriquer +
                "\nNombre de composante disponible: " + this.getInventaireComposant().size() +
                "\nNombre de robot disponible: " + this.getInventaireDeRobot().size() + "\n";
    }

    /**
     * Produit un composant CPU avec des valeurs par défaut et l'ajoute à l'inventaire du fournisseur.
     *
     * @return Le nouveau composant CPU produit.
     */
    public  Composant produireCPU() {
        return new Composant("cpu", "50000", "", "CPU");
    }

    /**
     * Ajoute une nouvelle notification à la liste des notifications du fournisseur.
     *
     * @param titre             Le titre de la notification.
     * @param message           Le message de la notification.
     * @param typeNotification  Le type de la notification.
     */
    public void addNotifs(String titre,String message, TypeNotification typeNotification){
        this.listeNotifications.add(new Notification(titre, message, typeNotification));
    }

    /**
     * Renvoie la liste des notifications du fournisseur.
     *
     * @return La liste des notifications du fournisseur.
     */
    public ArrayList<Notification> voirNotifications(){
        return getListeNotifications();
    }

}

