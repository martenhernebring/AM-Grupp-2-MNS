package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    
    @Test
    public void writeTest() {
        try {
            Path outFile =  Path.of("test.txt").toAbsolutePath().normalize(); 
            if (Files.exists(outFile)) {
                System.err.printf("The file %s already exists.%n", outFile);
                fail();
            } else {
                score.write("test.txt");
                if (!Files.exists(outFile)) {
                    System.err.printf("The file %s does not exist.%n", outFile);
                    fail();
                } else {
                    Files.delete(outFile);
                }     
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

}
