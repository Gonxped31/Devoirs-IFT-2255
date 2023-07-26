package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProfilFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel modifierNomPanel = new JPanel();
    private JPanel modifierAdressePanel = new JPanel();
    private JPanel modifierEmailPanel = new JPanel();
    private JPanel modifierTelephonePanel = new JPanel();
    private JPanel modifierCompagniePanel = new JPanel();
    private JPanel modifierCapaciteProductionPanel = new JPanel();
    private JPanel modifierMdpPanel = new JPanel();
    private JLabel profilFournisseurLabel = new JLabel("Que voulez-vous modifier?");
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
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public ModifierProfilFournisseurGUI() {
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
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(profilFournisseurLabel);
        mainPanel.add(btnNom);
        mainPanel.add(btnAdresse);
        mainPanel.add(btnEmail);
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(btnCapaciteProduction);
        mainPanel.add(btnMdp);
        mainPanel.add(btnRetour);
    }

    public void setModifierNomPanel() {
        modifierNomPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierNomPanel.add(nomLabel);
        modifierNomPanel.add(nouveauNomField);
    }

    public void setModifierAdressePanel() {
        modifierAdressePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierAdressePanel.add(adresseLabel);
        modifierAdressePanel.add(nouvelleAdresseField);
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

    public void setModifierCapaciteProductionPanel() {
        modifierCapaciteProductionPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierCapaciteProductionPanel.add(capaciteProductionLabel);
        modifierCapaciteProductionPanel.add(nouvelleCapaciteProductionField);
    }

    public void setModifierMdpPanel() {
        modifierMdpPanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierMdpPanel.add(mdpLabel);
        modifierMdpPanel.add(nouveauMdpField);
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
}
