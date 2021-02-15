package se.mns;

import java.awt.Color;
import java.awt.Dimension;
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

    private boolean gameOver;
    private boolean start;

    private boolean spacePressed;
    private boolean easyMode;

    private Timer timer;
    private List<Rectangle> aliens;
    private Rectangle spaceShip;
    private Score score;

    private int width;
    private int height;

    public GameSurface(final int width, final int height) {
        this.width = width;
        this.height = height;
        score = new Score();
        reset();
    }

    private void reset() {
        gameOver = false;
        start = true;
        aliens = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            addAlien(width, height);
        }


        spaceShip = new Rectangle(20, 20, 30, 20);
        timer = new Timer(50, this);
        timer.start();

 
        score.reset();
        easyMode = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(g);
    }

    private void addAlien(final int width, final int height) {
        int x = ThreadLocalRandom.current().nextInt(width / 2, width - 30);
        int y = ThreadLocalRandom.current().nextInt(20, height - 30);
        final int alienSize = 20; // Version one was set to 10
        aliens.add(new Rectangle(x, y, alienSize, alienSize));
    }

    /**
     * Call this method when the graphics needs to be repainted on the graphics
     * surface.
     * 
     * @param g the graphics to paint on
     */
    private void repaint(Graphics g) {
        final Dimension d = this.getSize();

        if (gameOver) {
            showMenu(g, d);
            return;
        }

        // fill the background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, d.width, d.height);

        // draw the aliens
        for (Rectangle alien : aliens) {
            g.setColor(Color.red);
            g.fillRect(alien.x, alien.y, alien.width, alien.height);
        }

        // draw the space ship
        g.setColor(Color.black);
        g.fillRect(spaceShip.x, spaceShip.y, spaceShip.width, spaceShip.height);
    }

    private void showMenu(Graphics g, Dimension d) {
        g.setColor(Color.red);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        String latestScore = "Latest Score: " + Integer.toString(score.getLatest());
        
        score.setHighest();
        
        String highestScore = "Highest Score: " + Integer.toString(score.getHighest());
        
        g.drawString(latestScore, 20, d.width / 2 - 24);
        g.drawString(highestScore, 20, d.width / 2 + 24);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this will trigger on the timer event
        // if the game is not over yet it will
        // update the positions of all aliens
        // and check for collision with the space ship

        if (gameOver) {
            return;

        } else if (!start) {
            final List<Rectangle> toRemove = new ArrayList<>();

            for (Rectangle alien : aliens) {
                alien.translate(-1, 0);
                if (alien.x + alien.width < 0) {
                    // we add to another list and remove later
                    // to avoid concurrent modification in a for-each loop
                    toRemove.add(alien);

                    if(easyMode) {
                    	score.increase10Times();
                    } else {
                    	score.increase();
                    }
                    

                }

                if (alien.intersects(spaceShip)) {
                    gameOver = true;
                }
            }

            aliens.removeAll(toRemove);


            // add new aliens for every one that was removed
            for (int i = 0; i < toRemove.size(); ++i) {
                Dimension d = getSize();
                addAlien(d.width, d.height);

            }
            final int spaceshipMovement = 2;
            if(spacePressed) {
                final int minHeight = spaceshipMovement;
                if (spaceShip.y > minHeight) {
                    spaceShip.translate(0, -spaceshipMovement);
                }
            } else {
                final int maxHeight = this.getSize().height - spaceShip.height - spaceshipMovement;
                if (spaceShip.y < maxHeight) {
                    spaceShip.translate(0, 2*spaceshipMovement);
                }
            }

        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!gameOver) {
            start = false;
        }
        spacePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // this event triggers when we press a key and then
        // we will move the space ship up if the game is not over yet

        if (gameOver && !start) {
            reset();
            return;
        }

        final int kc = e.getKeyCode();

        if (kc == KeyEvent.VK_SPACE) {

            spacePressed = true;
        } else if(kc==KeyEvent.VK_D){
        	easyMode = !easyMode;
        	//Switch difficulty
        	
        }
    } 
}
