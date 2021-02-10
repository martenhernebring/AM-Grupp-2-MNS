package se.mns;

public class Score {
	
	private int latest;
	private int highest;
	
	public Score() {
		this.latest = 0;
		this.highest = 0;
	}
	
	public int getLatest() {
		return this.latest;
	}
	
	public int getHighest() {
		return this.highest;
	}
	
	public void increase() {
		this.latest++;
	}
	
	public void reset() {
		this.latest = 0;
	}
	
	public void setHighest() {
		if(this.latest > this.highest) {
			this.highest = this.latest;
		}
	}
}
