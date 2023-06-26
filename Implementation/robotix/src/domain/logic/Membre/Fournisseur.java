package domain.logic.Membre;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.util.UUID;

import java.util.*;

public class Fournisseur extends Membre {
    private String typeRobotFabriquer;
    private String typeComposantesFabriquer;
    private String capaciteProductionComposantes;
    private LinkedList<Robot> inventaireDeRobot=new LinkedList<>();
    private LinkedList<Composant> inventaireComposant= new LinkedList<>();
    public Fournisseur(String nom, String adresse, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie){
        super(nom, adresse, email, numeroTelephone, nomcompagnie);
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capaciteProductionComposantes =capacite;
    }

    public String getNomCompagnie() {
        return this.nomCompagnie;
    }

    public LinkedList<Robot> getInventaireDeRobot() {
        return inventaireDeRobot;
    }

    public LinkedList<Composant> getInventaireComposant() {
        return inventaireComposant;
    }

    public String getCapaciteProductionComposantes() {
        return this.capaciteProductionComposantes;
    }

    public String getTypeComposantesFabriquer() {
        return this.typeComposantesFabriquer;
    }

    public String getTypeRobotFabriquer() {
        return this.typeRobotFabriquer;
    }

    public String getTelephone() {
        return this.numeroTelephone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getNom() {
        return this.nom;
    }

    public static boolean authentification(String nom, ArrayList<Fournisseur> listeFournisseurs) {
        boolean authentification = false;
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(nom)) {
                authentification = true;
                break;
            }
        }
        return authentification;
    }

    public static boolean verifierNomFournisseur(String inputNom, ArrayList<Fournisseur> listeFournisseurs) {
        boolean bool = false;
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (!fournisseur.getNom().equals(inputNom)) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public static boolean verifierEmailFournisseur(String inputEmail) {
        return inputEmail.contains("@");
    }

    public static boolean verifierTelephoneFournisseur(String inputTelephone) {
        return inputTelephone.length() == 10;
    }

    public void ajouterRobot() {
        inventaireDeRobot.add(new Robot(null,0, 0, 0, 0, 0, 0, null, null, null, null, null, UUID.randomUUID()));
    }

    public boolean retirerRobot(String nomRobot) {
        boolean bool = false;
        int nbRobot = 0;
        for (Robot robot : inventaireDeRobot) {
            if (robot.getNom().equals(nomRobot)) {
                inventaireDeRobot.remove(robot);
                nbRobot++;
                break;
            }
        }
        if (nbRobot != 0) {
            bool = true;
        }

        return bool;
    }

    public void ajouterComposante(String composante, double prix, String description, String typeComposant) {
        inventaireComposant.add(new Composant(composante, prix, description, typeComposant));
    }

    public boolean retirerComopsante(String nom) {
        boolean bool = false;
        int nbComposantes = 0;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(nom)) {
                inventaireComposant.remove(composant);
                ++nbComposantes;
                break;
            }
        }
        if (nbComposantes != 0) {
            bool = true;
        }
        return bool;
    }

    /*@Override
    public String toString() {
        return  "Fournisseur { " + '\n' +
                "Nom = " + getNom() + '\n' +
                "Adresse = " + getAdresse() + '\n' +
                "Email = " + getEmail() + '\n' +
                "Numéro de télephone = " + getTelephone() + '\n' +
                "Type de robots fabriqués = " + getTypeRobotFabriquer() + '\n' +
                "Type de composantes fabriquées = " + getTypeComposantesFabriquer() + '\n' +
                "Capacité de fabrication = " + getCapaciteProductionComposantes() + '\n' +
                "Nom de compagnie = " + getNomCompagnie() + '\n' +
                "}\n";
    }*/

    public static ArrayList<Fournisseur> trouverFournisseur(String choix, String info, ArrayList<Fournisseur> listeFournisseurs){
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        switch (choix) {
            case "1" :
                for (Fournisseur fournisseur :  listeFournisseurs) {
                    if (fournisseur.getNom().equals(info)) {
                        fournisseurs.add(fournisseur);
                    }
                }

            case "2" :
                for (Fournisseur fournisseur :  listeFournisseurs) {
                    if (fournisseur.getAdresse().equals(info)) {
                        fournisseurs.add(fournisseur);
                    }
                }

            case "3" :
                for (Fournisseur fournisseur :  listeFournisseurs) {
                    if (fournisseur.getTypeComposantesFabriquer().equals(info)) {
                        fournisseurs.add(fournisseur);

                    }
                }

            case "4" :
                fournisseurs = listeFournisseurs;
        }

        return fournisseurs;
    }

}

