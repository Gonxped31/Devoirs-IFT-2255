package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;
/**
 * Une classe personnalisée pour rendre les éléments d'une liste avec des cases à cocher (checkboxes).
 * Cette classe implémente l'interface ListCellRenderer pour personnaliser le rendu des éléments dans une JList.
 */
public class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer<String> {
    /**
     * Renvoie le composant qui rend l'élément dans la liste avec des cases à cocher.
     *
     * @param list         la liste pour laquelle le rendu est effectué
     * @param value        la valeur de l'élément à rendre
     * @param index        l'index de l'élément à rendre dans la liste
     * @param isSelected   indique si l'élément est sélectionné
     * @param cellHasFocus indique si l'élément a le focus
     * @return le composant JCheckBox qui représente l'élément dans la liste avec le rendu approprié
     */
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        // Configuration des propriétés du composant JCheckBox
        setEnabled(list.isEnabled());
        setSelected(isSelected);
        setFont(list.getFont());
        setText(value);
        return this;
    }
}

