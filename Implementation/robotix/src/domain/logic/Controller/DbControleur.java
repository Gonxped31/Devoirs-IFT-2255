package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;

import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import service.BaseDeDonneeActivite;
import service.BaseDeDonneeFournisseur;
import service.BaseDeDonneeInteret;
import service.BaseDeDonneeUtilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;


public class DbControleur {
    private static DbControleur dbControleur;
    private BaseDeDonneeFournisseur baseDeDonneeFournisseur;
    private BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;
    private BaseDeDonneeActivite baseDeDonneeActivite;
   private BaseDeDonneeInteret baseDeDonneeInteret;
    public DbControleur() throws IOException, ParseException {
        this.baseDeDonneeFournisseur=BaseDeDonneeFournisseur.getBaseDeDonneeFournisseur();
        this.baseDeDonneeUtilisateur=BaseDeDonneeUtilisateur.getBaseDeUtilisateur();
        this.baseDeDonneeActivite=new BaseDeDonneeActivite();
        this.baseDeDonneeInteret= new BaseDeDonneeInteret();
    }

    public static DbControleur getDbControleur() throws IOException, ParseException {
        return dbControleur == null ? dbControleur = new DbControleur() : dbControleur;
    }

    public String recupererListeUtilisateur(){
        return this.baseDeDonneeUtilisateur.recupererLalisteDesUtilisateur();
    }

     public ArrayList<Fournisseur> recupererListFournisseur(){
        return this.baseDeDonneeFournisseur.recupererLalisteDesFournisseur();
     }

     public String rechercherUtilisateurParPseudo(String pseudo){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParPseudo(pseudo);
     }

    public String rechercherUtilisateurParNom(String nom){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParNom(nom);
    }

    public String rechercherUtilisateurParPrenom(String prenom){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParPrenom(prenom);
    }

    public String rechercherUtilisateurParSuiveur(String pseudoUtilisateur){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParSuiveur(pseudoUtilisateur);
    }

    public String filtrerListeSuiveurParPseudo(String nomUtilisateur, String pseudoSuiveur)
    {
      return this.baseDeDonneeUtilisateur.filtrerListSuiveurParPseudo(nomUtilisateur, pseudoSuiveur);
    }

    public String rechercherComposantParNomOuParNomOuTroisPremierSousChaine(String nomComposant){
        return  this.baseDeDonneeFournisseur.rechercherComposantParNomOuTroisSouschaine(nomComposant);
    }

    public String rechercherComposantParType(String typeComposant){
        return this.baseDeDonneeFournisseur.rechercherComposantParType(typeComposant);
    }
    public String rechercherComposantParNomFournisseur(String nomFourniseur){
        return this.baseDeDonneeFournisseur.rechercherComposantParNomFournisseur(nomFourniseur);
    }

   public String rechercherFournisseurParNom(String nom){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParNom(nom);
   }

    public String rechercherFournisseurParAdresse(String adresse){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParAdresse(adresse);
    }
    public String rechercherFournisseurParTypeDeComposant(String typeDeComposant){
        return this.baseDeDonneeFournisseur.rechercherFournisseurParTypeDeComposant(typeDeComposant);
    }
    public void  ajouterActivite(Activite activite){
        this.baseDeDonneeActivite.ajouterObjet(activite);
    }
    public void supprimerActivite(Activite activite){
        this.baseDeDonneeActivite.supprimerObjet(activite);
    }
    public Activite retournerActivite(String nomActivite){
        return baseDeDonneeActivite.retournerActivite(nomActivite);
    }
    public ArrayList<Activite> recupererListeActivite(){
        return this.baseDeDonneeActivite.recupererLalisteDesActivite();
    }
    public boolean verifierNomFournissuer(String nomFourniseur){
        return this.baseDeDonneeFournisseur.verifierNomFounissseur(nomFourniseur);
    }

    public Composant achatComposante(String nomFournisseur, String nomComposante){
        return this.baseDeDonneeFournisseur.achatComposante(nomFournisseur, nomComposante);
    }

    public boolean verifierPseudo(String pseudo){
        return this.baseDeDonneeUtilisateur.verifierPseudo(pseudo);
    }

    public Robot retournerRobot(String numeroSerie){
        return this.baseDeDonneeFournisseur.retournerRobot(numeroSerie);
    }

    public Composant retournerComposante(String nom){
        return baseDeDonneeFournisseur.retournerComposante(nom);
    }

    public Utilisateur retournerUtilisateur(String pseudo){
        return baseDeDonneeUtilisateur.retournerUtilisateur(pseudo);
    }

    public Fournisseur retournerFournisseur(String nom){
        return baseDeDonneeFournisseur.retournerFournisseur(nom);
    }

    public void supprimerFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.supprimerObjet(f);
    }
    public void ajouterFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.ajouterObjet(f);
    }
    public void supprimerUtilisateur(Utilisateur u){
        this.baseDeDonneeUtilisateur.supprimerObjet(u);
    }
    public void ajouterUtilisateur(Utilisateur u){
        this.baseDeDonneeUtilisateur.ajouterObjet(u);
    }


    public HashSet<Interet> recupererListeInteret(){
       return baseDeDonneeInteret.recupererListeInteret();
    }

    public HashSet<Interet> recupererListeInteretUtilisateur(String pseudo){
         return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateur(pseudo);
    }
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre)
    {
        return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(pseudo,troislettre);
    }

    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return this.baseDeDonneeUtilisateur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre);
    }

    public String rechercherFournisseurParEmail(String email) {
        return this.baseDeDonneeFournisseur.rechercherFournisseurParEmail(email);
    }

    public String rechercherComposantParNom( String nom){
        return this.baseDeDonneeFournisseur.rehercherComposantParNom(nom);
    }
    public Fournisseur authentificatiFournisseur(String nomFournisseur, String mdp){
       return this.baseDeDonneeFournisseur.authentificatiFournisseur(nomFournisseur, mdp);
    }

    public Utilisateur authentificatiUtilisateur(String nomUtilisateur, String mdp){
        return this.baseDeDonneeUtilisateur.authentificatiUtilisateur(nomUtilisateur, mdp);
    }

    public String obtenirListRobotFournisseur( String nomFournisseur)
    {
        return this.baseDeDonneeFournisseur.obtenirListRobotFournisseur(nomFournisseur);
    }
    public UUID acheterRobot(String nomFournisseur, int numero){
        return this.baseDeDonneeFournisseur.acheterRobot(nomFournisseur,numero);
    }

   /* public String obtenirListeInteret()
    {
        return this.baseDeDonneeInteret.recupererListeInteret();
    }*/
    public Interet souscrireAunInteret(String nomInteret)
    {
        return this.baseDeDonneeInteret.retournerInteret(nomInteret);
    }

    public void ajouterInteret(String interet) {
        baseDeDonneeInteret.ajouterObjet(new Interet(interet));
    }

    public boolean extraireInterets(String interet) {
        return baseDeDonneeUtilisateur.extractInterests(interet);
    }

    public void supprimerInteret(String interet) {
        System.out.println(baseDeDonneeInteret.retournerInteret(interet));
        baseDeDonneeInteret.supprimerObjet(baseDeDonneeInteret.retournerInteret(interet));
    }

    public StringBuilder retournerListeInteretsUtilisateur(String pseudo) {
        return baseDeDonneeUtilisateur.retournerInteretsUtilisateur(pseudo);
    }

    public void modifierInteret(String interetCourant, String nouvelInteret) {
        baseDeDonneeInteret.supprimerObjet(baseDeDonneeInteret.retournerInteret(interetCourant));
        baseDeDonneeInteret.ajouterObjet(new Interet(nouvelInteret));
    }

    public boolean existeDansListeSuivi(String pseudo, String nom) {
        return baseDeDonneeUtilisateur.existeDansListeSuivi(pseudo, nom);
    }
}
