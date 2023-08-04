package domain.logic.Robot;

/**
 * Énumération des types de composants possibles pour un robot.
 * Chaque type de composant représente une caractéristique ou un équipement spécifique du robot.
 */
public enum TypesComposants {
    CPU, // Processeur central
    ECRAN, // Écran d'affichage
    ROUE, // Roue pour la mobilité
    HELICE, // Hélice pour la mobilité
    CAMERA, // Caméra pour la capture d'images/vidéos
    HAUTPARLEUR, // Haut-parleur pour la sortie audio
    MICRO, // Micro pour l'entrée audio
    BRAS; // Bras pour des actions spécifiques
}
