import javax.swing.*;
import java.awt.*;

public class CodeScreen extends JFrame {

    CodeScreen(){

    JTextField Inputsku = new JTextField(20);
    JButton Enterbutton = new JButton("Enter");
    JLabel skuinput = new JLabel("Input Product: ");


        setLayout(new GridBagLayout());
        //Top bar level
     addComponent(1,0,Inputsku);
     addComponent(0,0,skuinput);
     addComponent(3,0,Enterbutton);



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
