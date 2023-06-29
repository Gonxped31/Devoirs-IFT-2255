package domain.logic.Controller;

import domain.logic.Membre.Utilisateur;
import service.BaseDeDonneeActivite;
import service.BaseDeDonneeFournisseur;
import service.BaseDeDonneeUtilisateur;

import java.io.IOException;
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

    public String rechercherUtilisateurParSuiveur(String nomUtilisateur){
        return  this.baseDeDonneeUtilisateur.rechercherUtilisateurParSuiveur(nomUtilisateur);
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


    public String recupererListeInteret(){
       return this.baseDeDonneeUtilisateur.recupererListeInteret();
    }

    public String recupererListeInteretUtilisateur(String nom){
         return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateur(nom);
    }
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String nomUtilisateur, String troislettre)
    {
        return this.baseDeDonneeUtilisateur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(nomUtilisateur,troislettre);
    }

    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return this.baseDeDonneeUtilisateur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre);
    }

}
