package service;

import com.fasterxml.jackson.core.type.TypeReference;

import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;



 
import java.util.*;
import java.util.stream.Collectors;
/**
 * La classe BaseDeDonneeUtilisateur est responsable de la gestion des utilisateur dans la base de données.
 * Cette classe hérite de la classe BaseDeDonnee, qui fournit une interface commune pour toutes les classes de base de données.
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeUtilisateur extends BaseDeDonnee {
     private static final String FILE_NAME = "utilisateur.json";

    /**
     * Le constructeur par défaut de la classe BaseDeDonneeUtilisateur.
     * Il initialise la base de données des utilisateur en utilisant le nom du fichier défini par FILE_NAME.
     */
     public BaseDeDonneeUtilisateur()  {
        super(FILE_NAME,new TypeReference<ArrayList<Utilisateur>>() {});


     }

    /**
     * Cette méthode initialise la liste d'utilisateurs .
     */

    @Override
    protected void init() {

        List<Utilisateur> tempList= new ArrayList<>(Arrays.asList(
                new Utilisateur("Boubacar", "Kelly", "adresse1", "KellyB",
                        "BoubaCar", "emailboubacar@gmail.com", "5141111111", "Kelly Inc.", new ArrayList<Interet>()),
                new Utilisateur("Damov", "Kamen", "adresse2", "KD",
                        "KaMen", "emailkamen@gmail.com", "5142222222", "Kamen Inc.", new ArrayList<Interet>()),
                new Utilisateur("Gbian", "Samir", "adresse3", "SB",
                        "SaMir", "emailsamir@gmail.com", "5143333333", "Samir Inc.", new ArrayList<Interet>()),
                new Utilisateur("Doren", "Sky", "adresse4", "SD",
                        "DoRen", "emaildorensky@gmail.com", "5144444444", "Dorensky Inc.", new ArrayList<Interet>()),
                new Utilisateur("Brice", "Mb", "adresse5", "Brice",
                        "BrIce", "emailbrice@gmail.com", "51455555555", "Brice Inc.", new ArrayList<Interet>()),
                new Utilisateur("Francois", "Paris", "adresse6", "FP",
                        "FrancOis", "emailfrancois@gmail.com", "5146666666", "Francois Inc.", new ArrayList<Interet>()),
                new Utilisateur("Mehdi", "Til", "adresse7", "MT",
                        "MeHdi", "emailmehdi@gmail.com", "5147777777", "Mehdi Inc.", new ArrayList<Interet>()),
                new Utilisateur("Monica", "G", "adresse8", "MG",
                        "MonIca", "emailmonica@gmail.com", "5148888888", "Monica Inc.", new ArrayList<Interet>()),
                new Utilisateur("Stefano", "DiGir", "adresse9", "SDi",
                        "StefAno", "emailstefano@gmail.com", "5149999999", "Stefano Inc.", new ArrayList<Interet>()),
                new Utilisateur("James", "Greg", "adresse10", "JG",
                        "JaMes", "emailjames@gmail.com", "5141010101", "James Inc.", new ArrayList<Interet>())
        ));
        tempList.stream().forEach(utilisateur -> {
            this.ajouterObjet(utilisateur);

        });
    }

    /**
     * Cette méthode récupère la liste des informations sur les utilisateurs
     * et les retourne sous forme de chaine de charactere
     * @return La liste des utilisateurs.
     */
    public String recupererLalisteDesUtilisateur()
    {
        return (String) this.getListObjet().stream()
                .map(user ->((Utilisateur) user).getProfilUtilisateur())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Cette méthode recherche un utilisateur par pseudo.
     * @param pseudo Le pseudo de l'utilisateur.
     * @return Le profil de l'utilisateur si trouvé, sinon un message d'erreur.
     */
    public String rechercherUtilisateurParPseudo(String pseudo){
         return (String) this.getListObjet().stream()
                 .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                 .map(u-> {
                     return ((Utilisateur) u).getProfilUtilisateur();
                 })
                 .findFirst()
                 .orElse("Utilisateur non trouver, veuillez verifier le pseudo");
    }

    /**
     * Cette méthode recherche un utilisateur par nom.
     * @param nom Le nom de l'utilisateur.
     * @return Le profil de l'utilisateur si trouvé, sinon un message d'erreur.
     */
    public String rechercherUtilisateurParNom(String nom){
        return (String) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getNom().equals(nom))
                .map(u-> {
                    return ((Utilisateur) u).getProfilUtilisateur();
                })
                .findFirst()
                .orElse("Utilisateur non trouver, veuillez verifier le nom");
    }

    /**
     * Cette méthode recherche un utilisateur par prénom.
     * @param prenom Le prénom de l'utilisateur.
     * @return Le profil de l'utilisateur si trouvé, sinon un message d'erreur.
     */
    public String rechercherUtilisateurParPrenom(String prenom){
        return (String) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPrenom().equals(prenom))
                .map(u-> {
                    return ((Utilisateur) u).getProfilUtilisateur();
                })
                .findFirst()
                .orElse("Utilisateur non trouver, veuillez verifier le prenom");
    }
    /**
     * Cette méthode recherche les suiveurs d'un utilisateur.
     * @param pseudoUtilisateur Le pseudo de l'utilisateur.
     * @return La liste des suiveurs de l'utilisateur.
     */

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

    /**
     * Cette méthode filtre la liste des suiveurs d'un utilisateur par pseudo.
     * @param nomUtilisateur Le nom de l'utilisateur.
     * @param pseudoSuiveur Le pseudo du suiveur.
     * @return La liste des suiveurs filtrée.
     */

    public String filtrerListSuiveurParPseudo(String nomUtilisateur, String pseudoSuiveur){
        return (String) this.getListObjet().stream()
                .filter(u -> ((Utilisateur) u).getNom().equals(nomUtilisateur))
                .flatMap(u -> ((Utilisateur)u).getListSuiveur().stream())
                .filter(s -> ((Utilisateur) s).getPseudo().equals(pseudoSuiveur))
                .map(suiveur -> ((Utilisateur) suiveur).getProfilUtilisateur())
                .collect(Collectors.joining("\n"));
    }


    /**
     * Cette méthode récupère la liste des intérêts d'un utilisateur.
     * @param pseudo Le pseudo de l'utilisateur.
     * @return La liste des intérêts de l'utilisateur.
     */
    public String recupererListeInteretUtilisateur(String pseudo){
        return (String) this.getListObjet().stream()
                .filter(u -> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .map( i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    /**
     * Cette méthode filtre la liste des intérêts d'un utilisateur par les trois premières lettres.
     * @param pseudo Le pseudo de l'utilisateur.
     * @param troislettre Les trois premières lettres pour le filtrage.
     * @return La liste des intérêts filtrée.
     */
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre)
    {
        return (String) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).toLowerCase().trim().equals(troislettre.toLowerCase().trim()))
                .map(i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }




    /**
     * Cette méthode vérifie si un pseudo est unique.
     * @param pseudo Le pseudo à vérifier.
     * @return Vrai si le pseudo existe, sinon faux.
     */
    public boolean verifierPseudo(String pseudo){

    return this.getListObjet().stream()
            .anyMatch(u->((Utilisateur) u).getPseudo().equals(pseudo));

    }

    /**
     * Cette méthode retourne un utilisateur par son pseudo.
     * @param pseudo Le pseudo de l'utilisateur.
     * @return L'utilisateur si trouvé, sinon null.
     */
    public Utilisateur retournerUtilisateur(String pseudo){
        return (Utilisateur) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                .findFirst()
                .orElse(null);
    }


    /**
     * Cette méthode authentifie un utilisateur par son pseudo et son mot de passe.
     * @param pseudoUtilisateur Le pseudo de l'utilisateur.
     * @param mdp Le mot de passe de l'utilisateur.
     * @return L'utilisateur si l'authentification réussit, sinon null.
     */
    public  Utilisateur authentificatiUtilisateur(String pseudoUtilisateur, String mdp){
        return this.getListObjet().size()!=0 ?(Utilisateur) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudoUtilisateur) &&
                        ((Utilisateur) u).getMotDePasse().equals(mdp))
                .findFirst()
                .orElse(null) : null;
    }

}