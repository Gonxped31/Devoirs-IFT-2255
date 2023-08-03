

import domain.logic.Membre.Fournisseur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FournisseurTest {

    @Test
    void testNomCompagnie() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        assertEquals("compagnie", fournisseur.getNomCompagnie());
    }

    @Test
    void testAjouterRobot() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterRobot(new ArrayList<>(), "type");
        assertEquals(1, fournisseur.getInventaireDeRobot().size());
    }

    @Test
    void testRetirerRobot() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        UUID idRobot = fournisseur.ajouterRobot(new ArrayList<>(), "type");
        assertTrue(fournisseur.retirerRobot(idRobot.toString()));
    }

    @Test
    void testAjouterComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertEquals(1, fournisseur.getInventaireComposant().size());
    }

    @Test
    void testRetirerComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertTrue(fournisseur. retirerComopsante("nom"));
    }

    @Test
    void testModifierPrixComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertTrue(fournisseur.modifierPrixComposante("nom", "nouveauPrix"));
    }
}

