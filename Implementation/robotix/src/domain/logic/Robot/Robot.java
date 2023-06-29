package domain.logic.Robot;
import java.util.LinkedList;
import java.util.UUID;

// Pourquoi est-ce qu'un robot doit prendre des actions et des tâches en paramètre ?
public class Robot {
    private String nom;
    private int X, Y, vitesse, batterie, cpu;
    private double memoire; // En GB
    private LinkedList<Composant> composantes =new LinkedList<>();
    private String type;
    private LinkedList<Action> actions;
    private LinkedList<Tache> taches;
    private LinkedList<Activite> activites;
    private UUID numeroSerie;
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire,LinkedList<Composant> composantes, String type,
                 LinkedList<Action> action, LinkedList<Tache> taches, LinkedList<Activite> activites){
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

}
