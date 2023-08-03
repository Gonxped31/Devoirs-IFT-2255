

import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

public class UtilisateurTest {

    HashSet<Interet> interets = new HashSet<>();
    ArrayList<Notification> notifications = new ArrayList<>();
    Utilisateur utilisateur = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);

    @Test
    void testSetEmail() {
        String email = "test2@example.com";
        utilisateur.setEmail(email);
        assertEquals(email, utilisateur.getEmail());
    }

    @Test
    void testSuivreUtilisateur() {
        Utilisateur userToFollow = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);
        utilisateur.suivreUtilisateur(userToFollow);
        assertTrue(utilisateur.getListeUtilisateursSuivi().contains(userToFollow));
    }

    @Test
    void testEtreSuivi() {
        Utilisateur follower = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);
        utilisateur.etreSuivi(follower);
        assertTrue(utilisateur.getListSuiveur().contains(follower));
    }

    @Test
    void testEnregistrerRobot() {
        Robot robot = new Robot();
        assertFalse(utilisateur.enregistrerRobot(robot));
        assertTrue(utilisateur.enregistrerRobot(robot));
    }

    @Test
    public void testModifierProfil() {
        Utilisateur utilisateur = new Utilisateur(
                "Dupont",
                "Jean",
                "12 rue de la République",
                "jdupont",
                "1234",
                "jdupont@mail.com",
                "0102030405",
                "Compagnie Dupont",
                new HashSet<Interet>(),
                new ArrayList<Notification>()
        );

        // Nouvelles informations
        String nouveauNom = "Doe";
        String nouveauPrenom = "John";
        String nouvelleAdresse = "34 rue des Fleurs";
        String nouveauEmail = "jdoe@mail.com";
        String nouveauNumeroTelephone = "0607080910";

        utilisateur.modifierProfile("nom", nouveauNom);
        utilisateur.modifierProfile("prenom", nouveauPrenom);
        utilisateur.modifierProfile("adresse", nouvelleAdresse);
        utilisateur.modifierProfile("email", nouveauEmail);
        utilisateur.modifierProfile("numerotelephone", nouveauNumeroTelephone);

        assertEquals(nouveauNom, utilisateur.getNom(), "Le nom de l'utilisateur doit être mis à jour.");
        assertEquals(nouveauPrenom, utilisateur.getPrenom(), "Le prénom de l'utilisateur doit être mis à jour.");
        assertEquals(nouvelleAdresse, utilisateur.getAdresse(), "L'adresse de l'utilisateur doit être mise à jour.");
        assertEquals(nouveauEmail, utilisateur.getEmail(), "L'email de l'utilisateur doit être mis à jour.");
        assertEquals(nouveauNumeroTelephone, utilisateur.getTelephone(), "Le numéro de téléphone de l'utilisateur doit être mis à jour.");
    }

    @Test
    void testAjouterComposante() {
        Composant composant = new Composant();
        utilisateur.ajouterComposante(composant);
        assertTrue(utilisateur.getComposantesAchetes().contains(composant));
    }
}

