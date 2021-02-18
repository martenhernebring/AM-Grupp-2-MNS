package se.mns;

import java.awt.event.KeyEvent;

public class Status {

    // Key press status
    private boolean spacePressed;
    private boolean updateDifficulty;
    private boolean updateSpeed;
    
    // Game mode status
    private boolean speedUp;
    private boolean easyMode;
    private boolean start;
    private boolean gameOver;
    
    public boolean isSpacePressed() {
        return spacePressed;
    }
    
    public boolean isSpeedUp() {
        return speedUp;
    }

    public boolean isEasyMode() {
        return easyMode;
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
        easyMode = true;
        updateDifficulty = false;
        updateSpeed = false;
        speedUp = false;
        start = true;
    }

    public void update() {
        if (updateDifficulty) {
            easyMode = !easyMode;
            updateDifficulty = false;
        }
        if (updateSpeed) {
            speedUp = !speedUp;
            updateSpeed = false;
        }
    }

    public void keyPressed(int kc) {
        switch (kc) {
        case KeyEvent.VK_SPACE:
            spacePressed = true;
            break;
        case KeyEvent.VK_D:
            updateDifficulty = true;
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
