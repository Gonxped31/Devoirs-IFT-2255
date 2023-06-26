package domain.logic.Membre;
import domain.logic.Robot.Robot;

import java.util.*;

public class Fournisseur extends Membre {
    private String typeRobotFabriquer;
    private String typeComposantesFabriquer;
    private String capaciteProductionComposantes;
    private LinkedList<Robot> inventaireDeRobot=new LinkedList<>();
    private LinkedList<String> inventaireComposant= new LinkedList<>();
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

    public boolean authentification(String nom, ArrayList<Fournisseur> listeFournisseurs) {
        boolean authentification = false;
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(nom)) {
                authentification = true;
                break;
            }
        }
        return authentification;
    }

    public String verifierNomFournisseur(String inputNom, ArrayList<Fournisseur> listeFournisseurs) {
        String message = "";
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(inputNom)) {
                message = "Ce nom de fournisseur existe déjà. Veuillez saisir un autre nom: ";
                break;
            }
        }
        return message;
    }

    public boolean verifierEmailFournisseur(String inputEmail) {
        return inputEmail.contains("@");
    }

    public boolean verifierTelephoneFournisseur(String inputTelephone) {
        return inputTelephone.length() == 10;
    }

    public void vendreUnComposant(String composant){
      inventaireComposant.remove(composant);
    }

    public void ajouterRobot(Robot robot, Fournisseur fournisseur) {
        inventaireDeRobot.add(robot);
        System.out.println(" ");
        System.out.println("Le robot a été rajouté avec succès !");
        System.out.println(" ");
    }

    public void retirerRobot(String nomRobot, Fournisseur fournisseur) {
        int nbRobot = 0;
        for (Robot robot : inventaireDeRobot) {
            if (robot.getNom().equals(nomRobot)) {
                inventaireDeRobot.remove(robot);
                nbRobot++;
            }
        }
        if (nbRobot == 0) {
            System.out.println("Vous ne possédez ce robot.");

        } else {
            System.out.println("Le robot a été retiré avec succès !");
        }
    }

    public void ajouterComposante(String composante, Fournisseur fournisseur) {
        inventaireComposant.add(composante);
        System.out.println(" ");
        System.out.println("La composante a été rajoutée avec succès !");
        System.out.println(" ");
    }

    public void retirerComopsante(String composante2, Fournisseur fournisseur) {
        int nbComposantes = 0;
        for (String string : inventaireComposant) {
            if (string.equals(composante2)) {
                inventaireComposant.remove(string);
                ++nbComposantes;
            } else {
                continue;
            }
        }
        if (nbComposantes == 0) {
            System.out.println("Vous ne possédez cette composante.");
        } else {
            System.out.println("La composante a été retirée avec succès !");
        }
    }

    @Override
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
    }

    public ArrayList<Fournisseur> trouverFournisseur(String info, ArrayList<Fournisseur> listeFournisseurs){
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        switch (info) {
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
                    if (fournisseur.getAdresse().equals(info)) {
                        fournisseurs.add(fournisseur);

                    }
                }

            case "4" :
                for (Fournisseur fournisseur :  listeFournisseurs) {
                    if (fournisseur.getAdresse().equals(info)) {
                        fournisseurs.add(fournisseur);

                    }
                }

            case "5" :
                for (Fournisseur fournisseur :  listeFournisseurs) {
                    if (fournisseur.getAdresse().equals(info)) {
                        fournisseurs.add(fournisseur);

                    }
                }

            case "6" :
                fournisseurs = listeFournisseurs;
        }

        return fournisseurs;
    }

}

