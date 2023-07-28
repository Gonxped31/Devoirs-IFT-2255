package domain.logic.GUI.FournisseurGUI;

import domain.logic.Menu.MenusFournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnexionFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel formulairePanel = new JPanel(new GridBagLayout());
    private JLabel connexionFournisseurLabel = new JLabel("Connexion en tant que fournisseur", SwingConstants.CENTER);
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField nomField = new JTextField(10);
    private JPasswordField mdpField = new JPasswordField(10);
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints();

    public ConnexionFournisseurGUI()  {
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
                    menusFournisseur = new MenusFournisseur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menusFournisseur.afficherMenuFournisseur(jFrame);
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
