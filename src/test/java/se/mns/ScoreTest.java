package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

	Score score = new Score();
	
	@Test
	public void constructorTest() {
		assertEquals("Latest Score: 0", score.latest());
	}
	
	@Test
	public void latestTest() {
		assertEquals("Latest Score: 0", score.latest());
	}
	
	@Test
	public void highestTest() {
		assertEquals("Highest Score: 0", score.highest());
	}
	
	@Test
	public void increaseTest() {
		score.increase();
		assertEquals("Latest Score: 1", score.latest());
	}
	
	@Test
	public void updateTest() {
		score.increase();
		score.update();
		assertEquals("Highest Score: 1", score.highest());
	}
	
	@Test
	public void resetTest() {
		score.increase();
		score.reset();
		assertEquals("Latest Score: 0", score.latest());
	}

}
