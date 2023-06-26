package domain.logic.Membre;
import domain.logic.Main;
import domain.logic.Robot.Robot;
import java.util.Scanner;
import java.util.*;

public class Fournisseur extends Membre {
    public String typeRobotFabriquer;
    public String typeComposantesFabriquer;
    public String capacite;
    public static LinkedList<Robot> inventaireDeRobot=new LinkedList<>();
    public static LinkedList<String> inventaireComposant= new LinkedList<>();

    public Fournisseur(String nom, String adresse, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie){
        super(nom, adresse, email, numeroTelephone, nomcompagnie);
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capacite=capacite;
    }

    public void vendreUnComposant(String composant){
      inventaireComposant.remove(composant);
    }

    public void mettreInventaireRobotAjour(Robot robot, boolean modeAjout){
        if (modeAjout) {
            inventaireDeRobot.add(robot);
        } else {
            inventaireDeRobot.remove(robot);
        }
    }

    public void mettreInventaireComposantAjour( String composant, boolean modeAjout){
        if (modeAjout) {
            inventaireComposant.add(composant);
        } else {
            inventaireComposant.remove(composant);
        }
    }

    public static void menuFournisseur(Fournisseur fournisseur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******************** Menu Fournisseur ********************");
        System.out.println("Bienvenue " + fournisseur.getNom() + "! Veuillez choisir une option:");
        System.out.println("1- Ajouter un nouveau robot");
        System.out.println("2- Retirer un robot");
        System.out.println("3- Ajouter une composante");
        System.out.println("4- Retirer une composante");
        System.out.println("5- Quitter");
        System.out.print(">>> Votre choix : ");
        String choixUsager = scanner.nextLine();

        switch (choixUsager) {
            case "1" : 
                System.out.println("Veuillez entrer les infos du robot : ");
                System.out.print("Nom : ");
                String nomRobot = scanner.nextLine();
                System.out.print("CPU : ");
                String cpu = scanner.nextLine();
                System.out.print("Mémoire : ");
                String memoire = scanner.nextLine();
                System.out.print("Numéro de série : ");
                String numeroSerie = scanner.nextLine();
                Robot robot = new Robot(nomRobot, 0, 0, 0, 0, Integer.parseInt(cpu), Integer.parseInt(memoire), null, null, null, null, numeroSerie);
                ajouterRobot(robot, fournisseur);
                menuFournisseur(fournisseur);
            
            case "2" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String nom = scanner.nextLine();
                retirerRobot(nom, fournisseur);
            
            case "3" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante = scanner.nextLine();
                ajouterComposante(composante, fournisseur);
            
            case "4" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante2 = scanner.nextLine();
                retirerComopsante(composante2, fournisseur);
            
            case "5" : 
                System.out.println("Au revoir !");
                Main.choisirOptionMenu(Main.listeFournisseurs, Main.listeUtilisateurs);
                break;

        }
    }

    private static void ajouterRobot(Robot robot, Fournisseur fournisseur) {
        inventaireDeRobot.add(robot);
        System.out.println(" ");
        System.out.println("Le robot a été rajouté avec succès !");
        System.out.println(" ");
        menuFournisseur(fournisseur);
    }

    private static void retirerRobot(String nomRobot, Fournisseur fournisseur) {
        int nbRobot = 0;
        for (Robot robot : inventaireDeRobot) {
            if (robot.nom.equals(nomRobot)) {
                inventaireDeRobot.remove(robot);
                nbRobot++;
            } else {
                continue;
            }
        }
        if (nbRobot == 0) {
            System.out.println("Vous ne possédez ce robot.");
        } else {
            System.out.println("Le robot a été retiré avec succès !");
        }

        menuFournisseur(fournisseur);
    }

    private static void ajouterComposante(String composante, Fournisseur fournisseur) {
        inventaireComposant.add(composante);
        System.out.println(" ");
        System.out.println("La composante a été rajoutée avec succès !");
        System.out.println(" ");
        menuFournisseur(fournisseur);
    }

    private static void retirerComopsante(String composante2, Fournisseur fournisseur) {
        int nbComposantes = 0;
        for (String string : inventaireComposant) {
            if (string.equals(composante2)) {
                inventaireComposant.remove(string);
                ++nbComposantes;
            } else {
                continue;
            }
        }
        if (nbComposantes == 0) {
            System.out.println("Vous ne possédez cette composante.");
        } else {
            System.out.println("La composante a été retirée avec succès !");
        }

        menuFournisseur(fournisseur);
    }

    @Override
    public String toString() {
        return  "Fournisseur { " + '\n' +
                "Nom = " + getNom() + '\n' +
                "Adresse = " + getAdresse() + '\n' +
                "Email = " + getEmail() + '\n' +
                "Numéro de télephone = " + getTelephone() + '\n' +
                "Type de robots fabriqués = " + getTypeRobotFabriquer() + '\n' +
                "Type de composantes fabriquées = " + getTypeComposantesFabriquer() + '\n' +
                "Capacité de fabrication = " + getCapacite() + '\n' +
                "Nom de compagnie = " + getNomCompagnie() + '\n' +
                "}\n";
    }

    private String getNomCompagnie() {
        return this.nomCompagnie;
    }

    private String getCapacite() {
        return this.capacite;
    }

    private String getTypeComposantesFabriquer() {
        return this.typeComposantesFabriquer;
    }

    private String getTypeRobotFabriquer() {
        return this.typeRobotFabriquer;
    }

    private String getTelephone() {
        return this.numeroTelephone;
    }

    private String getEmail() {
        return this.email;
    }

    private String getAdresse() {
        return this.adresse;
    }

    public String getNom() {
        return this.nom;
    }
}

