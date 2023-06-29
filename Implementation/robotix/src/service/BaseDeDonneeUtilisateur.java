package service;

import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Robot;
import service.BaseDeDonneeFournisseur;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class BaseDeDonneeUtilisateur extends BaseDeDonneeCommun {
     private static final String FILE_NAME = "utilisateur.json";


     public BaseDeDonneeUtilisateur() throws IOException {
        super(FILE_NAME);
      
     }


    @Override
    protected Type getType() {
        return new TypeToken<ArrayList<Utilisateur>>(){}.getType();
    }

    @Override
    protected void init() {
        //Todo
        List<Utilisateur> tempList= new ArrayList<>(Arrays.asList(
                new Utilisateur("Boubacar", "Kelly", "adresse1", "KellyB",
                        "BoubaCar", "emailboubacar@gmail.com", "5141111111", "Kelly Inc.", new ArrayList<Interet>()),
                new Utilisateur("Damov", "Kamen", "adresse2", "KD",
                        "KaMen", "emailkamen@gmail.com", "5142222222", "Kamen Inc.", new ArrayList<Interet>()),
                new Utilisateur("Bio", "Samir", "adresse3", "SB",
                        "SaMir", "emailsamir@gmail.com", "5143333333", "Samir Inc.", new ArrayList<Interet>())
                /*new Fournisseur("Adams", "2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5", "adams3",
                        "service@innovatech.ca", "4509998888", "RobotC", "ECRAN","27", "Innovatech"),
                new Fournisseur("Wilson", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "wilson4",
                        "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA","35", "iRobot"),
                new Fournisseur("Thompson", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "thompson5",
                        "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR","22", "RoboPro")*/
        ));
        tempList.stream().forEach(fournisseur -> {
            this.ajouterObjet(fournisseur);
        });
    }
    public String recupererLalisteDesUtilisateur()
    {
        return (String) this.getListObjet().stream()
                .map(user ->((Utilisateur) user).getProfilUtilisateur())
                .collect(Collectors.joining("\n"));
    }

    public String rechercherUtilisateurParPseudo(String pseudo){
         return (String) this.getListObjet().stream()
                 .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                 .map(u-> {
                     return ((Utilisateur) u).getProfilUtilisateur();
                 })
                 .findFirst()
                 .orElse("Utilisateur non trouver, veuillez verifier le pseudo");
    }

    public String rechercherUtilisateurParNom(String nom){
        return (String) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getNom().equals(nom))
                .map(u-> {
                    return ((Utilisateur) u).getProfilUtilisateur();
                })
                .findFirst()
                .orElse("Utilisateur non trouver, veuillez verifier le nom");
    }

    public String rechercherUtilisateurParPrenom(String prenom){
        return (String) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPrenom().equals(prenom))
                .map(u-> {
                    return ((Utilisateur) u).getProfilUtilisateur();
                })
                .findFirst()
                .orElse("Utilisateur non trouver, veuillez verifier le prenom");
    }

    public String rechercherUtilisateurParSuiveur(String pseudoUtilisateur){

         return (String) this.getListObjet().stream()
                 .filter(u ->( (Utilisateur) u).getPseudo().equals(pseudoUtilisateur))
                 .map(u-> { return ((Utilisateur)u)
                         .getListSuiveur()
                         .stream()
                         .map(suiveur -> {return ((Utilisateur) suiveur).getProfilUtilisateur(); })
                         .collect(Collectors.joining("\n"));
      }).collect(Collectors.joining("\n"));


   }

    public String filtrerListSuiveurParPseudo(String nomUtilisateur, String pseudoSuiveur){
        return (String) this.getListObjet().stream()
                .filter(u -> ((Utilisateur) u).getNom().equals(nomUtilisateur))
                .flatMap(u -> ((Utilisateur)u).getListSuiveur().stream())
                .filter(s -> ((Utilisateur) s).getPseudo().equals(pseudoSuiveur))
                .map(suiveur -> ((Utilisateur) suiveur).getProfilUtilisateur())
                .collect(Collectors.joining("\n"));
    }


    public String recupererListeInteret(){
        return (String) this.getListObjet().stream()
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public String recupererListeInteretUtilisateur(String pseudo){
        return (String) this.getListObjet().stream()
                .filter(u -> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .distinct()
                .collect(Collectors.joining(", "));
    }
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre)
    {
        return (String) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).equals(troislettre))
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return (String) this.getListObjet().stream()
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).toUpperCase().equals(troislettre.toUpperCase()))
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public boolean verifierPseudo(String pseudo){
        return this.getListObjet().stream()
                .anyMatch(u-> ((Utilisateur) u).getPseudo().equals(pseudo));
    }

    public Utilisateur retournerUtilisateur(String pseudo){
        return (Utilisateur) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                .findFirst()
                .orElse(null);
    }
}