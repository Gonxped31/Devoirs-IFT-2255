package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnregistrerComposanteGUI {
    private JFrame jFrame = new JFrame();
    private JPanel enregistrerComposantePanel = new JPanel(new GridBagLayout());
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public EnregistrerComposanteGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Déclaration des composantes implementees dans le panel
        JLabel nomComposanteLabel = new JLabel("Nom de la composante");
        JLabel prixLabel = new JLabel("Prix");
        JLabel descriptionLabel = new JLabel("Description");
        JLabel typeComposanteLabel = new JLabel("Type de la composante");
        JTextField nomComposanteField = new JTextField();
        JTextField prixField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField typeComposanteField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer");
        JButton btnRetour = new JButton("Retour");

        // Setup la dimension des JTextField
        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        prixField.setPreferredSize(new Dimension(200, 30));
        descriptionField.setPreferredSize(new Dimension(200, 30));
        typeComposanteField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        enregistrerComposantePanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        enregistrerComposantePanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        enregistrerComposantePanel.add(prixLabel, constraints);
        constraints.gridy = 3;
        enregistrerComposantePanel.add(prixField, constraints);
        constraints.gridy = 4;
        enregistrerComposantePanel.add(descriptionLabel, constraints);
        constraints.gridy = 5;
        enregistrerComposantePanel.add(descriptionField, constraints);
        constraints.gridy = 6;
        enregistrerComposantePanel.add(typeComposanteLabel, constraints);
        constraints.gridy = 7;
        enregistrerComposantePanel.add(typeComposanteField, constraints);
        constraints.gridy = 8;
        enregistrerComposantePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 9;
        enregistrerComposantePanel.add(btnRetour, constraints);

        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().length() == 0 || prixField.getText().length() == 0 ||
                        descriptionField.getText().length() == 0 || typeComposanteField.getText().length() == 0 ) {
                    afficherMessageErreur();
                } else
                    afficherMessageValidation();
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

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(enregistrerComposantePanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    public void afficherMessageValidation() {
        String message = "La composante a ete rajoutée avec succes";
        String title = "Operation reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreur() {
        String message = "Veuillez bien remplir tous les champs necessaires afin d'enregistrer votre composante.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
