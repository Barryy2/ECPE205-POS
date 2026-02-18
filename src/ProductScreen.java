import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductScreen extends JFrame {

    private ArrayList<Prods> prods = new ArrayList<>();

    public ProductScreen() {

        //TASK1
        String[] choices = {"Basic Commodity","Non Basic Commodity"};
        final JComboBox<String> Choices = new JComboBox<String>(choices);

        Choices.setSize(20,10);
        JLabel SKU = new JLabel("SKU: ");
        JLabel Name = new JLabel("Name: ");
        JLabel Price = new JLabel("Price: ");
        JLabel CommodityList = new JLabel("Commodity");
        JLabel Commodity = new JLabel("Commodity: ");
        JTextField SKUtextfield = new JTextField(30);
        JTextField Nametextfield = new JTextField(30);
        JTextField Pricedtextfield = new JTextField(30);
        JButton Savebutt = new JButton("Save");


        setLayout(new GridBagLayout());

        // LABELS
        addComponent(0,0 , SKU);
        addComponent(0,1 , Name);
        addComponent(0 ,2,  Price);
        addComponent(0,3,Commodity);
        // TEXTFIELDS
        addComponent(1,0,SKUtextfield);
        addComponent(1,1,Nametextfield);
        addComponent(1,2,Pricedtextfield);
        // BUTTON
        addComponent(1,4,Savebutt);
        addComponent(1,3,Choices);


        // Table
        JTable table = new JTable(new AbstractTableModel() {
            String [] columns = new String[] {"SKU" , "Name" , "Price", "Commodity"};

            @Override
            public String getColumnName(int column){
                return columns[column];
            }

            @Override
            public int getRowCount() {
                return prods.size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }




            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Prods p = prods.get(rowIndex);
                switch (columnIndex) {
                    case 0: return p.getSku();
                    case 1: return p.getName();
                    case 2: return p.getPrice();
                    case 3: return p.getCommodities();
                    default: return null;
                }
            }
        });

        addComponent(0,5, 2,new JScrollPane(table) );

        // SAVE BUTTON
        Savebutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sku = SKUtextfield.getText();
                String name = Nametextfield.getText();
                String price = Pricedtextfield.getText();
                //TASK 2
                String comms = (String) Choices.getSelectedItem();

                if (!sku.isEmpty() && !name.isEmpty() && !price.isEmpty()  ) {
                    prods.add(new Prods(sku, name, price,comms));
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();

                    SKUtextfield.setText("");
                    Nametextfield.setText("");
                    Pricedtextfield.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });

        setTitle("ProductScreen");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public ArrayList<Prods> getProductList() {
        return prods;
    }






    private void addComponent(int x , int y , int width , Component c){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        add(c ,constraints);
        constraints.anchor = GridBagConstraints.CENTER;
    }

    private void addComponent(int x , int y , Component c){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        add(c ,constraints);
    }

    private void addComponent(int x , int y , int width , int fill , Component c){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
        add(c ,constraints);
    }
}