package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;

public class RequetePubliqueUtilisateurGUI {
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
    private JButton btnRechercheNomFournisseur = new JButton("Rechercher fournisseur par nom");
    private JButton btnRechercheNomComposante = new JButton("Rechercher une composante par nom");

    public RequetePubliqueUtilisateurGUI() {
        setMainPanel();
        setVoirListeUtilisateursPanel();
        setVoirListeFournisseursPanel();
        setVoirProfilPanel();
        setChercherUtilisateurPanel();
        setRecupererListeActivitesPanel();
        setRecupererListeInteretsPanel();
        setRechercheNomFournisseurPanel();
        setRechercheNomComposantePanel();
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
        mainPanel.add(btnRechercheNomComposante);
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

    public void changerContenu(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
