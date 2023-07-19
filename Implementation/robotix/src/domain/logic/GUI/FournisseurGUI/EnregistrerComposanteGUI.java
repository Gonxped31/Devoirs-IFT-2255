package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class EnregistrerComposanteGUI {
    private JPanel enregistrerComposantePanel = new JPanel();
    private JLabel enregistrerComposanteLabel = new JLabel("Enregistrer une composante");
    private JLabel nomComposanteLabel = new JLabel("Nom de la composante");
    private JLabel prixLabel = new JLabel("Prix");
    private JLabel descriptionLabel = new JLabel("Description");
    private JLabel typeComposanteLabel = new JLabel("Type de la composante");
    private JTextField nomComposanteField = new JTextField();
    private JTextField prixField = new JTextField();
    private JTextField descriptionField = new JTextField();
    private JTextField typeComposanteField = new JTextField();

    public EnregistrerComposanteGUI() {
        enregistrerComposantePanel.add(enregistrerComposanteLabel);
        enregistrerComposantePanel.add(nomComposanteLabel);
        enregistrerComposantePanel.add(nomComposanteField);
        enregistrerComposantePanel.add(prixLabel);
        enregistrerComposantePanel.add(prixField);
        enregistrerComposantePanel.add(descriptionLabel);
        enregistrerComposantePanel.add(descriptionField);
        enregistrerComposantePanel.add(typeComposanteLabel);
        enregistrerComposantePanel.add(typeComposanteField);
    }

    public JPanel getPanel() {
        return enregistrerComposantePanel;
    }
}
