package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProfilUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel modifierNomPanel = new JPanel();
    private JPanel modifierPrenomPanel = new JPanel();
    private JPanel modifierAdressePanel = new JPanel();
    private JPanel modifierPseudoPanel = new JPanel();
    private JPanel modifierEmailPanel = new JPanel();
    private JPanel modifierTelephonePanel = new JPanel();
    private JPanel modifierCompagniePanel = new JPanel();
    private JPanel modifierMdpPanel = new JPanel();
    private JLabel profilUtilisateurLabel = new JLabel("Que voulez-vous modifier?");
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
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public ModifierProfilUtilisateurGUI() {
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
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(profilUtilisateurLabel);
        mainPanel.add(btnNom);
        mainPanel.add(btnPrenom);
        mainPanel.add(btnAdresse);
        mainPanel.add(btnPseudo);
        mainPanel.add(btnEmail);
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(btnMdp);
        mainPanel.add(btnRetour);
    }

    public void setModifierNomPanel() {
        modifierNomPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierNomPanel.add(nomLabel);
        modifierNomPanel.add(nouveauNomField);
    }

    public void setModifierPrenomPanel() {
        modifierPrenomPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPrenomPanel.add(prenomLabel);
        modifierPrenomPanel.add(nouveauPrenomField);
    }

    public void setModifierAdressePanel() {
        modifierAdressePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierAdressePanel.add(adresseLabel);
        modifierAdressePanel.add(nouvelleAdresseField);
    }

    public void setModifierPseudoPanel() {
        modifierPseudoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPseudoPanel.add(pseudoLabel);
        modifierPseudoPanel.add(nouveauPseudoField);
    }

    public void setModifierEmailPanel() {
        modifierEmailPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierEmailPanel.add(emailLabel);
        modifierEmailPanel.add(nouveauEmailField);
    }

    public void setModifierTelephonePanel() {
        modifierTelephonePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierTelephonePanel.add(telephoneLabel);
        modifierTelephonePanel.add(nouveauTelephoneField);
    }

    public void setModifierCompagniePanel() {
        modifierCompagniePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierCompagniePanel.add(compagnieLabel);
        modifierCompagniePanel.add(nouvelleCompagnieField);
    }

    public void setModifierMdpPanel() {
        modifierMdpPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierMdpPanel.add(mdpLabel);
        modifierMdpPanel.add(nouveauMdpField);
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
}
