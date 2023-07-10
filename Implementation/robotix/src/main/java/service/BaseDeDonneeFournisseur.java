package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Membre.Fournisseur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * La classe BaseDeDonneeFournisseur est responsable de la gestion des fournisseurs dans la base de données.
 * Cette classe hérite de la classe BaseDeDonnee, qui fournit une interface commune pour toutes les classes de base de données.
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeFournisseur  extends BaseDeDonnee {
     private static final String FILE_NAME = "fournisseur.json";


    /**
     * Le constructeur par défaut de la classe BaseDeDonneeFournisseur.
     * Il initialise la base de données des fournisseurs en utilisant le nom du fichier défini par FILE_NAME.
     */
    
     public BaseDeDonneeFournisseur()  {
        super(FILE_NAME,new TypeReference<ArrayList<Fournisseur>>() {});

     }



    /**
     * Méthode d'initialisation de la base de données des fournisseurs.
     * Elle est appelée automatiquement par le constructeur de la classe parente (BaseDeDonnee).
     * Cette méthode est censée être redéfinie par toutes les classes qui héritent de la classe BaseDeDonnee.
     */
    @Override
    protected void init() {
         //Todo
        List<Fournisseur> tempList= new ArrayList<>(Arrays.asList(

       new Fournisseur("Roy", "123" ,"123 rue des Innovations, Montr�al, QC, H1A 0A1",  "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),

        new Fournisseur("Roy", "456", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),

        new Fournisseur("Bouchard", "789","456 avenue des Automates, Montr�al, QC, H5M 1N2",
                        "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "25", "Automatech"),

        new Fournisseur("Adams", "159","2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5",
                        "service@innovatech.ca", "4509998888", "RobotC", "ECRAN","27", "Innovatech"),

        new Fournisseur("Wilson", "753","89 boulevard de la Technologie, Laval, QC, H7M 7B7",
                        "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA","35", "iRobot"),

        new Fournisseur("Thompson", "486", "10 Place de la Robotique, Longueuil, QC, J4H 1A1",
                        "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR","22", "RoboPro")
        ));

        tempList.stream().forEach(fournisseur -> {
         this.ajouterObjet(fournisseur);
        });
    }






    /**
     * Cette méthode retourne un robot d'un fournisseur donné.
     * @param numeroSerie Le numéro de série du robot à retourner.
     * @return Le robot correspondant au numéro de série donné, ou null si aucun robot correspondant n'est trouvé.
     */
    public Robot retournerRobot(String numeroSerie){
       return (Robot) this.getListObjet().stream()
                       .flatMap(f -> ((Fournisseur)f).getInventaireDeRobot().stream())
                       .filter(r->((Robot) r).getNumeroSerie().toString().trim().equals(numeroSerie.trim()))
                       .findFirst()
                       .orElse(null);
     }

    /**
     * Cette méthode retourne une composante d'un fournisseur donné.
     * @param numero Le numéro de la composante à retourner.
     * @return La composante correspondante au numéro donné, ou null si aucune composante correspondante n'est trouvée.
     * @author Boubacar Hama Bague
     */
    public Composant retournerComposante(int numero){
        return (Composant) this.getListObjet().stream()
                .flatMap(f -> ((Fournisseur)f).getInventaireComposant().stream())
                .filter(composant -> ((Composant) composant).getNumero()==numero)
                .findFirst()
                .orElse(null);
    }

    /**
     * Cette méthode retourne la liste des information des tous les fournisseurs sous forme de chaine.
     * @return La liste des fournisseurs.
     * @author Boubacar Hama Bague
     */
    public String recupererLalisteDesFournisseur()
    {
        return (String) this.getListObjet().stream()
                .map(fournisseur ->((Fournisseur)fournisseur).getProfilFournisseur())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Cette méthode recherche une composante par son nom ou ses trois premières sous-chaînes.
     * @param nomOuTroisPremierSousChaine Le nom de la composante ou les trois premières sous-chaînes.
     * @return Les informations formatées de la composante, ou un message d'erreur si la composante n'est pas trouvée.
     * @author Boubacar Hama Bague
     */
    public String rechercherComposantParNomOuTroisSouschaine(String nomOuTroisPremierSousChaine){
        String composants= (String) this.getListObjet().stream()
                .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                .filter(composant -> ((Composant) composant).getNom().trim().equals(nomOuTroisPremierSousChaine.trim()) ||
                        ((Composant) composant).getNom().substring(0, 3).trim().equals(nomOuTroisPremierSousChaine.trim()))
                .map(composant -> ((Composant) composant).getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
        return  composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom ou les trois premier caractère" :composants;
    }

    /**
     * Cette méthode recherche une composante par son type.
     * @param typeComposant Le type de la composante.
     * @return Les informations formatées de la composante, ou un message d'erreur si la composante n'est pas trouvée.
     * @author Boubacar Hama Bague
     */
    public String rechercherComposantParType(String typeComposant){
        String composants = (String) this.getListObjet().stream()
                .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                .filter(composant -> ((Composant) composant).getTypeComposant().toLowerCase().trim().equals(typeComposant.toLowerCase().trim()))
                .map(c->((Composant) c).getInfoComposantFormater())
                .collect(Collectors.joining("\n"));

        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le type" : composants;
    }

    /**
     * Cette méthode recherche une composante par le nom de son fournisseur.
     * @param nomFournisseur Le nom du fournisseur.
     * @return Les informations formatées de la composante, ou un message d'erreur si la composante n'est pas trouvée.
     * @author Boubacar Hama Bague
     */
    public String rechercherComposantParNomFournisseur(String nomFournisseur){
        String composants= (String) this.getListObjet().stream().filter(f->((Fournisseur)f).getNom()
                .toLowerCase().trim().equals(nomFournisseur.toLowerCase().trim()))
                .findFirst()
                .flatMap(f->((Fournisseur)f).getInventaireComposant())
                .map(c->((Composant)c).getInfoComposantFormater())
                .stream().collect(Collectors.joining("\n"));
        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom du fourniseur": composants;
    }

    /**
     * Cette méthode recherche un fournisseur par son nom.
     * @param nom Le nom du fournisseur.
     * @return Le profil du fournisseur, ou un message d'erreur si le fournisseur n'est pas trouvé.
     * @author Boubacar Hama Bague
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
     * Cette méthode recherche un fournisseur par son adresse.
     * @param adresse L'adresse du fournisseur.
     * @return Le profil du fournisseur, ou un message d'erreur si le fournisseur n'est pas trouvé.
     * @author Boubacar Hama Bague
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
     * Cette méthode recherche un fournisseur par le type de composant qu'il fabrique.
     * @param typeDeComposant Le type de composant fabriqué par le fournisseur.
     * @return Le profil du fournisseur, ou un message d'erreur si le fournisseur n'est pas trouvé.
     * @author Boubacar Hama Bague
     */
    public String rechercherFournisseurParTypeDeComposant(String typeDeComposant){
        String founisseurs= (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getTypeRobotFabriquer().equals(typeDeComposant))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
        return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier le type" : founisseurs;
    }


    /**
     * Cette méthode recherche un fournisseur par son email.
     * @param email L'email du fournisseur.
     * @return Le profil du fournisseur, ou un message d'erreur si le fournisseur n'est pas trouvé.
     * @author Boubacar Hama Bague
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
     * Cette méthode vérifie si le nom d'un fourniseur est unique.
     * @param nomFournisseur Le nom du fournisseur.
     * @return True si le fournisseur existe, false sinon.
     * @author Boubacar Hama Bague
     */
    public boolean verifierNomFounissseur(String nomFournisseur){
         return this.getListObjet().stream()
                 .anyMatch(f-> ((Fournisseur) f).getNom().toLowerCase().trim().equals(nomFournisseur.toLowerCase().trim()));

    }

    /**
     * Cette méthode recherche un composant par son nom.
     * @param nom Le nom du composant.
     * @return Les informations sur le composant, ou un message d'erreur si le composant n'est pas trouvé.
     * @author Boubacar Hama Bague
     */
    public String rehercherComposantParNom(String nom){
        String composants = (String) this.getListObjet().stream().flatMap(f->((Fournisseur)f).getInventaireComposant().stream())
                .filter(c->((Composant)c).getNom().toLowerCase().trim().equals(nom.toLowerCase().trim()))
                .map(c->((Composant) c).getInfoComposantFormater())
                .collect(Collectors.joining("\n"));

        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom" : composants;
    }

    /**
     * Cette méthode permet l'authentification d'un fournisseur par son nom et son mot de passe.
     * @param nomFournisseur Le nom du fournisseur.
     * @param mdp Le mot de passe du fournisseur.
     * @return L'objet Fournisseur si l'authentification réussit, ou null si elle échoue.
     * @author Boubacar Hama Bague
     */
    public  Fournisseur authentificatiFournisseur(String nomFournisseur, String mdp){
         return (Fournisseur) this.getListObjet().stream()
                 .filter(f-> ((Fournisseur) f).getNom().equals(nomFournisseur) &&
                         ((Fournisseur) f).getMotDePasse().equals(mdp))
                 .findFirst()
                 .orElse(null);
    }

    /**
     * Cette méthode obtient la liste des robots d'un fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur.
     * @return Les informations sur tous les robots du fournisseur.
     * @author Boubacar Hama Bague
     */
    public String obtenirListRobotFournisseur(String nomFournisseur){
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .map(r->((Robot) r).getInfoRobotFormater())
                .collect(Collectors.joining("\n"));
    }
    /**
     * Cette méthode obtient la liste des composants d'un fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur.
     * @return Les informations sur tous les composants du fournisseur.
     * @author Boubacar Hama Bague
     */
    public String obtenirListComposantFournisseur(String nomFournisseur){
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                .map(c->((Composant) c).getInfoComposantFormaterPourVendre())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Cette méthode permet d'acheter un robot auprès d'un fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur auprès duquel le robot doit être acheté.
     * @param numero Le numéro du robot à acheter.
     * @return Le numéro de série du robot acheté, ou null si aucun robot correspondant n'est trouvé.
     *
     * @author Boubacar Hama Bague
     */
    public UUID acheterRobot(String nomFournisseur, int numero){
       return (UUID) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .filter(r->((Robot) r).getNumero()==numero )
                .findFirst()
                .map(r->((Robot)r).getNumeroSerie())
                .orElse(null);

    }
    /**
     * Cette méthode permet d'acheter un composant auprès d'un fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur auprès duquel le composant doit être acheté.
     * @param numero Le numéro du composant à acheter.
     * @return Le nom du composant acheté, ou null si aucun composant correspondant n'est trouvé.
     * @author Boubacar Hama Bague
     */
    public String acheterComposant(String nomFournisseur, int numero)
    {
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireComposant().stream())
                .findFirst()
                .filter(c->((Composant) c).getNumero()==numero)
                .map(c->((Composant) c).getNom())
                .orElse(null);
    }


    /**
     * Cette méthode retourne un objet fournisseur spécifique.
     * @param nomFournisseur Le nom du fournisseur à retourner.
     * @return L'objet Fournisseur correspondant, ou null si aucun fournisseur correspondant n'est trouvé.
     * @author Boubacar Hama Bague
     */

    public  Fournisseur retournerFournisseur(String nomFournisseur)
    {
        return (Fournisseur) this.getListObjet().stream().filter(f->
                ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .findFirst()
                .orElse(null);
    }

}