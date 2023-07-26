package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequetePubliqueFournisseurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel voirListeUtilisateursPanel = new JPanel();
    private JPanel voirListeFournisseursPanel = new JPanel();
    private JPanel voirProfilPanel = new JPanel();
    private JPanel chercherUtilisateurPanel = new JPanel();
    private JPanel recupererListeActivitesPanel = new JPanel();
    private JPanel recupererListeInteretsPanel = new JPanel();
    private JPanel rechercheNomFournisseurPanel = new JPanel();
    private JPanel rechercheNomComposantePanel = new JPanel();
    private JLabel requetePubliqueUtilisateurLabel = new JLabel("Veuillez faire une requete publique");
    private JButton btnVoirListeUtilisateurs = new JButton("Voir la liste d'utilisateurs");
    private JButton btnVoirListeFournisseurs = new JButton("Voir la liste des fournisseurs");
    private JButton btnVoirProfil = new JButton("Voir mon profil");
    private JButton btnChercherUtilisateur = new JButton("Chercher utilisateur par:  ");
    private JButton btnRecupererListeActivites = new JButton("Recuperer la liste des activites");
    private JButton btnRecupererListeInterets = new JButton("Recuperer la liste des interets");
    private JButton btnRechercheNomFournisseur = new JButton("Rechercher fournisseur");
    private JButton btnRechercheNomComposante = new JButton("Rechercher une composante");
    private JButton btnRetour = new JButton();
    private Container panelPrecedent = new Container();
    private JComboBox pesudo = new JComboBox<>();
    private JComboBox filtrer = new JComboBox<>();
    private JComboBox nom = new JComboBox<>();
    private JComboBox type = new JComboBox<>();
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();

    public RequetePubliqueFournisseurGUI() {
        setMainPanel();
        setVoirListeUtilisateursPanel();
        setVoirListeFournisseursPanel();
        setVoirProfilPanel();
        setChercherUtilisateurPanel();
        setRecupererListeActivitesPanel();
        setRecupererListeInteretsPanel();
        setRechercheNomFournisseurPanel();
        setRechercheNomComposantePanel();

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
        mainPanel.add(requetePubliqueUtilisateurLabel);
        mainPanel.add(btnVoirListeUtilisateurs);
        mainPanel.add(btnVoirListeFournisseurs);
        mainPanel.add(btnVoirProfil);
        mainPanel.add(btnChercherUtilisateur);
        mainPanel.add(btnRecupererListeActivites);
        mainPanel.add(btnRecupererListeInterets);
        mainPanel.add(btnRechercheNomFournisseur);
        mainPanel.add(btnVoirListeFournisseurs);
        mainPanel.add(btnRetour);
    }

    public void setVoirListeUtilisateursPanel() {
        voirListeUtilisateursPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setVoirListeFournisseursPanel() {
        voirListeFournisseursPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setVoirProfilPanel() {
        voirProfilPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setChercherUtilisateurPanel() {
        chercherUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRecupererListeActivitesPanel() {
        recupererListeActivitesPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRecupererListeInteretsPanel() {
        recupererListeInteretsPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRechercheNomFournisseurPanel() {
        rechercheNomFournisseurPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRechercheNomComposantePanel() {
        rechercheNomComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
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
