package domain.logic.Robot;
import java.util.LinkedList;
import java.util.UUID;

// Pourquoi est-ce qu'un robot doit prendre des actions et des tâches en paramètre ?
public class Robot {
    private String nom;
    private int X, Y, vitesse, batterie, cpu;
    private double memoire; // En GB
    private LinkedList<Composant> composantes;
    private LinkedList<String> actions;
    private LinkedList<String> taches;
    private LinkedList<String> activites;
    private UUID numeroSerie;
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<Composant> composantes,
                 LinkedList<String> action, LinkedList<String> taches, LinkedList<String> activites, UUID numeroSerie){
        this.nom = nom;
        this.X = X;
        this.Y = Y;
        this.vitesse = vitesse;
        this.batterie = batterie;
        this.cpu = cpu;
        this.memoire = memoire;
        this.composantes = composantes;
        this.actions = action;
        this.taches = taches;
        this.activites = activites;
        this.numeroSerie = numeroSerie;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
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

    public LinkedList<Composant> getComposantes() {
        return composantes;
    }

    public LinkedList<String> getActions() {
        return actions;
    }

    public LinkedList<String> getTaches() {
        return taches;
    }

    public LinkedList<String> getActivites() {
        return activites;
    }

    public UUID getNumeroSerie() {
        return numeroSerie;
    }
}
