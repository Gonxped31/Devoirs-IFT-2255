package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Format;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * La classe BaseDeDonneeFournisseur est responsable de la gestion des fournisseurs dans la base de données.
 * Cette classe hérite de la classe BaseDeDonnee, qui fournit une interface commune pour toutes les classes de base de données.
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeFournisseur  extends BaseDeDonneeCommun{
        private static final String FILE_NAME = "fournisseur.json";
        private List<Map<String, List<Robot>>> listRobot;
        private List<Map<String, List<Composant>>> listComposant;
        private static BaseDeDonneeFournisseur baseDeDonneeFournisseur;


    /**
     * Constructeur privé de la classe BaseDeDonneeFournisseur.
     * Ce constructeur est utilisé pour instancier un objet BaseDeDonneeFournisseur en utilisant les données du fichier spécifié.
     * Il appelle le constructeur de la classe parente BaseDeDonnee en lui fournissant le nom du fichier et un TypeReference approprié pour désérialiser les données en une liste d'objets de type Fournisseur.
     * Ensuite, il initialise les listes listComposant et listRobot, puis appelle la méthode initListeRobotEtComposant() pour remplir ces listes avec les données des fournisseurs et leurs inventaires de robots et de composants associés.
     * Ce constructeur est déclaré privé, ce qui signifie qu'il ne peut être appelé que depuis l'intérieur de la classe BaseDeDonneeFournisseur.
     *
     * @throws IOException    Une exception d'entrée/sortie peut se produire lors de la lecture du fichier.
     * @throws ParseException Une exception de parsing peut se produire lors de la désérialisation des données depuis le fichier.
     */
     private BaseDeDonneeFournisseur() throws IOException, ParseException {
            super(FILE_NAME,new TypeReference<ArrayList<Fournisseur>>() {});
            listComposant= new ArrayList<>();
            listRobot=new ArrayList<>();
            initListeRobotEtComposant();
     }

    /**
     * Le constructeur par défaut de la classe BaseDeDonneeFournisseur.
     * Il initialise la base de données des fournisseurs en utilisant le nom du fichier défini par FILE_NAME.
     */
     public static BaseDeDonneeFournisseur getBaseDeDonneeFournisseur() throws IOException, ParseException {
         return baseDeDonneeFournisseur == null ? new BaseDeDonneeFournisseur() : baseDeDonneeFournisseur;
     }

    /**
     * Méthode d'initialisation de la base de données des fournisseurs.
     * Elle est appelée automatiquement par le constructeur de la classe parente (BaseDeDonnee).
     * Cette méthode est censée être redéfinie par toutes les classes qui héritent de la classe BaseDeDonnee.
     */
    @Override
    protected void init() {
        List<Fournisseur> tempList= new ArrayList<>(Arrays.asList(

       new Fournisseur("Roy", "123" ,"123 rue des Innovations, Montr?al, QC, H1A 0A1",  "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),

        new Fournisseur("Kare", "456", "123 rue des Innovations, Montr?al, QC, H1A 0A1", "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),

        new Fournisseur("Bouchard", "789","456 avenue des Automates, Montr?al, QC, H5M 1N2",
                        "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "25", "Automatech"),

        new Fournisseur("Adams", "159","2376 boulevard des G?nies, Qu?bec, QC, G1W 2W5",
                        "service@innovatech.ca", "4509998888", "RobotC", "ECRAN","27", "Innovatech"),

        new Fournisseur("Wilson", "753","89 boulevard de la Technologie, Laval, QC, H7M 7B7",
                        "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA","35", "iRobot"),

        new Fournisseur("Thompson", "486", "10 Place de la Robotique, Longueuil, QC, J4H 1A1",
                        "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR","22", "RoboPro")
        ));

        LinkedList<Composant> listComposant = new LinkedList<>();
        listComposant.add(new Composant("cpu", "150000", "Description cpu", "CPU"));
        listComposant.add(new Composant("bras", "800000", "Description bras", "BRAS"));
        listComposant.add(new Composant("roue", "570000", "Description roue", "ROUE"));
        listComposant.add(new Composant("helice", "457000", "Description helice", "HELICE"));
        listComposant.add(new Composant("micro", "365000000", "Description micro", "MICRO"));
        listComposant.add(new Composant("hautparleur", "7540000", "Description hautparleur", "HAUTPARLEUR"));
        listComposant.add(new Composant("ecran", "841000000", "Description ecran", "ECRAN"));

        LinkedList<LinkedList<Robot>> listeRobots = new LinkedList<>();
        LinkedList<Robot> listeRobotRoy = new LinkedList<>();
        LinkedList<Robot> listeRobotKare = new LinkedList<>();
        LinkedList<Robot> listeRobotBouchard = new LinkedList<>();
        LinkedList<Robot> listeRobotAdams = new LinkedList<>();
        LinkedList<Robot> listeRobotWilson = new LinkedList<>();
        LinkedList<Robot> listeRobotThompson = new LinkedList<>();
        listeRobotRoy.add(new Robot("", 0, 0, 0, 100, 120, 32, listComposant, "Coureur", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotKare.add(new Robot("", 0, 0, 0, 32, 100, 64, listComposant, "Sauteur", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotBouchard.add(new Robot("", 0, 0, 0, 45, 75, 32, listComposant, "Musicienne", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotAdams.add(new Robot("", 0, 0, 0, 76, 85, 32, listComposant, "SurPoids", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotWilson.add(new Robot("", 0, 0, 0, 24, 105, 64, listComposant, "Calme", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotThompson.add(new Robot("", 0, 0, 0, 98, 46, 84, listComposant, "Abeille", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));

        listeRobots.add(listeRobotRoy);
        listeRobots.add(listeRobotKare);
        listeRobots.add(listeRobotBouchard);
        listeRobots.add(listeRobotAdams);
        listeRobots.add(listeRobotWilson);
        listeRobots.add(listeRobotThompson);

        for (int i = 0; i < tempList.size(); ++i) {
            tempList.get(i).setInventaireDeRobot(listeRobots.get(i));
            tempList.get(i).setInventaireComposant(listComposant);
        }

        tempList.stream().forEach(fournisseur -> {
            this.ajouterObjet(fournisseur);
        });

    }

    /**
     * Méthode pour initialiser les listes de robots et de composants associées aux fournisseurs et utilisateurs.
     * Cette méthode parcourt la liste des objets (fournisseurs et utilisateurs) récupérée à partir du fichier JSON,
     * puis associe les robots et les composants de chaque objet à la liste correspondante (listRobot et listComposant).
     * Les associations sont réalisées à l'aide de Map, en utilisant le nom du fournisseur/utilisateur comme clé
     * et la liste de robots/composants associés comme valeur.
     */
    protected void initListeRobotEtComposant(){

            this.getListObjet().stream().forEach(objet -> {

                listRobot.add(new HashMap<String, List<Robot>>() {{
                    put(
                            (objet instanceof Fournisseur ? ((Fournisseur) objet).getNom() : ((Utilisateur) objet).getPseudo()),
                            (objet instanceof Fournisseur ? ((Fournisseur) objet).getInventaireDeRobot() : ((Utilisateur) objet).getListeRobot())
                    );
                }});

                listComposant.add(new HashMap<String, List<Composant>>() {{
                    put(
                            (objet instanceof Fournisseur ? ((Fournisseur) objet).getNom() : ((Utilisateur) objet).getPseudo()),
                            (objet instanceof Fournisseur ? ((Fournisseur) objet).getInventaireComposant() : ((Utilisateur) objet).getComposantesAchetes())
                    );
                }});
            });


        }

    /**
     * Retourne la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs.
     *
     * @return La liste des associations entre les fournisseurs/utilisateurs et leurs composants.
     */
    public List<Map<String, List<Composant>>> getListComposant(){
        return listComposant;
    }

    /**
     * Retourne la liste des associations entre les fournisseurs/utilisateurs et leurs robots respectifs.
     *
     * @return La liste des associations entre les fournisseurs/utilisateurs et leurs robots.
     */
    public List<Map<String, List<Robot>>> getListRobot() {
        return listRobot;
    }

    /**
     * Recherche et retourne un objet Robot en fonction du numéro de série donné.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs robots respectifs,
     * et renvoie le premier Robot dont le numéro de série correspond au numéroSerie spécifié.
     *
     * @param numeroSerie Le numéro de série du robot à rechercher.
     * @return L'objet Robot correspondant au numéro de série donné, ou null si aucun robot correspondant n'est trouvé.
     */
    public Robot retournerRobot(String numeroSerie){
            for (Map<String, List<Robot>> map : listRobot) {
                for (List<Robot> robotList : map.values()) {
                    for (Robot robot : robotList) {
                        if (robot.getNumeroSerie().toString().trim().equals(numeroSerie.trim())) {
                            return robot;
                        }
                    }
                }
            }
            return null;
    }

    /**
     * Recherche et retourne une instance de l'objet Composant en fonction du nom donné.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs,
     * et renvoie le premier Composant dont le nom correspond exactement au nom spécifié.
     *
     * @param nom Le nom du composant à rechercher.
     * @return L'objet Composant correspondant au nom donné, ou null si aucun composant correspondant n'est trouvé.
     */
    public Composant retournerComposante(String nom){
        return listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * Recherche et retourne une instance de l'objet Fournisseur en fonction du nom donné.
     * La méthode parcourt la liste des objets récupérés à partir du fichier JSON,
     * et renvoie le premier Fournisseur dont le nom correspond exactement au nom spécifié.
     *
     * @param nom Le nom du fournisseur à rechercher.
     * @return L'objet Fournisseur correspondant au nom donné, ou null si aucun fournisseur correspondant n'est trouvé.
     */
    public Fournisseur retournerFournisseur(String nom){
        return (Fournisseur) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * Récupère la liste des fournisseurs disponibles dans la base de données.
     *
     * @return La liste des fournisseurs disponibles.
     */
    public ArrayList<Fournisseur> recupererLalisteDesFournisseur() {
        return this.getListObjet();
    }

    /**
     * Recherche et retourne les informations formatées des composants correspondant au nom spécifié ou aux trois premiers caractères du nom.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs,
     * puis renvoie les informations formatées (via la méthode getInfoComposantFormater) de tous les composants dont le nom correspond exactement
     * ou commence par les trois premiers caractères du nomOuTroisPremierSousChaine spécifié.
     *
     * @param nomOuTroisPremierSousChaine Le nom ou les trois premiers caractères du nom du composant à rechercher.
     * @return Une chaîne contenant les informations formatées des composants correspondants, séparées par des sauts de ligne,
     *         ou un message d'erreur si aucun composant correspondant n'est trouvé.
     */
    public String rechercherComposantParNomOuTroisSouschaine(String nomOuTroisPremierSousChaine){
        String composants= this.listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getNom().equals(nomOuTroisPremierSousChaine) ||
                        composant.getNom().substring(0, 3).equals(nomOuTroisPremierSousChaine))
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
        return  composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom ou les trois premier caractère" :composants;
    }

    /**
     * Recherche et retourne les informations formatées des composants correspondant au type de composant spécifié.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs,
     * puis renvoie les informations formatées (via la méthode getInfoComposantFormater) de tous les composants dont le type correspond exactement
     * (en ignorant la casse) au typeComposant spécifié.
     *
     * @param typeComposant Le type de composant à rechercher.
     * @return Une chaîne contenant les informations formatées des composants correspondants, séparées par des sauts de ligne,
     *         ou un message d'erreur si aucun composant correspondant n'est trouvé.
     */
    public String rechercherComposantParType(String typeComposant){
        String composants =this.listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getTypeComposant().toLowerCase().trim()
                        .equals(typeComposant.toLowerCase().trim()))
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));

        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le type" : composants;
    }

    /**
     * Recherche et retourne les informations formatées des composants associés au fournisseur dont le nom est spécifié.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs,
     * puis renvoie les informations formatées (via la méthode getInfoComposantFormater) de tous les composants associés
     * au fournisseur dont le nom correspond exactement au nomFournisseur spécifié.
     *
     * @param nomFournisseur Le nom du fournisseur dont les composants sont recherchés.
     * @return Une chaîne contenant les informations formatées des composants associés au fournisseur,
     *         séparées par des sauts de ligne, ou un message d'erreur si aucun composant associé n'est trouvé.
     */
    public String rechercherComposantParNomFournisseur(String nomFournisseur){
        String composants= this.listComposant.stream()
                .filter(map -> map.containsKey(nomFournisseur))
                .flatMap(map -> map.get(nomFournisseur).stream())
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
        return composants.isEmpty() ? "Aucune composante trouvée...": composants;
    }

    /**
     * Recherche et retourne les informations du profil du fournisseur dont le nom est spécifié.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie les informations du profil du premier fournisseur dont le nom correspond exactement au nom spécifié.
     *
     * @param nom Le nom du fournisseur dont le profil est recherché.
     * @return Les informations du profil du fournisseur, ou un message d'erreur si aucun fournisseur correspondant n'est trouvé.
     */
    public String rechercherFournisseurParNom(String nom){
        return (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getNom().equals(nom))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .findFirst()
                .orElse("Fournisseur non trouver, veuillez verifier le nom");
    }

    /**
     * Recherche et retourne les informations du profil des fournisseurs ayant l'adresse spécifiée.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie les informations du profil de tous les fournisseurs ayant l'adresse exactement égale à l'adresse spécifiée.
     * Les informations des différents fournisseurs sont séparées par des sauts de ligne.
     *
     * @param adresse L'adresse des fournisseurs recherchés.
     * @return Les informations du profil des fournisseurs ayant l'adresse spécifiée, ou un message d'erreur si aucun fournisseur correspondant n'est trouvé.
     */
    public String rechercherFournisseurParAdresse(String adresse){
        String founisseurs= (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getAdresse().equals(adresse))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
             return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier l'adresse" : founisseurs;
    }

    /**
     * Recherche et retourne les informations du profil des fournisseurs qui fabriquent le type de composant spécifié.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie les informations du profil de tous les fournisseurs qui fabriquent le type de composant exactement égal
     * (en ignorant la casse) au typeDeComposant spécifié.
     * Les informations des différents fournisseurs sont séparées par des sauts de ligne.
     *
     * @param typeDeComposant Le type de composant fabriqué par les fournisseurs recherchés.
     * @return Les informations du profil des fournisseurs qui fabriquent le type de composant spécifié,
     *         ou un message d'erreur si aucun fournisseur correspondant n'est trouvé.
     */
    public String rechercherFournisseurParTypeDeComposant(String typeDeComposant){
        String founisseurs= (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getTypeComposantesFabriquer().toUpperCase().equals(typeDeComposant.toUpperCase()))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n\n"));
        return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier le type" : founisseurs;
    }

    /**
     * Recherche et retourne les informations du profil des fournisseurs ayant l'email spécifié.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie les informations du profil de tous les fournisseurs ayant l'email exactement égal à l'email spécifié.
     * Les informations des différents fournisseurs sont séparées par des sauts de ligne.
     *
     * @param email L'email des fournisseurs recherchés.
     * @return Les informations du profil des fournisseurs ayant l'email spécifié, ou un message d'erreur si aucun fournisseur correspondant n'est trouvé.
     */
    public String rechercherFournisseurParEmail(String email) {
        String founisseurs = (String) this.getListObjet().stream()
                .filter(fournisseur -> ((Fournisseur) fournisseur).getEmail().equals(email))
                .map(f -> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
        return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier l'email" : founisseurs;
    }

    /**
     * Vérifie si un fournisseur avec le nom spécifié existe dans la liste des objets.
     *
     * @param nomFourniseur Le nom du fournisseur à vérifier.
     * @return true si un fournisseur avec le nom spécifié existe, sinon false.
     */
    public boolean verifierNomFounissseur(String nomFourniseur){
         return this.getListObjet().stream()
                 .anyMatch(f-> ((Fournisseur) f).getNom().equals(nomFourniseur));

    }

    /**
     * Recherche et retourne les informations formatées du composant dont le nom est spécifié.
     * La méthode parcourt la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs,
     * puis renvoie les informations formatées (via la méthode getInfoComposantFormater) du premier composant dont le nom correspond exactement au nom spécifié.
     *
     * @param nom Le nom du composant à rechercher.
     * @return Les informations formatées du composant correspondant, ou un message d'erreur si aucun composant correspondant n'est trouvé.
     */
    public String rehercherComposantParNom(String nom){
        String composants =this.listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getNom().equals(nom))
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));

        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom" : composants;
    }

    /**
     * Authentifie un fournisseur en vérifiant son nom et mot de passe.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie le fournisseur correspondant au nomFournisseur et mot de passe spécifiés, s'il est trouvé.
     *
     * @param nomFournisseur Le nom du fournisseur à authentifier.
     * @param mdp Le mot de passe du fournisseur à authentifier.
     * @return Le fournisseur authentifié, ou null si aucun fournisseur correspondant n'est trouvé.
     */
    public  Fournisseur authentificatiFournisseur(String nomFournisseur, String mdp){
         return (Fournisseur) this.getListObjet().stream()
                 .filter(f-> ((Fournisseur) f).getNom().equals(nomFournisseur) &&
                         ((Fournisseur) f).getMotDePasse().equals(mdp))
                 .findFirst()
                 .orElse(null);
    }

    /**
     * Récupère les informations formatées des robots appartenant au fournisseur dont le nom est spécifié.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie les informations formatées (via la méthode getInfoRobotFormater) de tous les robots appartenant au fournisseur dont le nom correspond exactement au nomFournisseur spécifié.
     * Les informations des différents robots sont séparées par des sauts de ligne.
     *
     * @param nomFournisseur Le nom du fournisseur dont les robots sont recherchés.
     * @return Les informations formatées des robots appartenant au fournisseur, ou un message d'erreur si aucun robot associé n'est trouvé.
     */
    public String obtenirListRobotFournisseur(String nomFournisseur){
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .map(r->((Robot) r).getInfoRobotFormater())
                .collect(Collectors.joining("\n"));
    }


    /**
     * Achète un robot en récupérant son numéro de série à partir du numéro spécifié et du nom du fournisseur.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie le numéro de série du robot associé au fournisseur dont le nom correspond exactement au nomFournisseur spécifié et dont le numéro correspond au numéro spécifié.
     *
     * @param nomFournisseur Le nom du fournisseur auprès duquel acheter le robot.
     * @param numero Le numéro du robot à acheter.
     * @return Le numéro de série du robot acheté, ou null si aucun robot correspondant n'est trouvé.
     */
    public UUID acheterRobot(String nomFournisseur, int numero){
        return (UUID) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .filter(r->((Robot) r).getNumero()==numero )
                .findFirst()
                .map(r->((Robot) r).getNumeroSerie())
                .orElse(null);
    }

    /**
     * Achète une composante en récupérant l'objet Composant à partir du nom du fournisseur et du nom de la composante.
     * La méthode parcourt la liste des objets (fournisseurs) récupérée à partir du fichier JSON,
     * puis renvoie l'objet Composant associé au fournisseur dont le nom correspond exactement au nomFournisseur spécifié et dont le nom de composante correspond au nomComposante spécifié.
     *
     * @param nomFournisseur Le nom du fournisseur auprès duquel acheter la composante.
     * @param nomComposante Le nom de la composante à acheter.
     * @return L'objet Composant acheté, ou null si aucune composante correspondante n'est trouvée.
     */
    public Composant achatComposante(String nomFournisseur, String nomComposante){
         return (Composant) this.getListObjet().stream()
                 .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                 .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                 .filter(c-> ((Composant) c).getNom().equals(nomComposante))
                 .findFirst()
                 .orElse(null);
    }

    /**
     * Cette méthode permet d'acheter un composant auprès d'un fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur auprès duquel le composant doit être acheté.
     * @param numero Le numéro du composant à acheter.
     * @return Le nom du composant acheté, ou null si aucun composant correspondant n'est trouvé.
     * @author Boubacar Hama Bague
     */
    public String acheterComposant(String nomFournisseur, int numero) {
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                .findFirst()
                .filter(c->((Composant) c).getNumero()==numero)
                .map(c->((Composant) c).getNom())
                .orElse(null);
    }
}