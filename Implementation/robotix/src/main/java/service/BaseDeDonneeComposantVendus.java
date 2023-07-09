package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import java.io.IOException;
import java.util.ArrayList;

public class BaseDeDonneeComposantVendus extends BaseDeDonnee{


        private static final String FILE_NAME= "composantVendus.json";

        public BaseDeDonneeComposantVendus() throws IOException {
            super(FILE_NAME,new TypeReference<ArrayList<Composant>>() {});
        }


        @Override
        protected void init() {

        }
        public Composant getCurrentSoldComposant(String nom, int numero){
            return (Composant) this.getListObjet().stream()
                    .filter(c->((Composant)c).getNumero()==numero && ((Composant) c).getNom().trim().equals(nom.trim()) )
                    .findFirst()
                    .orElse(null);
        }

}
