package se.mns;

public class Score {
	
	private int latest;
	private int highest;
	
	public Score() {
		latest = 0;
		highest = 0;
	}
	
	public String latest() {
		return "Latest Score: " + Integer.toString(latest);
	}
	
    public String highest() {
        return "Highest Score: " + Integer.toString(highest);
    }
	
	public void update() {
	    if(latest > highest) {
            highest = latest;
        }
	}
	
	public void increase() {
		latest++;
	}
	
	public void reset() {
		latest = 0;
	}
}
