package domain.logic.Outils;

import javax.swing.*;
import java.awt.*;

public class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer<String> {
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(isSelected);
        setFont(list.getFont());
        setText(value);
        return this;
    }
}

