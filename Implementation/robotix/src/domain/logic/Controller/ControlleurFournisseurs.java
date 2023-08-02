package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Notification;
import domain.logic.Membre.TypeNotification;
import domain.logic.Robot.Composant;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Contrôleur pour la gestion des fournisseurs.
 */
public class ControlleurFournisseurs {
    // Référence au contrôleur de la base de données
    private final DbControleur dataBaseController = DbControleur.getDbControleur();

    // Fournisseur actuellement connecté
    private Fournisseur fournisseurCourant;

    /**
     * Constructeur pour la création d'un nouveau fournisseur.
     *
     * @param nom                Le nom du fournisseur.
     * @param mdp                Le mot de passe du fournisseur.
     * @param adresse            L'adresse du fournisseur.
     * @param email              L'adresse email du fournisseur.
     * @param numeroTelephone    Le numéro de téléphone du fournisseur.
     * @param typeDeRobotFabriquer  Le type de robot à fabriquer par le fournisseur.
     * @param typeComposantesFabriquer  Le type de composantes à fabriquer par le fournisseur.
     * @param capacite           La capacité du fournisseur.
     * @param nomcompagnie       Le nom de la compagnie du fournisseur.
     * @throws IOException       En cas d'erreur d'entrée/sortie lors de l'accès à la base de données.
     * @throws ParseException    En cas d'erreur de parsing de date lors de l'accès à la base de données.
     */
    public ControlleurFournisseurs(String nom, String mdp, String adresse, String email, String numeroTelephone,
                                   String typeDeRobotFabriquer, String typeComposantesFabriquer, String capacite, String nomcompagnie) throws IOException, ParseException {
        this.fournisseurCourant = new Fournisseur(nom, mdp, adresse, email, numeroTelephone, typeDeRobotFabriquer, typeComposantesFabriquer, capacite, nomcompagnie);
    }

    /**
     * Constructeur par défaut.
     *
     * @throws IOException      En cas d'erreur d'entrée/sortie lors de l'accès à la base de données.
     * @throws ParseException   En cas d'erreur de parsing de date lors de l'accès à la base de données.
     */
    public ControlleurFournisseurs() throws IOException, ParseException {
    }

    /**
     * Méthode pour l'authentification d'un fournisseur.
     *
     * @param nomFournisseur Le nom du fournisseur à authentifier.
     * @param mdp Le mot de passe du fournisseur à authentifier.
     * @return {@code true} si l'authentification réussit, {@code false} sinon.
     */
    public boolean authentificationFournisseur(String nomFournisseur, String mdp) {
        Fournisseur f = this.dataBaseController.authentificatiFournisseur(nomFournisseur, mdp);
        this.fournisseurCourant = f;
        return f != null;
    }

    /**
     * Méthode pour inscrire un nouveau fournisseur.
     *
     * @param inputNom Le nom du fournisseur à inscrire.
     * @param mdp Le mot de passe du fournisseur à inscrire.
     * @param inputAdresse L'adresse du fournisseur à inscrire.
     * @param inputCourriel L'adresse email du fournisseur à inscrire.
     * @param inputTelephone Le numéro de téléphone du fournisseur à inscrire.
     * @param inputTypeRobot Le type de robot à fabriquer par le fournisseur.
     * @param inputTypeComposantes Le type de composantes à fabriquer par le fournisseur.
     * @param inputCapacite La capacité du fournisseur.
     * @param inputCompagnie Le nom de la compagnie du fournisseur.
     */
    public void inscriptionFournisseur(String inputNom, String mdp, String inputAdresse, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                       String inputTypeComposantes, String inputCapacite, String inputCompagnie){

        dataBaseController.ajouterFournisseur(new Fournisseur(inputNom,mdp, inputAdresse, inputCourriel,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie));
    }

    /**
     * Vérifie si le nom du fournisseur est valide en vérifiant s'il est unique dans la base de données.
     *
     * @param inputNom Le nom du fournisseur à vérifier.
     * @return {@code true} si le nom est valide (unique), {@code false} sinon.
     */
    public boolean verifierNom(String inputNom) {
        return dataBaseController.verifierNomFournissuer(inputNom);
    }


    public UUID ajouterRobot(String typeRobot, String nomFournisseur){
        Fournisseur f = dataBaseController.retournerFournisseur(nomFournisseur);
        this.dataBaseController.supprimerFournisseur(f);
        ArrayList<Composant> listComp = new ArrayList<>();
        listComp.add(f.produireCPU());
        UUID uuid = f.ajouterRobot(listComp, typeRobot);
        this.dataBaseController.ajouterFournisseur(f);
        return uuid;
    }

    /**
     * Méthode pour retirer un robot du fournisseur actuel.
     *
     * @param numeroSerie Le numéro de série du robot à retirer.
     * @return {@code true} si le robot a été retiré avec succès, {@code false} sinon.
     */
    public boolean retirerRobot(String numeroSerie, String nomFournisseur) {
        Fournisseur f = dataBaseController.retournerFournisseur(nomFournisseur);
        this.dataBaseController.supprimerFournisseur(f);
        boolean b = f.retirerRobot(numeroSerie);
        this.dataBaseController.ajouterFournisseur(f);
        return b;
    }

    /**
     * Méthode pour ajouter une nouvelle composante au fournisseur actuel.
     *
     * @param nom Le nom de la composante à ajouter.
     * @param prix Le prix de la composante à ajouter.
     * @param description La description de la composante à ajouter.
     * @param typesComposants Le type de composants de la composante à ajouter.
     */
    public void ajouterComposante(String nom, String prix, String description, String typesComposants){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        this.fournisseurCourant.ajouterComposante(nom, prix,description,typesComposants);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
    }

    /**
     * Méthode pour retirer une composante du fournisseur actuel.
     *
     * @param composante Le nom de la composante à retirer.
     * @return {@code true} si la composante a été retirée avec succès, {@code false} sinon.
     */
    public boolean retirerComposante(String composante){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean c = this.fournisseurCourant.retirerComopsante(composante);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return c;
    }

    /**
     * Méthode pour modifier le prix d'une composante du fournisseur actuel.
     *
     * @param nomComposante Le nom de la composante dont on veut modifier le prix.
     * @param prix Le nouveau prix de la composante.
     * @return {@code true} si la modification a réussi, {@code false} sinon.
     */
    public boolean modifierPrixComposante(String nomComposante, String prix){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean bool = this.fournisseurCourant.modifierPrixComposante(nomComposante, prix);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return bool;
    }

    /**
     * Méthode pour modifier la description d'une composante du fournisseur actuel.
     *
     * @param nomComposante Le nom de la composante dont on veut modifier la description.
     * @param description La nouvelle description de la composante.
     * @return {@code true} si la modification a réussi, {@code false} sinon.
     */
    public boolean modifierDescriptionComposante(String nomComposante, String description){
        this.dataBaseController.supprimerFournisseur(fournisseurCourant);
        boolean bool = this.fournisseurCourant.modifierDescriptionComposante(nomComposante, description);
        this.dataBaseController.ajouterFournisseur(fournisseurCourant);
        return bool;
    }

    /**
     * Méthode pour modifier le profil du fournisseur actuel.
     *
     * @param choix Le choix de modification à effectuer (par exemple, "nom", "mdp", "adresse", etc.).
     * @param info Les informations à mettre à jour pour le choix spécifié.
     * @param nomFournisseur Le nom du fournisseur chez qui la modification sera effectuee.
     */
    public void modifierProfile(String choix, String info, String nomFournisseur){
        Fournisseur f = dataBaseController.retournerFournisseur(nomFournisseur);
        this.dataBaseController.supprimerFournisseur(f);
        f.modifierProfile(choix, info);
        this.dataBaseController.ajouterFournisseur(f);
    }

    /**
     * Méthode pour afficher le profil du fournisseur actuel.
     *
     * @return Le profil du fournisseur sous forme de chaîne de caractères.
     */
    public String voirProfil() {
        return fournisseurCourant.getProfilFournisseur();
    }

    /**
     * Méthode pour ajouter une nouvelle notification au fournisseur spécifié.
     *
     * @param nom Le nom du fournisseur auquel ajouter la notification.
     * @param titre Le titre de la notification.
     * @param message Le message de la notification.
     * @param typeNotif Le type de notification (par exemple, TypeNotification.INFO, TypeNotification.ALERT, etc.).
     */
    public void ajouterNotifs(String nom, String titre, String message,  TypeNotification typeNotif){
        Fournisseur f = dataBaseController.retournerFournisseur(nom);
        ArrayList<Notification> notifsCourantes = f.getNotifs();
        this.dataBaseController.supprimerFournisseur(f);
        f.addNotifs(titre, message, typeNotif);
        f.setListeNotifications(notifsCourantes);
        this.dataBaseController.ajouterFournisseur(f);
    }

    /**
     * Méthode pour voir les notifications du fournisseur spécifié.
     *
     * @param nom Le nom du fournisseur dont on veut voir les notifications.
     * @return Une liste d'objets Notification contenant les notifications du fournisseur.
     */
    public ArrayList<Notification> voirNotifications(String nom){
        Fournisseur f = dataBaseController.retournerFournisseur(nom);
        return f.voirNotifications();
    }

    /**
     * Méthode pour supprimer toutes les notifications du fournisseur spécifié.
     *
     * @param nom Le nom du fournisseur dont on veut supprimer les notifications.
     */
    public void supprimerNotifs(String nom) {
        Fournisseur f = dataBaseController.retournerFournisseur(nom);
        dataBaseController.supprimerFournisseur(f);
        f.getNotifs().clear();
        dataBaseController.ajouterFournisseur(f);
    }
}
