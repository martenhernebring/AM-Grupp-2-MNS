package se.mns;

public class Player implements Comparable<Player> {

	private String name;
	private int score;

	public Player(String name, int score) {
		this.score = score;
		if(containsNumber(name)) {
            throw new IllegalArgumentException("No numbers are allowed in your name.");
        } else {
            this.name = name.strip();
        }
	}

	public String getName(){
		return name;
	}

	public int getScore(){
		return score;
	}

	@Override
	public int compareTo(Player other) {
		return Integer.valueOf(this.score).compareTo(Integer.valueOf(other.getScore()));
	}
	
	private boolean containsNumber(String name) {
		char[] chars = name.toCharArray();
		for(char c : chars) {
			if (Character.isDigit(c)) {
	            return true;
	        }
		}
		return false;
	}

	@Override
	public String toString() {
		return name + ", score: " + score + "\n";
	}
	
	

}
