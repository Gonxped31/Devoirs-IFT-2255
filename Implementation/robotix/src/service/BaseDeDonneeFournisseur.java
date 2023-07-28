package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class BaseDeDonneeFournisseur  extends BaseDeDonneeCommun{
        private static final String FILE_NAME = "fournisseur.json";
        private List<Map<String, List<Robot>>> listRobot;
        private List<Map<String, List<Composant>>> listComposant;

     public BaseDeDonneeFournisseur() throws IOException {
            super(FILE_NAME,new TypeReference<ArrayList<Fournisseur>>() {});
            listComposant= new ArrayList<>();
            listRobot=new ArrayList<>();
            initListeRobotEtComposant();
        }




    @Override
    protected void init() {
         //Todo
        List<Fournisseur> tempList= new ArrayList<>(Arrays.asList(

       new Fournisseur("Roy", "123" ,"123 rue des Innovations, Montr�al, QC, H1A 0A1",  "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),

        new Fournisseur("Kare", "456", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "nom1@robotech.ca",
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
       
    public List<Map<String, List<Composant>>> getListComposant(){
        return listComposant;
    }

    public List<Map<String, List<Robot>>> getListRobot() {
        return listRobot;
    }

    public Robot retournerRobot(String numeroSerie){
       return listRobot.stream()
                       .flatMap(map -> map.values().stream())
                       .flatMap(List::stream)
                       .filter(robot -> robot.getNumeroSerie().toString().trim().equals(numeroSerie.trim()))
                       .findFirst()
                       .orElse(null);
            /*for (Map<String, List<Robot>> map : listRobot) {
                for (List<Robot> robotList : map.values()) {
                    for (Robot robot : robotList) {
                        if (robot.getNumeroSerie().toString().trim().equals(numeroSerie.trim())) {
                            return robot;
                        }
                    }
                }
            }
            return null;*/
    }

    public Composant retournerComposante(String nom){
        return listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    public Fournisseur retournerFournisseur(String nom){
        return (Fournisseur) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    public String recupererLalisteDesFournisseur()
    {
        return (String) this.getListObjet().stream()
                .map(fournisseur ->((Fournisseur)fournisseur).getProfilFournisseur())
                .collect(Collectors.joining("\n"));
    }

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

    public String rechercherComposantParNomFournisseur(String nomFournisseur){
        String composants= this.listComposant.stream()
                .filter(map -> map.containsKey(nomFournisseur))
                .flatMap(map -> map.get(nomFournisseur).stream())
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));
        return composants.isEmpty() ? "Aucune composante trouvée...": composants;
    }

    public String rechercherFournisseurParNom(String nom){
        return (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getNom().equals(nom))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .findFirst()
                .orElse("Fournisseur non trouver, veuillez verifier le nom");
    }

    public String rechercherFournisseurParAdresse(String adresse){
        String founisseurs= (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getAdresse().equals(adresse))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
             return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier l'adresse" : founisseurs;
    }

    public String rechercherFournisseurParTypeDeComposant(String typeDeComposant){
        String founisseurs= (String) this.getListObjet().stream()
                .filter( fournisseur ->( (Fournisseur) fournisseur).getTypeRobotFabriquer().equals(typeDeComposant))
                .map(f-> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
        return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier le type" : founisseurs;
    }


    public String rechercherFournisseurParEmail(String email) {
        String founisseurs = (String) this.getListObjet().stream()
                .filter(fournisseur -> ((Fournisseur) fournisseur).getEmail().equals(email))
                .map(f -> {
                    return ((Fournisseur) f).getProfilFournisseur();
                })
                .collect(Collectors.joining("\n"));
        return founisseurs.isEmpty() ? "Fournisseur non trouver, veuillez verifier l'email" : founisseurs;
    }
    public boolean verifierNomFounissseur(String nomFourniseur){
         return this.getListObjet().stream()
                 .anyMatch(f-> ((Fournisseur) f).getNom().equals(nomFourniseur));

    }

    public String rehercherComposantParNom(String nom){
        String composants =this.listComposant.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(composant -> composant.getNom().equals(nom))
                .map(composant -> composant.getInfoComposantFormater())
                .collect(Collectors.joining("\n"));

        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom" : composants;
    }

    public  Fournisseur authentificatiFournisseur(String nomFournisseur, String mdp){
         return (Fournisseur) this.getListObjet().stream()
                 .filter(f-> ((Fournisseur) f).getNom().equals(nomFournisseur) &&
                         ((Fournisseur) f).getMotDePasse().equals(mdp))
                 .findFirst()
                 .orElse(null);
    }

    public String obtenirListRobotFournisseur(String nomFournisseur){
        return (String) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .map(r->((Robot) r).getInfoRobotFormater())
                .collect(Collectors.joining("\n"));
    }
    public UUID acheterRobot(String nomFournisseur, int numero){
        return (UUID) this.getListObjet().stream()
                .filter(f -> ((Fournisseur) f).getNom().trim().equals(nomFournisseur.trim()))
                .flatMap(f -> ((Fournisseur) f).getInventaireDeRobot().stream())
                .filter(r->((Robot) r).getNumero()==numero )
                .findFirst()
                .map(r->((Robot) r).getNumeroSerie())
                .orElse(null);

    }

}