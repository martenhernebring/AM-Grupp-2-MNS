package se.mns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Top10 {

	private List<Player> players = new ArrayList<>();

	public void add(Player p){
		players.add(p);
		
		sortDescending();

		remove11th();
		
	}

	private void sortDescending() {
		
		Collections.sort(players, Collections.reverseOrder());
	}
	
	private void remove11th() {
		if(players.size()>10){
			players.remove(players.size()-1);
		}
	}

	public List<Player> getPlayers(){
		return players;
	}

}
