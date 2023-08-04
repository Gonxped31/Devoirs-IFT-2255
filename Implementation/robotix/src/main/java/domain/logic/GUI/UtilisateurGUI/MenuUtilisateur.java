package domain.logic.GUI.UtilisateurGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import java.awt.event.ActionEvent;


import javax.swing.*;


import domain.logic.GUI.Menu;


public class MenuUtilisateur extends JFrame {
        private String pseudo;
        private JFrame jFrame = new JFrame();
        private JPanel menuUtilisateurPanel = new JPanel();
        private JLabel menuUtilisateurLabel = new JLabel("Menu Utilisateur", SwingConstants.CENTER);
        private JButton btnModifierProfil = new JButton("Modifier mon profil");
        private JButton btnGererFlotte = new JButton("Gerer ma flotte");
        private JButton btnGererTaches = new JButton("Gerer mes taches");
        private JButton btnGererActivites = new JButton("Gerer mes activites");
        private JButton btnGererReseauSocial = new JButton("Gerer mon reseau social");
        private JButton btnAchats = new JButton("Achats");
        private JButton btnVoirNotifications = new JButton("Voir mes notifications");
        private JButton btnRequetePublique = new JButton("Faire une requete publique");
        private JButton btnDeconnexion = new JButton("Deconnexion");
        private ModifierProfilUtilisateurGUI modifierProfilUtilisateurGUI;
        private GestionFlotteGUI gestionFlotteGUI;
        private GestionTachesGUI gestionTachesGUI;
        private GestionActivitesGUI gestionActivitesGUI;
        private GestionReseauGUI gestionReseauGUI;
        private AchatsGUI achatsGUI;
        private GestionNotifsGUI gestionNotifsGUI = new GestionNotifsGUI();
        private RequetePubliqueUtilisateurGUI requetePubliqueUtilisateurGUI;

        public MenuUtilisateur() throws IOException, ParseException {

        }

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

        private void instancierMenus(String pseudo) throws IOException, ParseException {
            gestionFlotteGUI = new GestionFlotteGUI(pseudo);
            achatsGUI = new AchatsGUI(pseudo);
            gestionTachesGUI = new GestionTachesGUI(pseudo);
            gestionActivitesGUI = new GestionActivitesGUI(pseudo);
            requetePubliqueUtilisateurGUI = new RequetePubliqueUtilisateurGUI(pseudo);
            gestionReseauGUI = new GestionReseauGUI(pseudo);
        }

        public void afficherMenuUtilisateurPanel(JFrame jFrame) {
            this.jFrame = jFrame;
            this.jFrame.setContentPane(menuUtilisateurPanel);
            this.jFrame.revalidate();
            this.jFrame.repaint();
        }

}