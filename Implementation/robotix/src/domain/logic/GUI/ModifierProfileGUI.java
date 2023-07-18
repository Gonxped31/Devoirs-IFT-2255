package domain.logic.GUI;

import javax.swing.*;

public class ModifierProfileGUI {
    private JPanel modifierProfilePanel = new JPanel();
    private JLabel modifierProfileLabel = new JLabel("Que voulez-vous modifier?");
    private JButton btnNom = new JButton("Nom");
    private JButton btnPrenom = new JButton("Prenom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnPseudo = new JButton("Pseudo");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnMdp = new JButton("Mot de passe");

    public ModifierProfileGUI() {
        modifierProfilePanel.add(modifierProfileLabel);
        modifierProfilePanel.add(btnNom);
        modifierProfilePanel.add(btnPrenom);
        modifierProfilePanel.add(btnAdresse);
        modifierProfilePanel.add(btnPseudo);
        modifierProfilePanel.add(btnEmail);
        modifierProfilePanel.add(btnNumeroTelephone);
        modifierProfilePanel.add(btnNomCompagnie);
        modifierProfilePanel.add(btnMdp);
    }

    public JPanel getModifierProfilePanel() {
        return modifierProfilePanel;
    }
}
