package domain.logic.Outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class GestionDates {

    // Verifer que la date de debut est bien avant la date de fin.
    public static boolean verifierCoherenceDate(String dateDebut, String dateFin){
        return LocalDate.parse(dateDebut).isBefore(LocalDate.parse(dateFin));
    }

    // Valider la date.
    public static boolean validerDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e){
            return false;
        }
        return true;
    }

    // Verifier si la date en parametre est au moins egale a la date  d'aujourd'hui.
    public static boolean veriferSiDateRealiste(String date){
        return (LocalDate.parse(date).isEqual(LocalDate.now()) || LocalDate.parse(date).isAfter(LocalDate.now()));
    }

}
