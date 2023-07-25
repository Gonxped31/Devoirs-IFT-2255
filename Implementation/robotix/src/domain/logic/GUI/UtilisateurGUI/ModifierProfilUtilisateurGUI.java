package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProfilUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel modifierProfilUtilisateurPanel = new JPanel();
    private JPanel modifierNomPanel = new JPanel();
    private JPanel modifierPrenomPanel = new JPanel();
    private JPanel modifierAdressePanel = new JPanel();
    private JPanel modifierPseudoPanel = new JPanel();
    private JPanel modifierEmailPanel = new JPanel();
    private JPanel modifierTelephonePanel = new JPanel();
    private JPanel modifierCompagniePanel = new JPanel();
    private JPanel modifierMdpPanel = new JPanel();
    private JLabel modifierProfilUtilisateurLabel = new JLabel("Que voulez-vous modifier?");
    private JLabel modifierNomLabel = new JLabel("Entrez votre nouveau nom");
    private JLabel modifierPrenomLabel = new JLabel("Entrez votre nouveau prenom");
    private JLabel modifierAdresseLabel = new JLabel("Entrez votre nouvelle adresse");
    private JLabel modifierPseudoLabel = new JLabel("Entrez votre nouveau pseudo");
    private JLabel modifierEmailLabel = new JLabel("Entrez votre nouveau email");
    private JLabel modifierTelephoneLabel = new JLabel("Entrez votre nouveau numero de telephone");
    private JLabel modifierCompagnieLabel = new JLabel("Entrez votre nouvelle compagnie");
    private JLabel modifierMdpLabel = new JLabel("Entrez votre nouveau mot de passe");
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

    public ModifierProfilUtilisateurGUI() {
        modifierProfilUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierProfilUtilisateurPanel.add(modifierProfilUtilisateurLabel);
        modifierProfilUtilisateurPanel.add(btnNom);
        modifierProfilUtilisateurPanel.add(btnPrenom);
        modifierProfilUtilisateurPanel.add(btnAdresse);
        modifierProfilUtilisateurPanel.add(btnPseudo);
        modifierProfilUtilisateurPanel.add(btnEmail);
        modifierProfilUtilisateurPanel.add(btnNumeroTelephone);
        modifierProfilUtilisateurPanel.add(btnNomCompagnie);
        modifierProfilUtilisateurPanel.add(btnMdp);

        modifierNomPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierNomPanel.add(modifierNomLabel);
        modifierNomPanel.add(nouveauNomField);

        modifierPrenomPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPrenomPanel.add(modifierPrenomLabel);
        modifierPrenomPanel.add(nouveauPrenomField);

        modifierAdressePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierAdressePanel.add(modifierAdresseLabel);
        modifierAdressePanel.add(nouvelleAdresseField);

        modifierPseudoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPseudoPanel.add(modifierPseudoLabel);
        modifierPseudoPanel.add(nouveauPseudoField);

        modifierEmailPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierEmailPanel.add(modifierEmailLabel);
        modifierEmailPanel.add(nouveauEmailField);

        modifierTelephonePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierTelephonePanel.add(modifierTelephoneLabel);
        modifierTelephonePanel.add(nouveauTelephoneField);

        modifierCompagniePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierCompagniePanel.add(modifierCompagnieLabel);
        modifierCompagniePanel.add(nouvelleCompagnieField);

        modifierMdpPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierMdpPanel.add(modifierMdpLabel);
        modifierMdpPanel.add(nouveauMdpField);

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
    }

    public void changerContenu(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(modifierProfilUtilisateurPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
