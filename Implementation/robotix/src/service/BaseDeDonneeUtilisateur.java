package service;

import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Utilisateur;
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
          //
         //new Utilisateurs (),
        ));

        tempList.stream().forEach(utilisateur-> {
         this.ajouterObjet(utilisateur);
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

    public String rechercherUtilisateurParSuiveur(String nomUtilisateur){

         return (String) this.getListObjet().stream()
                 .filter(u ->( (Utilisateur) u).getNom().equals(nomUtilisateur))
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

    public String recupererListeInteretUtilisateur(String nom){
        return (String) this.getListObjet().stream()
                .filter(u -> ((Utilisateur) u).getNom().equals(nom))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .distinct()
                .collect(Collectors.joining(", "));
    }
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String nomUtilisateur, String troislettre)
    {
        return (String) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getNom().equals(nomUtilisateur))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).equals(troislettre))
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return (String) this.getListObjet().stream()
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).equals(troislettre))
                .distinct()
                .collect(Collectors.joining(", "));
    }


}