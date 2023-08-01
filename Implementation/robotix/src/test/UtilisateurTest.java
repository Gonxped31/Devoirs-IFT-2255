package test;

import static org.junit.jupiter.api.Assertions.*;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Robot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

class UtilisateurTest {

    private HashSet<Interet> listeInteret = new HashSet<>();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private Utilisateur utilisateur = new Utilisateur("Louis", "William", "2023 avenue du Genie-Logiciel", "will03", "wlouis123",
            "willlouis@gmail.com", "4382215728", "Les Genies du Web", listeInteret, listeNotifications);

    @Test
    void testSiActiviteEstDejaDansLaListeActiviteRejoint() {
        Activite cascade = new Activite();
        cascade.setNom("Cascade aerienne");
        utilisateur.rejoindreActivite(cascade);

        Activite cascade2 = new Activite();
        cascade2.setNom("Cascade aerienne");
        assertTrue(utilisateur.rejoindreActivite(cascade2));
    }

    @Test
    void testSiUtilisateurEstSupprimeDansLaListeUtilisateursSuivi() {
        Utilisateur utilisateurSuivi = new Utilisateur("Robert", "Jacques", "2022 rue de l'Informatique", "rob2022", "jrob456",
                "jacquesrobert@gmail.com", "5145557777", "Big Rob", listeInteret, listeNotifications);
        utilisateur.suivreUtilisateur(utilisateurSuivi);

        assertTrue(utilisateur.supprimerUtilisateurDeMaListe("rob2022"));
    }

    @Test
    void testSiLeNomDuRobotEstRetrouve() {
        Robot robot = new Robot();
        robot.setNom("Alexandre");
        utilisateur.ajouterRobot(robot);

        assertNull(utilisateur.retrouverRobotNom("Paul"));
    }
}