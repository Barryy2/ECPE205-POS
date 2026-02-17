import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductScreen extends JFrame {

    ProductScreen() {
        int size = 5;
        JLabel SKU = new JLabel("SKU: ");
        JLabel Name = new JLabel("Name: ");
        JLabel Price = new JLabel("Price: ");
        JTextField SKUtextfield = new JTextField(30);
        JTextField Nametextfield = new JTextField(30);
        JTextField Pricedtextfield = new JTextField(30);
        JButton Savebutt = new JButton("Save");

        setLayout(new GridBagLayout());
        Container container = new Container();







        //LABELS
        addComponent(0,0 , SKU);
        addComponent(0,1 , Name);
        addComponent(0 ,2,  Price);
        //TEXTFIELDS
        addComponent(1,0,SKUtextfield);
        addComponent(1,1,Nametextfield);
        addComponent(1,2,Pricedtextfield);
        //Buttons
        addComponent(1,4,Savebutt);


        ArrayList<Prods> prods = new ArrayList<>();
        JTable table = new JTable(new AbstractTableModel() {
            String [] columns = new String[] {"SKU" , "Name" , "Price"};
            public String getColumnName(int column){

                return  columns[column];
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
                    case 0:
                        return p.getSku();
                    case 1:
                        return p.getName();
                    case 2:
                        return p.getPrice();
                    default:
                        return null;
                }
            }
        });

        addComponent(0,5, 2,new JScrollPane(table) );


        Savebutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sku = SKUtextfield.getText();
                String name = Nametextfield.getText();
                String price = Pricedtextfield.getText();
                if (!sku.isEmpty() && !name.isEmpty() && !price.isEmpty()) {
                    prods.add(new Prods(sku, name, price));
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();

                    SKUtextfield.setText("");
                    Nametextfield.setText("");
                    Pricedtextfield.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR ");
                }

            }
        });




        setTitle("ProductScreen");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

    }










        private  void addComponent (int x , int y , int width , Component c   ){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        add(c ,constraints);
            constraints.anchor = GridBagConstraints.CENTER;

    }

   private void addComponent (int x , int y , Component c   ){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        add(c ,constraints);

    }

   private void addComponent (int x , int y , int width , int fill , Component c   ){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
        add(c ,constraints);

    }

}
