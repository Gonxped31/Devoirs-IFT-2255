package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente une interface graphique pour la gestion des notifications.
 * Elle affiche les notifications de l'utilisateur.
 */
public class GestionNotifsGUI {
    /**
     * La fenêtre principale de l'interface graphique.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Le panneau principal de l'interface graphique avec une disposition en grille.
     */
    private JPanel mainPanel = new JPanel(new BorderLayout());

    /**
     * Panneau principal qui organise les composants avec le gestionnaire de mise en page BorderLayout.
     */
    private JPanel notifsPanel = new JPanel(new GridBagLayout());
    /**
     * Panneau qui contient les composants liés à l'affichage des notifications.
     * Il utilise le gestionnaire de mise en page GridBagLayout pour la disposition.
     */
    private JLabel gestionNotifsLabel = new JLabel("Vos notifications", SwingConstants.CENTER);
    /**
     * Étiquette qui affiche le titre "Vos notifications" centré dans le panneau.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Bouton qui permet à l'utilisateur de revenir au menu principal de l'application.
     */
    private Container panelPrecedent = new Container();
    /**
     * Conteneur qui garde en mémoire le panneau précédent auquel l'utilisateur devra revenir.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Constructeur de la classe GestionNotifsGUI.
     * Initialise et configure les composants de l'interface graphique pour la gestion des notifications.
     * Ajoute les composants au panneau principal et définit les actions associées aux boutons.
     */
    public GestionNotifsGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gestionNotifsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Ajout des composantes
        constraints.gridy = 0;
        notifsPanel.add(btnRetour, constraints);
        mainPanel.add(gestionNotifsLabel, BorderLayout.NORTH);
        mainPanel.add(notifsPanel, BorderLayout.CENTER);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    /**
     * Affiche le panneau principal de l'interface graphique de gestion des notifications dans la fenêtre JFrame spécifiée.
     *
     * @param jFrame La fenêtre JFrame dans laquelle afficher le panneau principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu de la fenêtre JFrame en revalidant et en redessinant la fenêtre.
     * Appelé pour appliquer les changements d'affichage après avoir modifié le contenu de la fenêtre.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
