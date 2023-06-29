package service;

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
        super(FILE_NAME);
         
     }

      public BaseDeDonneeFournisseur(String fileName) throws IOException {
        super(fileName);
        listComposant= new ArrayList<>();
        listRobot=new ArrayList<>();
        initListeRobotEtComposant();
     }

    @Override
    protected Type getType() {
        return new TypeToken<ArrayList<Fournisseur>>(){}.getType();
    }



    @Override
    protected void init() {
         //Todo
        List<Fournisseur> tempList= new ArrayList<>(Arrays.asList(
       /* new Fournisseur("Roy", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "roy1", "nom1@robotech.ca",
                        "5142104555", "RobotA", "CPU", "30", "RoboTechnologies"),
        new Fournisseur("Bouchard", "456 avenue des Automates, Montr�al, QC, H5M 1N2", "bouchard2",
                        "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "25", "Automatech"),
        new Fournisseur("Adams", "2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5", "adams3",
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

        private void initListeRobotEtComposant(){

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
                       .filter(robot -> robot.getNumeroSerie().equals(numeroSerie))
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
                .filter(composant -> composant.getTypeComposant().equals(typeComposant))
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
        return composants.isEmpty() ? "Composant non trouver, veuillez verifier le nom du fourniseur": composants;
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

    public boolean verifierNomFounissseur(String nomFourniseur){
         return this.getListObjet().stream()
                 .anyMatch(f-> ((Fournisseur) f).getNom().equals(nomFourniseur));
    }



}