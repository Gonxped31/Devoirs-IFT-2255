package domain.logic.Menu;

import java.util.ArrayList;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

public class MenuGestionFlotte {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private MenuUtilisateur menuUtil = new MenuUtilisateur();
    public void gererMaFlotte(Scanner scanner, String pseudo) {
        System.out.println("1- Enregistrer un robot");
        System.out.println("2- Afficher état d'un robot");
        System.out.println("3- Ajouter une composante a un robot");
        System.out.println("4- Afficher les metriques de ma flotte");
        System.out.println("5- Creer action");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> menuEnregistrerRobot(scanner, pseudo);
            case "2" -> printRobots(controlleurUtilisateurs.afficherEtatRobot(pseudo));
            case "3" -> menuAjouterComposante(scanner, pseudo);
            case "4" -> menuAfficherMetriquesFlotte(pseudo);
            case "5" -> menuCreerActions(scanner, pseudo);
        }
    }

    public void menuEnregistrerRobot(Scanner scanner,String pseudo){
        boolean reessayer = true;
        while (reessayer) {
            System.out.println("Nom du robot : ");
            String nomRobot = scanner.nextLine();
            System.out.println("Numero de serie: ");
            String numeroDeSerie = scanner.nextLine();
            if(controlleurUtilisateurs.enregistrerRobot(pseudo, nomRobot, numeroDeSerie)) {
                System.out.println("Le robot a été bien enrégistré !");
                reessayer = false;
            } else {
                System.out.println("Aucun robot vendu par nos founisseurs ne possède ce numéro de série. Voulez vous rééssayer ?");
                System.out.println("1- Oui");
                System.out.println("2- Non");
                System.out.print(">> Votre choix : ");
                if (scanner.nextLine().equalsIgnoreCase("non")){
                    break;
                }
            }
        }
        System.out.println(" ");
        menuUtil.menuUtilisateur(scanner, pseudo);
    }
    public void printRobots(ArrayList<Robot> robots) {
        for (Robot robot : robots) {
            System.out.println(">>" + robot.getNom());
            System.out.println("Position : (" + robot.getX() + ", " + robot.getY() + ")");
            System.out.println("Vitesse : " + robot.getVitesse());
            System.out.println("Niveau de batterie : " + robot.getBatterie());
            System.out.println("CPU : " + robot.getCpu());
            System.out.println("Memoire" + robot.getMemoire());
            System.out.println(" ");
        }
    }

    public void menuAjouterComposante(Scanner scanner, String pseudo) {
        System.out.print("Nom de la composante à ajouter : ");
        String nomComposante = scanner.nextLine();
        System.out.print("Nom du robot : ");
        String nomRobot = scanner.nextLine();
        if (controlleurUtilisateurs.ajouterComposanteRobot(nomComposante, nomRobot, pseudo)){
            System.out.println(" ");
            System.out.println("La composante a bien été ajoutée.");
            System.out.println(" ");
        } else {
            System.out.println("La composante ou le robot entrée n'existe pas ):");
        }
        menuUtil.menuUtilisateur(scanner, pseudo);
    }

    public void menuAfficherMetriquesFlotte(String pseudo) {
        System.out.println("********** Métriques de ma flotte **********");
        System.out.println("Nombre de robot : " + controlleurUtilisateurs.afficherMetriquesFlotte(pseudo));
        System.out.println("Consommation globale du CPU : 83 %");
        System.out.println("Consommation globale de la mémoire : 85 %");
        System.out.println(" ");
    }

    public void menuCreerActions(Scanner scanner, String pseudo) {
        ArrayList<Composant> composantes = new ArrayList<>();
        System.out.println("Quelles actions voulez-vous creer?");
        System.out.println("Nom: ");
        String nomAction = scanner.nextLine();
        System.out.println("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?: ");
        String decision = "Y";
        while (decision.toUpperCase().equals("Y")) {
            System.out.println("Entrez une composante:");
            String comp = scanner.nextLine();
            Composant compo = new Composant(comp, null, null, null);
            composantes.add(compo);
            System.out.println("Voulez-vous rajouter une composante a cette action (Y/N)?");
            decision = scanner.nextLine();
        }
        controlleurUtilisateurs.creerAction(pseudo, nomAction, composantes);
    }

    

}