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

/**
 * Cette classe gère la base de données des utilisateurs.
 * Elle étend la classe BaseDeDonneeCommun et est utilisée pour charger les données des utilisateurs à partir d'un fichier JSON et gérer les opérations liées aux utilisateurs.
 * Le nom du fichier JSON est défini comme constante FILE_NAME.
 */
public class BaseDeDonneeUtilisateur extends BaseDeDonneeCommun {
    /**
     *  Définition du nom du fichier JSON
     */
     private static final String FILE_NAME = "utilisateur.json";
     private static BaseDeDonneeUtilisateur baseDeDonneeUtilisateur;

    /**
     * Liste des intérêts
     */
     private ArrayList<Interet> listInteret;

    /**
     * Constructeur de la classe BaseDeDonneeUtilisateur.
     * Ce constructeur utilise le constructeur de la classe parente BaseDeDonneeCommun pour charger les données des utilisateurs à partir du fichier JSON spécifié.
     *
     * @throws IOException    Une exception d'entrée/sortie peut se produire lors de la lecture du fichier JSON.
     * @throws ParseException Une exception de parsing peut se produire lors de la désérialisation des données depuis le fichier JSON.
     */
     private BaseDeDonneeUtilisateur() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Utilisateur>>() {});
        listInteret=new ArrayList<>();
     }

    /**
     * Méthode statique pour obtenir l'instance unique de BaseDeDonneeUtilisateur.
     * Si l'instance n'existe pas, elle est créée en appelant le constructeur de la classe. Sinon, l'instance existante est retournée.
     *
     * @return L'instance unique de BaseDeDonneeUtilisateur.
     * @throws IOException    Une exception d'entrée/sortie peut se produire lors de la lecture du fichier JSON.
     * @throws ParseException Une exception de parsing peut se produire lors de la désérialisation des données depuis le fichier JSON.
     */
     public static BaseDeDonneeUtilisateur getBaseDeUtilisateur() throws IOException, ParseException {
         return baseDeDonneeUtilisateur == null ?  new BaseDeDonneeUtilisateur() : baseDeDonneeUtilisateur;
     }

    /**
     * Méthode pour initialiser la base de données des utilisateurs.
     * Cette méthode est appelée lors de la création d'un objet BaseDeDonneeUtilisateur pour initialiser la liste des utilisateurs avec des données par défaut.
     * Elle ajoute une liste d'objets Utilisateur pré-définis à la liste d'objets gérée par la classe parente BaseDeDonneeCommun.
     */
    @Override
    protected void init() {

        List<Utilisateur> tempList= new ArrayList<>(Arrays.asList(
                new Utilisateur("Boubacar", "Kelly", "adresse1", "KellyB",
                        "BoubaCar", "emailboubacar@gmail.com", "5141111111", "Kelly Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Damov", "Kamen", "adresse2", "KD",
                        "KaMen", "emailkamen@gmail.com", "5142222222", "Kamen Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Gbian", "Samir", "adresse3", "SB",
                        "SaMir", "emailsamir@gmail.com", "5143333333", "Samir Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Doren", "Sky", "adresse4", "SD",
                        "DoRen", "emaildorensky@gmail.com", "5144444444", "Dorensky Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Brice", "Mb", "adresse5", "Brice",
                        "BrIce", "emailbrice@gmail.com", "51455555555", "Brice Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Francois", "Paris", "adresse6", "FP",
                        "FrancOis", "emailfrancois@gmail.com", "5146666666", "Francois Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Mehdi", "Til", "adresse7", "MT",
                        "MeHdi", "emailmehdi@gmail.com", "5147777777", "Mehdi Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Monica", "G", "adresse8", "MG",
                        "MonIca", "emailmonica@gmail.com", "5148888888", "Monica Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("Stefano", "DiGir", "adresse9", "SDi",
                        "StefAno", "emailstefano@gmail.com", "5149999999", "Stefano Inc.", new HashSet<Interet>(),new ArrayList<Notification>()),
                new Utilisateur("James", "Greg", "adresse10", "JG",
                        "JaMes", "emailjames@gmail.com", "5141010101", "James Inc.", new HashSet<Interet>(), new ArrayList<Notification>())
        ));

        tempList.stream().forEach(utilisateur -> {
            this.ajouterObjet(utilisateur);
        });
    }

    /**
     * Récupère la liste des utilisateurs.
     *
     * @return Une ArrayList contenant les utilisateurs.
     */
    public ArrayList<Utilisateur> recupererLalisteDesUtilisateur() {
        return this.getListObjet();
    }

    /**
     * Recherche un utilisateur par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur recherché.
     * @return Le profil de l'utilisateur trouvé, ou un message d'erreur si l'utilisateur n'est pas trouvé.
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
     * Recherche un utilisateur par son nom.
     *
     * @param nom Le nom de l'utilisateur recherché.
     * @return Le profil de l'utilisateur trouvé, ou un message d'erreur si l'utilisateur n'est pas trouvé.
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
     * Recherche un utilisateur par son prénom.
     *
     * @param prenom Le prénom de l'utilisateur recherché.
     * @return Le profil de l'utilisateur trouvé, ou un message d'erreur si l'utilisateur n'est pas trouvé.
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
     * Recherche un utilisateur par son pseudo et renvoie les profils de ses suiveurs.
     *
     * @param pseudoUtilisateur Le pseudo de l'utilisateur dont on veut récupérer les suiveurs.
     * @return Les profils des suiveurs de l'utilisateur, séparés par des sauts de ligne.
     */
    public String rechercherUtilisateurParSuiveur(String pseudoUtilisateur){

         return (String) this.getListObjet().stream()
                 .filter(u ->( (Utilisateur) u).getPseudo().equals(pseudoUtilisateur))
                 .map(u-> { return ((Utilisateur)u)
                         .getListSuiveur()
                         .stream()
                         .map(suiveur -> {return ((Utilisateur) suiveur).getProfilUtilisateur(); })
                         .collect(Collectors.joining("\n\n"));
      }).collect(Collectors.joining("\n"));
   }

    /**
     * Filtre la liste des suiveurs d'un utilisateur par le pseudo du suiveur.
     *
     * @param nomUtilisateur Le nom de l'utilisateur dont on veut filtrer les suiveurs.
     * @param pseudoSuiveur  Le pseudo du suiveur que l'on souhaite retrouver.
     * @return Les profils des suiveurs de l'utilisateur filtrés par le pseudo, séparés par des sauts de ligne.
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
     * Récupère la liste des intérêts d'un utilisateur par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur dont on veut récupérer les intérêts.
     * @return Les noms des intérêts de l'utilisateur, séparés par des virgules.
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
     * Récupère la liste des intérêts d'un utilisateur par son pseudo, en filtrant sur les trois premières lettres.
     *
     * @param pseudo      Le pseudo de l'utilisateur dont on veut récupérer les intérêts.
     * @param troislettre La sous-chaîne des trois premières lettres utilisée pour filtrer les intérêts.
     * @return Les noms des intérêts de l'utilisateur, filtrés par les trois premières lettres, séparés par des virgules.
     */
    public String recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(String pseudo, String troislettre) {
        return (String) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudo))
                .flatMap(u -> ((Utilisateur)u).getListeInteret().stream())
                .filter(interet-> ((String) interet).substring(0,3).equals(troislettre))
                .map(i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    /**
     * Récupère la liste des intérêts filtrée sur les trois premières lettres.
     *
     * @param troislettre La sous-chaîne des trois premières lettres utilisée pour filtrer les intérêts.
     * @return Les noms des intérêts filtrés par les trois premières lettres, séparés par des sauts de ligne.
     */
    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre) {
        return this.listInteret.stream()
                .filter(interet-> interet.getNom().substring(0,3).toUpperCase().equals(troislettre.toUpperCase()))
                .map(i->i.getNom())
                .distinct()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Vérifie si un pseudo existe dans la liste des utilisateurs.
     *
     * @param pseudo Le pseudo à vérifier.
     * @return true si le pseudo existe, sinon false.
     */
    public boolean verifierPseudo(String pseudo){
    return this.getListObjet().stream()
            .anyMatch(u->((Utilisateur) u).getPseudo().equals(pseudo));
    }

    /**
     * Retourne l'utilisateur correspondant au pseudo donné.
     *
     * @param pseudo Le pseudo de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant au pseudo, ou null s'il n'est pas trouvé.
     */
    public Utilisateur retournerUtilisateur(String pseudo){
        return (Utilisateur) this.getListObjet().stream()
                .filter( utilisateur ->( (Utilisateur) utilisateur).getPseudo().equals(pseudo))
                .findFirst()
                .orElse(null);
    }

    /**
     * Authentifie un utilisateur en vérifiant le pseudo et le mot de passe.
     *
     * @param pseudoUtilisateur Le pseudo de l'utilisateur à authentifier.
     * @param mdp              Le mot de passe de l'utilisateur à authentifier.
     * @return L'utilisateur authentifié, ou null si l'authentification échoue.
     */
    public  Utilisateur authentificatiUtilisateur(String pseudoUtilisateur, String mdp){
        return (Utilisateur) this.getListObjet().stream()
                .filter(u-> ((Utilisateur) u).getPseudo().equals(pseudoUtilisateur) &&
                        ((Utilisateur) u).getMotDePasse().equals(mdp))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vérifie si un intérêt existe dans la liste des utilisateurs.
     *
     * @param interet Le nom de l'intérêt à vérifier.
     * @return false si l'intérêt existe, sinon true.
     */
    public boolean extractInterests(String interet) {
        List<Utilisateur> utilisateurs = this.getListObjet(); // Assuming getListObjet() returns List<Utilisateur>
        for (Utilisateur u : utilisateurs) {
            for (Interet i : u.getListeInteret()) {
                if (interet.equals(i.getNom())){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retourne les intérêts d'un utilisateur donné.
     *
     * @param pseudo Le pseudo de l'utilisateur dont on veut récupérer les intérêts.
     * @return Un StringBuilder contenant les noms des intérêts de l'utilisateur, séparés par des sauts de ligne.
     */
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

    /**
     * Vérifie si un utilisateur donné suit un autre utilisateur donné.
     *
     * @param pseudo Le pseudo de l'utilisateur dont on veut vérifier s'il suit quelqu'un.
     * @param nom    Le pseudo de l'utilisateur suivi.
     * @return true si l'utilisateur suit l'utilisateur donné, sinon false.
     */
    public boolean existeDansListeSuivi(String pseudo, String nom) {
        List<Utilisateur> utilisateurs = this.getListObjet();
        for (Utilisateur u : utilisateurs){
            for (Utilisateur suivi : u.getListeUtilisateursSuivi()){
                if (suivi.getPseudo().equals(nom)){
                    return true;
                }
            }
        }
        return false;
    }
}