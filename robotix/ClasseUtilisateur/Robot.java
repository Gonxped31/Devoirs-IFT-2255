import java.util.LinkedList;
// Pourquoi est-ce qu'un robot doit prendre des actions et des tâches en paramètre ?
public class Robot {
    String nom;
    int X;
    int Y;
    int vitesse; // En km/h
    int batterie; // Pourcentage
    int cpu; // Pourcentage
    double memoire; // En GB
    LinkedList<String> composantes = new LinkedList<String>();
    LinkedList<String> actions = new LinkedList<String>(); 
    LinkedList<String> taches = new LinkedList<String>(); 
    LinkedList<String> activites = new LinkedList<String>();
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<String> composantes, LinkedList<String> action, LinkedList<String> taches, LinkedList<String> activites){
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
    }
}
