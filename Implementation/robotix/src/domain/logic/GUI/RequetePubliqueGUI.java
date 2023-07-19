package domain.logic.GUI;

import javax.swing.*;

public class RequetePubliqueGUI {
    private JPanel gestionRequetePubliquePanel = new JPanel();
    private JLabel gestionRequetePubliqueLabel = new JLabel("Veuillez faire une requete publique");
    private JButton btnVoirListeUtilisateurs = new JButton("Voir la liste d'utilisateurs");
    private JButton btnVoirListeFournisseurs = new JButton("Voir la liste des fournisseurs");
    private JButton btnVoirProfil = new JButton("Voir mon profil");
    private JButton btnChercherUtilisateur = new JButton("Chercher utilisateur par:  ");
    private JButton btnRecupererListeActivites = new JButton("Recuperer la liste des activites");
    private JButton btnRecupererListeInterets = new JButton("Recuperer la liste des interets");
    private JButton btnRechercheNomFournisseur = new JButton("Rechercher fournisseur par nom");
    private JButton btnRechercheNomComposante = new JButton("Rechercher une composante par nom");

    public RequetePubliqueGUI() {
        gestionRequetePubliquePanel.add(gestionRequetePubliqueLabel);
        gestionRequetePubliquePanel.add(btnVoirListeUtilisateurs);
        gestionRequetePubliquePanel.add(btnVoirListeFournisseurs);
        gestionRequetePubliquePanel.add(btnVoirProfil);
        gestionRequetePubliquePanel.add(btnChercherUtilisateur);
        gestionRequetePubliquePanel.add(btnRecupererListeActivites);
        gestionRequetePubliquePanel.add(btnRecupererListeInterets);
        gestionRequetePubliquePanel.add(btnRechercheNomFournisseur);
        gestionRequetePubliquePanel.add(btnVoirListeFournisseurs);
    }

    public JPanel getPanel() {
        return gestionRequetePubliquePanel;
    }
}
