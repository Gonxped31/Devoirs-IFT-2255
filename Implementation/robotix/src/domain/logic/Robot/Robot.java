package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;

// Pourquoi est-ce qu'un robot doit prendre des actions et des tâches en paramètre ?
public class Robot implements java.io.Serializable{
    private static int numero = 0;
    private String nom;
    private int X, Y, vitesse, batterie, cpu;
    private double memoire; // En GB
    private LinkedList<Composant> composantes =new LinkedList<>();
    private String type;
    private LinkedList<Action> actions;
    private LinkedList<Tache> taches;
    private LinkedList<Activite> activites;
    private UUID numeroSerie;

    @JsonCreator
    public Robot(@JsonProperty("nom") String nom,@JsonProperty("X") int X, @JsonProperty("Y")int Y,@JsonProperty("vitesse") int vitesse,@JsonProperty("batterie") int batterie,@JsonProperty("cpu") int cpu,@JsonProperty("memoire") double memoire,@JsonProperty("composantes") LinkedList<Composant> composantes,@JsonProperty("type") String type,
                 @JsonProperty("action") LinkedList<Action> action,@JsonProperty("taches") LinkedList<Tache> taches,@JsonProperty("activites") LinkedList<Activite> activites){
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
        ++numero;
    }

    public Robot( ){
        this.numeroSerie= UUID.randomUUID();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int newY){
        this.Y = newY;
    }

    public void setX(int newX){
        this.X = newX;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getBatterie() {
        return batterie;
    }

    public int getCpu() {
        return cpu;
    }

    public double getMemoire() {
        return memoire;
    }

    public String getType() {
        return type;
    }

    public LinkedList<Composant> getComposantes() {
        return composantes;
    }

    public LinkedList<Action> getActions() {
        return actions;
    }

    public LinkedList<Tache> getTaches() {
        return taches;
    }

    public LinkedList<Activite> getActivites() {
        return activites;
    }

    public UUID getNumeroSerie() {
        return numeroSerie;
    }
    public void ajouterComposante(Composant composant){
        composantes.add(composant);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void allouerTache(Tache tache){
        taches.add(tache);
    }

    @JsonIgnore
    public String getInfoRobotFormater()
    {
        return " ***** Robot numero : " + numero + " *****"
                + " \nListe de composant : " + this.composantes.stream().map(c->c.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
    }
    @JsonProperty("numero")
    public int getNumero()
    {
        return numero;
    }
    @JsonProperty("numero")
    public void setNumero(int newNum){
        numero = newNum;
    }
}