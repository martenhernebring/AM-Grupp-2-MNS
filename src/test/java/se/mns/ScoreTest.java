package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class scoreTest {

	Score score = new Score();
	
	@Test
	public void testConstructor() {
<<<<<<< HEAD
		//assertEquals(0, score.getLatest());
	}
	
	@Test
	public void testGetLatestscores() {
		//assertEquals(0, score.getLatest());
	}
	
	@Test
	public void testscoreIncrease() {
		score.increase();
		//assertEquals(1, score.getLatest());
	}
	
	@Test
	public void testResetscores() {
		score.increase();
		score.reset();
		//assertEquals(0, score.getLatest());
	}

	@Test
	public void testSetHigestscore() {
		score.increase();
		//score.setHighest();
		//assertEquals(1, score.getHighest());
=======
		assertEquals(0, point.getLatest());
	}
	
	@Test
	public void testGetLatestPoints() {
		assertEquals(0, point.getLatest());
	}
	
	@Test
	public void testPointIncrease() {
		point.increase();
		assertEquals(1, point.getLatest());
	}
	
	@Test
	public void testResetPoints() {
		point.increase();
		point.reset();
		assertEquals(0, point.getLatest());
	}

	@Test
	public void testSetHigestPoint() {
		point.increase();
		point.update();
		assertEquals(1, point.getHighest());
>>>>>>> save
	}

	

}
