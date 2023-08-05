package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.DbControleur;
import domain.logic.GUI.FournisseurGUI.*;
import domain.logic.GUI.Menu;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente le menu pour un fournisseur connecté.
 * Il permet au fournisseur de choisir différentes actions à effectuer,
 * telles que l'ajout d'un nouveau robot, la gestion de ses composantes,
 * la modification de son profil, etc.
 */
public class MenusFournisseur extends JFrame {
	private String nomFournisseur;
	private domain.logic.GUI.Menu menu;
	private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
	private DbControleur dbControlleur = domain.logic.Controller.DbControleur.getDbControleur();


	private JFrame jFrame = new JFrame();
	private JPanel menuFournisseurPanel = new JPanel();
	private JLabel menuFournisseurLabel = new JLabel("Menu Fournisseur", SwingConstants.CENTER);
	private JButton btnAjouterRobot = new JButton("Ajouter un nouveau robot");
	private JButton btnRetirerRobot = new JButton("Retirer un robot");
	private JButton btnEnregistrerComposante = new JButton("Enregistrer une composante");
	private JButton btnGererComposante = new JButton("Gerer mes composantes");
	private JButton btnModifierProfil = new JButton("Modifier mon profil");
	private JButton btnRequetePublique = new JButton("Faire une requete publique");
	private JButton btnDeconnexion = new JButton("Deconnexion");
	private AjouterRobotGUI ajouterRobotGUI;
	private RetirerRobotGUI retirerRobotGUI;
	private EnregistrerComposanteGUI enregistrerComposanteGUI;
	private GererComposantesGUI gererComposantesGUI;
	private ModifierProfilFournisseurGUI modifierProfilFournisseurGUI;
	private RequetePubliqueFournisseurGUI requetePubliqueFournisseurGUI;

	/**
	 * Constructeur de la classe MenusFournisseur.
	 * Il configure les composants graphiques du menu et définit les action listeners pour les boutons.
	 *
	 * @param nomFournisseur Le nom du fournisseur connecté au menu.
	 * @throws IOException    En cas d'erreur lors de la lecture ou de l'écriture de fichiers.
	 * @throws ParseException En cas d'erreur lors de l'analyse de dates ou d'heures.
	 */
	public MenusFournisseur(String nomFournisseur) throws IOException, ParseException {
		this.nomFournisseur = nomFournisseur;
		instancierMenus(nomFournisseur);
		menuFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
		menuFournisseurPanel.setLayout(new GridLayout(0, 1));
		menuFournisseurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Ajout des composantes
		menuFournisseurPanel.add(menuFournisseurLabel);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnAjouterRobot);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnRetirerRobot);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnEnregistrerComposante);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnGererComposante);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnModifierProfil);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnRequetePublique);
		menuFournisseurPanel.add(Box.createHorizontalStrut(10));
		menuFournisseurPanel.add(btnDeconnexion);

		btnAjouterRobot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterRobotGUI.afficherMainPanel(jFrame);
			}
		});//Done
		btnRetirerRobot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retirerRobotGUI.afficherMainPanel(jFrame);
			}
		});//Done
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
		});//Done
		btnRequetePublique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requetePubliqueFournisseurGUI.afficherMainPanel(jFrame);
			}
		});//Done
		btnDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
				try {
					domain.logic.GUI.Menu menu = new Menu();
				} catch (IOException | ParseException ex) {
					throw new RuntimeException(ex);
				}
            }
		});
	}

	/**
	 * Instancie les différentes interfaces graphiques utilisées dans le menu fournisseur.
	 * Chaque interface est liée à une action spécifique du menu.
	 *
	 * @param nomFournisseur Le nom du fournisseur connecté au menu.
	 * @throws IOException    En cas d'erreur lors de la lecture ou de l'écriture de fichiers.
	 * @throws ParseException En cas d'erreur lors de l'analyse de dates ou d'heures.
	 */
	private void instancierMenus(String nomFournisseur) throws IOException, ParseException {
		requetePubliqueFournisseurGUI = new RequetePubliqueFournisseurGUI(nomFournisseur);
		modifierProfilFournisseurGUI = new ModifierProfilFournisseurGUI(nomFournisseur);
		ajouterRobotGUI = new AjouterRobotGUI(nomFournisseur);
		retirerRobotGUI = new RetirerRobotGUI(nomFournisseur);
		enregistrerComposanteGUI = new EnregistrerComposanteGUI(nomFournisseur);
		gererComposantesGUI = new GererComposantesGUI(nomFournisseur);
	}

	/**
	 * Affiche le menu fournisseur sur une fenêtre JFrame.
	 *
	 * @param jFrame La fenêtre JFrame où afficher le menu fournisseur.
	 */
	public void afficherMenuFournisseur(JFrame jFrame) {
		this.jFrame = jFrame;
		this.jFrame.setContentPane(menuFournisseurPanel);
		jFrame.revalidate();
		jFrame.repaint();
	}

}
