package se.mns;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A simple panel with a space invaders "game" in it. This is just to
 * demonstrate the bare minimum of stuff than can be done drawing on a panel.
 * This is by no means very good code.
 * 
 */
public class GameSurface extends JPanel implements ActionListener, KeyListener {
    // Required by compiler
    private static final long serialVersionUID = 6260582674762246325L;

    static final int SIZE = 400;
    private static final int PADDING = 0;
    private final int xHeight = 20;
    private final int yCenter = SIZE / 2 - 10;
    private final int diff = 18;

    private Status status;

    // Game animation objects
    private List<Rectangle> obstacles;
    private Rectangle bird;
    private int cakeAngle = 0;

    private Timer timer;
    private Score score;

    public GameSurface() {

        status = new Status();
        timer = new Timer(50, this);
        score = new Score();
        reset();
    }

    private void reset() {
        status.reset();
        score.resetLatest();

        obstacles = new ArrayList<>();
        addObstacles();
        bird = new Rectangle(20, 20, 30, 20);

        timer.restart();
    }

    private void addObstacles() {
        int randomCavity = ThreadLocalRandom.current().nextInt(30, 270);
        final int width = 50;
        final int gap = 70;
        final int yUpper = 0, yLower = randomCavity + gap;
        final int heightUpper = randomCavity, heightLower = SIZE - randomCavity - gap;
        obstacles.add(new Rectangle(SIZE - width, yUpper, width, heightUpper));
        obstacles.add(new Rectangle(SIZE - width, yLower, width, heightLower));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            repaint(g);
        } catch (IOException e) {
            System.err.println("Problems writing to file " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Call this method when the graphics needs to be repainted on the graphics
     * surface.
     * 
     * @param graphics the graphics to paint on
     * @throws IOException
     */
    private void repaint(Graphics graphics) throws IOException {

        if (status.isGameOver()) {
            if (status.isSaved()) {
                showMenu(graphics);
                return;
            } else {
                showRedBackground(graphics);
                showScore(graphics);
                score.save();
                status.setSave(true);
                this.repaint();
                return;
            }
        }

        showObstacles(graphics);
    }

    private void showMenu(Graphics graphics) throws IOException {
        
        showRedBackground(graphics);

        // show high scores
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", Font.BOLD, 18));

        List<Player> players = score.getTop10();
        final int s = players.size();
        
        if (s > 0) {
            for (int i = 0; i < s; i++) {
                graphics.drawString(players.get(i).toString(), xHeight, yCenter + ((2 * i - 9) * diff));
            }
        } else {
            showScore(graphics);
        }

    }
    
    private void showRedBackground(Graphics graphics) {
     // fill the background
        graphics.setColor(Color.red);
        graphics.fillRect(PADDING, PADDING, SIZE, SIZE);
    }
    
    private void showScore(Graphics graphics) {
     // show high scores
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", Font.BOLD, 32));
        graphics.drawString(score.latestText(), xHeight, yCenter - diff);
        graphics.drawString(score.highestText(), xHeight, yCenter + diff);
    }

    private void showObstacles(Graphics graphics) {
        // fill the background
        graphics.setColor(Color.cyan);
        graphics.fillRect(PADDING, PADDING, SIZE, SIZE);

        // draw the obstacles
        for (Rectangle obstacle : obstacles) {
            graphics.setColor(Color.red);
            graphics.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }

        // draw the bird
        graphics.setColor(Color.black);
        cakeAngle = (cakeAngle + 1) % 46;
        graphics.fillArc(bird.x, bird.y, bird.width, bird.height, cakeAngle, 360 - cakeAngle * 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this will trigger on the timer event
        // if the game is not over yet it will
        // update the positions of all obstacles
        // and check for collision with the space ship

        if (status.isGameOver()) {
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return;
        } else if (!status.isStart()) {
            moveObstacles();
            moveBird();
        }

        status.changeSpeed();

        this.repaint();
    }

    private void moveObstacles() {

        final int fastSpeed = 5, normalSpeed = 3;
        int xSpeed = status.isSpeedUp() ? fastSpeed : normalSpeed;

        moveObstacle(obstacles.get(0), xSpeed);
        moveObstacle(obstacles.get(1), xSpeed);

        if (obstacles.get(0).x + obstacles.get(0).width < 0) {
            // we add to another list and remove later
            // to avoid concurrent modification in a for-each loop
            obstacles = new ArrayList<>();
            score.increaseLatest();
            addObstacles();
        }

    }

    private void moveObstacle(Rectangle obstacle, int speed) {

        obstacle.translate(-speed, 0);

        if (obstacle.intersects(bird)) {
            status.setGameOver(true);
        }
    }

    private void moveBird() {
        final int ySpeed = 2;
        final int xSpeed = 0;
        if (status.isSpacePressed()) {
            final int yTop = ySpeed;
            if (bird.y > yTop) {
                bird.translate(xSpeed, -ySpeed);
            }
        } else {
            final int yBottom = this.getSize().height - bird.height - ySpeed;
            if (bird.y < yBottom) {
                bird.translate(xSpeed, 2 * ySpeed);
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
    public void keyPressed(KeyEvent evt) {
        // this event triggers when we press a key and then
        // we will move the space ship up if the game is not over yet
        if (status.isGameOver()) {
            if (!status.isStart()) {
                reset();
            }
            return;
        }
        status.keyPressed(evt.getKeyCode());
    }

}
