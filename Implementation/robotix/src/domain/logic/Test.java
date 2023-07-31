package domain.logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Test extends JFrame {
    private JList<String> supplierList;
    private JScrollPane scrollPane;
    private ArrayList<Supplier> suppliers;

    public Test() {
        suppliers = new ArrayList<>();
        suppliers.add(new Supplier("Supplier 1", "Address 1", "Contact 1"));
        suppliers.add(new Supplier("Supplier 2", "Address 2", "Contact 2"));
        // Add more suppliers with their characteristics

        supplierList = new JList<>(suppliers.stream().map(Supplier::getName).toArray(String[]::new));
        supplierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        supplierList.setVisibleRowCount(5);
        supplierList.setCellRenderer(new ComboBoxRenderer());

        supplierList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = supplierList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < suppliers.size()) {
                    Supplier selectedSupplier = suppliers.get(selectedIndex);
                    showSupplierDetails(selectedSupplier);
                    supplierList.clearSelection(); // Deselect the item after showing the details
                }
            }
        });

        scrollPane = new JScrollPane(supplierList);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setTitle("Supplier List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showSupplierDetails(Supplier supplier) {
        String message = "Supplier: " + supplier.getName() + "\n" +
                "Address: " + supplier.getAddress() + "\n" +
                "Contact: " + supplier.getContact();
        JOptionPane.showMessageDialog(this, message, "Supplier Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private static class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
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

    private static class Supplier {
        private String name;
        private String address;
        private String contact;

        public Supplier(String name, String address, String contact) {
            this.name = name;
            this.address = address;
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getContact() {
            return contact;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::new);
    }
}


