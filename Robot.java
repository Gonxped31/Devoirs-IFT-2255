public class Robot {
    String nom;
    int X;
    int Y;
    int vitesse; // En km/h
    int batterie; // Pourcentage
    int cpu; // Pourcentage
    double memoire; // En GB
    public Robot(String nom,int X, int Y, int vitesse, int batterie, int cpu, double memoire){
        this.nom = nom;
        this.X = X;
        this.Y = Y;
        this.vitesse = vitesse;
        this.batterie = batterie;
        this.cpu = cpu;
        this.memoire = memoire;
    }
}
