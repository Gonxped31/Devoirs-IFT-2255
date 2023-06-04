package domain.logic.Fournisseur;
import domain.logic.Robot.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fournisseur {
    public String nom;
    public String adresse;
    public String email;
    public String numeroTelephone;
    public String typeRobotFabriquer;
    public String typeComposantesFabriquer;
    public String capacite;
    public String nomCompagnie;
    public String pseudo;
    public static List<Robot> inventaireDeRobot=new ArrayList<>();
    public static List<String> inventaireComposant= new ArrayList<>();

    public Fournisseur(String nom, String adresse, String pseudo, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie){
        this.nom=nom;
        this.adresse=adresse;
        this.pseudo=pseudo;
        this.email=email;
        this.numeroTelephone=numeroTelephone;
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capacite=capacite;
        this.nomCompagnie=nomcompagnie;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return numeroTelephone;
    }

    public String getTypeRobotFabriquer() {
        return typeRobotFabriquer;
    }

    public String getTypeComposantesFabriquer() {
        return typeComposantesFabriquer;
    }

    public String getCapacite() {
        return capacite;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public void vendreUnComposant(Composant composant){
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
            case "1" -> {
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
            }
            case "2" -> {
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String nom = scanner.nextLine();
                retirerRobot(nom, fournisseur);
            }
            case "3" -> {
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante = scanner.nextLine();
                ajouterComposante(composante);
            }
            case "4" -> {
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante2 = scanner.nextLine();
                retirerComopsante(composante2);
            }
            case "5" -> System.out.println("Au revoir !");
        }
    }

    private static void ajouterRobot(Robot robot, Fournisseur fournisseur) {
        inventaireDeRobot.add(robot);
        System.out.println(" ");
        System.out.println("Le robot a été rajouté avec succès !");
        System.out.println(" ");
        menuFournisseur(fournisseur);
    }

    private static void retirerRobot(String nom, Fournisseur fournisseur) {
        int nbRobot = 0;
        for (Robot robot : inventaireDeRobot) {
            if (robot.nom == nomRobot) {
                inventaireDeRobot.remove(robot);
                ++nbRobot;
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
            if (string == composante2) {
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
}

