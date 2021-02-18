package se.mns;

import java.awt.event.KeyEvent;

public class Status {
	
	//Game mode status
    private boolean start; 
    private boolean gameOver; 
    private boolean speedUp; 
    private boolean easyMode;

    //Key press status
	private boolean spacePressed;
    private boolean updateDifficulty;
    private boolean updateSpeed;
    
    
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public boolean isSpeedUp() {
		return speedUp;
	}
	public boolean isEasyMode() {
		return easyMode;
	}
	public boolean isSpacePressed() {
		return spacePressed;
	}
	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}
	public boolean isUpdateDifficulty() {
		return updateDifficulty;
	}
	public boolean isUpdateSpeed() {
		return updateSpeed;
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
            updateDifficulty =true;
            break;
        case KeyEvent.VK_S:
            updateSpeed = true;
            break;
        default: //do nothing
        }
    }

}
