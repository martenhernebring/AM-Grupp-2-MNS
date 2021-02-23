package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {
	
	Player player;
	
	@Test
	public void noSpaceAllowedInName() {
		 
		assertThrows(IllegalArgumentException.class, ()->{
			player = new Player(" N a o y a ", 10);
		});
	}
	
	@Test
	public void noNumberAllowedInName() {
		 
		assertThrows(IllegalArgumentException.class, ()->{
			player = new Player("Naoya1", 10);
		});
	}
	
	@Test
	public void compareToTest() {
		Player player1 = new Player("Naoya", 10);
		Player player2 = new Player("Naoya", 20);
		assertTrue(player1.compareTo(player2)<0);
	}
	
}
