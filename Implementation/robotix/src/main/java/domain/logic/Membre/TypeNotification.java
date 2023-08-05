package domain.logic.Membre;

/**
 * Enumération des types de notification possibles.
 */
public enum TypeNotification {
    PROBLEME_ROBOT, // Problème avec un robot
    ACTIVITES_INTERESSE, // Activités qui intéressent l'utilisateur
    NOUVEAU_ABONNE, // Nouvel abonné au service
    NOUVEAU_PARTICIPANT, // Nouveau participant à une activité
    SENSIBILISATION, // Sensibilisation sur un sujet
    ACHAT_COMPOSANTS, // Achat de composants
    ACHAT_ROBOT; // Achat d'un robot
}
