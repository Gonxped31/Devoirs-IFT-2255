package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;

/**
 * Une classe personnalisée pour rendre les éléments d'une liste déroulante (combobox).
 * Cette classe implémente l'interface ListCellRenderer pour personnaliser le rendu des éléments dans une JList.
 */
public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {

    /**
     * Constructeur par défaut de la classe ComboBoxRenderer.
     * Initialise le composant JLabel en tant que rendu pour les éléments de la liste déroulante.
     * Active l'opacité pour permettre un rendu correct des couleurs de fond.
     */
    public ComboBoxRenderer() {
        setOpaque(true);
    }

    /**
     * Renvoie le composant qui rend l'élément dans la liste déroulante (combobox).
     *
     * @param list         la liste déroulante pour laquelle le rendu est effectué
     * @param value        la valeur de l'élément à rendre
     * @param index        l'index de l'élément à rendre dans la liste déroulante
     * @param isSelected   indique si l'élément est sélectionné
     * @param cellHasFocus indique si l'élément a le focus
     * @return le composant JLabel qui représente l'élément dans la liste déroulante avec le rendu approprié
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String itemValue = (String) value;
        setText(itemValue);
        return this;
    }
}
