package domain.logic.Membre;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import java.util.UUID;

import java.util.*;

public class Fournisseur extends Membre implements java.io.Serializable{
    private String typeRobotFabriquer;
    private String typeComposantesFabriquer;
    private String capaciteProductionComposantes;
    private LinkedList<Robot> inventaireDeRobot = new LinkedList<>();
    private LinkedList<Composant> inventaireComposant = new LinkedList<>();
    //private Notification notification = new Notification();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private int taillePrecedenteInventaireComposantes;

    @JsonCreator
    public Fournisseur(@JsonProperty("nom") String nom, @JsonProperty("mdp") String mdp,
                       @JsonProperty("adresse") String adresse, @JsonProperty("email") String email,
                       @JsonProperty("numeroTelephone") String numeroTelephone,
                       @JsonProperty("typeDeRobotFabriquer") String typeDeRobotFabriquer,
                       @JsonProperty("typeComposantesFabriquer") String typeComposantesFabriquer,
                       @JsonProperty("capacite") String capacite,
                       @JsonProperty("nomcompagnie") String nomcompagnie) {

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

    public void setInventaireDeRobot(LinkedList<Robot> inventaireDeRobot) {
        this.inventaireDeRobot = inventaireDeRobot;
    }

    public LinkedList<Composant> getInventaireComposant() {
        return inventaireComposant;
    }

    public void setInventaireComposant(LinkedList<Composant> inventaireComposant) {
        this.inventaireComposant = inventaireComposant;
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

    public String getEmail() {
        return this.email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getNom() {
        return super.getNom();
    }

    //public Notification getNotification() { return this.notification; }
    public ArrayList<Notification> getListeNotifications() { return this.listeNotifications; }

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

    public UUID ajouterRobot(ArrayList<Composant> composants, String typeRobot) {
        Robot r= new Robot("", 0, 0, 0, 100, 32, 32, new LinkedList<>(), "", new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        for( Composant c :composants){
            r.ajouterComposante(c);
            r.setType(typeRobot);
        }
        inventaireDeRobot.add(r);
        return   r.getNumeroSerie();
    }

    public boolean retirerRobot(String numeroSerie) {
        Robot robot = inventaireDeRobot.stream()
                .filter(r -> r.getNumeroSerie().toString().trim().equals(numeroSerie.trim()))
                .findFirst()
                .orElse(null);

        boolean robotExiste = robot != null;

        if (robotExiste) {
            inventaireDeRobot.remove(robot);
        }

        return robotExiste;
    }


    public void ajouterComposante(String nom, String prix, String description, String typeComposant) {
        taillePrecedenteInventaireComposantes = inventaireComposant.size();
        inventaireComposant.add(new Composant(nom,prix,description,typeComposant.toUpperCase()));
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

    public boolean modifierPrixComposante(String composante, String nouveauPrix){
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
            case "email" -> this.setEmail(info);
            case "numerotelephone" -> this.setTelephone(info);
            case "nomcompagnie" -> this.setNomCompagnie(info);
            case "capaciteproduction" -> this.setCapaciteProductionComposantes(info);
            case "mdp" -> this.setMotDePasse(info);
        }
    }
/*
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
    }*/
    @JsonIgnore
    public String getProfilFournisseur(){
        return "Nom: " + super.getNom() + "\nAdresse courriel: " +
                this.email + "\nTelephone: " + this.getTelephone() +
                "\nAddresse: " + this.getAdresse() +
                "\nType de robot fabriquer: " + this.typeRobotFabriquer +
                "\nType de composant fabriquer: " + this.typeComposantesFabriquer +
                "\nNombre de composante disponible: " + this.getInventaireComposant().size() +
                "\nNombre de robot disponible: " + this.getInventaireDeRobot().size() + "\n";
    }
    /*
    public boolean verifierEtatRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getVitesse() == 0 || robot.getMemoire() == 0) {
                DoitEtreNotifie = true;
                Notification nouvelleNotification = new Notification("MAUVAIS FONCTIONNEMENT", "Le robot " + robot.getNom() + " éprouve un problème de fonctionnement.", TypeNotification.PROBLEME_ROBOT);
                /*notification.setTitre("MAUVAIS FONCTIONNEMENT");
                notification.setMesssage("Le robot " + robot.getNom() + " éprouve un problème de fonctionnement.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(nouvelleNotification);
            }
        }
        return DoitEtreNotifie;
    }
    public boolean verifierBatterieRobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getBatterie() >= 20) {
                DoitEtreNotifie = true;
                Notification nouvelleNotification = new Notification("BATTERIE FAIBLE", "La batterie du robot " + robot.getNom() + " est à " + robot.getBatterie() + "%.", TypeNotification.PROBLEME_ROBOT);
                /*notification.setTitre("BATTERIE FAIBLE");
                notification.setMesssage("La batterie du robot " + robot.getNom() + " est à " + robot.getBatterie() + "%.");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(nouvelleNotification);
            }
        }
        return DoitEtreNotifie;
    }
    public boolean verifierCPURobot() {
        boolean DoitEtreNotifie = false;

        for (Robot robot : inventaireDeRobot) {
            if (robot.getCpu() >= 100) {
                DoitEtreNotifie = true;
                Notification nouvelleNotification = new Notification("SURCHARGE CPU", "Le CPU du robot " + robot.getNom() + " est surchagé", TypeNotification.PROBLEME_ROBOT);
                /*notification.setTitre("SURCHARGE CPU");
                notification.setMesssage("Le CPU du robot " + robot.getNom() + " est surchagé");
                notification.setTypeNotification(TypeNotification.PROBLEME_ROBOT);
                listeNotifications.add(nouvelleNotification);
            }
        }
        return DoitEtreNotifie;
    }

    public boolean verifierInventaireComposants() {
        boolean DoitEtreNotifie = false;

        if (inventaireComposant.size() > taillePrecedenteInventaireComposantes) {
            DoitEtreNotifie = true;
            Notification nouvelleNotification = new Notification("ACHAT D'UNE VOS COMPOSANTES", "Un utilisateur a acheté une de vos composantes", TypeNotification.ACHAT_COMPOSANTS);
            //notification.setTitre("ACHAT D'UNE VOS COMPOSANTES");
            //notification.setMesssage("Un utilisateur a acheté une de vos composantes");
            //notification.setTypeNotification(TypeNotification.ACHAT_COMPOSANTS);
            listeNotifications.add(nouvelleNotification);
        }
        return DoitEtreNotifie;
    }*/

    public  Composant produireCPU() {
        return new Composant("cpu", "50000", "", "CPU");
    }
    @JsonProperty("telephone")
    public String getNumeroTelephone() {
        return this.getTelephone();
    }

    @JsonProperty("listeNotifications")
    public ArrayList<Notification> getNotifs(){
        return this.listeNotifications;
    }

    public void addNotifs(String titre,String message, TypeNotification typeNotification){
        this.listeNotifications.add(new Notification(titre, message, typeNotification));
    }

    public void setListeNotifications(ArrayList<Notification> listeNotifications) {
        this.listeNotifications = listeNotifications;
    }

    public ArrayList<Notification> voirNotifications(){
        return getListeNotifications();
    }

}

