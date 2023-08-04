package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Représente un robot avec ses caractéristiques, ses composants, ses actions et ses tâches.
 * Cette classe est sérialisable en JSON.
 */
public class Robot implements java.io.Serializable{
    private static int numero = 0;
    private String nom;
    private int X, Y, vitesse, batterie, cpu;
    private double memoire; // En GB
    private LinkedList<Composant> composantes =new LinkedList<>();
    private String type;
    private LinkedList<Action> actions;
    private LinkedList<Tache> taches;
    private LinkedList<String> activites;
    private UUID numeroSerie;
    private boolean disponible;

    /**
     * Constructeur de la classe Robot.
     *
     * @param nom            le nom du robot
     * @param X              la coordonnée X du robot
     * @param Y              la coordonnée Y du robot
     * @param vitesse        la vitesse du robot
     * @param batterie       la capacité de la batterie du robot
     * @param cpu            la capacité de calcul du robot (CPU)
     * @param memoire        la capacité mémoire du robot en GB
     * @param composantes    la liste des composants du robot
     * @param type           le type de robot
     * @param action        la liste des actions du robot
     * @param taches         la liste des tâches du robot
     * @param activites      la liste des activités du robot
     */
    @JsonCreator
    public Robot(@JsonProperty("nom") String nom,
                 @JsonProperty("X") int X,
                 @JsonProperty("Y")int Y,
                 @JsonProperty("vitesse") int vitesse,
                 @JsonProperty("batterie") int batterie,
                 @JsonProperty("cpu") int cpu,
                 @JsonProperty("memoire") double memoire,
                 @JsonProperty("composantes") LinkedList<Composant> composantes,
                 @JsonProperty("type") String type,
                 @JsonProperty("action") LinkedList<Action> action,
                 @JsonProperty("taches") LinkedList<Tache> taches,
                 @JsonProperty("activites") LinkedList<String> activites){
        this.nom = nom;
        this.X = X;
        this.Y = Y;
        this.vitesse = vitesse;
        this.batterie = batterie;
        this.cpu = cpu;
        this.memoire = memoire;
        this.composantes = composantes;
        this.setType(type);
        this.actions = action;
        this.taches = taches;
        this.activites = activites;
        this.numeroSerie = UUID.randomUUID();
        this.disponible = true;
        ++numero;
    }

    /**
     * Constructeur par défaut de la classe Robot.
     */
    public Robot( ){
        this.numeroSerie= UUID.randomUUID();
    }

    /**
     * Renvoie le nom du robot.
     *
     * @return le nom du robot
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la coordonnée X du robot.
     *
     * @return la coordonnée X du robot
     */
    public int getX() {
        return X;
    }

    /**
     * Renvoie la coordonnée Y du robot.
     *
     * @return la coordonnée Y du robot
     */
    public int getY() {
        return Y;
    }

    /**
     * Renvoie la vitesse du robot.
     *
     * @return la vitesse du robot
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Renvoie la capacité de la batterie du robot.
     *
     * @return la capacité de la batterie du robot
     */
    public int getBatterie() {
        return batterie;
    }

    /**
     * Renvoie la capacité de calcul (CPU) du robot.
     *
     * @return la capacité de calcul (CPU) du robot
     */
    public int getCpu() {
        return cpu;
    }

    /**
     * Renvoie la capacité mémoire du robot en GB.
     *
     * @return la capacité mémoire du robot en GB
     */
    public double getMemoire() {
        return memoire;
    }

    /**
     * Renvoie le type de robot.
     *
     * @return le type de robot
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoie la liste des composants du robot.
     *
     * @return la liste des composants du robot
     */
    public LinkedList<Composant> getComposantes() {
        return composantes;
    }

    /**
     * Renvoie la liste des actions du robot.
     *
     * @return la liste des actions du robot
     */
    public LinkedList<Action> getActions() {
        return actions;
    }

    /**
     * Renvoie la liste des tâches du robot.
     *
     * @return la liste des tâches du robot
     */
    public LinkedList<Tache> getTaches() {
        return taches;
    }

    /**
     * Renvoie la liste des activités du robot.
     *
     * @return la liste des activités du robot
     */
    public LinkedList<String> getActivites() {
        return activites;
    }

    /**
     * Renvoie le numéro de série du robot (UUID).
     *
     * @return le numéro de série du robot (UUID)
     */
    public UUID getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Renvoie le numéro du robot.
     *
     * @return le numéro du robot
     */
    @JsonProperty("numero")
    public int getNumero()
    {
        return numero;
    }

    /**
     * Vérifie si le robot est disponible.
     *
     * @return true si le robot est disponible, false sinon
     */
    public boolean estDisponible() {
        return disponible;
    }


    /**
     * Définit le nom du robot.
     *
     * @param nom le nom à définir pour le robot
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Déplace le robot en mettant à jour sa coordonnée Y.
     *
     * @param newY la nouvelle coordonnée Y du robot
     */
    public void setY(int newY){
        this.Y = newY;
    }

    /**
     * Déplace le robot en mettant à jour sa coordonnée X.
     *
     * @param newX la nouvelle coordonnée X du robot
     */
    public void setX(int newX){
        this.X = newX;
    }


    /**
     * Définit le type du robot.
     *
     * @param type le type à définir pour le robot
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Définit la disponibilité du robot.
     *
     * @param disponible la disponibilité à définir pour le robot
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Définit le numéro du robot.
     *
     * @param newNum le nouveau numéro du robot à définir
     */
    @JsonProperty("numero")
    public void setNumero(int newNum){
        numero = newNum;
    }

    /**
     * Ajoute un composant à la liste des composants du robot.
     *
     * @param composant le composant à ajouter
     */
    public void ajouterComposante(Composant composant){
        composantes.add(composant);
    }

    /**
     * Alloue une tâche au robot en ajoutant la tâche à la liste des tâches du robot.
     *
     * @param tache la tâche à allouer
     */
    public void allouerTache(Tache tache){
        taches.add(tache);
    }

    /**
     * Affiche l'état du robot sous forme de texte.
     *
     * @return une chaîne de caractères représentant l'état du robot
     */
    public String afficherEtatRobot(){
        StringBuilder etat = new StringBuilder();
        etat.append("Nom du robot: " + nom + "\n");
        etat.append("Position X, Y: " + X + ", " + Y + "\n");
        etat.append("Vitesse: " + vitesse + "\n");
        etat.append("Batterie: " + batterie + "\n");
        etat.append("CPU: " + cpu + "\n");
        etat.append("Memoire: " + memoire + " GB\n");
        etat.append("Type: " + type + "\n");
        etat.append("Numéro de série: " + numeroSerie + "\n");
        etat.append("Nombre de composantes: " + composantes.size() + "\n");
        etat.append("Nombre d'actions: " + actions.size() + "\n");
        etat.append("Nombre de tâches: " + taches.size() + "\n");

        return etat.toString();
    }

    /**
     * Renvoie une chaîne de caractères formatée représentant les informations du robot.
     *
     * @return une chaîne de caractères formatée représentant les informations du robot
     */
    @JsonIgnore
    public String getInfoRobotFormater() {
        return " ***** Robot numero : " + numero + " *****"
                + " \nListe de composant : " + this.composantes.stream().map(c->c.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
    }

}