package domain.logic.GUI.FournisseurGUI;

import domain.logic.Menu.MenuUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GererComposantesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel supprimerComposantePanel = new JPanel();
    private JPanel modifierPrixComposantePanel = new JPanel();
    private JPanel modifierDescComposantePanel = new JPanel();
    private JLabel gererComposantesLabel = new JLabel("Choisissez une option", SwingConstants.CENTER);
    private JLabel supprimerComposanteLabel = new JLabel("Supprimer une composante");
    private JLabel supprimerNomComposanteLabel = new JLabel("Nom de la composante a supprimer");
    private JLabel modifierPrixComposanteLabel = new JLabel("Modifier le prix d'une composante");
    private JLabel modifierNomComposanteLabel = new JLabel("Nom de la composante a modifier");
    private JLabel modifierDescComposanteLabel = new JLabel("Modifier la description d'une composante");
    private JTextField nomComposanteField = new JTextField();
    private JTextField prixComposanteField = new JTextField();
    private JTextField descComposanteField = new JTextField();
    private JButton btnSupprimer = new JButton("Supprimer");
    private JButton btnModifierPrix = new JButton("Modifier le prix");
    private JButton btnModifierDesc = new JButton("Modifier la description");
    private JButton btnAnnuler = new JButton("Annuler");
    private JButton btnConfirmerSupprression = new JButton("Supprimer la composante");
    private JButton btnConfirmerModifPrix = new JButton("Confirmer la modification du prix");
    private JButton btnConfirmerModifDesc = new JButton("Confirmer la modification de la description");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public GererComposantesGUI() {
        setMainPanel();
        setSupprimerComposantePanel();
        setModifierPrixComposantePanel();
        setModifierDescComposantePanel();

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(supprimerComposantePanel);
                mettreAJourFrame();
            }
        });
        btnModifierPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPrixComposantePanel);
                mettreAJourFrame();
            }
        });
        btnModifierDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierDescComposantePanel);
                mettreAJourFrame();
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        gererComposantesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gererComposantesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnSupprimer);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierPrix);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierDesc);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setSupprimerComposantePanel() {
        supprimerComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        supprimerComposantePanel.add(supprimerComposanteLabel);
        supprimerComposantePanel.add(Box.createHorizontalStrut(10));
        supprimerComposantePanel.add(supprimerNomComposanteLabel);
        supprimerComposantePanel.add(nomComposanteField);
        supprimerComposantePanel.add(btnConfirmerSupprression);
        supprimerComposantePanel.add(btnAnnuler);
    }

    public void setModifierPrixComposantePanel() {
        modifierPrixComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierPrixComposantePanel.add(modifierPrixComposanteLabel);
        modifierPrixComposantePanel.add(Box.createHorizontalStrut(10));
        modifierPrixComposantePanel.add(modifierNomComposanteLabel);
        modifierPrixComposantePanel.add(nomComposanteField);
        modifierPrixComposantePanel.add(prixComposanteField);
        modifierPrixComposantePanel.add(btnConfirmerModifPrix);
        modifierPrixComposantePanel.add(btnAnnuler);
    }

    public void setModifierDescComposantePanel() {
        modifierDescComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
        modifierDescComposantePanel.add(modifierDescComposanteLabel);
        modifierDescComposantePanel.add(Box.createHorizontalStrut(10));
        modifierDescComposantePanel.add(modifierNomComposanteLabel);
        modifierDescComposantePanel.add(descComposanteField);
        modifierDescComposantePanel.add(btnConfirmerModifDesc);
        modifierDescComposantePanel.add(btnAnnuler);
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
