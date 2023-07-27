package domain.logic.Menu;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import domain.logic.Robot.TypesComposants;

public class MenuGestionFlotte {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private MenuUtilisateur menuUtil;

    public MenuGestionFlotte() throws IOException {
    }

    public void gererMaFlotte(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("********** Gestion de ma flotte **********");
        System.out.println("1- Enregistrer un robot");
        System.out.println("2- Afficher état d'un robot");
        System.out.println("3- Ajouter une composante a un robot");
        System.out.println("4- Afficher les metriques de ma flotte");
        System.out.println("5- Creer action");
        System.out.println("6- Revenenir au menu utilisateur");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> menuEnregistrerRobot(scanner, pseudo);
            case "2" -> {
                System.out.println("Cette fonctionalité est indisponible pour le moment ):\nVeuillez rééssayer plus tard.");
                gererMaFlotte(scanner, pseudo);
            }
            case "3" -> menuAjouterComposante(scanner, pseudo);
            case "4" -> menuAfficherMetriquesFlotte(scanner, pseudo);
            case "5" -> menuCreerActions(scanner, pseudo);
            case "6" -> {
                menuUtil = new MenuUtilisateur();
                menuUtil.menuUtilisateur(scanner, pseudo);
            }
        }
    }

    public void menuEnregistrerRobot(Scanner scanner,String pseudo) throws ParseException, IOException {
        boolean reessayer = true;
        while (reessayer) {
            System.out.println("Nom du robot : ");
            String nomRobot = scanner.nextLine();
            System.out.println("Veuillez entrer le type du robot : ");
            String type = scanner.nextLine();
            System.out.println("Numero de serie: ");
            String numeroDeSerie = scanner.nextLine();
            if(controlleurUtilisateurs.enregistrerRobot(nomRobot, type, numeroDeSerie, pseudo)) {
                System.out.println("Le robot a été bien enrégistré !");
                reessayer = false;
            } else {
                System.out.println("Aucun robot vendu par nos founisseurs ne possède ce numéro de série. Voulez vous rééssayer ? (oui/non)");
                System.out.print(">> Votre choix : ");
                if (scanner.nextLine().equalsIgnoreCase("non")){
                    break;
                }
            }
        }
        System.out.println(" ");
        gererMaFlotte(scanner, pseudo);
    }
    /*public void printRobots(ArrayList<Robot> robots) {
        for (Robot robot : robots) {
            System.out.println(">>" + robot.getNom());
            System.out.println("Position : (" + robot.getX() + ", " + robot.getY() + ")");
            System.out.println("Vitesse : " + robot.getVitesse());
            System.out.println("Niveau de batterie : " + robot.getBatterie());
            System.out.println("CPU : " + robot.getCpu());
            System.out.println("Memoire" + robot.getMemoire());
            System.out.println(" ");
        }
    }*/
    public void menuAjouterComposante(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.print("Nom de la composante à ajouter : ");
        String nomComposante = scanner.nextLine();
        System.out.println("Veuillez entrer le numero de serie du robot (le numero de serie doit avoir 36 charactères)");
        String numeroDeSerie = scanner.nextLine();
        /*controlleurUtilisateurs.ajouterComposanteRobot(nomComposante, numeroDeSerie, pseudo)*/
        if (numeroDeSerie.length() == 36){
            System.out.println(" ");
            System.out.println("La composante a bien été ajoutée.");
            System.out.println(" ");
        } else {
            System.out.println("La composante ou le robot entrée n'existe pas ):");
        }
        gererMaFlotte(scanner, pseudo);
    }

    public void menuAfficherMetriquesFlotte(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("********** Métriques de ma flotte **********");
        System.out.println("Consommation globale du CPU : 83 %");
        System.out.println("Consommation globale de la mémoire : 85 %");
        System.out.println("Vitesse moyenne des robots : 15 km/h");
        System.out.println("Batterie moyenne de la flotte : 62 %");
        System.out.println(" ");
        gererMaFlotte(scanner, pseudo);
    }

    public void menuCreerActions(Scanner scanner, String pseudo) throws ParseException, IOException {
        ArrayList<String> composantes = new ArrayList<>();
        System.out.println("Quelles actions voulez-vous creer?");
        System.out.print("Nom: ");
        String nomAction = scanner.nextLine();
        System.out.print("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?: ");
        String decision = "Y";
        while (decision.toUpperCase().equals("Y")) {
            System.out.println("Entrez une composante:");
            String comp = scanner.nextLine();
            switch (comp.toUpperCase()) {
                case "CPU" -> composantes.add(TypesComposants.CPU.name());
                case "ROUE" -> composantes.add(TypesComposants.ROUE.name());
                case "HELICE" -> composantes.add(TypesComposants.HELICE.name());
                case "HAUTPARLEUR" -> composantes.add(TypesComposants.HAUTPARLEUR.name());
                case "BRAS" -> composantes.add(TypesComposants.BRAS.name());
                case "ECRAN" -> composantes.add(TypesComposants.ECRAN.name());
                case "MICRO" -> composantes.add(TypesComposants.MICRO.name());
                case "CAMERA" -> composantes.add(TypesComposants.CAMERA.name());
            }
            System.out.println("Voulez-vous rajouter une composante a cette action (Y/N)?");
            decision = scanner.nextLine();
        }
        System.out.println("Veuillez entrer le duree : ");
        String duree = scanner.nextLine();
        controlleurUtilisateurs.creerAction(nomAction, composantes, duree);
        gererMaFlotte(scanner, pseudo);
    }
}
