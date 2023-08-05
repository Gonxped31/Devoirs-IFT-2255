package domain.logic.Membre;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Représente une notification.
 */
public class Notification implements java.io.Serializable{
    private String titre;
    private String message;
    private String date;
    private TypeNotification typeNotification;

    /**
     * Crée une nouvelle notification avec les informations spécifiées.
     *
     * @param titre             Le titre de la notification.
     * @param message           Le message de la notification.
     * @param typeNotification  Le type de la notification (e.g., TypeNotification.NOUVEAU_ABONNE).
     */
    @JsonCreator
    public Notification(@JsonProperty("titre") String titre,
                        @JsonProperty("message") String message,
                        @JsonProperty("typeNotification") TypeNotification typeNotification){
        this.titre = titre;
        this.message = message;
        this.date = (new Date()).toString();
        this.typeNotification = typeNotification;
    }


    /**
     * Crée une nouvelle notification sans informations spécifiées.
     */
    public Notification() {

    }

    /**
     * Renvoie le titre de la notification.
     *
     * @return Le titre de la notification.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Renvoie le message de la notification.
     *
     * @return Le message de la notification.
     */
    public String getMesssage() {
        return message;
    }

    /**
     * Renvoie la date de la notification sous forme de String.
     *
     * @return La date de la notification (formatée en String).
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Renvoie le type de la notification.
     *
     * @return Le type de la notification.
     */
    public TypeNotification getTypeNotification() {
        return typeNotification;
    }


    /**
     * Définit le titre de la notification.
     *
     * @param titre Le nouveau titre de la notification.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Définit le message de la notification.
     *
     * @param message Le nouveau message de la notification.
     */
    public void setMesssage(String message) {
        this.message = message;
    }

    /**
     * Définit le type de la notification.
     *
     * @param typeNotification Le nouveau type de la notification.
     */
    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la notification.
     *
     * @return Une chaîne de caractères représentant la notification.
     */
    @Override
    public String toString() {
        return  getTitre().toUpperCase() + '\'' +
                getMesssage() + '\'' +
                "Date:" + getDate();
    }
}
