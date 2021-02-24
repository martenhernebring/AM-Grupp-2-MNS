package se.mns;

import javax.swing.JOptionPane;

public class SimplePane {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("What is your name?");
        String greeting = "Welcome " + name;
        System.out.println(greeting);
    }

}
