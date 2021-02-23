package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	Player player = new Player("Naoya", 10);
	
	@Test
	public void compareToTest() {
		Player player2 = new Player("Naoya2", 20);
		assertTrue(player.compareTo(player2)<0);
	}

}
