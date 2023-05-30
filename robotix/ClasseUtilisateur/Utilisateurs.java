import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import domain.logic.Fournisseur.Fournisseur;

public class Utilisateurs {
    public static void main(String[] args) {
        /*LinkedList<String> composantes = new LinkedList<String>();
        Robot r = new Robot("Bobby",100, 150, 20, 58, 20, 0.5, new LinkedList<String>(), new LinkedList<String>());
        afficherEtatRobot(r);
        LinkedList<String> comp = ajouterComposantes(composantes, new Scanner(System.in)); 
        creerAction(comp, r);
        allouerTachesRobot(r);
        voirActivitesMaintenues(r);*/

        enregistrerRobot();
    }

//----Kamen----------------------------------------------------------------------------------------------------------------

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(LinkedList<String> composantes, Scanner scanner){
        String input = "";
        while (!input.equals("None")){
            System.out.print("Ajouter composante: ");
            input = scanner.nextLine();
            if (!input.equals("None")){
                composantes.add(input);
            }
        }

        System.out.println(composantes);
        return composantes;
    }

    /* REMARQUE :
        Si un robot prend des actions en paramètre, pourquoi est-ce qu'on a besoin d'un robot en paramètre pour créer des action (pour créer un robot, on a besoin d'action
        et pour creer des actions, on a besoin d'un robot...)*/

    //A partir de ce qui a été scanner, on produit une tache
    public static void creerAction(LinkedList<String> composantes, Robot robot) {
        Scanner scan = new Scanner(System.in);
        String action = "";
        boolean missingComponentAdded = false;
    
        while (!action.equals("None")) {
            System.out.print("Ajouter action: ");
            action = scan.nextLine();
            if (action.equals("Parler")) {
                if (composantes.contains("Haut-parleur")) {
                    System.out.print(robot.nom + " peut parler! \n");
                    robot.actions.add("Parler");
                } else {
                    System.out.print("Il vous manque la composante : Haut-parleur \n");
                    missingComponentAdded = true;
                }
            } else if (action.equals("Ecouter")) {
                if (composantes.contains("Micro")) {
                    System.out.print(robot.nom + " peut ecouter! \n");
                    robot.actions.add("Ecouter");
                } else {
                    System.out.print("Il vous manque la composante : Micro \n");
                    missingComponentAdded = true;
                }
            } else if (action.equals("Deplacer")) {
                if (composantes.contains("Roue")) {
                    System.out.print(robot.nom + " peut se deplacer! \n");
                    robot.actions.add("Deplacer");
                } else {
                    System.out.print("Il vous manque la composante : Roue \n");
                    missingComponentAdded = true;
                }
            }
    
            if (missingComponentAdded) {
                composantes = ajouterComposantes(composantes,scan);  // Pass the Scanner object
                missingComponentAdded = false;
            }
        }
    
        System.out.println(robot.actions);
    }
    

    //Prends un tableau d'actions en parametre, et output 
    //Prends une liste chainées des actions voulues, et ouput une liste chainées avec la tâches ajoutées
    public static void allouerTachesRobot(Robot r){
        Scanner scan = new Scanner(System.in);
        String tacheVoulue = "";
        Boolean end = true;
    
        while (end){
            System.out.print("Veuillez creer une tache:");
            tacheVoulue = scan.nextLine();
            if (r.actions.contains("Parler") && r.actions.contains("Deplacer") && tacheVoulue.equals("Deplacer et dire allo")){
                r.taches.add("Se deplacer et dire 'Allo!' a l'utilisateur");
                System.out.println("Voici les taches de " + r.nom + ": " + r.taches);
            }
            else if (r.actions.contains("Parler") && r.actions.contains("Deplacer") && r.actions.contains("Ecouter")){
                r.taches.add("Se deplacer, ecouter l'entree sonore de l'utilisateur et la répéter");
                System.out.println("Voici les taches de " + r.nom + ": " + r.taches);
            } else if (tacheVoulue.equals("None")){
                end = false;
            }else {
                System.out.println("Il manque des actions pour creer une tache!");
                creerAction(r.actions, r);
            }
        }
        scan.close();
        
    }

    public static void voirActivitesMaintenues(Robot r){
        System.out.println("\n\n Voici les activitées maintenues par " + r.nom);
        for (String act : r.taches){
            System.out.println("    - " + act);
        }
    }

    public static void afficherEtatRobot(Robot robot){ 
        System.out.println("Nom: " + robot.nom);
        System.out.println("Coordonnées: " + "X: " + robot.X  + "; Y: " + robot.Y);
        System.out.println("Vitesse: " + robot.vitesse + "km/h");
        System.out.println("Batterie: " + robot.batterie + "%");
        System.out.println("CPU: " + robot.cpu + "%");
        System.out.println("Memoire: " + robot.memoire + "%");
    }

    public static void creerTache() {
        // TODO
    }



//-Samir-----------------------------------------------------------------------------------------------


    public static void enregistrerRobot() {
        LinkedList<Robot> robots = new LinkedList<>();
        System.out.println("************* Enrégistrer un robot *************");
        do {
            LinkedList<String> infosRobot = demanderInfosRobots();
            // CREER DES ACTIONS.
            robots.add(new Robot(infosRobot.get(0), 0, 0, 0, 100, 20, Double.parseDouble(infosRobot.get(1)) , null, null));
        } while(enregistrerDeNouveau());
        
    }

    public static LinkedList<String> demanderInfosRobots() {
        // TODO
        // Contient dans l'ordre : Nom, composantes
        LinkedList<String> infosRobots;

        int nombreCompoantes = 2;
        infosRobots = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("Nom du robot à ajouter : ");
        String nom = scanner.nextLine();
        System.out.print("Capacité mémoire de ce robot (en GB) : ");
        String memoire = scanner.nextLine();

        boolean repeat = true;
        do {
            try {
                System.out.print("Nombre de composantes à rajouter au robot (minimum de 2) : ");
                nombreCompoantes = Integer.parseInt(scanner.nextLine());
                if (nombreCompoantes >= 2) {
                    infosRobots.clear(); // Clear the list before adding new components
                    for (int i = 1; i <= nombreCompoantes; ++i) {
                        System.out.print("Composante " + i + " : ");
                        String choix = scanner.nextLine();
                        infosRobots.add(choix);
                        //System.out.println();
                    }
                    verifierComposantes(infosRobots);
                    repeat = false;
                } else {
                    System.out.println(" ");
                    System.out.print("Veuillez entrer une valeur >= 2.");
                    System.out.println(" ");
                }
            } catch (IOException e) {
                System.out.println(" ");
                System.out.print("Votre liste de composantes doit contenir au moins un CPU et une autre composante.");
                System.out.println(" ");
                infosRobots.clear();
                continue;
            }
        
        } while (repeat);
        infosRobots.addFirst(memoire);
        infosRobots.addFirst(nom);
        System.out.println("Enregistrement réussi ! ");
        scanner.close();
        
        return infosRobots;
        

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

    public static Boolean enregistrerDeNouveau() {
        boolean repeter = true;
        boolean result = false;
        Scanner scanner = new Scanner(System.in);
        while (repeter) {
            System.out.println(" ");
            System.out.println("Choisissez une option (entrez simplement le chiffre) : ");
            System.out.println("1- Enregistrer un autre robot");
            System.out.println("2- Quitter");
            System.out.print(">>> Votre choix : ");
            String answer = scanner.nextLine();
            if (answer.equals("1")) {
                result = true;
                repeter = false;
            } else if (answer.equals("2")) {
                result = false;
                repeter = false;
            } else {
                System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
        return result;
    }
    
    

    public static void afficherMetriquesFlotte() {
        // TODO
    }

    public static void gestionDesProblèmes() {
        // TODO
    }

    public static void participerActivités() {
        // TODO
    }

    /*public static void trouverFournisseurs() {
        HashMap<String, String> fournisseursChoisis = new HashMap<>();

        String[] caracteristiques1 = {"Nom1", "Deplacement", "Roue", "3000 $", "adresse1", "514-524-5341", "10"};
        String[] caracteristiques2 = {"Nom2", "Discussion", "Haut-parleur", "60'000 $", "adresse2", "514-524-5342", "3"};
        String[] caracteristiques3 = {"Nom3", "Ecoute", "Micro", "5'865'421 $", "adresse3", "514-524-5343", "7"};
        String[] caracteristiques4 = {"Nom4", "Performance", "CPU", "1'000'000'000 $", "adresse4", "514-524-5344", "6"};
        String[] caracteristiques5 = {"Nom5", "Affichage", "Camera", "300'000'000 $", "adresse5", "514-524-5345", "15"};
        String[] caracteristiques = {"Nom","Type de robot","Composantes vendues", "Prix", "Adresse","Contacte","Capacité"};


        HashMap<String, ArrayList<String>> fournisseurs = new HashMap<>();
        fournisseurs.put("Nom1", new ArrayList<String>());
        fournisseurs.put("Nom2", new ArrayList<String>());
        fournisseurs.put("Nom3", new ArrayList<String>());
        fournisseurs.put("Nom4", new ArrayList<String>());
        fournisseurs.put("Nom5", new ArrayList<String>());

        for (int i = 0; i < caracteristiques1.length; ++i) {
            fournisseurs.get("Nom1").add(caracteristiques1[i]);
            fournisseurs.get("Nom2").add(caracteristiques2[i]);
            fournisseurs.get("Nom3").add(caracteristiques3[i]);
            fournisseurs.get("Nom4").add(caracteristiques4[i]);
            fournisseurs.get("Nom5").add(caracteristiques5[i]);
        }

        Scanner scanner = new Scanner(System.in);
        String continuer = true;

        System.out.println("************* Choix de founisseurs *************");

        While (continuer); {
            System.out.println(" ");
            System.out.println("Veuillez choisir une option :");
            System.out.println("1- Effectuer un choix avec filtre");
            System.out.println("2- Effectuer un choix sans filtre");
            System.out.println("3- Quitter");
            String choix = scanner.nextLine();

            if (choix.equals("1")){
                trouverAvecFiltre();
            } else {
                trouverSansFiltre();
            }

        }

        
    }

    public static void trouverSansFiltre() {
        
        HashMap<String, String> fournisseursAffichage = new HashMap<>();
        fournisseursAffichage.put("Nom1", "Fournisseur 1" + ": " + "Nom1" + ", Type de robot vendue : " + "Deplacement" + ", Composante vendue : " + "Roue");
        fournisseursAffichage.put("Nom2", "Fournisseur 2" + ": " + "Nom2" + ", Type de robot vendue : " + "Discussion" + ", Composante vendue : " + "Haut-parleur");
        fournisseursAffichage.put("Nom3", "Fournisseur 3" + ": " + "Nom3" + ", Type de robot vendue : " + "Ecoute" + ", Composante vendue : " + "Micro");
        fournisseursAffichage.put("Nom4", "Fournisseur 4" + ": " + "Nom4" + ", Type de robot vendue : " + "Performance" + ", Composante vendue : " + "CPU");
        fournisseursAffichage.put("Nom5", "Fournisseur 5" + ": " + "Nom5" + ", Type de robot vendue : " + "Affichage" + ", Composante vendue : " + "Camera");

        System.out.println(">>> Veuillez choisir un fournisseur dans la liste de fournisseurs suivante en entrant sont nom :");
    
        for (int i = 1; i < end; ++i) {
            String fournis = fournisseursAffichage.get("Nom"+i);
            if (fournis == null){
                continue;
            } else {
                System.out.println(fournis);
            }
        }
    
        System.out.println("Si vous ne voulez plus choisir, entrez juste 'Aucun'. ");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        Boolean verifierChoix = fournisseursAffichage.containsKey(choix);

        if (!choix.equals("Aucun") && verifierChoix){
            if (afficherDetailsFournisseur(choix, fournisseurs)){
                fournisseursChoisis.put(choix, fournisseursAffichage.remove(choix));
            }
        } else if (!verifierChoix){
            System.out.println(" ");
            System.out.println("Veuillez entrer un nom valide SVP !");
            continue;
        } else{
            System.out.println("Merci !");
            break;
        }

        if (fournisseursAffichage.size() > 0) {
            System.out.println("Voulez vous effectuer un autre choix ?");
            String reponse = scanner.nextLine();
        
            if (reponse.toUpperCase().equals("OUI")) {
                choix = "";
            } else {
                System.out.println("Merci  !");
                break;
            }
        } else {
            System.out.println("Il n'y a plus de fournisseurs à choisir. Merci !");
        }


        scanner.close();


    }

    public static Boolean checkElemDansTab(String[] tab, String valeur) {

        Boolean bool = false;

        for (String string : tab) {
            if (string.equals(valeur)){
                bool = true;
            }
        }
        return bool;
    }

    public static void trouverAvecFiltre() {

        System.out.println("Quel filtre désirez vous utiliser parmi les filtre suivant : ");
        System.out.println("-> Nom");
        System.out.println("-> Type de robot");
        System.out.println("-> Composantes vendues");
        System.out.println("-> Adresse");
        System.out.println("-> Contacte");
        System.out.println("-> Capacité");

        String filtre = scanner.nextLine();

        if (checkElemDansTab(caracteristiques, filtre)){
            
            switch (filtre) {

                case "Nom":
                    System.out.print("Entrez le nom recherché : ");
                    String nom = scanner.nextLine();
                    if (fournisseurs.containsKey(nom)){
                        System.out.println(fournisseursAffichage.get(nom));
                    } else{
                        System.out.println("Il n'y a aucun fournisseur de ce nom.");
                    }

                case "Type de robot":
                    System.out.print("Entrez le type de robot recherché : ");
                    String type = scanner.nextLine();
                    int j = 0;
                    for (int i = 0; i < fournisseurs.size(); ++i){
                        if (type.equals(fournisseurs.get("Nom"+i).get(1))){
                            afficherDetailsFournisseur("Nom"+i, fournisseurs);
                            j++;
                        } 
                    }

                    if (j == 0) {
                        System.out.println("Il n'y a pas de fournisseur produisant des robots de ce type.");
                    }
                    
                case "Composantes vendues":
                    System.out.print("Entrez la composante recherchée : ");
                    String composante = scanner.nextLine();
                    int k = 0;
                    for (int i = 0; i < fournisseurs.size(); ++i){
                        if (composante.equals(fournisseurs.get("Nom"+i).get(2))){
                            afficherDetailsFournisseur("Nom"+i, fournisseurs);
                            k++;
                        } 
                    }

                    if (k == 0) {
                        System.out.println("Il n'y a pas de fournisseur qui vendent cette composante.");
                    }

            }

            afficherDetailsFournisseur(choix, fournisseurs);
        } 
    }

    public static Boolean validerChoix() {
        String repeat = true;
        Boolean bool = false;
        while (repeat) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Voulez vous valider votre choix ? (répondre par oui ou non) : ");
            String validation = scanner.nextLine();

            if (validation.toUpperCase().equals("OUI")) {
                bool = true;
                repeat = false;
            } else if (validation.toUpperCase().equals("NON")) {
                bool = false;
                repeat = false;
            } else {
                System.out.println("Veuillez entrer soit oui, soit non.");
                repeat = true;
            }
        }
        return bool;
    }

    public static Boolean afficherDetailsFournisseur(String choix, HashMap<String, ArrayList<String>> founisseurs) {

        ArrayList<String> fournisseur = founisseurs.get(choix);
        Boolean bool = false;

        System.out.println(" ");
        System.out.println("***** Détails du fournisseurs ***** ");
        System.out.println(" ");
        System.out.println("Nom : " + fournisseur.get(0));
        System.out.println("Type de robot : " + fournisseur.get(1));
        System.out.println("Composantes vendues : " + fournisseur.get(2) + " " + fournisseur.get(3));
        System.out.println("Adresse : " + fournisseur.get(4));
        System.out.println("Contacte : " + fournisseur.get(5));
        System.out.println("Capacité : " + fournisseur.get(6));


        if (validerChoix()) {
            System.out.println(" ");
            System.out.println("Enrégistrement réussi !");
            System.out.println(" ");
            System.out.println("Veuillez choisir une option :");
            System.out.println("1- Effectuer un choix avec filtre");
            System.out.println("2- Effectuer un choix sans filtre");
            System.out.println("3- Quitter");
            
            switch (scanner.nextLine()){
                case "1":
                    trouverAvecFiltre();

                case "2":
                    bool = true;

                case "3":


            }
        }
        return bool;
        
    }*/

}