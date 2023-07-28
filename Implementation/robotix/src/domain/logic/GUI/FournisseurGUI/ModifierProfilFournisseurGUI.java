package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProfilFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel modifierNomPanel = new JPanel(new GridBagLayout());
    private JPanel modifierAdressePanel = new JPanel(new GridBagLayout());
    private JPanel modifierEmailPanel = new JPanel(new GridBagLayout());
    private JPanel modifierTelephonePanel = new JPanel(new GridBagLayout());
    private JPanel modifierCompagniePanel = new JPanel(new GridBagLayout());
    private JPanel modifierCapaciteProductionPanel = new JPanel(new GridBagLayout());
    private JPanel modifierMdpPanel = new JPanel(new GridBagLayout());
    private JLabel profilFournisseurLabel = new JLabel("Que voulez-vous modifier?", SwingConstants.CENTER);
    private JLabel nomLabel = new JLabel("Entrez votre nouveau nom");
    private JLabel adresseLabel = new JLabel("Entrez votre nouvelle adresse");
    private JLabel emailLabel = new JLabel("Entrez votre nouveau email");
    private JLabel telephoneLabel = new JLabel("Entrez votre nouveau numero de telephone");
    private JLabel compagnieLabel = new JLabel("Entrez votre nouvelle compagnie");
    private JLabel capaciteProductionLabel = new JLabel("Entrez votre nouvelle capacite de production");
    private JLabel mdpLabel = new JLabel("Entrez votre nouveau mot de passe");
    private JTextField nouveauNomField = new JTextField();
    private JTextField nouvelleAdresseField = new JTextField();
    private JTextField nouveauEmailField = new JTextField();
    private JTextField nouveauTelephoneField = new JTextField();
    private JTextField nouvelleCompagnieField = new JTextField();
    private JTextField nouvelleCapaciteProductionField = new JTextField();
    private JPasswordField nouveauMdpField = new JPasswordField();
    private JButton btnNom = new JButton("Nom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnCapaciteProduction = new JButton("Capacite de production");
    private JButton btnMdp = new JButton("Mot de passe");
    private JButton btnRetour = new JButton("Retour au menu fournisseur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public ModifierProfilFournisseurGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setModifierNomPanel();
        setModifierAdressePanel();
        setModifierEmailPanel();
        setModifierTelephonePanel();
        setModifierCompagniePanel();
        setModifierCapaciteProductionPanel();
        setModifierMdpPanel();

        btnNom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierNomPanel);
                mettreAJourFrame();
            }
        });
        btnAdresse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierAdressePanel);
                mettreAJourFrame();
            }
        });
        btnEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierEmailPanel);
                mettreAJourFrame();
            }
        });
        btnNumeroTelephone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierTelephonePanel);
                mettreAJourFrame();
            }
        });
        btnNomCompagnie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierCompagniePanel);
                mettreAJourFrame();
            }
        });
        btnCapaciteProduction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierCapaciteProductionPanel);
                mettreAJourFrame();
            }
        });
        btnMdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierMdpPanel);
                mettreAJourFrame();
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent);
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        profilFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(profilFournisseurLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAdresse);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnEmail);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCapaciteProduction);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnMdp);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setModifierNomPanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauNomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierNomPanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        modifierNomPanel.add(nouveauNomField, constraints);
        constraints.gridy = 2;
        modifierNomPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierNomPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauNomField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierAdressePanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleAdresseField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierAdressePanel.add(adresseLabel, constraints);
        constraints.gridy = 1;
        modifierAdressePanel.add(nouvelleAdresseField, constraints);
        constraints.gridy = 2;
        modifierAdressePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierAdressePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleAdresseField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierEmailPanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauEmailField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierEmailPanel.add(emailLabel, constraints);
        constraints.gridy = 1;
        modifierEmailPanel.add(nouveauEmailField, constraints);
        constraints.gridy = 2;
        modifierEmailPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierEmailPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauEmailField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierTelephonePanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauTelephoneField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierTelephonePanel.add(telephoneLabel, constraints);
        constraints.gridy = 1;
        modifierTelephonePanel.add(nouveauTelephoneField, constraints);
        constraints.gridy = 2;
        modifierTelephonePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierTelephonePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauTelephoneField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierCompagniePanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleCompagnieField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierCompagniePanel.add(compagnieLabel, constraints);
        constraints.gridy = 1;
        modifierCompagniePanel.add(nouvelleCompagnieField, constraints);
        constraints.gridy = 2;
        modifierCompagniePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierCompagniePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleCompagnieField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierCapaciteProductionPanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleCapaciteProductionField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierCapaciteProductionPanel.add(capaciteProductionLabel, constraints);
        constraints.gridy = 1;
        modifierCapaciteProductionPanel.add(nouvelleCapaciteProductionField, constraints);
        constraints.gridy = 2;
        modifierCapaciteProductionPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierCapaciteProductionPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleCapaciteProductionField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierMdpPanel() {
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauMdpField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierMdpPanel.add(mdpLabel, constraints);
        constraints.gridy = 1;
        modifierMdpPanel.add(nouveauMdpField, constraints);
        constraints.gridy = 2;
        modifierMdpPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierMdpPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauMdpField);
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
    public void onBtnEnregistrerClicked(JButton btnEnregistrer, JTextField response) {
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
