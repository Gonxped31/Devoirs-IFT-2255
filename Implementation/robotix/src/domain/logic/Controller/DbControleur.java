package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;

import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import service.BaseDeDonneeActivite;
import service.BaseDeDonneeFournisseur;
import service.BaseDeDonneeUtilisateur;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DbControleur {
    private BaseDeDonneeFournisseur baseDeDonneeFournisseur;
    private BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;
    private BaseDeDonneeActivite baseDeDonneeActivite;

    public DbControleur () throws IOException {
        this.baseDeDonneeFournisseur=new BaseDeDonneeFournisseur();
        this.baseDeDonneeUtilisateur=new BaseDeDonneeUtilisateur();
        this.baseDeDonneeActivite=new BaseDeDonneeActivite();
    }

    /* Section Utilisateur */
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
    public String recupererListeActivite(){
        return this.baseDeDonneeActivite.recupererLalisteDesActivite();
    }
    public boolean verifierPseudo(String pseudo){
        return this.baseDeDonneeUtilisateur.verifierPseudo(pseudo);
    }
    public Robot retournerRobot(String numeroSerie){
        return baseDeDonneeFournisseur.retournerRobot(numeroSerie);
    }
    public Composant retournerComposante(String nom){
        return baseDeDonneeFournisseur.retournerComposante(nom);
    }

    public Utilisateur retournerUtilisateur(String pseudo){
        return baseDeDonneeUtilisateur.retournerUtilisateur(pseudo);
    }

    public void supprimerFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.supprimerObjet(f);
    }

    public Fournisseur retournerFournisseurParNom(String nom){
        return this.baseDeDonneeFournisseur.retournerFournisseur(nom);
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
       return this.baseDeDonneeUtilisateur.recupererListeInteret();
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


    /* Contrôles fournisseur */
    public boolean verifierNomFournissuer(String nomFourniseur){
        return this.baseDeDonneeFournisseur.verifierNomFounissseur(nomFourniseur);
    }
    public String rechercherFournisseurParEmail(String email) {
        return this.baseDeDonneeFournisseur.rechercherFournisseurParEmail(email);
    }
    public void supprimerFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.supprimerObjet(f);
    }
    public void ajouterFournisseur(Fournisseur f) {
        this.baseDeDonneeFournisseur.ajouterObjet(f);
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

}
