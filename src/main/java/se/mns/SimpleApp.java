package se.mns;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JFrame;

public class SimpleApp {

    public static void main(String[] args) {
        JFrame main = new JFrame("Simple Surface");

        var ss = new SimpleSurface(400, 400);

        main.setSize(400, 400); 
        main.setResizable(false);
        main.add(ss);
        main.addKeyListener(ss);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }

}
