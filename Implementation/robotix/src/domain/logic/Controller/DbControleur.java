package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;

import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import service.BaseDeDonneeActivite;
import service.BaseDeDonneeFournisseur;
import service.BaseDeDonneeInteret;
import service.BaseDeDonneeUtilisateur;

import java.io.IOException;
import java.util.UUID;


public class DbControleur {
    private BaseDeDonneeFournisseur baseDeDonneeFournisseur;
    private BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;
    private BaseDeDonneeActivite baseDeDonneeActivite;
   private BaseDeDonneeInteret baseDeDonneeInteret;
    public DbControleur () throws IOException {
        this.baseDeDonneeFournisseur=new BaseDeDonneeFournisseur();
        this.baseDeDonneeUtilisateur=new BaseDeDonneeUtilisateur();
        this.baseDeDonneeActivite=new BaseDeDonneeActivite();
        this.baseDeDonneeInteret= new BaseDeDonneeInteret();
    }

    public String recupererListeUtilisateur(){
        return this.baseDeDonneeUtilisateur.recupererLalisteDesUtilisateur();
    }

     public String recupererListFournisseur(){
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
    public String recupererListeActivite(){
        return this.baseDeDonneeActivite.recupererLalisteDesActivite();
    }
    public boolean verifierNomFournissuer(String nomFourniseur){
        return this.baseDeDonneeFournisseur.verifierNomFounissseur(nomFourniseur);
    }

    public boolean verifierPseudo(String pseudo){
        return this.baseDeDonneeUtilisateur.verifierPseudo(pseudo);
    }

    public Robot retournerRobot(String numeroSerie){
        System.out.println(this.baseDeDonneeFournisseur.getListRobot());
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


    public String recupererListeInteret(){
       return this.baseDeDonneeInteret.recupererListeInteret();
    }

    public String recupererListeInteretUtilisateur(String pseudo){
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

    public String obtenirListeInteret()
    {
        return this.baseDeDonneeInteret.recupererListeInteret();
    }
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
}
