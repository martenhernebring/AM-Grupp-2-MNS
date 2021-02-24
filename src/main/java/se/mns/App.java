package se.mns;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * This is a very small "game" just to show the absolute basics of
 * how to draw on a surface in a frame using Swing/AWT.
 * 
 */
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Birb");

        var gameSurface = new GameSurface();

        frame.setSize(GameSurface.SIZE, GameSurface.SIZE); 
        frame.setResizable(false);
        frame.add(gameSurface);
        frame.addKeyListener(gameSurface);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}