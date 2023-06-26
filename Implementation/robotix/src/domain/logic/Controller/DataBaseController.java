package domain.logic.Controller;

import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateurs;
import domain.logic.Robot.Robot;

import java.util.ArrayList;
import java.util.LinkedList;

public class DataBaseController {
    private ArrayList<Fournisseur> listeFournisseurs = genererFournisseurs();
    private ArrayList<Utilisateurs> listeUtilisateurs = genererUtilisateurs();

    public ArrayList<Fournisseur> getListeFournisseurs() {
        return this.listeFournisseurs;
    }

    public ArrayList<Utilisateurs> getListeUtilisateurs() {
        return this.listeUtilisateurs;
    }

    public void ajouterUtilisateur(Utilisateurs utilisateur){
        listeUtilisateurs.add(utilisateur);
    }

    public void ajouterFournisseur(Fournisseur fournisseur){
        listeFournisseurs.add(fournisseur);
    }

    private ArrayList<Utilisateurs> genererUtilisateurs() {
        ArrayList<Utilisateurs> listeUtilisateurs = new ArrayList<>();
        return listeUtilisateurs;
    }

    /*
            M�thode qui permet la creation de fournisseurs deja inscrits dans le systeme
        */
    private ArrayList<Fournisseur> genererFournisseurs() {
        Fournisseur founisseur1 = new Fournisseur("Roy", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "roy1",
                "nom1@robotech.ca", "5142104555", "RobotA", "CPU", "RoboTechnologies");
        Fournisseur founisseur2 = new Fournisseur("Bouchard", "456 avenue des Automates, Montr�al, QC, H5M 1N2", "bouchard2",
                "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "Automatech");
        Fournisseur founisseur3 = new Fournisseur("Adams", "2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5", "adams3",
                "service@innovatech.ca", "4509998888", "RobotC", "ECRAN", "Innovatech");
        Fournisseur founisseur4 = new Fournisseur("Wilson", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "wilson4",
                "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA", "iRobot");
        Fournisseur founisseur5 = new Fournisseur("Thompson", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "thompson5",
                "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR", "RoboPro");

        listeFournisseurs.add(founisseur1);
        listeFournisseurs.add(founisseur2);
        listeFournisseurs.add(founisseur3);
        listeFournisseurs.add(founisseur4);
        listeFournisseurs.add(founisseur5);

        return listeFournisseurs;
    }

}
