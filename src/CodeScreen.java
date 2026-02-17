import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CodeScreen extends JFrame {

    private ArrayList<Orders> orders = new ArrayList<>();
    private JTable table;
    private JTextField Inputsku;
    private ArrayList<Prods> products; // Product list from ProductScreen

    public CodeScreen(ArrayList<Prods> products) {
        this.products = products; // store reference to ProductScreen products

        Inputsku = new JTextField(20);
        JButton Enterbutton = new JButton("Enter");
        JLabel skuinput = new JLabel("Input Product: ");
        JLabel Price = new JLabel("Price: ");

        setLayout(new GridBagLayout());
        // Top bar level
        addComponent(1, 0, Inputsku);
        addComponent(0, 0, skuinput);
        addComponent(2, 0, Enterbutton);
        // addComponent(2,3,Price);

        // Table lists
        table = new JTable(new AbstractTableModel() {
            String[] columns = new String[]{"SKU", "Name", "Price", "Qty", "amount"};

            @Override
            public String getColumnName(int column) {
                return columns[column];
            }

            @Override
            public int getRowCount() {
                return orders.size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Orders ords = orders.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return ords.getSku();
                    case 1:
                        return ords.getName();
                    case 2:
                        return ords.getPrice();
                    case 3:
                        return ords.getQty();
                    case 4:
                        return ords.getAmount();
                    default:
                        return null;
                }
            }
        });

        addComponent(0, 3, 4, new JScrollPane(table));

        // --- ENTER BUTTON FUNCTIONALITY ---
        Enterbutton.addActionListener(e -> {
            String input = Inputsku.getText().trim();
            if (input.isEmpty()) return;

            // Check product from ProductScreen
            Prods foundProduct = null;
            for (Prods p : products) {
                if (p.getSku().equalsIgnoreCase(input) || p.getName().equalsIgnoreCase(input)) {
                    foundProduct = p;
                    break;
                }
            }

            if (foundProduct == null) {
                JOptionPane.showMessageDialog(null, "Product not found!");
                return;
            }

            // Check if product already in orders
            Orders existingOrder = null;
            for (Orders o : orders) {
                if (o.getSku().equalsIgnoreCase(foundProduct.getSku())) {
                    existingOrder = o;
                    break;
                }
            }

            if (existingOrder != null) {
                // Increment quantity and update amount
                int qty = Integer.parseInt(existingOrder.getQty()) + 1;
                double price = Double.parseDouble(foundProduct.getPrice());
                double amount = price * qty;

                existingOrder.setQty(String.valueOf(qty));
                existingOrder.setAmount(String.valueOf(amount));
            } else {
                // Add new order
                int qty = 1;
                double price = Double.parseDouble(foundProduct.getPrice());
                double amount = price * qty;
                orders.add(new Orders(foundProduct.getSku(), foundProduct.getName(),
                        foundProduct.getPrice(), String.valueOf(qty), String.valueOf(amount)));
            }

            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
            Inputsku.setText("");
        });

        setTitle("Order Screen");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    // --- addComponent methods ---
    private void addComponent(int x, int y, int width, Component c) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        add(c, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
    }

    private void addComponent(int x, int y, Component c) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        add(c, constraints);
    }

    private void addComponent(int x, int y, int width, int fill, Component c) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
        add(c, constraints);
    }
}