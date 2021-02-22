package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

	Score score = new Score();
	
	@Test
    public void testConstructor() {
        assertEquals(0, score.getLatest());
    }
	
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
	public void increaseTest() {
		score.increase();
		score.reset();
		assertEquals(0, score.getLatest());
	}
	
	@Test
	public void updateTest() {
		score.increase();
		score.update();
		assertEquals(1, score.getHighest());

	}
	
	@Test
	public void resetTest() {
		score.increase();
		score.reset();
		assertEquals("Latest Score: 0", score.getLatest());
	}

}
