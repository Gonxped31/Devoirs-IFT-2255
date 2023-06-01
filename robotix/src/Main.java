import java.awt.*;
import java.util.ArrayList;

import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Fournisseur.Type;
import domain.logic.Robot.TypeRobot;

public class Main {
    public static void main(String[] args) {

        Fournisseur roboTech = new Fournisseur("RoboTech", "123 rue des Innovations, Montréal, QC, H1A 0A1", "info@robotech.ca",
                "4502104555", TypeRobot.A, 30);
        Fournisseur automatech = new Fournisseur("Automatech", "456 avenue des Automates, Montréal, QC, H5M 1N2", "contact@automatech.ca",
                "4503335432", TypeRobot.B, 25);
        Fournisseur innovatech = new Fournisseur("Innovatech", "2376 boulevard des Génies, Québec, QC, G1W 2W5", "service@innovatech.ca",
                "4509998888", TypeRobot.C, 27);
        Fournisseur iRobot = new Fournisseur("iRobot", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "assistance@iRobot.ca",
                "4502109876", TypeRobot.B, 35);
        Fournisseur roboPro = new Fournisseur("RoboPro", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "info@roboPro.ca",
                "4506780000", TypeRobot.A, 22);

        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
        listeFournisseurs.add(roboTech);
        listeFournisseurs.add(automatech);
        listeFournisseurs.add(innovatech);
        listeFournisseurs.add(iRobot);
        listeFournisseurs.add(roboPro);

    }


}