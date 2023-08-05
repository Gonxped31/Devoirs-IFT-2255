package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

/**
 * Cette classe gère l'interface graphique pour ajouter un nouveau robot pour un fournisseur donné.
 */
public class AjouterRobotGUI {
    /**
     * Le contrôleur pour gérer les fournisseurs et leurs robots.
     */
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    /**
     * Le nom du fournisseur associé à ce GUI.
     */
    private String nomFournisseur;
    /**
     * Le JFrame principal utilisé pour afficher cette GUI.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Le JPanel qui contient les composants pour ajouter un robot.
     */
    private JPanel ajouterRobotPanel = new JPanel(new GridBagLayout());
    /**
     * Le conteneur pour stocker le panel précédent lors du retour au menu du fournisseur.
     */
    private Container panelPrecedent = new Container();
    /**
     * Les contraintes utilisées pour agencer les composants au sein du panel.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Constructeur de la classe AjouterRobotGUI.
     * @param nomFournisseur Le nom du fournisseur pour lequel ajouter le robot.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ParseException Si une erreur de parsing se produit.
     */
    public AjouterRobotGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        // Déclaration des composantes implementees dans le panel
        JLabel infosLabel = new JLabel("Entrez le type du robot a rajouter");
        JTextField infosField = new JTextField();
        JButton btnAjouter = new JButton("Ajouter ce nouveau robot");
        JButton btnRetour = new JButton("Retour au menu Fournisseur");

        // Setup la dimension du JTextField
        infosField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        ajouterRobotPanel.add(infosLabel, constraints);
        constraints.gridy = 1;
        ajouterRobotPanel.add(infosField, constraints);
        constraints.gridy = 2;
        ajouterRobotPanel.add(btnAjouter, constraints);
        constraints.gridy = 3;
        ajouterRobotPanel.add(btnRetour, constraints);

        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = infosField.getText();
                if (!type.isEmpty()){
                    UUID uuid = controlleurFournisseurs.ajouterRobot(type, nomFournisseur);
                    String message = "Le robot a ete bien ajoute.\nVoici le numero de serie du robot :\n" + uuid;
                    JOptionPane.showMessageDialog(null, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez enrter un type.", "Errur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });

    }


    /**
     * Affiche le panel d'ajout de robot sur une fenêtre JFrame.
     * @param jFrame La fenêtre JFrame sur laquelle afficher le panel.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(ajouterRobotPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour la fenêtre après avoir effectué des modifications sur les composants graphiques.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}