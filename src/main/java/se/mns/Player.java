package se.mns;

public class Player implements Comparable<Player> {

	private String name;
	private int score;

	public Player(String name, int score) {
		this.name = name;
		this.score = score;
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

}
