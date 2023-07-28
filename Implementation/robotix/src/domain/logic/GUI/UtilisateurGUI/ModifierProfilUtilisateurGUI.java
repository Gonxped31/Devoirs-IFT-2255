package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProfilUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel modifierNomPanel = new JPanel(new GridBagLayout());
    private JPanel modifierPrenomPanel = new JPanel(new GridBagLayout());
    private JPanel modifierAdressePanel = new JPanel(new GridBagLayout());
    private JPanel modifierPseudoPanel = new JPanel(new GridBagLayout());
    private JPanel modifierEmailPanel = new JPanel(new GridBagLayout());
    private JPanel modifierTelephonePanel = new JPanel(new GridBagLayout());
    private JPanel modifierCompagniePanel = new JPanel(new GridBagLayout());
    private JPanel modifierMdpPanel = new JPanel(new GridBagLayout());
    private JLabel profilUtilisateurLabel = new JLabel("Que voulez-vous modifier?", SwingConstants.CENTER);
    private JButton btnNom = new JButton("Nom");
    private JButton btnPrenom = new JButton("Prenom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnPseudo = new JButton("Pseudo");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnMdp = new JButton("Mot de passe");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public ModifierProfilUtilisateurGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setModifierNomPanel();
        setModifierPrenomPanel();
        setModifierAdressePanel();
        setModifierPseudoPanel();
        setModifierEmailPanel();
        setModifierTelephonePanel();
        setModifierCompagniePanel();
        setModifierMdpPanel();

        btnNom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierNomPanel);
                mettreAJourFrame();
            }
        });
        btnPrenom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPrenomPanel);
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
        btnPseudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPseudoPanel);
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
        profilUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(profilUtilisateurLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnPrenom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAdresse);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnPseudo);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnEmail);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnMdp);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setModifierNomPanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomLabel = new JLabel("Entrez votre nouveau nom");
        JTextField nouveauNomField = new JTextField();
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

    public void setModifierPrenomPanel() {
        JLabel prenomLabel = new JLabel("Entrez votre nouveau prenom");
        JTextField nouveauPrenomField = new JTextField();
        JButton btnEnregistrerPrenom = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauPrenomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPrenomPanel.add(prenomLabel, constraints);
        constraints.gridy = 1;
        modifierPrenomPanel.add(nouveauPrenomField, constraints);
        constraints.gridy = 2;
        modifierPrenomPanel.add(btnEnregistrerPrenom, constraints);
        constraints.gridy = 3;
        modifierPrenomPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrerPrenom, nouveauPrenomField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierAdressePanel() {
        JLabel adresseLabel = new JLabel("Entrez votre nouvelle adresse");
        JTextField nouvelleAdresseField = new JTextField();
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

    public void setModifierPseudoPanel() {
        JLabel pseudoLabel = new JLabel("Entrez votre nouveau pseudo");
        JTextField nouveauPseudoField = new JTextField();
        JButton btnEnregistrerPseudo = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauPseudoField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        modifierPseudoPanel.add(nouveauPseudoField, constraints);
        constraints.gridy = 2;
        modifierPseudoPanel.add(btnEnregistrerPseudo, constraints);
        constraints.gridy = 3;
        modifierPseudoPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrerPseudo, nouveauPseudoField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setModifierEmailPanel() {
        JLabel emailLabel = new JLabel("Entrez votre nouveau email");
        JTextField nouveauEmailField = new JTextField();
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
        JLabel telephoneLabel = new JLabel("Entrez votre nouveau numero de telephone");
        JTextField nouveauTelephoneField = new JTextField();
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
        JLabel compagnieLabel = new JLabel("Entrez votre nouvelle compagnie");
        JTextField nouvelleCompagnieField = new JTextField();
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

    public void setModifierMdpPanel() {
        JLabel mdpLabel = new JLabel("Entrez votre nouveau mot de passe");
        JPasswordField nouveauMdpField = new JPasswordField();
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
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
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
                if (response.getText().length() == 0)
                    afficherMessageErreur();
                else
                    afficherMessageConfirmation();
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

    public void afficherMessageConfirmation() {
        String message = "Votre ___ a ete change avec succes!";
        String title = "Modification terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreur() {
        String message = "Une erreur s'est produite ! Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
