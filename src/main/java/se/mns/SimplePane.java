package se.mns;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class SimplePane {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("What is your name?");
        String greeting = "Welcome " + name;
        System.out.println(greeting);
        
        /*final JTextField pf = new JTextField();
        password = JOptionPane.showConfirmDialog(null, pf, "Ange lösenord", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(pf.getPassword()): "";*/
    }

}
