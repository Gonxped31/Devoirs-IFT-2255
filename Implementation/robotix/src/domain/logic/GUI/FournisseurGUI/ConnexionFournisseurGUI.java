package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
/**
 * Cette classe gère l'interface graphique de connexion pour les fournisseurs.
 */
public class ConnexionFournisseurGUI {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel formulairePanel = new JPanel(new GridBagLayout());
    private JLabel connexionFournisseurLabel = new JLabel("Connexion en tant que fournisseur", SwingConstants.CENTER);
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField nomField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Constructeur de la classe ConnexionFournisseurGUI.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ParseException Si une erreur de parsing se produit.
     */
    public ConnexionFournisseurGUI() throws IOException, ParseException {
        // Setup de quelques composantes
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        connexionFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        connexionFournisseurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomField.setPreferredSize(new Dimension(200, 30));
        mdpField.setPreferredSize(new Dimension(200, 30));

        // Mise en page du formulaire
        formulairePanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        formulairePanel.add(nomField, constraints);
        constraints.gridy = 2;
        formulairePanel.add(mdpLabel, constraints);
        constraints.gridy = 3;
        formulairePanel.add(mdpField, constraints);
        constraints.gridy = 4;
        formulairePanel.add(btnSeConnecter, constraints);
        constraints.gridy = 5;
        formulairePanel.add(btnRetour, constraints);

        // Ajout des composantes
        mainPanel.add(connexionFournisseurLabel, BorderLayout.NORTH);
        mainPanel.add(formulairePanel, BorderLayout.CENTER);

        btnSeConnecter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                    String nom = nomField.getText();
                    String mdp = new String(mdpField.getPassword());
                    if (controlleurFournisseurs.authentificationFournisseur(nom, mdp)){
                        menusFournisseur = new MenusFournisseur(nom);
                        menusFournisseur.afficherMenuFournisseur(jFrame);
                    } else {
                        String message = "Le fournisseur \"" + nom + "\" n'existe pas ou le mot de passe est incorrecte.";
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
     * Affiche le panel de connexion fournisseur sur une fenêtre JFrame.
     * @param jFrame La fenêtre JFrame sur laquelle afficher le panel.
     */
    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
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
