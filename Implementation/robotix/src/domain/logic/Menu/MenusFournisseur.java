package domain.logic.Menu;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.DbControleur;
import domain.logic.GUI.FournisseurGUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MenusFournisseur extends JFrame {
	private Menu menu;
	private ControlleurFournisseurs controlleurFournisseurs=new ControlleurFournisseurs();
	private DbControleur dbControlleur = new DbControleur();
	private JFrame jFrame = new JFrame();
	private JPanel menuFournisseurPanel = new JPanel();
	private JLabel menuFournisseurLabel = new JLabel("Menu Fournisseur");
	private JButton btnAjouterRobot = new JButton("Ajouter un nouveau robot");
	private JButton btnRetirerRobot = new JButton("Retirer un robot");
	private JButton btnEnregistrerComposante = new JButton("Enregistrer une composante");
	private JButton btnGererComposante = new JButton("Gerer mes composantes");
	private JButton btnModifierProfil = new JButton("Modifier mon profil");
	private JButton btnRequetePublique = new JButton("Faire une requete publique");
	private JButton btnRetour = new JButton("Retour");
	private AjouterRobotGUI ajouterRobotGUI = new AjouterRobotGUI();
	private RetirerRobotGUI retirerRobotGUI = new RetirerRobotGUI();
	private EnregistrerComposanteGUI enregistrerComposanteGUI = new EnregistrerComposanteGUI();
	private GererComposantesGUI gererComposantesGUI = new GererComposantesGUI();
	private ModifierProfilFournisseurGUI modifierProfilFournisseurGUI = new ModifierProfilFournisseurGUI();
	private RequetePubliqueFournisseurGUI requetePubliqueFournisseurGUI = new RequetePubliqueFournisseurGUI();

	public MenusFournisseur() throws IOException {
		menuFournisseurPanel.setLayout(new GridLayout(0, 2, 5, 5));
		menuFournisseurPanel.add(menuFournisseurLabel);
		menuFournisseurPanel.add(btnAjouterRobot);
		menuFournisseurPanel.add(btnRetirerRobot);
		menuFournisseurPanel.add(btnEnregistrerComposante);
		menuFournisseurPanel.add(btnGererComposante);
		menuFournisseurPanel.add(btnModifierProfil);
		menuFournisseurPanel.add(btnRequetePublique);
		menuFournisseurPanel.add(btnRetour);
		btnAjouterRobot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.getContentPane().removeAll();
				jFrame.setContentPane(ajouterRobotGUI.getPanel());
				mettreAJourFrame();
			}
		});
		btnRetirerRobot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retirerRobotGUI.afficherMainPanel(jFrame);
			}
		});
		btnEnregistrerComposante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enregistrerComposanteGUI.afficherMainPanel(jFrame);
			}
		});
		btnGererComposante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gererComposantesGUI.afficherMainPanel(jFrame);
			}
		});
		btnModifierProfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierProfilFournisseurGUI.afficherMainPanel(jFrame);
			}
		});
		btnRequetePublique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requetePubliqueFournisseurGUI.afficherMainPanel(jFrame);
			}
		});
		btnRetour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public void afficherMenuFournisseur(JFrame jFrame) {
		this.jFrame = jFrame;
		this.jFrame.getContentPane().removeAll();
		this.jFrame.setContentPane(menuFournisseurPanel);
		mettreAJourFrame();
	}

	public void mettreAJourFrame() {
		jFrame.revalidate();
		jFrame.repaint();
	}

	public void menuInscriptionFournisseur(Scanner scanner) throws ParseException, IOException {
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

		System.out.println("Mot de passe: ");
		String mdp = scanner.nextLine();
		boolean continuer = false;
		do {
			System.out.print("Veuillez entrer votre mot de passe de nouveau: ");
			String confMdp = scanner.nextLine();
			if(!confMdp.equals(mdp)){
				continuer = true;
			}
		} while (continuer);
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
       this.controlleurFournisseurs= new ControlleurFournisseurs(inputNom,mdp,inputAdresse,inputEmail,
			   inputTelephone,inputTypeRobot,inputTypeComposantes,inputCapacite,inputCompagnie);
		controlleurFournisseurs.inscriptionFournisseur(inputNom, mdp, inputAdresse, inputEmail,
				inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);
        menuFournisseur(scanner, inputNom);//menu.menuPrincipale(scanner);
	}

	public void menuConnexionFournisseur(Scanner scanner) throws ParseException, IOException {
		System.out.println("Veuillez entrez votre nom de fournisseur: ");
		String nomFounisseur = scanner.nextLine();
		System.out.println("Veuillez entrez le mdp: ");
		String mdp = scanner.nextLine();
		if (controlleurFournisseurs.authentificationFournisseur(nomFounisseur, mdp)) {
			System.out.println("Bienvenue " + nomFounisseur + "!");
			menuFournisseur(scanner, nomFounisseur);
		} else {
			System.out.println(nomFounisseur + " n'existe pas.");
			menu = new Menu();
			menu.menuPrincipale(scanner);
		}
	}

	public void menuFournisseur(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.println("******************** Menu Fournisseur de " + nomFournisseur + " ********************");
		System.out.println("Bienvenue ! Veuillez choisir une option:");
		System.out.println("1- Ajouter un nouveau robot");
		System.out.println("2- Retirer un robot");
		System.out.println("3- Enregistrer une composante");
		System.out.println("4- Gérer mes composantes");
		System.out.println("5- Modifier mon profile");
		System.out.println("6- Revenir au menu robotix");
		System.out.println("7- Faire une requete publique");
		System.out.print(">>> Votre choix : ");
		String choixUsager = scanner.nextLine();

		switch (choixUsager) {
			case "1" -> {

				ArrayList<ArrayList<String>> nomsComposantAajouter=new ArrayList<>();

				boolean continuer;
				do {
					System.out.print("Veuillez entrez les information du composant : ");
					nomsComposantAajouter.add(menuEnregistrerComposante(scanner));
					//TODO
					System.out.println("Voulez-vous rajouter une autre composante ? (repondre par oui ou non)");
					String reponse = scanner.nextLine();
					continuer = demander(reponse, scanner);
				} while(continuer);

				UUID uuid= controlleurFournisseurs.ajouterRobot(nomsComposantAajouter);
				System.out.println(" ");
				System.out.println( String.format("Voici le numero de seri du robot : %s",uuid ) );
				System.out.println("Le robot a été rajouté avec succès !");
				System.out.println(" ");
				menuFournisseur(scanner, nomFournisseur);
			}
			case "2" -> {
				System.out.print("Veuillez entrer le numero de serie du robot à retirer : ");
				String numeroSerie = scanner.nextLine();
				if(controlleurFournisseurs.retirerRobot(numeroSerie)){
					System.out.println("Le robot a été retiré avec succès !");

				} else {
					System.out.println("Vous ne possédez ce robot.");
				}
				menuFournisseur(scanner, nomFournisseur);
			}
			case "3" -> menuEnregistrerUnComposante(scanner, nomFournisseur);
			case "4" -> menuGererComposantes(scanner, nomFournisseur);
			case "5" -> menuMotifierProfileFournisseur(scanner, nomFournisseur);
			case "6" -> {
				System.out.println("Au revoir !");
				menu = new Menu();
				menu.menuPrincipale(scanner);
			}
			case "7" -> menuRequetesPubliques(scanner, nomFournisseur);
		}
	}

	public void menuEnregistrerUnComposante(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.print("Nom de la composante : ");
		String composante = scanner.nextLine();
		System.out.print("Prix : ");
		String prix = scanner.nextLine();
		System.out.print("Description : ");
		String description = scanner.nextLine();
		System.out.print("Type de la composante : ");
		String type = scanner.nextLine();
		controlleurFournisseurs.ajouterComposante(composante,  prix, description, type);
		System.out.println(" ");
		System.out.println("La composante a été rajoutée avec succès");
		System.out.println(" ");
		menuFournisseur(scanner, nomFournisseur);
	}


	public void menuRequetesPubliques(Scanner scanner,String nomFournisseur) throws ParseException, IOException {
		System.out.println("Veuillez faire une requete publique : ");
		System.out.println("1- Voir la liste d'utilisateurs");
		System.out.println("2- Voir la liste des fournisseurs");
		System.out.println("3- Voir mon profil");
		System.out.println("4- Chercher utilisateur par: ");//TODO
		System.out.println("5- Recuperer la liste des activites");
		System.out.println("6- Recuperer la liste des interets");
		System.out.println("7- Rechercher fournisseur ");//TODO
		System.out.println("8- Rechercher une composante");//TODO
		String choix = scanner.nextLine();
		switch (choix){
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
				menuChercherUtilisateur(scanner);
				menuFournisseur(scanner, nomFournisseur);
			}
			case "5" -> {
				String acts = dbControlleur.recupererListeActivite();
				if (acts.length() == 0){
					System.out.println("Il n'y a aucune activitée.");
				} else {
					System.out.println(acts);
				}
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


	public void menuRechercheComposante(Scanner scanner, String nomFournisseur){
		System.out.println("Filtrer par: ");
		System.out.println("1- Type de la composante");
		System.out.println("2- Nom de fournisseur");
		String decision = scanner.nextLine();
		switch (decision){
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


	public void menuRechercheInterets(Scanner scanner, String nomFournisseur){
		System.out.println("Voulez vous appliquer un filtre? (oui/non)");
		String decision = scanner.nextLine();
		switch (decision){
			case "oui" -> {
				System.out.println("Par quel filtre voulez vous filtrer?");
				System.out.println("1- Filtrer par trois premieres lettres");
				System.out.println("2- Filtrer par pseudo utilisateur");
				System.out.println("3- Filtrer par pseudo utilisateur et trois premieres lettres d'interets");
				String decisionFiltre = scanner.nextLine();
				switch(decisionFiltre.toUpperCase()){
					case "1" -> {
						System.out.println("Entrez vos 3 characteres");
						String troislettre = scanner.nextLine();
						String res = dbControlleur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre);
						if (res.length() == 0){
							System.out.println("Il n'y a aucun interet");
						} else {
							System.out.println(res);
						}
					}
					case "2" -> {
						System.out.println("Entrez le pseudo de l'utilisateur");
						String pseudo = scanner.nextLine();
						String res = dbControlleur.recupererListeInteretUtilisateur(pseudo);
						if (res.length() == 0){
							System.out.println("Il n'y a aucun interet");
						} else {
							System.out.println(res);
						}

					}
					case "3" -> {
						System.out.println("Entrez le pseudo de l'utilisateur: ");
						String pseudo = scanner.nextLine();
						System.out.println("Entrez les 3 characteres de l'interet");
						String troislettre = scanner.nextLine();
						String res = dbControlleur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(pseudo, troislettre);
						if (res.length() == 0){
							System.out.println("Il n'y a aucun interet");
						} else {
							System.out.println(res);
						}
					}
				}
			}
			case "non" -> {
				String res = dbControlleur.recupererListeInteret();
				if (res.length() == 0){
					System.out.println("Il n'y a aucun interet");
				} else {
					System.out.println(res);
				}
			}
		}
	}

	public void menuChercherFournisseur(Scanner scanner, String nomFournisseur){
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

	public void menuChercherUtilisateur(Scanner scanner){
		System.out.println("Filtrer par:");
		System.out.println("1- Pseudo");
		System.out.println("2- Nom");
		System.out.println("3- Prenom");
		System.out.println("4- Obtenir liste des suiveurs de? (pseudo voulu)");
		String decision = scanner.nextLine();
		switch (decision){
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

	private boolean demander(String reponse, Scanner scanner){
		boolean continuer = false;
		boolean choix = false;
		do {
			if (reponse.toLowerCase().trim().equals("oui")) {
				choix = true;
			} else if (!reponse.toLowerCase().trim().equals("non")) {
				System.out.println("Choisissez entre 'oui' et 'non' svp.");
				reponse = scanner.nextLine();
				continuer = true;
			} else if(reponse.toLowerCase().trim().equals("non")){
				continuer = false;
			}
		} while (continuer);
		return choix;
	}

	/* Gestion de composantes */
	public void menuGererComposantes(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.println(" ");
		System.out.println("Choisissez une option :");
		System.out.println("1- Supprimer une composante");
		System.out.println("2- Modifier le prix d'une composante");
		System.out.println("3- Modifier la description d'une composante");
		System.out.println("4- Revenir au menu fournisseur");
		System.out.println(">>Votre choix :");
		String choix = scanner.nextLine();
		switch (choix) {
			case "1" -> menuSupprimerComposante(scanner, nomFournisseur);
			case "2" -> menuModifierPrixComposante(scanner, nomFournisseur);
			case "3" -> menuModifierDescriptionComposante(scanner, nomFournisseur);
			case "4" -> menuFournisseur(scanner, nomFournisseur);
		}
	}

	public void menuSupprimerComposante(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.print("Nom de la composante à supprimer : ");
		String composante = scanner.nextLine();
		if(controlleurFournisseurs.retirerComposante(composante)){
			System.out.println("La composante a été retirée avec succès !");
		} else {
			System.out.println("Vous ne possédez cette composante.");
		}
		System.out.println(" ");
		menuGererComposantes(scanner, nomFournisseur);
	}

	public void menuModifierPrixComposante(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.print("Nom de la composante à modifier :");
		String composante = scanner.nextLine();
		System.out.print("Nouveau prix :");
		String nouveauPrix = scanner.nextLine();
		System.out.println("Modification en cours...");
		if (controlleurFournisseurs.modifierPrixComposante(composante, nouveauPrix)){
			System.out.println("Modification réussite !");
			System.out.println("Vous devez vous reconnecter pour voir les modifications.");
		} else {
			System.out.println("Échec de la modification. Vérifiez que vous possédez bien cette composante.");
		}
		menuGererComposantes(scanner, nomFournisseur);
	}

	public void menuModifierDescriptionComposante(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.print("Nom de la composante à modifier :");
		String composante = scanner.nextLine();
		System.out.print("Nouvelle description :");
		String nouvelleDescription = scanner.nextLine();
		System.out.println("Modification en cours...");
		if (controlleurFournisseurs.modifierDescriptionComposante(composante, nouvelleDescription)){
			System.out.println("Modification réussite !");
			System.out.println("Vous devez vous reconnecter pour voir les modifications.");
		} else {
			System.out.println("Échec de la modification. Vérifiez que vous possédez bien cette composante.");
		}

		System.out.println(" ");
		menuGererComposantes(scanner, nomFournisseur);
	}

	public ArrayList<String> menuEnregistrerComposante(Scanner scanner) throws ParseException {
		ArrayList<String> paramettreCoposants = new ArrayList<>();
		System.out.print("Nom de la composante : ");
		String composante = scanner.nextLine();
		System.out.print("Prix : ");
		String prix = scanner.nextLine();
		System.out.print("Description : ");
		String description = scanner.nextLine();
		System.out.print("Type de la composante : ");
		String type = scanner.nextLine();
		System.out.println(" ");
		System.out.println("La composante a été rajoutée avec succès");
		System.out.println(" ");
		paramettreCoposants.add(composante);
		paramettreCoposants.add(prix);
		paramettreCoposants.add(description);
		paramettreCoposants.add(type);
		return paramettreCoposants;
	}

	/* Modification du profile */
	public void menuMotifierProfileFournisseur(Scanner scanner, String nomFournisseur) throws ParseException, IOException {
		System.out.println("Que voulez-vous modifier");
		System.out.println("1- Nom");
		System.out.println("2- Adresse");
		System.out.println("3- Email");
		System.out.println("4- Numero de telephone");
		System.out.println("5- Nom de la compagnie");
		System.out.println("6- Capacitée de production");
		System.out.println("7- Mot de passe");
		System.out.println("8- Retour au menu Fournisseur");
		String choix = scanner.nextLine();
		switch (choix) {
			case "1" -> {
				System.out.println("Entrez votre nouveau nom: ");
				String nom = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("nom", nom);
				System.out.println("Modification terminée avec succès !");
			}
			case "2" -> {
				System.out.println("Entrez votre nouveau adresse: ");
				String adresse = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("adresse", adresse);
				System.out.println("Modification terminée avec succès !");
			}
			case "3" -> {
				System.out.println("Entrez votre nouvelle Email: ");
				String email = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("email", email);
				System.out.println("Modification terminée avec succès !");
			}
			case "4" -> {
				System.out.println("Entrez votre nouveau numero de telephone : ");
				String numTele = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("numerotelephone", numTele);
				System.out.println("Modification terminée avec succès !");
			}
			case "5" -> {
				System.out.println("Entrez votre nouvelle compagnie : ");
				String compagnie = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("nomcompagnie", compagnie);
				System.out.println("Modification terminée avec succès !");
			}
			case "6" -> {
				System.out.println("Entrez votre nouvelle capacite de production : ");
				String capacite = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("capaciteProduction", capacite);
				System.out.println("Modification terminée avec succès !");
			}
			case "7" -> {
				System.out.print("Entrez votre nouveau mot de passe : ");
				String mdp = scanner.nextLine();
				System.out.println("Modification en cours...");
				controlleurFournisseurs.modifierProfile("mdp", mdp);
				System.out.println("Modification terminée avec succès !");
			}
			case "8" -> menuFournisseur(scanner, nomFournisseur);
		}
		System.out.println(" ");
		menuFournisseur(scanner, nomFournisseur);
	}
}
