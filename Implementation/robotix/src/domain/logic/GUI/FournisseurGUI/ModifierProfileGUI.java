package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class ModifierProfileGUI {
    private JPanel modifierProfilePanel = new JPanel();
    private JLabel modifierProfileLabel = new JLabel("Que voulez-vous modifier?");
    private JButton btnNom = new JButton("Nom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnCapaciteProduction = new JButton("Capacite de production");
    private JButton btnMdp = new JButton("Mot de passe");

    public ModifierProfileGUI() {
        modifierProfilePanel.add(modifierProfileLabel);
        modifierProfilePanel.add(btnNom);
        modifierProfilePanel.add(btnAdresse);
        modifierProfilePanel.add(btnEmail);
        modifierProfilePanel.add(btnNumeroTelephone);
        modifierProfilePanel.add(btnNomCompagnie);
        modifierProfilePanel.add(btnCapaciteProduction);
        modifierProfilePanel.add(btnMdp);
    }

    public JPanel getPanel() {
        return modifierProfilePanel;
    }
}
