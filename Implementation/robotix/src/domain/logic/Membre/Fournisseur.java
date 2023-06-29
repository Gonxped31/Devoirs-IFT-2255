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
    private int taillePrecedenteInventaireComposantes;

    public Fournisseur(String nom, String mdp,  String adresse, String email, String numeroTelephone,
                       String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie){
        super(nom, adresse, email, numeroTelephone, nomcompagnie, mdp);
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
        return super.getNom();
    }

    public Notification getNotification() { return this.notification; }
    public LinkedList<Notification> getListeNotifications() { return this.listeNotifications; }

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

    public boolean retirerRobot(String numeroSerie) {
        boolean bool = false;
        int nbRobot = 0;
        for (Robot robot : inventaireDeRobot) {
            if (robot.getNumeroSerie().equals(numeroSerie)) {
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

    public boolean retirerComopsante(Composant composant) {
        boolean check = inventaireComposant.stream()
                .anyMatch(c -> c.equals(composant));
        if (check){
            this.inventaireComposant.remove(composant);
        }
        return check;
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

    public boolean[] notifier() {
        boolean[] tabBoolean = new boolean[4];
        boolean NotifierEtatRobot;
        boolean NotifierBatterieRobot;
        boolean NotifiercCPURobot;
        boolean NotifierAchatComposants;

        NotifierEtatRobot = verifierEtatRobot();
        NotifierBatterieRobot = verifierBatterieRobot();
        NotifiercCPURobot = verifierCPURobot();
        NotifierAchatComposants = verifierInventaireComposants();

        tabBoolean[0] = NotifierEtatRobot;
        tabBoolean[1] = NotifierAchatComposants;
        tabBoolean[2] = NotifierBatterieRobot;
        tabBoolean[3] = NotifiercCPURobot;

        return tabBoolean;
    }
    public String getProfilFournisseur(){
        return "Nom :" + super.getNom() + "\n adresse courriel : " +
                this.email + "\nTelephone : " + this.numeroTelephone +
                "Type de robot fabriquer :" + this.typeRobotFabriquer +
                "Type de composant fabriquer :" + this.typeComposantesFabriquer +
                "Nombre de robot disponible :" + this.getInventaireComposant().size() +
                "Nombre de robot disponible : " + this.getInventaireComposant().size();
    }

    public boolean verifierEtatRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getVitesse() == 0 || robot.getMemoire() == 0) {
                DoitEtreNotifie = true;
                notification.setTitre("MAUVAIS FONCTIONNEMENT");
                notification.setMesssage("Le robot " + robot.getNom() + " éprouve un problème de fonctionnement.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }
    public boolean verifierBatterieRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getBatterie() >= 20) {
                DoitEtreNotifie = true;
                notification.setTitre("BATTERIE FAIBLE");
                notification.setMesssage("La batterie du robot " + robot.getNom() + " est à " + robot.getBatterie() + "%.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }
    public boolean verifierCPURobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getCpu() >= 100) {
                DoitEtreNotifie = true;
                notification.setTitre("SURCHARGE CPU");
                notification.setMesssage("Le CPU du robot " + robot.getNom() + " est surchagé");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(notification);
            }
        }
        return DoitEtreNotifie;
    }

    public boolean verifierInventaireComposants() {
        boolean DoitEtreNotifie = false;

        if (inventaireComposant.size() > taillePrecedenteInventaireComposantes) {
            DoitEtreNotifie = true;
            notification.setTitre("ACHAT D'UNE VOS COMPOSANTES");
            notification.setMesssage("Un utilisateur a acheté une de vos composantes");
            notification.setTypeNotification(TypeNotification.ACHAT_COMPOSANTS);
            listeNotifications.add(notification);
        }
        return DoitEtreNotifie;
    }
}

