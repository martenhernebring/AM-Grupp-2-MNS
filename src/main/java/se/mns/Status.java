package se.mns;

import java.awt.event.KeyEvent;

public class Status {

    // Key press status
    private boolean spacePressed;
    private boolean updateSpeed;
    
    // Game mode status
    private boolean speedUp;
    private boolean start;
    private boolean gameOver;
    
    public boolean isSpacePressed() {
        return spacePressed;
    }
    
    public boolean isSpeedUp() {
        return speedUp;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void reset() {
        updateSpeed = false;
        speedUp = false;
        start = true;
    }

    public void changeSpeed() {
        if (updateSpeed) {
            speedUp = !speedUp;
            updateSpeed = false;
        }
    }

    public void keyPressed(int keycode) {
        switch (keycode) {
        case KeyEvent.VK_SPACE:
            spacePressed = true;
            break;
        case KeyEvent.VK_S:
            updateSpeed = true;
            break;
        default: // do nothing
        }
    }

    public void keyReleased() {
        if (!gameOver) {
            start = false;
        } else if (start) {
            gameOver = false;
        }
        spacePressed = false;
    }

}
