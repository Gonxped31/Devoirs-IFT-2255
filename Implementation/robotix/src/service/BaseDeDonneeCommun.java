package service;

import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Membre;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class BaseDeDonneeCommun extends BaseDeDonnee {


        private List<Map<String, List<Robot>>> listRobot;
        private List<Map<String, List<Composant>>> listComposant;
        public BaseDeDonneeCommun(String fileName) throws IOException {
            super(fileName);
            listComposant = new ArrayList<>();
            listRobot = new ArrayList<>();
            if(!listRobot.equals(null) && !listComposant.equals(null)) {
                initListeRobotEtComposant();
            }
        }




        @Override
        protected Type getType() {
            return new TypeToken<ArrayList<Membre>>(){}.getType();
        }


        @Override
        protected void init() {

        }

   protected void initListeRobotEtComposant() {

        }




        public List<Map<String, List<Composant>>> getListComposant() {
            return listComposant;
        }

        public List<Map<String, List<Robot>>> getListRobot() {
            return listRobot;
        }

        public Robot retournerRobot(String numeroSerie) {
            return listRobot.stream()
                    .flatMap(map -> map.values().stream())
                    .flatMap(List::stream)
                    .filter(robot -> robot.getNumeroSerie().equals(numeroSerie))
                    .findFirst()
                    .orElse(null);
        }

    }


