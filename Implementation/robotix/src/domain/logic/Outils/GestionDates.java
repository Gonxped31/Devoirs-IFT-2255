package domain.logic.Outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Cette classe représente une utilité pour la gestion des dates.
 */
public class GestionDates {

    /**
     * Vérifie si la date de début est avant ou égale à la date de fin.
     * @param dateDebut La date de début.
     * @param dateFin La date de fin.
     * @return {@code true} si la date de début est avant ou égale à la date de fin, {@code false} sinon.
     */    public static boolean verifierCoherenceDate(String dateDebut, String dateFin){
        return LocalDate.parse(dateDebut).isBefore(LocalDate.parse(dateFin)) || LocalDate.parse(dateDebut).isEqual(LocalDate.parse(dateFin));
    }

    /**
     * Valide le format de la date.
     * @param date La date à valider.
     * @return {@code true} si la date est au format correct et valide, {@code false} sinon.
     */    public static boolean validerDate(String date){
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

    /**
     * Vérifie si la date donnée est au moins égale à la date d'aujourd'hui ou une date future.
     * @param date La date à vérifier.
     * @return {@code true} si la date est égale à la date d'aujourd'hui ou une date future, {@code false} sinon.
     */    public static boolean veriferSiDateRealiste(String date){
        return (LocalDate.parse(date).isEqual(LocalDate.now()) || LocalDate.parse(date).isAfter(LocalDate.now()));
    }



}
