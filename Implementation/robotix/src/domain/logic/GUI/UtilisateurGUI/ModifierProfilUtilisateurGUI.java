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
    private JLabel nomLabel = new JLabel("Entrez votre nouveau nom");
    private JLabel prenomLabel = new JLabel("Entrez votre nouveau prenom");
    private JLabel adresseLabel = new JLabel("Entrez votre nouvelle adresse");
    private JLabel pseudoLabel = new JLabel("Entrez votre nouveau pseudo");
    private JLabel emailLabel = new JLabel("Entrez votre nouveau email");
    private JLabel telephoneLabel = new JLabel("Entrez votre nouveau numero de telephone");
    private JLabel compagnieLabel = new JLabel("Entrez votre nouvelle compagnie");
    private JLabel mdpLabel = new JLabel("Entrez votre nouveau mot de passe");
    private JTextField nouveauNomField = new JTextField();
    private JTextField nouveauPrenomField = new JTextField();
    private JTextField nouvelleAdresseField = new JTextField();
    private JTextField nouveauPseudoField = new JTextField();
    private JTextField nouveauEmailField = new JTextField();
    private JTextField nouveauTelephoneField = new JTextField();
    private JTextField nouvelleCompagnieField = new JTextField();
    private JPasswordField nouveauMdpField = new JPasswordField();
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
        JButton btnEnregistrerNom = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerNom);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauNomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierNomPanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        modifierNomPanel.add(nouveauNomField, constraints);
        constraints.gridy = 2;
        modifierNomPanel.add(btnEnregistrerNom, constraints);
        constraints.gridy = 3;
        modifierNomPanel.add(btnAnnuler, constraints);
    }

    public void setModifierPrenomPanel() {
        JButton btnEnregistrerPrenom = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerPrenom);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauPrenomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPrenomPanel.add(prenomLabel, constraints);
        constraints.gridy = 1;
        modifierPrenomPanel.add(nouveauPrenomField, constraints);
        constraints.gridy = 2;
        modifierPrenomPanel.add(btnEnregistrerPrenom, constraints);
        constraints.gridy = 3;
        modifierPrenomPanel.add(btnAnnuler, constraints);
    }

    public void setModifierAdressePanel() {
        JButton btnEnregistrerAdresse = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerAdresse);
        onBtnAnnulerClicked(btnAnnuler);

        nouvelleAdresseField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierAdressePanel.add(adresseLabel, constraints);
        constraints.gridy = 1;
        modifierAdressePanel.add(nouvelleAdresseField, constraints);
        constraints.gridy = 2;
        modifierAdressePanel.add(btnEnregistrerAdresse, constraints);
        constraints.gridy = 3;
        modifierAdressePanel.add(btnAnnuler, constraints);
    }

    public void setModifierPseudoPanel() {
        JButton btnEnregistrerPseudo = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerPseudo);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauPseudoField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        modifierPseudoPanel.add(nouveauPseudoField, constraints);
        constraints.gridy = 2;
        modifierPseudoPanel.add(btnEnregistrerPseudo, constraints);
        constraints.gridy = 3;
        modifierPseudoPanel.add(btnAnnuler, constraints);
    }

    public void setModifierEmailPanel() {
        JButton btnEnregistrerEmail = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerEmail);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauEmailField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierEmailPanel.add(emailLabel, constraints);
        constraints.gridy = 1;
        modifierEmailPanel.add(nouveauEmailField, constraints);
        constraints.gridy = 2;
        modifierEmailPanel.add(btnEnregistrerEmail, constraints);
        constraints.gridy = 3;
        modifierEmailPanel.add(btnAnnuler, constraints);
    }

    public void setModifierTelephonePanel() {
        JButton btnEnregistrerTelephone = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerTelephone);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauTelephoneField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierTelephonePanel.add(telephoneLabel, constraints);
        constraints.gridy = 1;
        modifierTelephonePanel.add(nouveauTelephoneField, constraints);
        constraints.gridy = 2;
        modifierTelephonePanel.add(btnEnregistrerTelephone, constraints);
        constraints.gridy = 3;
        modifierTelephonePanel.add(btnAnnuler, constraints);
    }

    public void setModifierCompagniePanel() {
        JButton btnEnregistrerCompagnie = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerCompagnie);
        onBtnAnnulerClicked(btnAnnuler);

        nouvelleCompagnieField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierCompagniePanel.add(compagnieLabel, constraints);
        constraints.gridy = 1;
        modifierCompagniePanel.add(nouvelleCompagnieField, constraints);
        constraints.gridy = 2;
        modifierCompagniePanel.add(btnEnregistrerCompagnie, constraints);
        constraints.gridy = 3;
        modifierCompagniePanel.add(btnAnnuler, constraints);
    }

    public void setModifierMdpPanel() {
        JButton btnEnregistrerMdp = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");
        onBtnEnregistrerClicked(btnEnregistrerMdp);
        onBtnAnnulerClicked(btnAnnuler);

        nouveauMdpField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierMdpPanel.add(mdpLabel, constraints);
        constraints.gridy = 1;
        modifierMdpPanel.add(nouveauMdpField, constraints);
        constraints.gridy = 2;
        modifierMdpPanel.add(btnEnregistrerMdp, constraints);
        constraints.gridy = 3;
        modifierMdpPanel.add(btnAnnuler, constraints);
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

    public void onBtnEnregistrerClicked(JButton btnEnregistrer) {
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
