import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CodeScreen extends JFrame {

    CodeScreen(){

    JTextField Inputsku = new JTextField(20);
    JButton Enterbutton = new JButton("Enter");
    JLabel skuinput = new JLabel("Input Product: ");
    JLabel Price = new JLabel("Price: ");



        setLayout(new GridBagLayout());
        //Top bar level
     addComponent(1,0,Inputsku);
     addComponent(0,0,skuinput);
     addComponent(2,0,Enterbutton);
     //addComponent(2,3,Price);

        //table lists
        ArrayList<Orders> orders = new ArrayList<>();
        JTable table = new JTable(new AbstractTableModel() {
            String [] columns = new String[] {"SKU" , "Name" , "Price" , "Qty" , "amount"};
            public String getColumnName(int column){

                return  columns[column];
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
                        return  ords.getQty();

                    case 4:
                        return  ords.getAmount();

                    default:
                        return null;
                }
            }
        });

        addComponent(0,3, 4,new JScrollPane(table) );





        setTitle("Order Screen");
        setSize(300,400);
        JFrame jFrame = new JFrame();
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
//        constraints.gridwidth = width;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
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
