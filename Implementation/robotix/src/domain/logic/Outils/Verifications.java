package domain.logic.Outils;

import java.util.Arrays;
import java.util.List;

public class Verifications {
    public static boolean verifierEmail(String inputEmail) {
        String[] emailValide = {"@umontreal.ca", "@ulaval.ca", "@gmail.com", "@outlook.com"};
        List<String> liste = Arrays.asList(emailValide);
        String end = "";
        if (inputEmail.contains("@")){
            end = inputEmail.substring(inputEmail.indexOf("@"));
        }
        return liste.contains(end);
    }

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
