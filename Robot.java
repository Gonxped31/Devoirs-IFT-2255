import java.util.LinkedList;

public class Robot {
    String nom;
    int X;
    int Y;
    int vitesse; // En km/h
    int batterie; // Pourcentage
    int cpu; // Pourcentage
    double memoire; // En GB
    LinkedList<String> actions = new LinkedList<String>(); 
    LinkedList<String> activites = new LinkedList<String>(); 
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire, LinkedList<String> action, LinkedList<String> activites ){
        this.nom = nom;
        this.X = X;
        this.Y = Y;
        this.vitesse = vitesse;
        this.batterie = batterie;
        this.cpu = cpu;
        this.memoire = memoire;
        this.actions = action;
        this.activites = activites;
    }
}
