package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class ModifierProfilFournisseurGUI {
    private JPanel modifierProfilFournisseurPanel = new JPanel();
    private JLabel modifierProfilFournisseurLabel = new JLabel("Que voulez-vous modifier?");
    private JButton btnNom = new JButton("Nom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnCapaciteProduction = new JButton("Capacite de production");
    private JButton btnMdp = new JButton("Mot de passe");

    public ModifierProfilFournisseurGUI() {
        modifierProfilFournisseurPanel.add(modifierProfilFournisseurLabel);
        modifierProfilFournisseurPanel.add(btnNom);
        modifierProfilFournisseurPanel.add(btnAdresse);
        modifierProfilFournisseurPanel.add(btnEmail);
        modifierProfilFournisseurPanel.add(btnNumeroTelephone);
        modifierProfilFournisseurPanel.add(btnNomCompagnie);
        modifierProfilFournisseurPanel.add(btnCapaciteProduction);
        modifierProfilFournisseurPanel.add(btnMdp);
    }

    public JPanel getPanel() {
        return modifierProfilFournisseurPanel;
    }
}
