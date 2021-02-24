package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Top10Test {

	Top10 top10 = new Top10();
	Player player1 = new Player("Naoya", 10);
	Player player2 = new Player("Naoya", 20);
	
	@Test
	public void addDescendingOrderTest() {
		top10.add(player1);
		top10.add(player2);
		
		//Check if the first place in the list has 20 points.
		assertEquals(20, top10.getPlayers().get(0).getScore());
	}
	
	@Test
	public void addDeletes11thPlayer() {
		
		//Adds 11 players. 
		for(int i = 0; i<11; i++) {
			Player player = new Player("Player", i);
			top10.add(player);
		}
		
		//Check if the size of players is 10, not 11
		assertEquals(10, top10.getPlayers().size());
	}

}
