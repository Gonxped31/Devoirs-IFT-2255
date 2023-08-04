package domain.logic.Outils;

import java.util.Arrays;
import java.util.List;

/**
 * Une classe utilitaire contenant des méthodes pour effectuer des vérifications sur des chaînes de caractères.
 */
public class Verifications {

    /**
     * Vérifie si une adresse email est valide en se basant sur une liste d'extensions de domaine autorisées.
     *
     * @param inputEmail l'adresse email à vérifier
     * @return true si l'adresse email est valide, false sinon
     */
    public static boolean verifierEmail(String inputEmail) {
        String[] emailValide = {"@umontreal.ca", "@ulaval.ca", "@gmail.com", "@outlook.com"};
        List<String> liste = Arrays.asList(emailValide);
        String end = "";
        if (inputEmail.contains("@")){
            end = inputEmail.substring(inputEmail.indexOf("@"));
        }
        return liste.contains(end);
    }

    /**
     * Vérifie si un numéro de téléphone est valide.
     *
     * @param inputTelephone le numéro de téléphone à vérifier
     * @return true si le numéro de téléphone est valide (10 chiffres), false sinon
     */
    public static boolean verifierTelephone(String inputTelephone) {
        if (inputTelephone.length() == 10){
            try {
                Integer.parseInt(inputTelephone);
                return true;
            } catch (NumberFormatException e){
                return false;
            }
        } else {
            return false;
        }
    }
}
