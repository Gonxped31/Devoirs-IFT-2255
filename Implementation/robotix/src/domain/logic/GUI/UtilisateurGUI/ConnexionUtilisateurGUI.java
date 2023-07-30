package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Menu.MenuUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnexionUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel formulairePanel = new JPanel(new GridBagLayout());
    private JLabel connexionUtilisateurLabel = new JLabel("Connexion en tant qu'utilisateur", SwingConstants.CENTER);
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField pseudoField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenuUtilisateur menuUtilisateur;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints();

    public ConnexionUtilisateurGUI() {
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
                    menuUtilisateur = new MenuUtilisateur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menuUtilisateur.afficherMenuUtilisateurPanel(jFrame);
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

    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
