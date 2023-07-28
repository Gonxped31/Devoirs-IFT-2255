package domain.logic.GUI.FournisseurGUI;

import domain.logic.Menu.MenusFournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InscriptionFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel inscriptionFournisseurPanel = new JPanel();
    private JLabel inscriptionFournisseurLabel = new JLabel("Inscription en tant que nouveau fournisseur", SwingConstants.CENTER);
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel confirmerMdpLabel = new JLabel("Confirmer le mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel typeRobotFabriquesLabel = new JLabel("Type de robots fabriques");
    private JLabel typeComposantesFabriquesLabel = new JLabel("Type de composantes fabriques");
    private JLabel capaciteFabricationLabel = new JLabel("Capacite de fabrication");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JTextField nomField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JPasswordField confirmerMdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField typeRobotFabriquesField = new JTextField();
    private JTextField typeComposantesFabriquesField = new JTextField();
    private JTextField capaciteFabricationField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private Container panelPrecedent = new Container();

    public InscriptionFournisseurGUI()  {
        inscriptionFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inscriptionFournisseurPanel.setLayout(new GridLayout(0, 1));
        inscriptionFournisseurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        inscriptionFournisseurPanel.add(inscriptionFournisseurLabel);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(nomLabel);
        inscriptionFournisseurPanel.add(nomField);
        inscriptionFournisseurPanel.add(courrielLabel);
        inscriptionFournisseurPanel.add(courrielField);
        inscriptionFournisseurPanel.add(mdpLabel);
        inscriptionFournisseurPanel.add(mdpField);
        inscriptionFournisseurPanel.add(confirmerMdpLabel);
        inscriptionFournisseurPanel.add(confirmerMdpField);
        inscriptionFournisseurPanel.add(telephoneLabel);
        inscriptionFournisseurPanel.add(telephoneField);
        inscriptionFournisseurPanel.add(adresseLabel);
        inscriptionFournisseurPanel.add(adresseField);
        inscriptionFournisseurPanel.add(typeRobotFabriquesLabel);
        inscriptionFournisseurPanel.add(typeRobotFabriquesField);
        inscriptionFournisseurPanel.add(typeComposantesFabriquesLabel);
        inscriptionFournisseurPanel.add(typeComposantesFabriquesField);
        inscriptionFournisseurPanel.add(capaciteFabricationLabel);
        inscriptionFournisseurPanel.add(capaciteFabricationField);
        inscriptionFournisseurPanel.add(nomCompagnieLabel);
        inscriptionFournisseurPanel.add(nomCompagnieField);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(btnConfirmerInscription);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(btnRetour);

        btnConfirmerInscription.addActionListener(new ActionListener() {
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
        this.jFrame.setContentPane(inscriptionFournisseurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
