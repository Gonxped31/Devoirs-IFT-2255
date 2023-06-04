package domain.logic.Utilisateurs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Robot.Robot;

//Faire import domain.logic.Fournisseur.Fournisseur;
import java.util.UUID;

public class Utilisateurs {
    public String nom, prenom, pseudo, courriel, telephone;
    protected static LinkedList<Robot> robots = new LinkedList<>();
    protected static LinkedList<String> uuids = new LinkedList<>();
    protected static LinkedList<String> taches = new LinkedList<>();
    protected static LinkedList<String> composantes = new LinkedList<>();

    public static void main(Scanner scanner) {
        menu(scanner);
    }

    public Utilisateurs(String nom, String prenom, String pseudo, String courriel, String telephone){
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.courriel = courriel;
        this.telephone = telephone;
    }

    public static void menu(Scanner scanner) {
        ArrayList<String> fournisCPU = new ArrayList<>();
        fournisCPU.add("Fournisseur6" );
        fournisCPU.add("Adresse1");
        fournisCPU.add("Courriel3");
        fournisCPU.add("Numero4");
        fournisCPU.add("type3");
        fournisCPU.add("compagnie2");
        System.out.println("******************** Menu ********************");
        System.out.println("Bienvenue! Veuillez choisir une option:");
        System.out.println("1- Enregistrer un robot");
        System.out.println("2- Afficher état d'un robot");
        System.out.println("3- Ajouter une composante a un robot");
        System.out.println("4- Ajouter une action a un robot");
        System.out.println("5- Ajouter une tâche");
        System.out.println("6- Allouer une tache a un robot");
        System.out.println("7- Afficher métriques d'une flotte");
        System.out.println("8- Participer à une activité");
        System.out.println("9- Afficher activités maintenues par un robot d'une flotte");
        System.out.println("10- Afficher les problèmes du système");
        System.out.println("11- Trouver un fournisseur");
        System.out.println("12- Quitter");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch(choix){
            case("1"):
                enregistrerRobot();
                break;
            case("2"):
                afficherEtatRobot(scanner);
                break;
            case("3"):
                ajouterComposantes(scanner);
                break;
            case("4"):
                ajouterAction(scanner);
                break;
            case("5"):
                creerTaches(scanner);
                break;
            case("6"):
                allouerTachesRobot(scanner);
                break;
            case("7"):
                afficherMetriquesFlotte(scanner);
                break;
            case("8"):
                participerActivites(scanner);
                break;
            case("9"):
                voirActivitesMaintenues(scanner);
                break;
            case("10"):
                gestionDesProblèmes(scanner);
                break;
            case("11"):
                trouverFournisseurs();
                break;
            case("12"):
                break;
        }
    }

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(Scanner scanner){

        Robot robot = demanderRobot(scanner);

        if (robot == null){
            menu(scanner);
        } else {
            System.out.print("Ajouter composante (Bras), (Roue), (Haut-parleur), (Micro), (None):");
            String input = scanner.nextLine();
            robot.composantes.add(input);
    
            System.out.println("Voulez-vous ajouter de nouvelles composantes? : ");
            String decision = scanner.nextLine();
            if (decision.toUpperCase().equals("OUI")){
                ajouterComposantes(scanner);
            } else{
                menu(scanner);
            }
        }

        return composantes;
    }

    //A partir de ce qui a été scanner, on produit une action
    public static void ajouterAction(Scanner scanner) {
        Robot robot = demanderRobot(scanner);
        if (robot == null){
            menu(scanner);
        } else{
            String action = "";
            boolean missingComponentAdded = false;
            while (!action.equals("None")) {
                System.out.print("Ajouter action: ");
                action = scanner.nextLine();
    
                switch (action.toUpperCase()){
                    case "PARLER" :
                        if (robot.composantes.contains("Haut-parleur")) {
                            System.out.print(robot.nom + " peut parler! \n");
                            robot.actions.add("Parler");
                        } else {
                            System.out.print("Il vous manque la composante : Haut-parleur \n");
                            missingComponentAdded = true;
                        }
                        break;
    
                    case "ECOUTER" :
                        if (robot.composantes.contains("Micro")) {
                            System.out.print(robot.nom + " peut ecouter! \n");
                            robot.actions.add("Ecouter");
                        } else {
                            System.out.print("Il vous manque la composante : Micro \n");
                            missingComponentAdded = true;
                        }
                        break;
    
                    case "DEPLACER" :
                        if (robot.composantes.contains("Roue")) {
                            System.out.print(robot.nom + " peut se deplacer! \n");
                            robot.actions.add("Deplacer");
                        } else {
                            System.out.print("Il vous manque la composante : Roue \n");
                            missingComponentAdded = true;
                        }
                        break;
    
                    case "ATTRAPER" :
                        if (robot.composantes.contains("Bras")) {
                            System.out.print(robot.nom + " peut attraper! \n");
                            robot.actions.add("Attraper");
                        } else {
                            System.out.print("Il vous manque la composante : Bras \n");
                            missingComponentAdded = true;
                        }
                        break;
    
                    default:
                        System.out.println("Vous devez choisir parmis les quatres composantes");
                        break;
                }
    
                System.out.println("Voulez-vous rajouter une autre action ?");
                String decision = scanner.nextLine();
                if (decision.toUpperCase().equals("OUI")){
                    ajouterAction(scanner);
                } else{
                    menu(scanner);
                }
                
            }
        }
        
    
    }

    public static void creerTaches(Scanner scanner) {
        boolean repeter = true;
        while (repeter){
            //Juste deux options de taches
            System.out.println("Quelle taches voulez vous créer 'Faire des zigzags' ou 'Attraper et relacher un objet'");
            String tache = scanner.nextLine();
            taches.add(tache);
            System.out.println("Voulez-vous ajouter une autre tache :  ");
            String verdict = scanner.nextLine();
            if (verdict.toUpperCase().equals("OUI")){
               creerTaches(scanner);
            } else {
                System.out.println(" ");
                System.out.println("La tâche a été ajoutée à la liste des tâches !");
                System.out.println(" ");
                menu(scanner);
            }
        }
        System.out.println(taches);
    }
    
    //Prends une liste chainées des actions voulues, et ouput une liste chainées avec la tâches ajoutées
    public static void allouerTachesRobot(Scanner scanner){
        Robot r = demanderRobot(scanner);
        if (r == null){
            menu(scanner);
        } else{
            boolean end = true;
            while (end){
                System.out.println("Veuillez entrez la tache a allouer a " + r.nom + ":");
                String tache = scanner.nextLine();
                if (tache.equals("Faire des zigzags") && taches.contains(tache) && r.actions.contains("Deplacer")){
                    r.taches.add(tache);
                    System.out.println(r.nom + " peut maintenant " + tache +"!!!");
                    break;
                } else if (tache.equals("Faire des zigzags") && !taches.contains(tache)) {
                    System.out.println("Tache n'a pas ete ajoutee car elle n'existe pas");
                    break;
                } else if (tache.equals("Faire des zigzags") && taches.contains(tache) && !r.actions.contains("Deplacer")){
                    System.out.println("Il manque l'action 'Deplacer', veuillez l'ajouter");
                    break;
                } 
                
                if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && r.actions.contains("Deplacer") && r.actions.contains("Attraper")){
                    r.taches.add(tache);
                    System.out.println(r.nom + " peut maintenant " + tache +"!!!");
                    break;
                } else if (tache.equals("Attraper et relacher un objet") && !taches.contains(tache) && r.actions.contains("Deplacer") && r.actions.contains("Attraper")) {
                    System.out.println("Tache n'a pas ete ajoutee car elle n'existe pas");
                    break;
                } else if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && !r.actions.contains("Deplacer") && r.actions.contains("Attraper")){
                    System.out.println("Il manque l'action 'Deplacer', veuillez l'ajouter");
                    break;
                }else if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && r.actions.contains("Deplacer") && !r.actions.contains("Attraper")){
                    System.out.println("Il manque l'action 'Attraper', veuillez l'ajouter");
                    break;
                } else {
                    System.out.println("Il vous manque les actions 'Attraper' et 'Deplacer'");
                    break;
                } 
            }
        }


        System.out.print("Voulez-vous allouer une autre tache ? : ");
        String decision = scanner.nextLine();
        if (decision.toUpperCase().equals("OUI")){
            allouerTachesRobot(scanner);
        } else {
            menu(scanner);
        }
    }

    public static void voirActivitesMaintenues(Scanner scanner){
        Robot r = demanderRobot(scanner);
        if (r == null){
            menu(scanner);
        } else{
            if (r.activites.size() == 0){
                System.out.println(r.nom + " n'est inscrite à aucune activitée.");
            } else {
                System.out.println("Voici les activitées auxquelles " + r.nom + " participe.");
                for (String act : r.activites){
                    System.out.println("    - " + act);
                }
                System.out.println("Voulez-vous revenir au menu principal?");
                String decision = scanner.nextLine();
                if (decision.toUpperCase().equals("OUI")){
                    menu(scanner);
                }
                else {
                    voirActivitesMaintenues(scanner);
                }
            }
        }
        
    }

    public static void afficherEtatRobot(Scanner scanner){ 
        Robot robot = demanderRobot(scanner);
        if (robot != null){
            System.out.println("Voici les informations de votre robot : ");
            System.out.println("Nom: " + robot.nom);
            System.out.println("Coordonnées: " + "X: " + robot.X  + "; Y: " + robot.Y);
            System.out.println("Vitesse: " + robot.vitesse + "km/h");
            System.out.println("Batterie: " + robot.batterie + "%");
            System.out.println("CPU: " + robot.cpu + "%");
            System.out.println("Memoire: " + robot.memoire + "%");
            if (robot.composantes.size() != 0){
                System.out.println("Composantes du robot : ");
                int i = 1;
                for (String composante : robot.composantes) {
                    System.out.println(i + "- " + composante);
                }
            } else {
                System.out.println("Aucune composante dans le robot.");
            }
            

            System.out.println("Voulez-vous revenir au menu principal?");
            String decision = scanner.nextLine();
            if (decision.toUpperCase().equals("OUI")){
                menu(scanner);
            }
            else {
                afficherEtatRobot(scanner);
            }
        } else {
            menu(scanner);
        }
        
    }
    
    public static Robot demanderRobot(Scanner scanner) {
        if (robots.size() == 0){
            System.out.println(" ");
            System.out.println("Vous ne possédez aucun robot. Veuillez en acheter / enrégistrer un.");
            System.out.println(" ");
            return null;
        } else {
            try {
                System.out.println("Choisissez un robot ?");
                int i = 1;
                for (Robot robot : robots) {  
                    System.out.println(i + "-" + robot.nom);
                    ++i;
                }
                System.out.print("Votre choix : ");
                String answer = scanner.nextLine();
                int index  = Integer.parseInt(answer) - 1;
                Robot robot = robots.get(index);
                return robot;

            } catch (NumberFormatException e) {
                System.out.println("Veuillez sélectionner un robot dans la liste.");
                return demanderRobot(scanner);
            }
        }
        
    }

    public static void enregistrerRobot() {
        System.out.println("************* Enrégistrer un robot *************");
        Scanner scanner = new Scanner(System.in);
        do {
            LinkedList<String> infosRobot = demanderInfosRobots(scanner);
            // CREER DES ACTIONS.
            if (infosRobot == null){
                menu(scanner);
                break;
            } else{
                robots.add(new Robot(infosRobot.get(0), 0, 0, 0, 100, 20, Double.parseDouble(infosRobot.get(1)) , new LinkedList<>() ,new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), infosRobot.get(2)));
            }
        } while(enregistrerDeNouveau(scanner));
        scanner.close();
    }

    public static LinkedList<String> demanderInfosRobots(Scanner scanner) {
        // Contient dans l'ordre : Nom, composantes
        LinkedList<String> infosRobots;

        infosRobots = new LinkedList<>();
        if (uuids.size() == 0){
            System.out.println(" ");
            System.out.println("Vous n'avez acheté aucun robot. ");
            System.out.println(" ");
            return null;
        } else {
            System.out.print("Numéro de série : ");
            String numeroSerie = scanner.nextLine();

            if (uuids.contains(numeroSerie)){
                System.out.println(" ");
                System.out.print("Nom du robot à ajouter : ");
                String nom = scanner.nextLine();
                System.out.print("Capacité mémoire de ce robot (en GB) : ");
                String memoire = scanner.nextLine();
                infosRobots.addFirst(numeroSerie);
                infosRobots.addFirst(memoire);
                infosRobots.addFirst(nom);
                System.out.println(" ");
                System.out.println("Enregistrement réussi ! ");        
                return infosRobots;
            } else {
                System.out.print("Le numéro de série n'est pas valide. Voulez vous reessayer ? : ");
                if (scanner.nextLine().toUpperCase().equals("OUI")){
                    return demanderInfosRobots(scanner);
                } else {
                    return null;
                }
            }
        }
    }

    public static void verifierComposantes(LinkedList<String> list) throws IOException {
        int compteur = 0;
        int tailleListe = list.size();
        for (String string : list) {
            if (string.toUpperCase().equals("CPU")){
                compteur++;
            }
        }

        if (compteur == tailleListe || (compteur == tailleListe && tailleListe == 2 || compteur == 0)){
            throw new IOException();
        }

    }

    public static Boolean enregistrerDeNouveau(Scanner scanner) {
        boolean repeter = true;
        boolean result = false;
        while (repeter) {
            System.out.println(" ");
            System.out.println("Choisissez une option (entrez simplement le chiffre) : ");
            System.out.println("1- Enregistrer un autre robot");
            System.out.println("2- Revenir au menu");
            System.out.print(">>> Votre choix : ");
            String answer = scanner.nextLine();
            if (answer.equals("1")) {
                result = true;
                repeter = false;
            } else if (answer.equals("2")) {
                result = false;
                repeter = false;
                menu(scanner);
            } else {
                System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        return result;
    }

    public static void afficherMetriquesFlotte(Scanner scanner) {
        if (robots.size() == 0){
            System.out.println(" ");
            System.out.println("Vous ne possédez aucun robot...");
            System.out.println(" ");
            menu(scanner);
        }
        System.out.println("************* Métriques de ma flotte *************");
        System.out.println(" ");
        System.out.println(">>> Nombre de robot dans la flotte : " + robots.size());
        System.out.println(">>> Liste des robots : ");
        for (int i = 1; i < robots.size() + 1; i++) {
            Robot robot = robots.get(i-1);
            System.out.println(i + "- " + robot.nom + "\n   Batterie : 100 %\n   Consommation CPU : 73 %");
        }
        System.out.println(">>> Batterie moyenne des robots : 100 %" );
        System.out.println(">>> Consommation moyenne du CPU : 73 %" );
        System.out.println(" ");
        System.out.print("Voulez-vous revenir au menu ? : ");
        String decision = scanner.nextLine();
        if (decision.toUpperCase().equals("OUI")){
            menu(scanner);
        } else {
            System.out.println("Au revoir!");
            menu(scanner);
        }
        
    }

    public static void gestionDesProblèmes(Scanner scanner) {
        Random rand = new Random();
        int n = rand.nextInt(3);

        if(robots.size() == 0){
            System.out.println(" ");
            System.out.println("Il n'y a aucun problème ! :)");
            System.out.println(" ");
            menu(scanner);
        } else {
            switch (n){
                case 0:
                    System.out.println("Un robot manque de batteries! Allez le recharger");
                    break;
                case 1:
                    System.out.println("Un robot overheat! Arretez le immediatement");
                    break;
                case 2: 
                    System.out.println("Un robot se sent seul... :(, allez lui allouer une activites");
                    break;
            }
            System.out.println("Voulez-vous revenir au menu principal?");
            String decision = scanner.nextLine();
            if (decision.toUpperCase().equals("OUI")){
                menu(scanner);
            }
            else {
                menu(scanner);
            }
        }
        
    }

    public static void participerActivites(Scanner scanner) {
        Robot r = demanderRobot(scanner);
        if (r == null){
            menu(scanner);
        } else {
            boolean repeter = true;
            while (repeter){
                //Juste deux options de taches
                //Prenons l'exemple de la tag
                System.out.println("Quelle actitvite voulez-vous assigner a votre robot (Jouer a tag) ou (Faire une course)?");
                String activite = scanner.nextLine();
                if (activite.equals("Jouer a tag") && r.taches.contains("Faire de zigzags") && r.taches.contains("Attraper et relacher un objet")){
                    System.out.println(r.nom + " participe à l'activité '" + activite + "' !");
                    r.activites.add(activite);
                    break;
                } else if (activite.equals("Jouer a tag") && !r.taches.contains("Faire des zigzags") && r.taches.contains("Attraper et relacher un objet")) {
                    System.out.println(r.nom + " ne peut pas participer à l'activité 'Jouer a tag' car il ne peut pas faire la tache 'Faire des zigzags'.");
                    break;
                } else if (activite.equals("Jouer a tag") && r.taches.contains("Faire des zigzags") && !r.taches.contains("Attraper et relacher un objet")){
                    System.out.println(r.nom + " ne peut pas participer à l'activité 'Jouer a tag' car il ne peut pas faire la tache 'Attraper un objet et le relacher'.");
                    break;
                } else if (activite.equals("Jouer a tag") && !r.taches.contains("Faire des zigzags") && !r.taches.contains("Attraper et relacher un objet")){
                    System.out.println(r.nom + " ne peut pas participer à l'activité 'Jouer a tag' car il ne peut pas 'Faire des zigzags' et 'Attraper un objet et le relacher'");
                    break;
                }
                
                if (activite.equals("Faire une course") && r.taches.contains("Faire des zigzags")){
                    System.out.println(r.nom + " participe à l'activité '" + activite + "' !");
                    r.activites.add(activite);
                    break;
                } else if (activite.equals("Faire une course") && !r.taches.contains("Faire des zigzags")) {
                    System.out.println(r.nom + " ne peut pas participer à l'activité 'Faire une course' car il ne peut pas faire des zigzags.");
                    break;
                }

            }
        }
        

        System.out.println("Voulez-vous ajouter une autre activite ? :");
        String verdict = scanner.nextLine();
        if (verdict.toUpperCase().equals("OUI")){
            participerActivites(scanner);
        } else {
            System.out.println(" ");
            menu(scanner);
        }
    }

    public static void trouverFournisseurs() {
        ArrayList<ArrayList<String>> fournisseurs = new ArrayList<>();
        ArrayList<String> fournisCPU = new ArrayList<>();
        ArrayList<String> fournisCPU2 = new ArrayList<>();
        ArrayList<String> fournisRoue = new ArrayList<>();
        ArrayList<String> fournisHp = new ArrayList<>();
        ArrayList<String> fournisMic = new ArrayList<>();
        ArrayList<String> fournisMic2 = new ArrayList<>();

        fournisCPU2.add("Fournisseur5");
        fournisCPU2.add("Adresse2");
        fournisCPU2.add("Courriel3");
        fournisCPU2.add("Numero4");
        fournisCPU2.add("type2");
        fournisCPU2.add("compagnie1");
        
        fournisMic2.add("Fournisseur6" );
        fournisMic2.add("Adresse1");
        fournisMic2.add("Courriel3");
        fournisMic2.add("Numero4");
        fournisMic2.add("type3");
        fournisMic2.add("compagnie2");

        fournisseurs.add(fournisCPU);
        fournisseurs.add(fournisRoue);
        fournisseurs.add(fournisHp);
        fournisseurs.add(fournisMic);

        int iterateur = 1;
        for (ArrayList<String> fournisseur : fournisseurs) {
            fournisseur.add("Fournisseur" + iterateur);
            fournisseur.add("Adresse" + iterateur);
            fournisseur.add("Courriel" + iterateur);
            fournisseur.add("Numero" + iterateur);
            fournisseur.add("type" + iterateur);
            fournisseur.add("compagnie" + iterateur);
            ++iterateur;
        }

        fournisseurs.add(fournisCPU2);
        fournisseurs.add(fournisMic2);

        HashMap<String, ArrayList<String>> fournisMap = new HashMap<>();
        for (int i = 1; i < fournisseurs.size() + 1; i++) {
            fournisMap.put("Fournisseur" + i, fournisseurs.get(i - 1));
        }


        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        Boolean bool = false;
        while (continuer) {
            System.out.println("Choisissez un filtre parmi les suivants : ");
            System.out.println("1- Nom");
            System.out.println("2- Adresse");
            System.out.println("3- Courriel");
            System.out.println("4- Telephone");
            System.out.println("5- Type de robots fabriqués");
            System.out.println("6- Nom de la compagnie");
            System.out.println("7- Aucun filtre");
            System.out.print(">>> Votre choix :");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("Veuillez entrer le nom recherché : ");
                    String nom = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, nom);
                    continuer = false;
                    break;
                case "2":
                    System.out.print("Veuillez entrer l'adresse recherchée : ");
                    String adresse = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, adresse);
                    continuer = false;
                    break;
                
                case "3":
                    System.out.print("Veuillez entrer le courriel recherché : ");
                    String courriel = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, courriel);
                    continuer = false;
                    break;
                
                case "4":
                    System.out.print("Veuillez entrer le numero recherché : ");
                    String numero = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, numero);
                    continuer = false;
                    break;
    
                case "5":
                    System.out.print("Veuillez entrer le type de robot recherché : ");
                    String type = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, type);
                    continuer = false;
                    break;
                
                case "6":
                    System.out.print("Veuillez entrer le nom de la compagnie recherché : ");
                    String nomCompagnie = scanner.nextLine();
                    bool = getFournisseurs(fournisseurs, nomCompagnie);
                    continuer = false;
                    break;

                case "7":
                    for (ArrayList<String> element : fournisseurs) {
                        System.out.println(element);
                    }
                    continuer = false;
                    break;
    
                default:
                    System.out.println("Veuillez choisir un élément dans la liste.");
                    break;
            }
    
        }

        if (bool){
            selectionnerFournisseur(scanner, fournisMap);

        } else {
            trouverFournisseurs();
        }
        
        scanner.close();
        
    }

    public static void selectionnerFournisseur(Scanner scanner, HashMap<String, ArrayList<String>> fournisMap) {
        System.out.println(" ");
        System.out.print("Veuillez entrer le nom du fournisseur chez qui vous voulez acheter une composante : ");
        String answer = scanner.nextLine();

        if (fournisMap.containsKey(answer)) {
            acheterComposantes(scanner, fournisMap.get(answer));
        } else {
            System.out.println("Ce fournisseur n'est pas dans la liste.");
            menu(scanner);
        }

        System.out.println("Voulez-vous revenir au menu principal?");
        String decision = scanner.nextLine();
        if (decision.toUpperCase().equals("OUI")){
            menu(scanner);
        }
        else {
            trouverFournisseurs();
        }
    }

    // Affiche les fournisseurs recherchés
    public static Boolean getFournisseurs(ArrayList<ArrayList<String>> fournisseurs, String entree) {
        ArrayList<ArrayList<String>> reponse = new ArrayList<>();
        for (int i = 0; i < fournisseurs.size(); i++) {
            ArrayList<String> fournis = fournisseurs.get(i);
            for (int j = 0; j < fournis.size(); j++) {
                if (entree.equals(fournis.get(j))) {
                    reponse.add(fournis);
                }
            }
        }

        if (reponse.size() == 0){
            System.out.println("Il n'y a pas de fournisseur avec cette caractéristique.");
            return false;
        } else {
            for (ArrayList<String> element : reponse) {
                System.out.println(element);
            }
            return true;
        }
    }
    public static void acheterComposantes(Scanner scanner, ArrayList<String> fournisseurChoisi) {

        String fourniseurs =  fournisseurChoisi.get(0);
        switch (fourniseurs) {
            case "Fournisseur1":
            case "Fournisseur3":
                System.out.println("Composante vendu : CPU ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Composante indispensable pour tout robot.");
                System.out.println("Prix composante : 1 000 000 000 $ (non négociable)");
                System.out.println(" ");
                System.out.println("Type de robot vendu : Cascadeur");
                System.out.println("Prix du robot : 100 000 000 000 $");
                composantes.add("CPU");
                break;

            case "Fournisseur2":
                System.out.println("Composante vendu : Bras ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour émettre un song.");
                System.out.println("Prix composante : 2 000 000 $ (non négociable)");
                System.out.println(" ");
                System.out.println("Type de robot vendu : Observateur");
                System.out.println("Prix du robot : 500 000 000 000 $");
                composantes.add("Bras");
                break;

            case "Fournisseur4":
                System.out.println("Composante vendu : Haut-parleur ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour émettre un song.");
                System.out.println("Prix composante : 2 000 000 $ (non négociable)");
                System.out.println(" ");
                System.out.println("Type de robot vendu : Observateur");
                System.out.println("Prix du robot : 500 000 000 000 $");
                composantes.add("Haut-parleur");
                break;

            case "Fourniseur5":
                System.out.println("Composante vendu : Micro ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour émettre un song.");
                System.out.println("Prix composante : 2 000 000 $ (non négociable)");
                System.out.println(" ");
                System.out.println("Type de robot vendu : Observateur");
                System.out.println("Prix du robot : 500 000 000 000 $");
                composantes.add("Haut-parleur");
                break;

            default:
                System.out.println(" ");
                System.out.println("Composante vendu : Roue ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour se déplacer.");
                System.out.println("Prix composante : 5 000 000 $ (non négociable)");
                System.out.println(" ");
                System.out.println("Type de robot vendu : DJ");
                System.out.println("Prix du robot : 300 000 000 000 $");
                composantes.add("Haut-parleur");
                break;
        }


        System.out.print("Voulez-vous acheter un robot ou pluôt une composante ? : ");
        String choix = scanner.nextLine();

        switch (choix.toUpperCase()) {
            case "ROBOT" :
                System.out.print(">>> Valider l'achat du robot ? : ");
                String reponse1 = scanner.nextLine();
        
                if (reponse1.toUpperCase().equals("OUI")){
                    System.out.println("Validation de l'achat en cours...");

                    System.out.println("Achat Validé !");
                    UUID uuid = UUID.randomUUID();
                    uuids.add(uuid.toString());
                    System.out.println("Voici le numéro de série de votre robot : " + uuid);
                    System.out.println("(VEUILLEZ LE NOTER CAR IL EST INDISPENSABLE POUR POUVOIR ENRÉGISTRER VOTRE ROBOT !)");
                } else {
                    System.out.println("Achat annulé...");
                }

            case "COMPOSANTE" :
                System.out.print(">>> Valider l'achat de la composante ? : ");
                String reponse2 = scanner.nextLine();
        
                if (reponse2.toUpperCase().equals("OUI")){
                    System.out.println("Validation de l'achat en cours...");
                    switch (fourniseurs) {
                        case "Fournisseur1":
                        case "Fournisseur3":
                            composantes.add("CPU");
                            break;
            
                        case "Fournisseur2":
                            composantes.add("Bras");
                            break;
            
                        case "Fournisseur4":
                            composantes.add("Haut-parleur");
                            break;
            
                        case "Fourniseur5":
                            composantes.add("Haut-parleur");
                            break;
            
                        default:
                            composantes.add("Haut-parleur");
                            break;
                    }
                    System.out.println("Achat Validé !");
                } else {
                    System.out.println("Achat annulé...");
                }
        }

        
        System.out.print("Voulez-vous revenir au menu principal ? : ");
        String decision = scanner.nextLine();
        if (decision.toUpperCase().equals("OUI")){
            menu(scanner);
        } else {
            acheterComposantes(scanner, fournisseurChoisi);
        }
    }
    
}