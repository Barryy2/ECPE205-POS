import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CodeScreen extends JFrame {

    private ArrayList<Orders> orders = new ArrayList<>();
    private JTable table;
    private JTextField Inputsku;
    private ArrayList<Prods> products;

    private JLabel totalBeforeDiscountLabel;
    private JLabel discountLabel;
    private JLabel finalTotalLabel;

    public CodeScreen(ArrayList<Prods> products) {
        this.products = products;

        Inputsku = new JTextField(20);
        JButton Enterbutton = new JButton("Enter");
        JLabel skuinput = new JLabel("Input Product: ");
        JButton Seniordiscountbutton = new JButton("Add Senior Discount");
        JButton ClearButton = new JButton("Clear");

        setLayout(new GridBagLayout());

        // Top bar
        addComponent(1, 0, Inputsku);
        addComponent(0, 0, skuinput);
        addComponent(2, 0, Enterbutton);
        addComponent(3, 0, Seniordiscountbutton);
        addComponent(4, 0, ClearButton);

        // Table
        table = new JTable(new AbstractTableModel() {
            String[] columns = new String[]{"SKU", "Name", "Price", "Qty", "Amount"};

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
                    case 0: return ords.getSku();
                    case 1: return ords.getName();
                    case 2: return ords.getPrice();
                    case 3: return ords.getQty();
                    case 4: return ords.getAmount();
                    default: return null;
                }
            }
        });

        addComponent(0, 3, 5, new JScrollPane(table));

        // Bottom total panels
        JPanel totalsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        totalBeforeDiscountLabel = new JLabel("Total: 0.00");
        discountLabel = new JLabel("Discount: 0.00");
        finalTotalLabel = new JLabel("Final Total: 0.00");

        totalsPanel.add(totalBeforeDiscountLabel);
        totalsPanel.add(discountLabel);
        totalsPanel.add(finalTotalLabel);

        addComponent(0, 4, 5, totalsPanel);

        // Enter product
        Enterbutton.addActionListener(e -> {
            String input = Inputsku.getText().trim();
            if (input.isEmpty()) return;

            Prods foundProduct = null;
            for (Prods p : products) {
                if (p.getSku().equalsIgnoreCase(input) || p.getName().equalsIgnoreCase(input)) {
                    foundProduct = p;
                    break;
                }
            }

            if (foundProduct == null) {
                JOptionPane.showMessageDialog(null, "Product not found");
                return;
            }

            Orders existingOrder = null;
            for (Orders o : orders) {
                if (o.getSku().equalsIgnoreCase(foundProduct.getSku())) {
                    existingOrder = o;
                    break;
                }
            }

            if (existingOrder != null) {
                int qty = Integer.parseInt(existingOrder.getQty()) + 1;
                double price = Double.parseDouble(foundProduct.getPrice());
                double amount = price * qty;

                existingOrder.setQty(String.valueOf(qty));
                existingOrder.setAmount(String.valueOf(amount));
            } else {
                int qty = 1;
                double price = Double.parseDouble(foundProduct.getPrice());
                double amount = price * qty;

                orders.add(new Orders(
                        foundProduct.getSku(),
                        foundProduct.getName(),
                        foundProduct.getPrice(),
                        String.valueOf(qty),
                        String.valueOf(amount)
                ));
            }

            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
            updateTotals();
            Inputsku.setText("");
        });

        // Senior discount
        Seniordiscountbutton.addActionListener(e -> {
            applySeniorDiscount();
        });

        // Clear
        ClearButton.addActionListener(e -> {
            orders.clear();
            ((AbstractTableModel) table.getModel()).fireTableDataChanged();
            updateTotals();
            Inputsku.setText("");
        });

        setTitle("Order Screen");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void updateTotals() {
        double total = 0;
        for (Orders o : orders) {
            total += Double.parseDouble(o.getAmount());
        }

        double discount = 0;
        double finalTotal = total - discount;

        totalBeforeDiscountLabel.setText(String.format("Total: %.2f", total));
        discountLabel.setText(String.format("Discount: %.2f", discount));
        finalTotalLabel.setText(String.format("Final Total: %.2f", finalTotal));
    }

    private void applySeniorDiscount() {
        double totalBeforeDiscount = 0;
        double totalAfterDiscount = 0;
        double tax = 0.05;
        String choiceschecker = "Non Basic Commodity";
        for (Orders o : orders) {
            double price = Double.parseDouble(o.getPrice());
            int qty = Integer.parseInt(o.getQty());
            totalBeforeDiscount += price * qty;

            String commodity = "";
            for (Prods p : products) {
                if (p.getSku().equalsIgnoreCase(o.getSku())) {
                    commodity = p.getCommodities();
                    break;
                }
            }

            if (choiceschecker.equalsIgnoreCase(commodity)) {
                //for nonbasic
                double discountedPrice = price - (price / 1.12 * 0.12) - (price / 1.12 * 0.2);
                totalAfterDiscount += discountedPrice * qty;
            } else {
                //basic formula
                double discountedPrice = price - (price * tax);
                totalAfterDiscount += discountedPrice * qty;
            }
        }

        double discount = totalBeforeDiscount - totalAfterDiscount;

        totalBeforeDiscountLabel.setText(String.format("Total: %.2f", totalBeforeDiscount));
        discountLabel.setText(String.format("Discount: %.2f", discount));
        finalTotalLabel.setText(String.format("Final Total: %.2f", totalAfterDiscount));
    }

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