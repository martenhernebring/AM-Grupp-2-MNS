package se.mns;

public class Score {
	
	private int latest;
	private int highest;
	
	public Score() {
		latest = 0;
		highest = 0;
	}
	
	public int getLatest() {
        return latest;
    }

    public int getHighest() {
        return highest;
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
