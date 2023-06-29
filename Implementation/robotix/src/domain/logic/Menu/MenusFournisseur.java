package domain.logic.Menu;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Membre.Fournisseur;

import java.util.ArrayList;
import java.util.Scanner;

public class MenusFournisseur {

	private Menu menu;
	private ControlleurFournisseurs controlleurFournisseurs;

	public void menuInscriptionFournisseur(Scanner scanner) {
		boolean NomUnique = false;
		boolean EmailValide = false;
		boolean TelephoneValide = false;

		String inputNom = "";
		String inputEmail = "";
		String inputTelephone = "";
		String inputAdresse;
		String inputTypeRobot;
		String inputTypeComposantes;
		String inputCapacite;
		String inputCompagnie;

		System.out.println("********Nouveau fournisseur********");

		while (!NomUnique) {
			System.out.print("Nom: ");
			inputNom = scanner.nextLine();
			NomUnique = controlleurFournisseurs.verifierNom(inputNom);
			if (!NomUnique){
				System.out.println("Ce nom de fournisseur existe déjà. Veuillez saisir un autre nom: ");
			}
		}

		while (!EmailValide) {
			System.out.print("Adresse courriel: ");
			inputEmail = scanner.nextLine();
			EmailValide = controlleurFournisseurs.verifierEmail(inputEmail);
			if (!EmailValide) {
				System.out.println("Email invalide, veuillez reessayer.");
			}
		}

		while (!TelephoneValide) {
			System.out.print("Num�ro de t�l�phone: ");
			inputTelephone = scanner.nextLine();
			TelephoneValide = controlleurFournisseurs.verifierTelephone(inputTelephone);
			if (!TelephoneValide) {
				System.out.println("Le num�ro de t�l�phone doit obtenir exactement 10 caract�res. Veuillez r�essayez: ");
			}
		}

		System.out.print("Adresse : ");
		inputAdresse = scanner.nextLine();
		System.out.print("Type de robots fabriqu�s: ");
		inputTypeRobot = scanner.nextLine();
		System.out.print("Type de composantes fabriqu�es: ");
		inputTypeComposantes = scanner.nextLine();
		System.out.print("Capacit� de fabrication: ");
		inputCapacite = scanner.nextLine();
		System.out.print("Nom de compagnie: ");
		inputCompagnie = scanner.nextLine();

		controlleurFournisseurs.inscriptionFournisseur(inputNom, inputAdresse, inputEmail,
				inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);

		menu.menuPrincipale(scanner);
	}

	public void menuConnexionFournisseur(Scanner scanner) {
		System.out.println("Veuillez entrez votre nom de fournisseur: ");
		String nomFounisseur = scanner.nextLine();
		if (controlleurFournisseurs.authentificationFournisseur(nomFounisseur, "Fournisseur")) {
			System.out.println("Bienvenue " + nomFounisseur + "!");
			menuFournisseur(scanner, nomFounisseur);
		} else {
			System.out.println(nomFounisseur + " n'existe pas.");
			menu.menuPrincipale(scanner);
		}
	}

	public void menuFournisseur(Scanner scanner, String nomFournisseur) {
		System.out.println("******************** Menu Fournisseur de " + nomFournisseur + " ********************");
		System.out.println("Bienvenue ! Veuillez choisir une option:");
		System.out.println("1- Ajouter un nouveau robot");
		System.out.println("2- Retirer un robot");
		System.out.println("3- Enregistrer une composante");
		System.out.println("4- Retirer une composante");
		System.out.println("5- Modifier mon profile fournisseur");
		System.out.println("6- Revenir au menu robotix");
		System.out.print(">>> Votre choix : ");
		String choixUsager = scanner.nextLine();

		switch (choixUsager) {
			case "1" -> {
				String composante;
				boolean continuer;
				do {
					System.out.print("Entrez une composante que possède le robot à rajouter : ");
					composante = scanner.nextLine();
					System.out.println("Voulez-vous rajouter une autre composante ? (repondre par oui ou non)");
					String reponse = scanner.nextLine();
					continuer = demander(reponse, scanner);
				} while(continuer);

				controlleurFournisseurs.ajouterRobot(nomFournisseur);
				System.out.println(" ");
				System.out.println("Le robot a été rajouté avec succès !");
				System.out.println(" ");
				menuFournisseur(scanner, nomFournisseur);
			}

			case "2" -> {
				System.out.print("Veuillez entrer le nom du robot à retirer : ");
				String nomRobot2 = scanner.nextLine();
				if(controlleurFournisseurs.retirerRobot(nomRobot2, nomFournisseur)){
					System.out.println("Le robot a été retiré avec succès !");

				} else {
					System.out.println("Vous ne possédez ce robot.");
				}
				menuFournisseur(scanner, nomFournisseur);
			}

			case "3" -> menuEnregistrerComposante(scanner, nomFournisseur);

			case "4" -> {
				System.out.print("Nom de la composante : ");
				String composante2 = scanner.nextLine();
				if(controlleurFournisseurs.retirerComposante(composante2, nomFournisseur)){
					System.out.println("La composante a été retirée avec succès !");
				} else {
					System.out.println("Vous ne possédez cette composante.");
				}
				menuFournisseur(scanner, nomFournisseur);
			}

			case "5" -> menuModifierProfileFournisseur(scanner);

			case "6" -> {
				System.out.println("Au revoir !");
				menu.menuPrincipale(scanner);
			}
		}
	}

	private boolean demander(String reponse, Scanner scanner){
		boolean continuer = false;
		boolean choix = false;
		do {
			if (reponse.equalsIgnoreCase("oui")) {
				choix = true;
				continuer = false;
			} else if (!reponse.equalsIgnoreCase("non")) {
				System.out.println("Choisissez entre 'oui' et 'non' svp.");
				reponse = scanner.nextLine();
				continuer = true;
			} else {
				continuer = false;
			}
		} while (continuer);
		return choix;
	}


	public void menuModifierProfileFournisseur(Scanner scanner) {
		// TODO - implement MenusFournisseur.menuModifierProfileFournisseur
		throw new UnsupportedOperationException();
	}

	public void menuGererComposantes(Scanner scanner) {
		// TODO - implement MenusFournisseur.menuGererComposantes
		throw new UnsupportedOperationException();
	}

	public void menuEnregistrerComposante(Scanner scanner, String nomFournisseur) {
		System.out.print("Nom de la composante : ");
		String composante = scanner.nextLine();
		System.out.print("Prix : ");
		String prix = scanner.nextLine();
		System.out.print("Description : ");
		String description = scanner.nextLine();
		System.out.print("Type de la composante : ");
		String type = scanner.nextLine();
		controlleurFournisseurs.ajouterComposante(composante, Double.parseDouble(prix), description, type, nomFournisseur);
		System.out.println(" ");
		System.out.println("La composante a été rajoutée avec succès");
		System.out.println(" ");
		menuFournisseur(scanner, nomFournisseur);
	}

	public void menuNotifications(Scanner scanner) {
		// TODO - implement MenusFournisseur.menuNotifications
		throw new UnsupportedOperationException();
	}

	public void menuVoirProfileFournisseur(Scanner scanner) {
		// TODO - implement MenusFournisseur.menuVoirProfileFournisseur
		throw new UnsupportedOperationException();
	}

}