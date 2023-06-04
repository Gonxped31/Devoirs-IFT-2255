package domain.logic.Robot;
import java.util.LinkedList;
// Pourquoi est-ce qu'un robot doit prendre des actions et des tâches en paramètre ?
public class Robot {
    public String nom;
    public int X, Y, vitesse, batterie, cpu;
    public double memoire; // En GB
    public LinkedList<String> composantes = new LinkedList<String>();
    public LinkedList<String> actions = new LinkedList<String>(); 
    public LinkedList<String> taches = new LinkedList<String>(); 
    public LinkedList<String> activites = new LinkedList<String>();
    public String numeroSerie;
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<String> composantes, LinkedList<String> action, LinkedList<String> taches, LinkedList<String> activites, String numeroSerie){
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
}
