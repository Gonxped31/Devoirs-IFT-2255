package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;

import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import service.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Controlleur pour la gestion des bases de données.
 */
public class DbControleur {
    private static DbControleur dbControleur;
    private BaseDeDonneeComposantVendus baseDeDonneeComposantVendus = new BaseDeDonneeComposantVendus();
    private BaseDeDonneeRobotVendus baseDeDonneeRobotVendus;
    private BaseDeDonneeFournisseur baseDeDonneeFournisseur;
    private BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;
    private BaseDeDonneeActivite baseDeDonneeActivite;
    private BaseDeDonneeInteret baseDeDonneeInteret;

    /**
     * Constructeur de la classe DbControleur.
     *
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la création des bases de données.
     * @throws ParseException Si une erreur de parsing se produit lors de la création des bases de données.
     */
    private DbControleur() throws IOException, ParseException {
        this.baseDeDonneeFournisseur=BaseDeDonneeFournisseur.getBaseDeDonneeFournisseur();
        this.baseDeDonneeUtilisateur=BaseDeDonneeUtilisateur.getBaseDeUtilisateur();
        this.baseDeDonneeActivite=new BaseDeDonneeActivite();
        this.baseDeDonneeInteret= new BaseDeDonneeInteret();
        this.baseDeDonneeRobotVendus = new BaseDeDonneeRobotVendus();
    }

    /**
     * Méthode statique pour obtenir l'instance unique de DbControleur.
     *
     * @return L'instance unique de DbControleur.
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la création de DbControleur.
     * @throws ParseException Si une erreur de parsing se produit lors de la création de DbControleur.
     */
    public static DbControleur getDbControleur() throws IOException, ParseException {
        return dbControleur == null ? dbControleur = new DbControleur() : dbControleur;
    }

    /**
     * Récupère la liste des utilisateurs à partir de la base de données utilisateur.
     *
     * @return La liste des utilisateurs stockés dans la base de données.
     */
    public ArrayList<Utilisateur> recupererListeUtilisateur(){
        return this.baseDeDonneeUtilisateur.recupererLalisteDesUtilisateur();
    }

    /**
     * Récupère la liste des fournisseurs à partir de la base de données fournisseur.
     *
     * @return La liste des fournisseurs stockés dans la base de données.
     */
     public ArrayList<Fournisseur> recupererListFournisseur(){
        return this.baseDeDonneeFournisseur.recupererLalisteDesFournisseur();
     }

    /**
     * Recherche un utilisateur par son pseudo dans la base de données.
     *
     * @param pseudo Le pseudo de l'utilisateur à rechercher.
     * @return Les informations de l'utilisateur correspondant au pseudo recherché.
     */
     public String rechercherUtilisateurParPseudo(String pseudo){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParPseudo(pseudo);
     }

    /**
     * Recherche un utilisateur par son nom dans la base de données.
     *
     * @param nom Le nom de l'utilisateur à rechercher.
     * @return Les informations de l'utilisateur correspondant au nom recherché.
     */
    public String rechercherUtilisateurParNom(String nom){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParNom(nom);
    }

    /**
     * Recherche un utilisateur par son prénom dans la base de données.
     *
     * @param prenom Le prénom de l'utilisateur à rechercher.
     * @return Les informations de l'utilisateur correspondant au prénom recherché.
     */
    public String rechercherUtilisateurParPrenom(String prenom){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParPrenom(prenom);
    }

    /**
     * Recherche un utilisateur par son suiveur dans la base de données.
     *
     * @param pseudoUtilisateur Le pseudo de l'utilisateur à rechercher.
     * @return Les informations de l'utilisateur correspondant au suiveur recherché.
     */
    public String rechercherUtilisateurParSuiveur(String pseudoUtilisateur){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParSuiveur(pseudoUtilisateur);
    }

    /**
     * Filtrer la liste des suiveurs d'un utilisateur par pseudo.
     *
     * @param nomUtilisateur  Le nom de l'utilisateur pour lequel on souhaite filtrer les suiveurs.
     * @param pseudoSuiveur   Le pseudo du suiveur à rechercher.
     * @return Les informations du suiveur correspondant au pseudo recherché.
     */
    public String filtrerListeSuiveurParPseudo(String nomUtilisateur, String pseudoSuiveur) {
      return this.baseDeDonneeUtilisateur.filtrerListSuiveurParPseudo(nomUtilisateur, pseudoSuiveur);
    }

    /**
     * Recherche un composant par son nom, ou par une sous-chaîne de trois caractères de son nom.
     *
     * @param nomComposant Le nom du composant ou une sous-chaîne de trois caractères à rechercher.
     * @return Les informations du composant correspondant au nom ou à la sous-chaîne de trois caractères recherchée.
     */
    public String rechercherComposantParNomOuParNomOuTroisPremierSousChaine(String nomComposant){
        return  this.baseDeDonneeFournisseur.rechercherComposantParNomOuTroisSouschaine(nomComposant);
    }

    /**
     * Recherche un composant par son type dans la base de données.
     *
     * @param typeComposant Le type du composant à rechercher.
     * @return Les informations du composant correspondant au type recherché.
     */
    public String rechercherComposantParType(String typeComposant){
        return this.baseDeDonneeFournisseur.rechercherComposantParType(typeComposant);
    }

    /**
     * Recherche un composant par le nom du fournisseur dans la base de données.
     *
     * @param nomFourniseur Le nom du fournisseur dont on souhaite rechercher les composants.
     * @return Les informations du composant correspondant au nom du fournisseur recherché.
     */
    public String rechercherComposantParNomFournisseur(String nomFourniseur){
        return this.baseDeDonneeFournisseur.rechercherComposantParNomFournisseur(nomFourniseur);
    }

    /**
     * Recherche un fournisseur par son nom dans la base de données.
     *
     * @param nom Le nom du fournisseur à rechercher.
     * @return Les informations du fournisseur correspondant au nom recherché.
     */
    public String rechercherFournisseurParNom(String nom){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParNom(nom);
    }

    /**
     * Recherche un fournisseur par son adresse dans la base de données.
     *
     * @param adresse L'adresse du fournisseur à rechercher.
     * @return Les informations du fournisseur correspondant à l'adresse recherchée.
     */
    public String rechercherFournisseurParAdresse(String adresse){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParAdresse(adresse);
    }

    /**
     * Recherche un fournisseur par le type de composant qu'il fournit dans la base de données.
     *
     * @param typeDeComposant Le type de composant fourni par le fournisseur à rechercher.
     * @return Les informations du fournisseur correspondant au type de composant recherché.
     */
    public String rechercherFournisseurParTypeDeComposant(String typeDeComposant){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParTypeDeComposant(typeDeComposant);
    }

    /**
     * Ajoute une activité à la base de données des activités.
     *
     * @param activite L'activité à ajouter à la base de données.
     */
    public void  ajouterActivite(Activite activite){
        this.baseDeDonneeActivite.ajouterObjet(activite);
    }

    /**
     * Supprime une activité de la base de données des activités.
     *
     * @param activite L'activité à supprimer de la base de données.
     */
    public void supprimerActivite(Activite activite){
        this.baseDeDonneeActivite.supprimerObjet(activite);
    }

    /**
     * Retourne une activité à partir de son nom.
     *
     * @param nomActivite Le nom de l'activité à rechercher.
     * @return L'activité correspondant au nom recherché.
     */
    public Activite retournerActivite(String nomActivite){
        return baseDeDonneeActivite.retournerActivite(nomActivite);
    }

    /**
     * Récupère la liste des activités à partir de la base de données.
     *
     * @return La liste des activités stockées dans la base de données.
     */
    public ArrayList<Activite> recupererListeActivite(){
        return this.baseDeDonneeActivite.recupererLalisteDesActivite();
    }

    /**
     * Vérifie si le nom d'un fournisseur existe dans la base de données des fournisseurs.
     *
     * @param nomFourniseur Le nom du fournisseur à vérifier.
     * @return Vrai si le nom du fournisseur existe dans la base de données, sinon faux.
     */
    public boolean verifierNomFournissuer(String nomFourniseur){
        return this.baseDeDonneeFournisseur.verifierNomFounissseur(nomFourniseur);
    }

    /**
     * Achète un composant auprès du fournisseur spécifié et effectue les mises à jour nécessaires dans les bases de données.
     *
     * @param nomFournisseur Le nom du fournisseur auprès duquel acheter le composant.
     * @param nomComposante  Le nom du composant à acheter.
     * @return Le composant acheté ou null s'il n'a pas été trouvé dans la base de données.
     */
    public Composant acheterComposant(String nomFournisseur, String nomComposante) {

        Composant composante = this.baseDeDonneeFournisseur.achatComposante(nomFournisseur,nomComposante);
        if( composante!=null) {
            Fournisseur f =this.baseDeDonneeFournisseur.retournerFournisseur(nomFournisseur);
            Composant c= this.baseDeDonneeFournisseur.retournerComposante(nomComposante);
            this.baseDeDonneeComposantVendus.ajouterObjet(c);
            this.supprimerFournisseur(f);
            f.retirerComopsante(nomComposante);
            this.ajouterFournisseur(f);
        }
        return composante;
    }

    /**
     * Vérifie si un pseudo d'utilisateur existe dans la base de données des utilisateurs.
     *
     * @param pseudo Le pseudo de l'utilisateur à vérifier.
     * @return Vrai si le pseudo de l'utilisateur existe dans la base de données, sinon faux.
     */
    public boolean verifierPseudo(String pseudo){
        return this.baseDeDonneeUtilisateur.verifierPseudo(pseudo);
    }

    /**
     * Retourne un robot à partir de son numéro de série.
     *
     * @param numeroSerie Le numéro de série du robot à rechercher.
     * @return Le robot correspondant au numéro de série recherché ou null s'il n'est pas trouvé.
     */
    public Robot retournerRobot(String numeroSerie){
        return this.baseDeDonneeFournisseur.retournerRobot(numeroSerie);
    }

    /**
     * Retourne un composant à partir de son nom.
     *
     * @param nom Le nom du composant à rechercher.
     * @return Le composant correspondant au nom recherché ou null s'il n'est pas trouvé.
     */
    public Composant retournerComposante(String nom){
        return baseDeDonneeFournisseur.retournerComposante(nom);
    }

    /**
     * Retourne un utilisateur à partir de son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant au pseudo recherché ou null s'il n'est pas trouvé.
     */
    public Utilisateur retournerUtilisateur(String pseudo){
        return baseDeDonneeUtilisateur.retournerUtilisateur(pseudo);
    }

    /**
     * Retourne un fournisseur à partir de son nom.
     *
     * @param nom Le nom du fournisseur à rechercher.
     * @return Le fournisseur correspondant au nom recherché ou null s'il n'est pas trouvé.
     */
    public Fournisseur retournerFournisseur(String nom){
        return baseDeDonneeFournisseur.retournerFournisseur(nom);
    }

    /**
     * Supprime un fournisseur de la base de données des fournisseurs.
     *
     * @param f Le fournisseur à supprimer de la base de données.
     */
    public void supprimerFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.supprimerObjet(f);
    }

    /**
     * Ajoute un fournisseur à la base de données des fournisseurs.
     *
     * @param f Le fournisseur à ajouter à la base de données.
     */
    public void ajouterFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.ajouterObjet(f);
    }

    /**
     * Supprime un utilisateur de la base de données des utilisateurs.
     *
     * @param u L'utilisateur à supprimer de la base de données.
     */
    public void supprimerUtilisateur(Utilisateur u){
        this.baseDeDonneeUtilisateur.supprimerObjet(u);
    }

    /**
     * Ajoute un utilisateur à la base de données des utilisateurs.
     *
     * @param u L'utilisateur à ajouter à la base de données.
     */
    public void ajouterUtilisateur(Utilisateur u){
        this.baseDeDonneeUtilisateur.ajouterObjet(u);
    }

    /**
     * Récupère la liste des intérêts à partir de la base de données.
     *
     * @return La liste des intérêts stockés dans la base de données.
     */
    public ArrayList<Interet> recupererListeInteret(){
       return baseDeDonneeInteret.recupererListeInteret();
    }

    /**
     * Récupère la liste des intérêts d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel récupérer la liste des intérêts.
     * @return La liste des intérêts de l'utilisateur ou une chaîne vide s'il n'a aucun intérêt.
     */
    public String recupererListeInteretUtilisateur(String pseudo){
         return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateur(pseudo);
    }

    /**
     * Récupère la liste des intérêts d'un utilisateur par filtrage sur une sous-chaîne de trois caractères.
     *
     * @param pseudo      Le pseudo de l'utilisateur pour lequel récupérer la liste des intérêts.
     * @param troislettre La sous-chaîne de trois caractères à filtrer.
     * @return La liste des intérêts de l'utilisateur filtrée par la sous-chaîne de trois caractères.
     */
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre) {
        return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(pseudo,troislettre);
    }

    /**
     * Récupère la liste des intérêts par filtrage sur une sous-chaîne de trois caractères.
     *
     * @param troislettre La sous-chaîne de trois caractères à filtrer.
     * @return La liste des intérêts filtrée par la sous-chaîne de trois caractères.
     */
    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return this.baseDeDonneeUtilisateur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre);
    }

    /**
     * Recherche un fournisseur par son adresse e-mail dans la base de données.
     *
     * @param email L'adresse e-mail du fournisseur à rechercher.
     * @return Les informations du fournisseur correspondant à l'adresse e-mail recherchée.
     */
    public String rechercherFournisseurParEmail(String email) {
        return this.baseDeDonneeFournisseur.rechercherFournisseurParEmail(email);
    }

    /**
     * Recherche un composant par son nom dans la base de données des fournisseurs.
     *
     * @param nom Le nom du composant à rechercher.
     * @return Les informations du composant correspondant au nom recherché.
     */
    public String rechercherComposantParNom( String nom){
        return this.baseDeDonneeFournisseur.rehercherComposantParNom(nom);
    }

    /**
     * Authentifie un fournisseur en vérifiant son nom et son mot de passe dans la base de données des fournisseurs.
     *
     * @param nomFournisseur Le nom du fournisseur à authentifier.
     * @param mdp Le mot de passe du fournisseur à authentifier.
     * @return Le fournisseur authentifié ou null s'il n'est pas trouvé ou si l'authentification échoue.
     */
    public Fournisseur authentificatiFournisseur(String nomFournisseur, String mdp){
       return this.baseDeDonneeFournisseur.authentificatiFournisseur(nomFournisseur, mdp);
    }

    /**
     * Authentifie un utilisateur en vérifiant son nom et son mot de passe dans la base de données des utilisateurs.
     *
     * @param nomUtilisateur Le nom de l'utilisateur à authentifier.
     * @param mdp Le mot de passe de l'utilisateur à authentifier.
     * @return L'utilisateur authentifié ou null s'il n'est pas trouvé ou si l'authentification échoue.
     */
    public Utilisateur authentificatiUtilisateur(String nomUtilisateur, String mdp){
        return this.baseDeDonneeUtilisateur.authentificatiUtilisateur(nomUtilisateur, mdp);
    }

    /**
     * Obtient la liste des robots d'un fournisseur spécifié par son nom.
     *
     * @param nomFournisseur Le nom du fournisseur pour lequel récupérer la liste des robots.
     * @return La liste des robots du fournisseur ou une chaîne vide s'il n'en possède aucun.
     */
    public String obtenirListRobotFournisseur( String nomFournisseur) {
        return this.baseDeDonneeFournisseur.obtenirListRobotFournisseur(nomFournisseur);
    }

    /**
     * Achète un robot auprès du fournisseur spécifié en utilisant son numéro et effectue les mises à jour nécessaires dans les bases de données.
     *
     * @param nomFournisseur Le nom du fournisseur auprès duquel acheter le robot.
     * @param numero Le numéro du robot à acheter.
     * @return L'UUID du robot acheté ou null s'il n'est pas trouvé dans la base de données des fournisseurs.
     */
    public UUID acheterRobot(String nomFournisseur, int numero){

        UUID uuid =this.baseDeDonneeFournisseur.acheterRobot(nomFournisseur,numero);
        if(uuid!=null) {
            Fournisseur f= this.baseDeDonneeFournisseur.retournerFournisseur(nomFournisseur);
            Robot r = this.retournerRobot(uuid.toString());
            this.baseDeDonneeRobotVendus.ajouterObjet(r);
            this.supprimerFournisseur(f);
            f.retirerRobot(uuid.toString());
            this.ajouterFournisseur(f);
        }
        return uuid;
    }

    /**
     * Souscrit à un intérêt en recherchant l'intérêt par son nom dans la base de données.
     *
     * @param nomInteret Le nom de l'intérêt à souscrire.
     * @return L'objet Interet correspondant au nom recherché ou null s'il n'est pas trouvé.
     */
    public Interet souscrireAunInteret(String nomInteret){
        return this.baseDeDonneeInteret.retournerInteret(nomInteret);
    }

    /**
     * Ajoute un nouvel intérêt à la base de données des intérêts.
     *
     * @param interet Le nom du nouvel intérêt à ajouter.
     */
    public void ajouterInteret(String interet) {
        baseDeDonneeInteret.ajouterObjet(new Interet(interet));
    }

    /**
     * Extrait les intérêts d'un utilisateur en utilisant le nom de l'intérêt à extraire.
     *
     * @param interet Le nom de l'intérêt à extraire.
     * @return Vrai si l'extraction réussit, sinon faux.
     */
    public boolean extraireInterets(String interet) {
        return baseDeDonneeUtilisateur.extractInterests(interet);
    }

    /**
     * Supprime un intérêt de la base de données des intérêts en recherchant l'intérêt par son nom.
     *
     * @param interet Le nom de l'intérêt à supprimer.
     */
    public void supprimerInteret(String interet) {
        System.out.println(baseDeDonneeInteret.retournerInteret(interet));
        baseDeDonneeInteret.supprimerObjet(baseDeDonneeInteret.retournerInteret(interet));
    }

    /**
     * Retourne la liste des intérêts d'un utilisateur spécifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur pour lequel retourner la liste des intérêts.
     * @return Un StringBuilder contenant la liste des intérêts de l'utilisateur ou un message si l'utilisateur n'a aucun intérêt.
     */
    public StringBuilder retournerListeInteretsUtilisateur(String pseudo) {
        return baseDeDonneeUtilisateur.retournerInteretsUtilisateur(pseudo);
    }

    /**
     * Modifie un intérêt existant en supprimant l'intérêt courant et en ajoutant le nouvel intérêt spécifié.
     *
     * @param interetCourant L'intérêt à modifier.
     * @param nouvelInteret  Le nouvel intérêt à ajouter.
     */
    public void modifierInteret(String interetCourant, String nouvelInteret) {
        baseDeDonneeInteret.supprimerObjet(baseDeDonneeInteret.retournerInteret(interetCourant));
        baseDeDonneeInteret.ajouterObjet(new Interet(nouvelInteret));
    }

    /**
     * Vérifie si un utilisateur suit déjà un autre utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @param nom    Le nom de l'utilisateur suivi.
     * @return Vrai si l'utilisateur suit déjà l'autre utilisateur, sinon faux.
     */
    public boolean existeDansListeSuivi(String pseudo, String nom) {
        return baseDeDonneeUtilisateur.existeDansListeSuivi(pseudo, nom);
    }

    /**
     * Récupère le robot actuellement vendu à partir de son numéro de série.
     *
     * @param numeroSerie Le numéro de série du robot à rechercher.
     * @return Le robot actuellement vendu correspondant au numéro de série recherché ou null s'il n'est pas trouvé.
     */
    public Robot getCurentSoldRobot(String numeroSerie) {
        return this.baseDeDonneeRobotVendus.getCurrentSoldRobot(numeroSerie);
    }
}
