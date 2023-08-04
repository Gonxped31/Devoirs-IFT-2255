package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * La classe RetirerRobotGUI représente une interface graphique (GUI) pour retirer des robots de l'inventaire d'un fournisseur.
 * Ce GUI permet à l'utilisateur d'entrer le numéro de série du robot qu'il souhaite retirer et effectue les actions nécessaires
 * en utilisant la classe de contrôleur ControlleurFournisseurs.
 */
public class RetirerRobotGUI {
    /**
     * Le contrôleur pour gérer les fournisseurs et leurs robots.
     */
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    /**
     * Le nom du fournisseur associé à cette GUI.
     */
    private String nomFournisseur;
    /**
     * Le JFrame principal utilisé pour afficher cette GUI.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Le JPanel qui contient les composants pour retirer un robot.
     */
    private JPanel retirerRobotPanel = new JPanel(new GridBagLayout());
    /**
     * Le conteneur pour stocker le panel précédent lors du retour au menu du fournisseur.
     */
    private Container panelPrecedent = new Container();
    /**
     * Les contraintes utilisées pour agencer les composants au sein du panel.
     */
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Crée une nouvelle instance de RetirerRobotGUI pour le nom du fournisseur donné.
     *
     * @param nomFournisseur Le nom du fournisseur.
     * @throws IOException    Si une erreur d'entrée/sortie se produit pendant le traitement.
     * @throws ParseException Si une erreur de parsing se produit pendant le traitement.
     */
    public RetirerRobotGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Déclaration des composantes implementees dans le panel
        JLabel numeroRobotLabel = new JLabel("Entrez le numero de serie du robot à retirer");
        JTextField numeroRobotField = new JTextField();
        JButton btnRetirerRobot = new JButton("Retirer le robot");
        JButton btnRetour = new JButton("Retour au menu Fournisseur");

        // Setup la dimension du JTextField
        numeroRobotField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        retirerRobotPanel.add(numeroRobotLabel, constraints);
        constraints.gridy = 1;
        retirerRobotPanel.add(numeroRobotField, constraints);
        constraints.gridy = 2;
        retirerRobotPanel.add(btnRetirerRobot, constraints);
        constraints.gridy = 3;
        retirerRobotPanel.add(btnRetour, constraints);

        btnRetirerRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSerie = numeroRobotField.getText();
                if (!numeroSerie.isEmpty()){
                    if (controlleurFournisseurs.retirerRobot(numeroSerie, nomFournisseur)){
                        JOptionPane.showMessageDialog(null, "Robot retire avec succes",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne possedez pas ce robot",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un numeroDeSerie",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
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
     * Affiche le panel principal de cette GUI dans le JFrame fourni.
     *
     * @param jFrame Le JFrame pour afficher le panel principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(retirerRobotPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le JFrame en le revalidant et le repeignant après les modifications.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Affiche une boîte de dialogue pour informer l'utilisateur que le robot a été retiré avec succès.
     */
    public void afficherMessageValidation() {
        String message = "Le robot a ete retire avec succes";
        String title = "Operation reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur lorsque l'utilisateur essaie de retirer un robot qu'il ne possède pas.
     */
    public void afficherMessageErreur() {
        String message = "Vous ne possedez ce robot. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}