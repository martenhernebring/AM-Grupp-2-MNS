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
	private static final long serialVersionUID = 6260582674762246325L;

	static final int SIZE = 400;

	private Status status;

	//Game animation objects
	private List<Rectangle> aliens;
	private Rectangle spaceShip;

	private Timer timer;
	private Score score;

	public GameSurface() {
		status = new Status();
		score = new Score();
		status.setGameOver(false);
		reset();
	}

	private void reset() {

		resetStatus();

		aliens = new ArrayList<>();
		for (int i = 0; i < 5; ++i) {
			addAlien();
		}

		spaceShip = new Rectangle(20, 20, 30, 20);

		timer = new Timer(50, this);
		timer.start();

		score.reset();
	}

	private void resetStatus() {

		status.setEasyMode(true);
		status.setChangeDifficulty(false);
		status.setChangeSpeed(false);
		status.setSpeedUp(false);
		status.setStart(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(g);
	}

	private void addAlien() {
		int x = ThreadLocalRandom.current().nextInt(SIZE / 2, SIZE - 30);
		int y = ThreadLocalRandom.current().nextInt(20, SIZE - 30);
		if(status.isEasyMode()) {
			final int easy = 20;
			aliens.add(new Rectangle(x, y, easy, easy));
		} else {
			final int hard = 40;
			aliens.add(new Rectangle(x, y, hard, hard));
		}

	}

	/**
	 * Call this method when the graphics needs to be repainted on the graphics
	 * surface.
	 * 
	 * @param g the graphics to paint on
	 * @throws InterruptedException 
	 */
	private void repaint(Graphics g) {


		if (status.isGameOver()) {
			showMenu(g);
			return;
		}

		// fill the background
		g.setColor(Color.cyan);
		g.fillRect(0, 0, SIZE, SIZE);

		// draw the aliens
		for (Rectangle alien : aliens) {
			g.setColor(Color.red);
			g.fillRect(alien.x, alien.y, alien.width, alien.height);
		}

		// draw the space ship
		g.setColor(Color.black);
		g.fillRect(spaceShip.x, spaceShip.y, spaceShip.width, spaceShip.height);
	}

	private void showMenu(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, SIZE, SIZE);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 32));

		String latestScore = "Latest Score: " + Integer.toString(score.getLatest());

		score.setHighest();

		String highestScore = "Highest Score: " + Integer.toString(score.getHighest());

		g.drawString(latestScore, 20, SIZE / 2 - 24);
		g.drawString(highestScore, 20, SIZE / 2 + 24);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this will trigger on the timer event
		// if the game is not over yet it will
		// update the positions of all aliens
		// and check for collision with the space ship

		if(status.isGameOver()) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt(); 
			}
			return;
		} else if(!status.isStart()) {
			moveAliens(); 	
			moveSpaceship();
		}

		if(status.isChangeDifficulty()) {
			status.setEasyMode(!status.isEasyMode());
			status.setChangeDifficulty(false);
		}  
		if(status.isChangeSpeed()) {   	
			status.setSpeedUp(!status.isSpeedUp());
			status.setChangeSpeed(false);
		}

		this.repaint();
	}

	private void moveAliens() {
		final List<Rectangle> toRemove = new ArrayList<>();

		for (Rectangle alien : aliens) {

			if(status.isSpeedUp()) {  
				alien.translate(-5, 0);
			} else {
				alien.translate(-1, 0);
			}

			if (alien.x + alien.width < 0) {
				// we add to another list and remove later
				// to avoid concurrent modification in a for-each loop
				toRemove.add(alien);
				score.increase();
			}

			if (alien.intersects(spaceShip)) {
				status.setGameOver(true);
			}
		}

		aliens.removeAll(toRemove);

		// add new aliens for every one that was removed
		for (int i = 0; i < toRemove.size(); ++i) {
			addAlien();
		}

	}

	private void moveSpaceship() {
		final int spaceshipMovement = 2;
		if(status.isSpacePressed()) {
			final int minHeight = spaceshipMovement;
			if (spaceShip.y > minHeight) {
				spaceShip.translate(0, -spaceshipMovement);
			}
		} else {
			final int maxHeight = this.getSize().height - spaceShip.height - spaceshipMovement;
			if (spaceShip.y < maxHeight) {
				spaceShip.translate(0, 2*spaceshipMovement);
			} else {
				status.setGameOver(true);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!status.isGameOver()) {
			status.setStart(false);
		} else if(status.isStart()){
			status.setGameOver(false);
		}
		status.setSpacePressed(false);
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
			if(!status.isStart()) {
				reset();
			}
			return;
		}

		final int kc = e.getKeyCode();

		switch(kc) {
		case KeyEvent.VK_SPACE: status.setSpacePressed(true);
		break;
		case KeyEvent.VK_D: status.setChangeDifficulty(true);
		break;
		case KeyEvent.VK_S: status.setChangeSpeed(true);
		break;
		}

	}

}
