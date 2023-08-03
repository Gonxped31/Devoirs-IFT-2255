package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Robot;
import service.BaseDeDonneeFournisseur;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseDeDonneeUtilisateur extends BaseDeDonneeCommun {
     private static final String FILE_NAME = "utilisateur.json";
     private static BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;

     private ArrayList<Interet> listInteret;
     private BaseDeDonneeUtilisateur() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Utilisateur>>() {});
        listInteret=new ArrayList<>();
       // init();
     }

     public static BaseDeDonneeUtilisateur getBaseDeUtilisateur() throws IOException, ParseException {
         return baseDeDonneeUtilisateur == null ?  new BaseDeDonneeUtilisateur() : baseDeDonneeUtilisateur;
     }

    @Override
    protected void init() {

        List<Utilisateur> tempList= new ArrayList<>(Arrays.asList(
                new Utilisateur("Boubacar", "Kelly", "adresse1", "KellyB",
                        "BoubaCar", "emailboubacar@gmail.com", "5141111111", "Kelly Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Damov", "Kamen", "adresse2", "KD",
                        "KaMen", "emailkamen@gmail.com", "5142222222", "Kamen Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Gbian", "Samir", "adresse3", "SB",
                        "SaMir", "emailsamir@gmail.com", "5143333333", "Samir Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Doren", "Sky", "adresse4", "SD",
                        "DoRen", "emaildorensky@gmail.com", "5144444444", "Dorensky Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Brice", "Mb", "adresse5", "Brice",
                        "BrIce", "emailbrice@gmail.com", "51455555555", "Brice Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Francois", "Paris", "adresse6", "FP",
                        "FrancOis", "emailfrancois@gmail.com", "5146666666", "Francois Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Mehdi", "Til", "adresse7", "MT",
                        "MeHdi", "emailmehdi@gmail.com", "5147777777", "Mehdi Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Monica", "G", "adresse8", "MG",
                        "MonIca", "emailmonica@gmail.com", "5148888888", "Monica Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("Stefano", "DiGir", "adresse9", "SDi",
                        "StefAno", "emailstefano@gmail.com", "5149999999", "Stefano Inc.", new HashSet<Interet>(),new ArrayList<Notification>(), new HashSet<>()),
                new Utilisateur("James", "Greg", "adresse10", "JG",
                        "JaMes", "emailjames@gmail.com", "5141010101", "James Inc.", new HashSet<Interet>(), new ArrayList<Notification>(), new HashSet<>())
        ));

        tempList.stream().forEach(utilisateur -> {
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

    public String rechercherUtilisateurParSuiveur(String pseudoUtilisateur){

         return (String) this.getListObjet().stream()
                 .filter(u ->( (Utilisateur) u).getPseudo().equals(pseudoUtilisateur))
                 .map(u-> { return ((Utilisateur)u)
                         .getListSuiveur()
                         .stream()
                         .map(suiveur -> {return ((String) suiveur); })
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



    public HashSet<Interet> recupererListeInteretUtilisateur(String pseudo) {
        HashSet<Interet> interets = new HashSet<>();

        for (Object u : getListObjet()) {
            if (u instanceof Utilisateur) {
                Utilisateur utilisateur = (Utilisateur) u;
                if (utilisateur.getPseudo().equals(pseudo)) {
                    for (Object i : utilisateur.getListeInteret()) {
                        if (i instanceof Interet) {
                            interets.add((Interet) i);
                        }
                    }
                }
            }
        }

        return interets;
    }
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre)
    {
        return (String) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).equals(troislettre))
                .map(i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return (String) this.listInteret.stream()
                .filter(interet-> interet.getNom().substring(0,3).toUpperCase().equals(troislettre.toUpperCase()))
                .map(i->i.getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public boolean verifierPseudo(String pseudo){
    return this.getListObjet().stream()
            .anyMatch(u->((Utilisateur) u).getPseudo().equals(pseudo));
    }

    public Utilisateur retournerUtilisateur(String pseudo){
        return (Utilisateur) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                .findFirst()
                .orElse(null);
    }

    public  Utilisateur authentificatiUtilisateur(String pseudoUtilisateur, String mdp){
        return (Utilisateur) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudoUtilisateur) &&
                        ((Utilisateur) u).getMotDePasse().equals(mdp))
                .findFirst()
                .orElse(null);
    }

    public boolean extractInterests(String interet) {
        List<Utilisateur> utilisateurs = this.getListObjet(); //
        for (Utilisateur u : utilisateurs) {
            for (Interet i : u.getListeInteret()) {
                if (interet.equals(i.getNom())){
                    return false;
                }
            }
        }
        return true;
    }


    public StringBuilder retournerInteretsUtilisateur(String pseudo) {
        List<Utilisateur> utilisateurs = this.getListObjet();
        StringBuilder interestsStringBuilder = new StringBuilder();
        for (Utilisateur u : utilisateurs){
            if (u.getPseudo().equals(pseudo)) {
                for (Interet i : u.getListeInteret()) {
                    interestsStringBuilder.append(i.getNom()).append("\n");
                }
                break;
            }
        }
        return interestsStringBuilder;
    }

    public boolean existeDansListeSuivi(String pseudo, String nom) {
        List<Utilisateur> utilisateurs = this.getListObjet();
        for (Utilisateur u : utilisateurs){
            for (String suivi : u.getListeUtilisateursSuivi()){
                if (suivi.equals(nom)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean retournerInteret(String interetChoisi, String pseudo) {
        List<Utilisateur> utilisateurs = this.getListObjet();
        for (Utilisateur u : utilisateurs){
            if (pseudo.equals(u.getPseudo())){
                for (Interet interet : u.getListeInteret()){
                    if (interet.getNom().equals(interetChoisi)){
                        return  true;
                    }
                }
            }

        }
        return false;

    }
}