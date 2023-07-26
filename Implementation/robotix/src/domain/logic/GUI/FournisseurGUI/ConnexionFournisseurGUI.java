package domain.logic.GUI.FournisseurGUI;

import domain.logic.Menu.MenusFournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnexionFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel connexionFournisseurPanel = new JPanel();
    private JLabel connexionFournisseurLabel = new JLabel("Connexion en tant que fournisseur");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField nomField = new JTextField(10);
    private JPasswordField mdpField = new JPasswordField(10);
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private Container panelPrecedent = new Container();

    public ConnexionFournisseurGUI()  {
        connexionFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        connexionFournisseurPanel.setLayout(new BoxLayout(connexionFournisseurPanel, BoxLayout.Y_AXIS));
        connexionFournisseurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        nomField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nomField.getPreferredSize().height));
        mdpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, mdpField.getPreferredSize().height));

        // Ajout des composantes
        connexionFournisseurPanel.add(connexionFournisseurLabel);
        connexionFournisseurPanel.add(nomLabel);
        connexionFournisseurPanel.add(nomField);
        connexionFournisseurPanel.add(mdpLabel);
        connexionFournisseurPanel.add(mdpField);
        connexionFournisseurPanel.add(btnSeConnecter);
        connexionFournisseurPanel.add(btnRetour);

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
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
    }

    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.getContentPane().removeAll();
        this.jFrame.setContentPane(connexionFournisseurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
