package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;

public class HTMLListCellRenderer extends DefaultListCellRenderer {
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

