package domain.logic.Menu;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Notification;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MenusFournisseur {
	private Menu menu;
	private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
	private DbControleur dbControlleur = new DbControleur();

	public MenusFournisseur() throws IOException {
	}

	public void menuInscriptionFournisseur(Scanner scanner) throws ParseException {
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
			if (NomUnique) {
				System.out.println("Ce nom de fournisseur existe déjà. Veuillez saisir un autre nom: ");
			} else {
				break;
			}
		}

		System.out.println("Mot de passe: ");
		String mdp = scanner.nextLine();

		while (!EmailValide) {
			System.out.print("Adresse courriel: ");
			inputEmail = scanner.nextLine();
			EmailValide = controlleurFournisseurs.verifierEmail(inputEmail);
			if (!EmailValide) {
				System.out.println("Email invalide, veuillez reessayer.");
			}
		}

		while (!TelephoneValide) {
			System.out.print("Numéro de téléphone: ");
			inputTelephone = scanner.nextLine();
			TelephoneValide = controlleurFournisseurs.verifierTelephone(inputTelephone);
			if (!TelephoneValide) {
				System.out
						.println("Le numéro de téléphone doit obtenir exactement 10 caractères. Veuillez réessayez: ");
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

		controlleurFournisseurs.inscriptionFournisseur(inputNom, mdp, inputAdresse, inputEmail,
				inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);

		// menuPrincipale(scanner);
	}

	public void menuConnexionFournisseur(Scanner scanner) throws ParseException, IOException {
		System.out.println("Veuillez entrez votre nom de fournisseur: ");
		String nomFournisseur = scanner.nextLine();
		if (controlleurFournisseurs.authentificationFournisseur(nomFournisseur, "Fournisseur")) {
			System.out.println("Bienvenue " + nomFournisseur + "!");
			menuFournisseur(scanner, nomFournisseur);
		} else {
			System.out.println(nomFournisseur + " n'existe pas.");
			menu.menuPrincipale(scanner);
		}
	}

	public void menuFournisseur(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.println("******************** Menu Fournisseur de " + nomFournisseur + " ********************");
		System.out.println(" ");
		LinkedList<Notification> notifications = controlleurFournisseurs.notifier();
		if (notifications.size() > 0) {
			System.out.println(">>> Vous avez " + notifications.size() + "notifications <<<");
		}
		System.out.println(" ");
		System.out.println("Bienvenue ! Veuillez choisir une option:");
		System.out.println("1- Ajouter un nouveau robot");
		System.out.println("2- Retirer un robot");
		System.out.println("3- Enregistrer une composante");
		System.out.println("4- Gérer mes composantes");
		System.out.println("5- Voir mes notifications");
		System.out.println("6- Modifier mon profile");
		System.out.println("7- Faire une requete publique");
		System.out.println("8- Revenir au menu robotix");
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
				} while (continuer);

				controlleurFournisseurs.ajouterRobot();
				System.out.println(" ");
				System.out.println("Le robot a été rajouté avec succès !");
				System.out.println(" ");
				menuFournisseur(scanner, nomFournisseur);
			}

			case "2" -> {
				System.out.print("Veuillez entrer le numero de serie du robot à retirer : ");
				String numeroSerie = scanner.nextLine();
				if (controlleurFournisseurs.retirerRobot(numeroSerie)) {
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
				if (controlleurFournisseurs.retirerComposante(composante2)) {
					System.out.println("La composante a été retirée avec succès !");
				} else {
					System.out.println("Vous ne possédez cette composante.");
				}
				menuFournisseur(scanner, nomFournisseur);
			}

			case "5" -> {
				System.out.println("Au revoir !");
				menu.menuPrincipale(scanner);
			}
		}
	}

	public void menuRequetesPubliques(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.println("Veuillez faire une requete publique : ");
		System.out.println("1- Voir la liste d'utilisateurs");
		System.out.println("2- Voir la liste des fournisseurs");
		System.out.println("3- Voir mon profil");
		System.out.println("4- Chercher utilisateur par: ");// TODO
		System.out.println("5- Recuperer la liste des activites");
		System.out.println("6- Recuperer la liste des interets");
		System.out.println("7- Rechercher fournisseur par nom");// TODO
		System.out.println("8- Rechercher une composante par nom");// TODO
		String choix = scanner.nextLine();
		switch (choix) {
			case "1" -> {
				System.out.println(dbControlleur.recupererListeUtilisateur());
				menuFournisseur(scanner, nomFournisseur);
			}
			case "2" -> {
				System.out.println(dbControlleur.recupererListFournisseur());
				menuFournisseur(scanner, nomFournisseur);
			}
			case "3" -> {
				System.out.println(controlleurFournisseurs.voirProfil());
				menuFournisseur(scanner, nomFournisseur);
			}
			case "4" -> {
				menuChercherUtilisateur(scanner, nomFournisseur);
				menuFournisseur(scanner, nomFournisseur);
			}
			case "5" -> {
				System.out.println(dbControlleur.recupererListeActivite());
				menuFournisseur(scanner, nomFournisseur);
			}
			case "6" -> {
				menuRechercheInterets(scanner, nomFournisseur);
				menuFournisseur(scanner, nomFournisseur);
			}
			case "7" -> {
				menuChercherFournisseur(scanner, nomFournisseur);
				menuFournisseur(scanner, nomFournisseur);
			}
			case "8" -> {
				menuRechercheComposante(scanner, nomFournisseur);
				menuFournisseur(scanner, nomFournisseur);
			}
		}
	}

	public void menuRechercheComposante(Scanner scanner, String nomFournisseur) {
		System.out.println("Filtrer par: ");
		System.out.println("1- Type de la composante");
		System.out.println("2- Nom de fournisseur");
		String decision = scanner.nextLine();
		switch (decision) {
			case "1" -> {
				System.out.println("Entrez le type : ");
				String nom = scanner.nextLine();
				System.out.println(dbControlleur.rechercherComposantParType(nom));
			}
			case "2" -> {
				System.out.println("Entrez le nom de fournisseur : ");
				String nomFour = scanner.nextLine();
				System.out.println(dbControlleur.rechercherComposantParNomFournisseur(nomFour));
			}
		}
	}

	public void menuRechercheInterets(Scanner scanner, String nomFournisseur) {
		System.out.println("Voulez vous appliquer un filtre?");
		System.out.println("1- Oui");
		System.out.println("2- Non");
		String decision = scanner.nextLine();
		switch (decision) {
			case "1" -> {
				System.out.println("Par quel filtre voulez vous filtrer?");
				System.out.println("1- Filtrer par trois premieres lettres");
				System.out.println("2- Filtrer par pseudo utilisateur");
				System.out.println("3- Filtrer par pseudo utilisateur et trois premieres lettres d'interets");
				String decisionFiltre = scanner.nextLine();
				switch (decisionFiltre.toUpperCase()) {
					case "1" -> {
						System.out.println("Entrez vos 3 characteres");
						String troislettre = scanner.nextLine();
						System.out.println(
								dbControlleur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre));
					}
					case "2" -> {
						System.out.println("Entrez le pseudo de l'utilisateur");
						String pseudo = scanner.nextLine();
						System.out.println(dbControlleur.recupererListeInteretUtilisateur(pseudo));

					}
					case "3" -> {
						System.out.println("Entrez le pseudo de l'utilisateur: ");
						String pseudo = scanner.nextLine();
						System.out.println("Entrez les 3 characteres de l'interet");
						String troislettre = scanner.nextLine();
						System.out.println(
								dbControlleur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(
										pseudo, troislettre));
					}
				}
			}
			case "2" -> {
				System.out.println(dbControlleur.recupererListeInteret());
			}
		}
	}

	public void menuChercherFournisseur(Scanner scanner, String nomFournisseur) {
		System.out.println("Filtrer par:");
		System.out.println("1- Nom");
		System.out.println("2- Email");
		System.out.println("3- Adresse");
		System.out.println("4- Type de composantes");
		String decision = scanner.nextLine();
		switch (decision) {
			case "1" -> {
				System.out.println("Entrez le nom du fournisseur : ");
				String nom = scanner.nextLine();
				System.out.println(dbControlleur.rechercherFournisseurParNom(nom));
			}
			case "2" -> {
				System.out.println("Entrez l'email du fournisseur : ");
				String email = scanner.nextLine();
				System.out.println(dbControlleur.rechercherFournisseurParEmail(email));
			}
			case "3" -> {
				System.out.println("Entrez l'adresse du fournisseur : ");
				String adresse = scanner.nextLine();
				System.out.println(dbControlleur.rechercherFournisseurParAdresse(adresse));
			}
			case "4" -> {
				System.out.println("Entrez le type de composantes : ");
				String type = scanner.nextLine();
				System.out.println(dbControlleur.rechercherFournisseurParTypeDeComposant(type));
			}
		}
	}

	public void menuChercherUtilisateur(Scanner scanner, String nomFournisseur) {
		System.out.println("Filtrer par:");
		System.out.println("1- Pseudo");
		System.out.println("2- Nom");
		System.out.println("3- Prenom");
		System.out.println("4- Obtenir liste des suiveurs de? (pseudo voulu)");
		String decision = scanner.nextLine();
		switch (decision) {
			case "1" -> {
				System.out.println("Entrez le pseudo");
				String decisionPseudo = scanner.nextLine();
				System.out.println((dbControlleur.rechercherUtilisateurParPseudo(decisionPseudo)));
			}
			case "2" -> {
				System.out.println("Entrez le nom");
				String decisionNom = scanner.nextLine();
				System.out.println(dbControlleur.rechercherUtilisateurParNom(decisionNom));
			}
			case "3" -> {
				System.out.println("Entrez le prenom");
				String decisionPrenom = scanner.nextLine();
				System.out.println(dbControlleur.rechercherUtilisateurParPrenom(decisionPrenom));
			}
			case "4" -> {
				System.out.println("Entrez le pseudo");
				String pseudoUtilisateur = scanner.nextLine();
				System.out.println(dbControlleur.rechercherUtilisateurParSuiveur(pseudoUtilisateur));
			}
		}
	}

	private boolean demander(String reponse, Scanner scanner) {
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

	public void menuEnregistrerComposante(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.print("Nom de la composante : ");
		String composante = scanner.nextLine();
		System.out.print("Prix : ");
		String prix = scanner.nextLine();
		System.out.print("Description : ");
		String description = scanner.nextLine();
		System.out.print("Type de la composante : ");
		String type = scanner.nextLine();
		controlleurFournisseurs.ajouterComposante(composante, Double.parseDouble(prix), description, type,
				nomFournisseur);
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