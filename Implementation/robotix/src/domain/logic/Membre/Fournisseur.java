package domain.logic.Membre;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.time.LocalDateTime;
import java.util.UUID;

import java.util.*;

public class Fournisseur extends Membre {
    private String typeRobotFabriquer;
    private String typeComposantesFabriquer;
    private String capaciteProductionComposantes;
    private LinkedList<Robot> inventaireDeRobot=new LinkedList<>();
    private LinkedList<Composant> inventaireComposant= new LinkedList<>();
    private Notification notification = new Notification();
    private LinkedList<Notification> listeNotifications = new LinkedList<>();
    private boolean[] tabBoolNotification = new boolean[4];
    private int taillePrecedenteInventaireComposantes;

    public Fournisseur(String nom, String adresse, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie){
        super(nom, adresse, email, numeroTelephone, nomcompagnie);
        this.typeRobotFabriquer=typeDeRobotFabriquer;
        this.typeComposantesFabriquer=typeComposantesFabriquer;
        this.capaciteProductionComposantes =capacite;
    }

    /* Getters */

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
        return super.getNom();
    }
    public Notification getNotification() { return this.notification; }

    public boolean[] getTabBoolNotification() {
        return tabBoolNotification;
    }

    public LinkedList<Notification> getListeNotifications() { return this.listeNotifications; }


    /* Setters */
    public void setCapaciteProductionComposantes(String capaciteProductionComposantes) {
        this.capaciteProductionComposantes = capaciteProductionComposantes;
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
        inventaireDeRobot.add(new Robot());
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

    public void ajouterComposante(/*String composante, double prix, String description, String typeComposant*/) {
        taillePrecedenteInventaireComposantes = inventaireComposant.size();
        inventaireComposant.add(new Composant());
        //TODO
        //inventaireComposant.add(new Composant(composante, prix, description, typeComposant));
    }

    public boolean retirerComopsante(String nom) {
        boolean bool = false;
        int nbComposantes = 0;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(nom)) {
                this.inventaireComposant.remove(composant);
                ++nbComposantes;
                break;
            }
        }
        if (nbComposantes != 0) {
            bool = true;
        }
        return bool;
    }

    public boolean modifierPrixComposante(String composante, Double nouveauPrix){
        boolean bool = false;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(composante)){
                composant.setPrix(nouveauPrix);
                bool = true;
                break;
            }
        }
        return bool;
    }

    public boolean modifierDescriptionComposante(String composante, String nouvelleDescription){
        boolean bool = false;
        for (Composant composant : inventaireComposant) {
            if (composant.getNom().equals(composante)){
                composant.setDescription(nouvelleDescription);
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void modifierProfile(String choix, String info){
        switch (choix.toLowerCase()) {
            case "nom" -> this.setNom(info);
            case "addresse" -> this.setAdresse(info);
            case "emain" -> this.setEmail(info);
            case "numerotelephone" -> this.setNumeroTelephone(info);
            case "nomcompagnie" -> this.setNomCompagnie(info);
            case "capaciteproduction" -> this.setCapaciteProductionComposantes(info);
        }
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

    public LinkedList<Notification> notifier() {
        //boolean NotifierAchatComposants;

        /*NotifierAchatComposants = */verifierInventaireComposants();

        //tabBoolNotification[1] = NotifierAchatComposants;

        return listeNotifications;
    }
    public String getProfilFournisseur(){
        return "Nom :" + super.getNom() + "\n adresse courriel : " +
                this.email + "\nTelephone : " + this.numeroTelephone +
                "Type de robot fabriquer :" + this.typeRobotFabriquer +
                "Type de composant fabriquer :" + this.typeComposantesFabriquer +
                "Nombre de robot disponible :" + this.getInventaireComposant().size() +
                "Nombre de robot disponible : " + this.getInventaireComposant().size();
    }

    public void verifierInventaireComposants() {
        //boolean DoitEtreNotifie = false;

        if (inventaireComposant.size() > taillePrecedenteInventaireComposantes) {
            //DoitEtreNotifie = true;
            notification.setTitre("ACHAT D'UNE VOS COMPOSANTES");
            notification.setMesssage("Un utilisateur a acheté une de vos composantes");
            notification.setTypeNotification(TypeNotification.ACHAT_COMPOSANTS);
            listeNotifications.add(notification);
        }
        //return DoitEtreNotifie;
    }
}

