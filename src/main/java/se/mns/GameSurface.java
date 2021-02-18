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

    private Timer timer;
    private Score score;

    public GameSurface() {
        status = new Status();
        score = new Score();
        reset();
    }

    private void reset() {
        status.reset();
        score.reset();

        obstacles = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            addObstacle();
        }
        bird = new Rectangle(20, 20, 30, 20);

        timer = new Timer(50, this);
        timer.start();
    }

    private void addObstacle() {
        //int x = ThreadLocalRandom.current().nextInt(width / 2, width - 30);
        //int y = ThreadLocalRandom.current().nextInt(60, height - 50);
        //obstacles.add(new Rectangle(x, y, 25, 60));
        int x = ThreadLocalRandom.current().nextInt(SIZE / 2, SIZE - 30);
        int y = ThreadLocalRandom.current().nextInt(60, SIZE - 50);
        if (status.isEasyMode()) {
            //final int easy = 20;
            obstacles.add(new Rectangle(x, y, 25, 60));
        } else {
            //final int hard = 40;
            obstacles.add(new Rectangle(x, y, 50, 120));
        }
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
        int angle = 1% 46;
        //g.fillRect(bird.x, bird.y, bird.width, bird.height);
        g.fillArc(bird.x, bird.y, bird.width, bird.height, angle, 360-angle *2);
    }

    private void showMenu(Graphics g) {
        // fill the background
        g.setColor(Color.red);
        g.fillRect(0, 0, SIZE, SIZE);

        //get high scores
        score.update();
        String latestScore = score.latest();
        String highestScore = score.highest();

        //show high scores
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(latestScore, 20, SIZE / 2 - 24);
        g.drawString(highestScore, 20, SIZE / 2 + 24);

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
            moveobstacles();
            movebird();
        }

        status.update();

        this.repaint();
    }

    private void moveobstacles() {
        final List<Rectangle> toRemove = new ArrayList<>();

        for (Rectangle obstacle : obstacles) {

            if (status.isSpeedUp()) {
                obstacle.translate(-3, 0);
            } else {
                obstacle.translate(-1, 0);
            }

            if (obstacle.x + obstacle.width < 0) {
                // we add to another list and remove later
                // to avoid concurrent modification in a for-each loop
                toRemove.add(obstacle);
                score.increase();
            }

            if (obstacle.intersects(bird)) {
                status.setGameOver(true);
            }
        }

        obstacles.removeAll(toRemove);

        // add new obstacles for every one that was removed
        for (int i = 0; i < toRemove.size(); ++i) {
            addObstacle();
        }

    }

    private void movebird() {
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
