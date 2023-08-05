package domain.logic.GUI.UtilisateurGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import java.awt.event.ActionEvent;


import javax.swing.*;


import domain.logic.GUI.Menu;

/**
 * Interface utilisateur pour le menu de l'utilisateur. Permet à l'utilisateur de naviguer
 * et d'accéder aux différentes fonctionnalités et options disponibles pour son compte.
 */
public class MenuUtilisateur extends JFrame {
    /**
     * Pseudo de l'utilisateur actuellement connecté.
     */
        private String pseudo;
    /**
     * Fenêtre principale de l'interface utilisateur.
     */
        private JFrame jFrame = new JFrame();
    /**
     * Panel qui contient les éléments du menu utilisateur.
     */
        private JPanel menuUtilisateurPanel = new JPanel();
    /**
     * Libellé affiché au centre du menu utilisateur.
     */
        private JLabel menuUtilisateurLabel = new JLabel("Menu Utilisateur", SwingConstants.CENTER);
    /**
     * Bouton pour accéder à la modification du profil utilisateur.
     */
        private JButton btnModifierProfil = new JButton("Modifier mon profil");
    /**
     * Bouton pour accéder à la gestion de la flotte.
     */
        private JButton btnGererFlotte = new JButton("Gerer ma flotte");
    /**
     * Bouton pour accéder à la gestion des tâches.
     */
        private JButton btnGererTaches = new JButton("Gerer mes taches");
    /**
     * Bouton pour accéder à la gestion des activités.
     */
        private JButton btnGererActivites = new JButton("Gerer mes activites");
    /**
     * Bouton pour accéder à la gestion du réseau social.
     */
        private JButton btnGererReseauSocial = new JButton("Gerer mon reseau social");
    /**
     * Bouton pour accéder à la section d'achats.
     */
        private JButton btnAchats = new JButton("Achats");
    /**
     * Bouton pour accéder à la liste des notifications.
     */
        private JButton btnVoirNotifications = new JButton("Voir mes notifications");
    /**
     * Bouton pour effectuer une requête publique.
     */
        private JButton btnRequetePublique = new JButton("Faire une requete publique");
    /**
     * Bouton pour se déconnecter de l'application.
     */
        private JButton btnDeconnexion = new JButton("Deconnexion");
    /**
     * Interface utilisateur pour la modification du profil.
     */
        private ModifierProfilUtilisateurGUI modifierProfilUtilisateurGUI;
    /**
     * Interface utilisateur pour la gestion de la flotte.
     */
        private GestionFlotteGUI gestionFlotteGUI;
    /**
     * Interface utilisateur pour la gestion des tâches.
     */
        private GestionTachesGUI gestionTachesGUI;
    /**
     * Interface utilisateur pour la gestion des activités.
     */
        private GestionActivitesGUI gestionActivitesGUI;
    /**
     * Interface utilisateur pour la gestion du réseau social.
     */
        private GestionReseauGUI gestionReseauGUI;
    /**
     * Interface utilisateur pour les achats.
     */
        private AchatsGUI achatsGUI;
    /**
     * Interface utilisateur pour la gestion des notifications.
     */
        private GestionNotifsGUI gestionNotifsGUI = new GestionNotifsGUI();
    /**
     * Interface utilisateur pour la création de requêtes publiques.
     */
        private RequetePubliqueUtilisateurGUI requetePubliqueUtilisateurGUI;

        /**
         * Constructeur par défaut de la classe MenuUtilisateur.
         * Crée une instance de l'interface utilisateur du menu sans initialiser de données.
         * @throws IOException En cas d'erreur d'entrée/sortie.
         * @throws ParseException En cas d'erreur lors de l'analyse de la date.
         */
        public MenuUtilisateur() throws IOException, ParseException {

        }
        /**
         * Constructeur de la classe MenuUtilisateur.
         * Initialise l'interface utilisateur du menu pour un utilisateur spécifié par son pseudo.
         * @param pseudo Le pseudo de l'utilisateur.
         * @throws IOException En cas d'erreur d'entrée/sortie.
         * @throws ParseException En cas d'erreur lors de l'analyse de la date.
         */
        public MenuUtilisateur(String pseudo) throws IOException, ParseException {
            this.pseudo = pseudo;
            instancierMenus(pseudo);

            modifierProfilUtilisateurGUI = new ModifierProfilUtilisateurGUI(pseudo);
            menuUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
            menuUtilisateurPanel.setLayout(new GridLayout(0, 1));
            menuUtilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Ajout des composantes
            menuUtilisateurPanel.add(menuUtilisateurLabel);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnModifierProfil);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererFlotte);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererTaches);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererActivites);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererReseauSocial);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnAchats);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnVoirNotifications);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnRequetePublique);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnDeconnexion);

            btnModifierProfil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modifierProfilUtilisateurGUI.afficherMainPanel(jFrame);
                }
            }); //Connexion faite
            btnGererFlotte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionFlotteGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererTaches.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionTachesGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererActivites.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionActivitesGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererReseauSocial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionReseauGUI.afficherMainPanel(jFrame);
                }
            });
            btnAchats.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    achatsGUI.afficherMainPanel(jFrame);
                }
            }); //Connexion etablie
            btnVoirNotifications.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionNotifsGUI.afficherMainPanel(jFrame);
                }
            });
            btnRequetePublique.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    requetePubliqueUtilisateurGUI.afficherMainPanel(jFrame);
                }
            });
            btnDeconnexion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.dispose();
                    try {
                        domain.logic.GUI.Menu menu = new Menu();
                    } catch (IOException | ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

        }

    /**
     * Instancie les différentes interfaces utilisateur nécessaires pour la gestion du menu.
     *
     * @param pseudo Le pseudo de l'utilisateur pour les initialisations.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'instanciation.
     * @throws ParseException Si une erreur de parsing se produit lors de l'instanciation.
     */
        private void instancierMenus(String pseudo) throws IOException, ParseException {
            gestionFlotteGUI = new GestionFlotteGUI(pseudo);
            achatsGUI = new AchatsGUI(pseudo);
            gestionTachesGUI = new GestionTachesGUI(pseudo);
            gestionActivitesGUI = new GestionActivitesGUI(pseudo);
            requetePubliqueUtilisateurGUI = new RequetePubliqueUtilisateurGUI(pseudo);
            gestionReseauGUI = new GestionReseauGUI(pseudo);
        }

    /**
     * Affiche le panneau du menu utilisateur dans la fenêtre principale.
     *
     * @param jFrame La fenêtre principale où afficher le panneau du menu utilisateur.
     */
        public void afficherMenuUtilisateurPanel(JFrame jFrame) {
            this.jFrame = jFrame;
            this.jFrame.setContentPane(menuUtilisateurPanel);
            this.jFrame.revalidate();
            this.jFrame.repaint();
        }

}