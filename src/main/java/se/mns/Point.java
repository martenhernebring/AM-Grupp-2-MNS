package se.mns;

public class Point {
	
	private int latestPoint;
	private int highestPoint;
	
	public Point() {
		this.latestPoint = 0;
		this.highestPoint = 0;
	}
	
	public int getLatestPoints() {
		return this.latestPoint;
	}
	
	public void pointIncrease() {
		this.latestPoint++;
	}
	
	public void resetPoints() {
		this.latestPoint = 0;
	}
	
	public void setHighestPoint() {
		if(this.latestPoint > this.highestPoint) {
			this.highestPoint = this.latestPoint;
		}
	}
}
