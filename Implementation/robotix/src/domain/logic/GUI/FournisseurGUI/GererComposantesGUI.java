package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererComposantesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel suppressionComposantePanel = new JPanel(new GridBagLayout());
    private JPanel modifierPrixPanel = new JPanel(new GridBagLayout());
    private JPanel modifierDescPanel = new JPanel(new GridBagLayout());
    private JLabel gererComposantesLabel = new JLabel("Choisissez une option", SwingConstants.CENTER);
    private JButton btnSupprimer = new JButton("Supprimer");
    private JButton btnModifierPrix = new JButton("Modifier le prix");
    private JButton btnModifierDesc = new JButton("Modifier la description");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GererComposantesGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Setup des panels
        setMainPanel();
        setSuppressionComposantePanel();
        setModificationPrixPanel();
        setModificationDescPanel();

        // ActionListener de tous les boutons de ce panel
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(suppressionComposantePanel);
                mettreAJourFrame();
            }
        });
        btnModifierPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPrixPanel);
                mettreAJourFrame();
            }
        });
        btnModifierDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierDescPanel);
                mettreAJourFrame();
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

    public void setMainPanel() {
        gererComposantesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gererComposantesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnSupprimer);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierPrix);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierDesc);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setSuppressionComposantePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a supprimer");
        JTextField nomComposanteField = new JTextField();
        JButton btnConfirmerSupprression = new JButton("Supprimer la composante");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension du JTextField
        nomComposanteField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        suppressionComposantePanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        suppressionComposantePanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        suppressionComposantePanel.add(btnConfirmerSupprression, constraints);
        constraints.gridy = 3;
        suppressionComposantePanel.add(btnAnnuler, constraints);

        // ActionListener des boutons
        onBtnSuppressionClicked(btnConfirmerSupprression, nomComposanteField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModificationPrixPanel() {
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a modifier");
        JLabel nouveauPrixLabel = new JLabel("Nouveau prix");
        JTextField nomComposanteField = new JTextField();
        JTextField nouveauPrixField = new JTextField();
        JButton btnModifierPrix = new JButton("Modifier le prix");
        JButton btnAnnuler = new JButton("Annuler");

        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        nouveauPrixField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        modifierPrixPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        modifierPrixPanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        modifierPrixPanel.add(nouveauPrixLabel, constraints);
        constraints.gridy = 3;
        modifierPrixPanel.add(nouveauPrixField, constraints);
        constraints.gridy = 4;
        modifierPrixPanel.add(btnModifierPrix, constraints);
        constraints.gridy = 5;
        modifierPrixPanel.add(btnAnnuler, constraints);

        onBtnModifierPrixClicked(btnModifierPrix, nomComposanteField, nouveauPrixField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModificationDescPanel() {
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a modifier");
        JLabel nouveauDescLabel = new JLabel("Nouvelle description");
        JTextField nomComposanteField = new JTextField();
        JTextField nouveauDescField = new JTextField();
        JButton btnModifierDesc = new JButton("Modifier la descriprion");
        JButton btnAnnuler = new JButton("Annuler");

        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        nouveauDescField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        modifierDescPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        modifierDescPanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        modifierDescPanel.add(nouveauDescLabel, constraints);
        constraints.gridy = 3;
        modifierDescPanel.add(nouveauDescField, constraints);
        constraints.gridy = 4;
        modifierDescPanel.add(btnModifierDesc, constraints);
        constraints.gridy = 5;
        modifierDescPanel.add(btnAnnuler, constraints);

        onBtnModifierDescClicked(btnModifierDesc, nomComposanteField, nouveauDescField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    public void onBtnSuppressionClicked(JButton btnConfirmerSupprression, JTextField nomComposanteField) {
        btnConfirmerSupprression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().length() == 0)
                    afficherMessageErreurSuppresion();
                else
                    confirmerSuppresion();
            }
        });
    }

    public void onBtnModifierPrixClicked(JButton btnModifierPrix, JTextField nomComposanteField, JTextField nouveauPrixField) {
        btnModifierPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().length() == 0 || nouveauPrixField.getText().length() == 0)
                    afficherMessageErreurModificationPrix();
                else
                    confirmerModificationPrix();
            }
        });
    }

    public void onBtnModifierDescClicked(JButton btnModifierDesc, JTextField nomComposanteField, JTextField nouveauDescField) {
        btnModifierDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().length() == 0 || nouveauDescField.getText().length() == 0)
                    afficherMessageErreurModificationDesc();
                else
                    confirmerModificationDesc();
            }
        });
    }

    public void confirmerSuppresion() {
        String message = "La composante a ete retiree avec succes! ";
        String title = "Suppression reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerModificationPrix() {
        String message = "Le prix de la composante a ete modifiee avec succes! ";
        String title = "Modification reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerModificationDesc() {
        String message = "La description de la composante a ete modifieee avec succes! ";
        String title = "Modification reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurSuppresion() {
        String message = "Vous ne possédez cette composante. Veuillez reessayer";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurModificationPrix() {
        String message = "Echec de la modification. Verifiez que vous possedez bien cette composante";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurModificationDesc() {
        String message = "Echec de la modification. Verifiez que vous possedez bien cette composante";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void onBtnAnnulerClicked(JButton btnAnnuler) {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }
}
