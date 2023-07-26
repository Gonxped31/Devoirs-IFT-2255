package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;

public class GererComposantesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel supprimerComposantePanel = new JPanel();
    private JPanel modifierPrixComposantePanel = new JPanel();
    private JPanel modifierDescComposantePanel = new JPanel();
    private JLabel gererComposantesLabel = new JLabel("Choisissez une option");
    private JLabel supprimerComposanteLabel = new JLabel("Supprimer une composante");
    private JLabel supprimerNomComposanteLabel = new JLabel("Nom de la composante a supprimer");
    private JLabel modifierPrixComposanteLabel = new JLabel("Modifier le prix d'une composante");
    private JLabel modifierNomComposanteLabel = new JLabel("Nom de la composante a modifier");
    private JLabel modifierDescComposanteLabel = new JLabel("Modifier la description d'une composante");
    private JTextField nomComposante = new JTextField();
    private JTextField prixComposante = new JTextField();
    private JTextField descComposante = new JTextField();
    private JButton btnSupprimer = new JButton("Supprimer");
    private JButton btnModifierPrix = new JButton("Modifier le prix");
    private JButton btnModifierDesc = new JButton("Modifier la description");
    private JButton btnAnnuler = new JButton("Annuler");
    private JButton btnConfirmerSupprression = new JButton("Supprimer la composante");
    private JButton btnConfirmerModifPrix = new JButton("Confirmer la modification du prix");
    private JButton btnConfirmerModifDesc = new JButton("Confirmer la modification de la description");

    public GererComposantesGUI() {
        setMainPanel();
        setSupprimerComposantePanel();
        setModifierPrixComposantePanel();
        setModifierDescComposantePanel();
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gererComposantesLabel);
        mainPanel.add(btnSupprimer);
        mainPanel.add(btnModifierPrix);
        mainPanel.add(btnModifierDesc);
    }

    public void setSupprimerComposantePanel() {
        supprimerComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        supprimerComposantePanel.add(supprimerComposanteLabel);
        supprimerComposantePanel.add(supprimerNomComposanteLabel);
        supprimerComposantePanel.add(nomComposante);
        supprimerComposantePanel.add(btnConfirmerSupprression);
        supprimerComposantePanel.add(btnAnnuler);
    }

    public void setModifierPrixComposantePanel() {
        modifierPrixComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPrixComposantePanel.add(modifierPrixComposanteLabel);
        modifierPrixComposantePanel.add(modifierNomComposanteLabel);
        modifierPrixComposantePanel.add(nomComposante);
        modifierPrixComposantePanel.add(prixComposante);
        modifierPrixComposantePanel.add(btnConfirmerModifPrix);
        modifierPrixComposantePanel.add(btnAnnuler);
    }

    public void setModifierDescComposantePanel() {
        modifierDescComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierDescComposantePanel.add(modifierDescComposanteLabel);
        modifierDescComposantePanel.add(modifierNomComposanteLabel);
        modifierDescComposantePanel.add(descComposante);
        modifierDescComposantePanel.add(btnConfirmerModifDesc);
        modifierDescComposantePanel.add(btnAnnuler);
    }


    public void afficherMainPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
