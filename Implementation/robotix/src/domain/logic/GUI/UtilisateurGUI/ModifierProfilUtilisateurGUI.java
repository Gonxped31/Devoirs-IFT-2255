package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;

public class ModifierProfilUtilisateurGUI {
    private JPanel modifierProfilUtilisateurPanel = new JPanel();
    private JLabel modifierProfilUtilisateurLabel = new JLabel("Que voulez-vous modifier?");
    private JButton btnNom = new JButton("Nom");
    private JButton btnPrenom = new JButton("Prenom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnPseudo = new JButton("Pseudo");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnMdp = new JButton("Mot de passe");

    public ModifierProfilUtilisateurGUI() {
        modifierProfilUtilisateurPanel.add(modifierProfilUtilisateurLabel);
        modifierProfilUtilisateurPanel.add(btnNom);
        modifierProfilUtilisateurPanel.add(btnPrenom);
        modifierProfilUtilisateurPanel.add(btnAdresse);
        modifierProfilUtilisateurPanel.add(btnPseudo);
        modifierProfilUtilisateurPanel.add(btnEmail);
        modifierProfilUtilisateurPanel.add(btnNumeroTelephone);
        modifierProfilUtilisateurPanel.add(btnNomCompagnie);
        modifierProfilUtilisateurPanel.add(btnMdp);
    }

    public JPanel getPanel() {
        return modifierProfilUtilisateurPanel;
    }
}
