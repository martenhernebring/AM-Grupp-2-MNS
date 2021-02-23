package se.mns;

public class Player implements Comparable<Player> {

	private String name;
	private int score;

	public Player(String name, int score) {
		setName(name);
		this.score = score;
	}

	private void setName(String name) {
		if(containsSpace(name)) {
			throw new IllegalArgumentException("No space is allowed in name");
		} else if(containsNumber(name)) {
			throw new IllegalArgumentException("No numbers are allowed in your name.");
		} 
		else {
			this.name = name;
		}
	}
	public String getName(){
		return name;
	}

	public int getScore(){
		return score;
	}

	@Override
	public int compareTo(Player p) {
		return Integer.valueOf(this.score).compareTo(Integer.valueOf(p.getScore()));
	}
	
	private boolean containsSpace(String name) {
		char[] chars = name.toCharArray();
		for(char c : chars) {
			if(c == ' ') {
				return true;
			}
		}
		return false;
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

}
