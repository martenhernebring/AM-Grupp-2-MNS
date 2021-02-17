package se.mns;

public class Status {
	
	//Game mode status
    private boolean start; 
    private boolean gameOver; 
    private boolean speedUp; 
    private boolean easyMode;

    //Key press status
	private boolean spacePressed;
    private boolean changeDifficulty;
    private boolean changeSpeed;
    
    
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
	public void setSpeedUp(boolean speedUp) {
		this.speedUp = speedUp;
	}
	public boolean isEasyMode() {
		return easyMode;
	}
	public void setEasyMode(boolean easyMode) {
		this.easyMode = easyMode;
	}
	public boolean isSpacePressed() {
		return spacePressed;
	}
	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}
	public boolean isChangeDifficulty() {
		return changeDifficulty;
	}
	public void setChangeDifficulty(boolean changeDifficulty) {
		this.changeDifficulty = changeDifficulty;
	}
	public boolean isChangeSpeed() {
		return changeSpeed;
	}
	public void setChangeSpeed(boolean changeSpeed) {
		this.changeSpeed = changeSpeed;
	}


}
