package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionTachesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel creerTachePanel = new JPanel();
    private JPanel allouerTachePanel = new JPanel();
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches");
    private JLabel creerTacheLabel = new JLabel("Quelles tache voulez-vous creer?");
    private JLabel allouerTacheLabel = new JLabel("A quel robot voulez-vous allouer une tache?");
    private JLabel allouerTacheLabel2 = new JLabel("Quel est le nom de la tache a allouer?");
    private JTextField nomTacheCreeField = new JTextField();
    private JTextField nomTacheAlloueField = new JTextField();
    private JTextField nomRobotField = new JTextField();
    private JButton btnCreerTache = new JButton("Creer une tache");
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public GestionTachesGUI() {
        setMainPanel();
        setCreerTachePanel();
        setAllouerTachePanel();

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionTachesLabel);
        mainPanel.add(btnCreerTache);
        mainPanel.add(btnAllouerTache);
        mainPanel.add(btnRetour);
    }

    public void setCreerTachePanel() {
        creerTachePanel.setLayout(new GridLayout(0, 2, 5, 5));
        creerTachePanel.add(creerTacheLabel);
        creerTachePanel.add(nomTacheCreeField);
    }

    public void setAllouerTachePanel() {
        allouerTachePanel.setLayout(new GridLayout(0, 2, 5, 5));
        allouerTachePanel.add(allouerTacheLabel);
        allouerTachePanel.add(nomRobotField);
        allouerTachePanel.add(allouerTacheLabel2);
        allouerTachePanel.add(nomTacheAlloueField);
    }

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
