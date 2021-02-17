package se.mns;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A simple panel with a space invaders "game" in it. This is just to
 * demonstrate the bare minimum of stuff than can be done drawing on a panel.
 * This is by no means very good code.
 * 
 */
public class SimpleSurface extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 6260582674762246325L;

    private Timer timer;
    private List<Rectangle> obstacles;
    private Rectangle bird;

    public SimpleSurface(final int width, final int height) {

        obstacles = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            addObstacles(width, height);
        }

        bird = new Rectangle(20, 20, 30, 20);
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(g);
    }

    private void addObstacles(final int width, final int height) {
        int x = ThreadLocalRandom.current().nextInt(width / 2, width - 30);
        int y = ThreadLocalRandom.current().nextInt(50, height - 50);
        obstacles.add(new Rectangle(x, y, 25, 50));
    }
    private int angle = 0;

    /**
     * Call this method when the graphics needs to be repainted on the graphics
     * surface.
     * 
     * @param g the graphics to paint on
     */
    private void repaint(Graphics g) {
        final Dimension d = this.getSize();
        
        // fill the background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, d.width, d.height);
        
        
        // draw the obstacles
        for (Rectangle obstacle : obstacles) {
            g.setColor(Color.red);
            g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
        
        // draw the bird
        g.setColor(Color.black);
        angle = (angle +1)% 46;
        //g.fillRect(bird.x, bird.y, bird.width, bird.height);
        g.fillArc(bird.x, bird.y, bird.width, bird.height, angle, 360-angle *2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this will trigger on the timer event
        // if the game is not over yet it will
        // update the positions of all obstacles
        // and check for collision with the space ship
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
