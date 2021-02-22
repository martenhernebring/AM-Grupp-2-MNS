package se.mns;

import java.awt.Color;
import java.awt.Font;
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
public class GameSurface extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 6260582674762246325L;

    static final int SIZE = 400;

    private Status status;

    // Game animation objects
    private List<Rectangle> obstacles;
    private Rectangle bird;
    private int angle = 0;

    private Timer timer;
    private Save save;

    public GameSurface() {
        save = new Save();
        status = new Status();
        timer = new Timer(50, this);
        reset();
    }

    private void reset() {
        status.reset();
        save.reset();

        obstacles = new ArrayList<>();
        addObstacles();
        bird = new Rectangle(20, 20, 30, 20);

        timer.restart();
    }

    private void addObstacles() {
        int random = ThreadLocalRandom.current().nextInt(30, 270);
        final int width = 50;
        final int gap = 70;
        obstacles.add(new Rectangle(SIZE-width, 0, width, random));
        obstacles.add(new Rectangle(SIZE-width, random + gap, width, SIZE - random - gap));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(g);
    }

    /**
     * Call this method when the graphics needs to be repainted on the graphics
     * surface.
     * 
     * @param g the graphics to paint on
     */
    private void repaint(Graphics g) {

        if (status.isGameOver()) {
            showMenu(g);
            return;
        }

        // fill the background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, SIZE, SIZE);

        // draw the obstacles
        for (Rectangle obstacle : obstacles) {
            g.setColor(Color.red);
            g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }

        // draw the bird
        g.setColor(Color.black);
        angle = (angle + 1) % 46;
        g.fillArc(bird.x, bird.y, bird.width, bird.height, angle, 360-angle *2);
    }

    private void showMenu(Graphics g) {
        // fill the background
        g.setColor(Color.red);
        g.fillRect(0, 0, SIZE, SIZE);

        save.update();

        //show high saves
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        
        g.drawString(save.latest(), 20, SIZE / 2 - 24);
        g.drawString(save.highest(), 20, SIZE / 2 + 24);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this will trigger on the timer event
        // if the game is not over yet it will
        // update the positions of all obstacles
        // and check for collision with the space ship

        if (status.isGameOver()) {
            Time.sleepQuarterSecond();
            return;
        } else if (!status.isStart()) {
            moveObstacles();
            moveBird();
        }

        status.update();

        this.repaint();
    }

    private void moveObstacles() {
        
        int speed = status.isSpeedUp() ? 3 : 1;

        for (Rectangle obstacle : obstacles) {

            obstacle.translate(-speed, 0);

            if (obstacle.x + obstacle.width < 0) {
                // we add to another list and remove later
                // to avoid concurrent modification in a for-each loop
                //toRemove.add(obstacle);
                save.increase();
                obstacles = new ArrayList<>();
                addObstacles();
            }

            if (obstacle.intersects(bird)) {
                status.setGameOver(true);
            }
        }

        //obstacles.removeAll(toRemove);

    }

    private void moveBird() {
        final int birdMovement = 2;
        if (status.isSpacePressed()) {
            final int minHeight = birdMovement;
            if (bird.y > minHeight) {
                bird.translate(0, -birdMovement);
            }
        } else {
            final int maxHeight = this.getSize().height - bird.height - birdMovement;
            if (bird.y < maxHeight) {
                bird.translate(0, 2 * birdMovement);
            } else {
                status.setGameOver(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        status.keyReleased();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // this event triggers when we press a key and then
        // we will move the space ship up if the game is not over yet
        if (status.isGameOver()) {
            if (!status.isStart()) {
                reset();
            }
            return;
        }
        final int kc = e.getKeyCode();
        status.keyPressed(kc);
    }

}
