package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Membre;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;

/**
 * La classe BaseDeDonneeCommun étend la classe BaseDeDonnee et représente une base de données commune pour les fournisseurs,
 * les utilisateurs et les robots. Elle stocke les listes de robots et de composants associées aux fournisseurs et utilisateurs.
 * Cette classe utilise la sérialisation et la désérialisation de fichiers JSON pour lire et écrire les données dans la base de données.
 * Elle hérite de la classe BaseDeDonnee qui fournit des méthodes pour la gestion des fichiers JSON.
 */
public class BaseDeDonneeCommun extends BaseDeDonnee {

        /**
        * Liste des associations entre les fournisseurs/utilisateurs et leurs robots respectifs.
        */
        private List<Map<String, List<Robot>>> listRobot;

        /**
        * Liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs.
        */
        private List<Map<String, List<Composant>>> listComposant;

    /**
     * Crée une nouvelle instance de BaseDeDonneeCommun avec le nom du fichier JSON et le type de référence spécifié.
     *
     * @param fileName Le nom du fichier JSON à utiliser pour la base de données.
     * @param type     Le type de référence pour la désérialisation des données JSON.
     * @throws IOException    Si une erreur d'entrée/sortie se produit pendant le traitement.
     * @throws ParseException Si une erreur de parsing se produit pendant le traitement.
     */
        public <T> BaseDeDonneeCommun(String fileName, TypeReference<ArrayList<T>> type) throws IOException, ParseException {
            super(fileName,type );
            listComposant = new ArrayList<>();
            listRobot = new ArrayList<>();
        }


    /**
     * Méthode pour initialiser la base de données.
     * Cette méthode est actuellement vide, mais peut être étendue pour effectuer des opérations d'initialisation supplémentaires si nécessaire.
     */
        @Override
        protected void init() {

        }

    /**
     * Méthode pour initialiser les listes de robots et de composants associées aux fournisseurs et utilisateurs.
     * Cette méthode est actuellement vide, mais peut être étendue pour charger les données des fichiers JSON dans les listes appropriées.
     */
        protected void initListeRobotEtComposant() {

        }

        /**
        * Retourne la liste des associations entre les fournisseurs/utilisateurs et leurs composants respectifs.
        *
        * @return La liste des associations entre les fournisseurs/utilisateurs et leurs composants.
        */
        public List<Map<String, List<Composant>>> getListComposant() {
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
        public Robot retournerRobot(String numeroSerie) {
            return listRobot.stream()
                    .flatMap(map -> map.values().stream())
                    .flatMap(List::stream)
                    .filter(robot -> robot.getNumeroSerie().equals(numeroSerie))
                    .findFirst()
                    .orElse(null);
        }
}


