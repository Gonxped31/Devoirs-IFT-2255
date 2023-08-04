package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;

/**
 * Un affichage personnalisé pour les éléments d'une liste qui peuvent être représentés sous forme de code HTML.
 * Cette classe hérite de DefaultListCellRenderer pour personnaliser le rendu des éléments dans une JList.
 */
public class HTMLListCellRenderer extends DefaultListCellRenderer {
    /**
     * Renvoie le composant qui rend l'élément dans la liste avec un rendu spécifique pour le contenu HTML.
     *
     * @param list         la liste pour laquelle le rendu est effectué
     * @param value        la valeur de l'élément à rendre
     * @param index        l'index de l'élément à rendre dans la liste
     * @param isSelected   indique si l'élément est sélectionné
     * @param cellHasFocus indique si l'élément a le focus
     * @return le composant JLabel qui représente l'élément dans la liste avec le rendu approprié pour le contenu HTML
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (c instanceof JLabel && value instanceof String) {
            JLabel label = (JLabel) c;
            label.setText((String) value);
        }
        return c;
    }
}

