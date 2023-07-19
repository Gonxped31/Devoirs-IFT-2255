package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class RequetePubliqueFournisseurGUI {
    private JPanel requetePubliqueUtilisateurPanel = new JPanel();
    private JLabel requetePubliqueUtilisateurLabel = new JLabel("Veuillez faire une requete publique");
    private JButton btnVoirListeUtilisateurs = new JButton("Voir la liste d'utilisateurs");
    private JButton btnVoirListeFournisseurs = new JButton("Voir la liste des fournisseurs");
    private JButton btnVoirProfil = new JButton("Voir mon profil");
    private JButton btnChercherUtilisateur = new JButton("Chercher utilisateur par:  ");
    private JButton btnRecupererListeActivites = new JButton("Recuperer la liste des activites");
    private JButton btnRecupererListeInterets = new JButton("Recuperer la liste des interets");
    private JButton btnRechercheNomFournisseur = new JButton("Rechercher fournisseur");
    private JButton btnRechercheNomComposante = new JButton("Rechercher une composante");

    public RequetePubliqueFournisseurGUI() {
        requetePubliqueUtilisateurPanel.add(requetePubliqueUtilisateurLabel);
        requetePubliqueUtilisateurPanel.add(btnVoirListeUtilisateurs);
        requetePubliqueUtilisateurPanel.add(btnVoirListeFournisseurs);
        requetePubliqueUtilisateurPanel.add(btnVoirProfil);
        requetePubliqueUtilisateurPanel.add(btnChercherUtilisateur);
        requetePubliqueUtilisateurPanel.add(btnRecupererListeActivites);
        requetePubliqueUtilisateurPanel.add(btnRecupererListeInterets);
        requetePubliqueUtilisateurPanel.add(btnRechercheNomFournisseur);
        requetePubliqueUtilisateurPanel.add(btnVoirListeFournisseurs);
    }

    public JPanel getPanel() {
        return requetePubliqueUtilisateurPanel;
    }
}
