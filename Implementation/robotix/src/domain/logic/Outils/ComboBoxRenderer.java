package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
    public ComboBoxRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String itemValue = (String) value;
        setText(itemValue);
        return this;
    }
}
