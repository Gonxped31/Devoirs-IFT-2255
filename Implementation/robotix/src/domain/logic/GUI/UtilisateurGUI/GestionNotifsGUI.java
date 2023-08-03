package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GestionNotifsGUI {
    String pseudo;
    private ControlleurUtilisateurs controlleurUtilisateur = new ControlleurUtilisateurs();
    private DbControleur dbControleur = new DbControleur();
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel notifsPanel = new JPanel(new GridBagLayout());
    private JPanel listeNotifsPanel = new JPanel();
    private JLabel gestionNotifsLabel = new JLabel("Vos notifications", SwingConstants.CENTER);
    private JButton btnSupprimerNotifs = new JButton("Supprimer vos notifications");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private JScrollPane scrollPaneNotifs;
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionNotifsGUI() throws IOException, ParseException {
        this.pseudo = pseudo;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gestionNotifsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        recupererListeDeNotifs();

        // Ajout des composantes
        constraints.gridy = 0;
        notifsPanel.add(listeNotifsPanel, constraints);
        constraints.gridy = 1;
        notifsPanel.add(btnSupprimerNotifs,constraints );
        constraints.gridy = 2;
        notifsPanel.add(btnRetour, constraints);
        constraints.gridy = 3;
        mainPanel.add(gestionNotifsLabel, BorderLayout.NORTH);
        constraints.gridy = 4;
        mainPanel.add(notifsPanel, BorderLayout.CENTER);

            btnSupprimerNotifs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.setContentPane(notifsPanel); // Mettre a jour le contentPane avec le panel precedent
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

    private void recupererListeDeNotifs() {

        ArrayList<Notification> listeNotification = controlleurUtilisateur.voirNotifications(pseudo);


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
