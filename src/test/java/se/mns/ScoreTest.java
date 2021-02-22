package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class scoreTest {

	Score score = new Score();
	
	@Test
	public void testGetLatestscores() {
		assertEquals(0, score.getLatest());
	}
	
	@Test
	public void testscoreIncrease() {
		score.increase();
		assertEquals(1, score.getLatest());
	}
	
	@Test
	public void testResetscores() {
		score.increase();
		score.reset();
		assertEquals(0, score.getLatest());
	}

	@Test
	public void testSetHigestscore() {
		score.increase();
		score.update();
		assertEquals(1, score.getHighest());
	}

	

}
