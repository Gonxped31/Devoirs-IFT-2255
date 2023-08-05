package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * Cette classe représente une interface graphique pour la connexion d'un utilisateur.
 * Elle permet de saisir un pseudo et un mot de passe pour l'authentification,
 * ainsi que de se connecter et afficher le menu utilisateur en cas de succès.
 */
public class ConnexionUtilisateurGUI {
    /**
     * Contrôleur des utilisateurs pour gérer les opérations liées aux utilisateurs.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Fenêtre JFrame pour l'interface utilisateur.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Panneau principal de l'interface utilisateur avec une disposition en BorderLayout.
     */
    private JPanel mainPanel = new JPanel(new BorderLayout());
    /**
     * Panneau contenant le formulaire de connexion avec une disposition en GridBagLayout.
     */
    private JPanel formulairePanel = new JPanel(new GridBagLayout());
    /**
     * Étiquette pour indiquer la connexion en tant qu'utilisateur.
     */
    private JLabel connexionUtilisateurLabel = new JLabel("Connexion en tant qu'utilisateur", SwingConstants.CENTER);
    /**
     * Étiquette pour le champ de saisie du pseudo.
     */
    private JLabel pseudoLabel = new JLabel("Pseudo");
    /**
     * Étiquette pour le champ de saisie du mot de passe.
     */
    private JLabel mdpLabel = new JLabel("Mot de passe");
    /**
     * Champ de texte pour saisir le pseudo.
     */
    private JTextField pseudoField = new JTextField();
    /**
     * Champ de mot de passe pour saisir le mot de passe.
     */
    private JPasswordField mdpField = new JPasswordField();
    /**
     * Bouton pour se connecter.
     */
    private JButton btnSeConnecter = new JButton("Se Connecter");
    /**
     * Bouton pour revenir en arrière.
     */
    private JButton btnRetour = new JButton("Retour");
    /**
     * Instance du menu utilisateur associée à l'interface.
     */
    private MenuUtilisateur menuUtilisateur;
    /**
     * Conteneur pour stocker le panneau précédent.
     */
    private Container panelPrecedent = new Container();
    /**
     * Contraintes pour la disposition des composants dans le formulaire.
     */
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Crée une interface graphique pour la connexion d'un utilisateur.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ParseException Si une erreur se produit lors de l'analyse.
     */
    public ConnexionUtilisateurGUI() throws IOException, ParseException {
        // Setup de quelques composantes
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        connexionUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        connexionUtilisateurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pseudoField.setPreferredSize(new Dimension(200, 30));
        mdpField.setPreferredSize(new Dimension(200, 30));

        // Mise en page du formulaire
        formulairePanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        formulairePanel.add(pseudoField, constraints);
        constraints.gridy = 2;
        formulairePanel.add(mdpLabel, constraints);
        constraints.gridy = 3;
        formulairePanel.add(mdpField, constraints);
        constraints.gridy = 4;
        formulairePanel.add(btnSeConnecter, constraints);
        constraints.gridy = 5;
        formulairePanel.add(btnRetour, constraints);

        // Ajout des composantes
        mainPanel.add(connexionUtilisateurLabel, BorderLayout.NORTH);
        mainPanel.add(formulairePanel, BorderLayout.CENTER);

        btnSeConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pseudo = pseudoField.getText();
                    String mdp = new String(mdpField.getPassword());
                    if (controlleurUtilisateurs.authentification(pseudo, mdp)){
                        menuUtilisateur = new MenuUtilisateur(pseudo);
                        menuUtilisateur.afficherMenuUtilisateurPanel(jFrame);
                    } else {
                        String message = "L'utilisateur \"" + pseudo + "\" n'existe pas ou le mot de passe est incorrecte.";
                        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ParseException ex) {
                    throw new RuntimeException(ex);
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
     * Affiche le panneau de connexion dans la fenêtre JFrame spécifiée.
     *
     * @param jFrame La fenêtre JFrame dans laquelle afficher le panneau de connexion.
     */
    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu de la fenêtre JFrame en révalidant et en repeignant la fenêtre.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
