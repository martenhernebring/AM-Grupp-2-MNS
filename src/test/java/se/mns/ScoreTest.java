package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
		score.increaseLatest();
		assertEquals(1, score.getLatest());

	}
	
	@Test
	public void increaseTest() {
		score.increaseLatest();
		score.resetLatest();
		assertEquals(0, score.getLatest());
	}
	
	@Test
	public void updateTest() {
		score.increaseLatest();
		score.updateHighest();
		assertEquals(1, score.getHighest());
	}

	
	@Test
    public void latestTest() {
        assertEquals("Latest Score: 0", score.latestText());
    }
    
    @Test
    public void highestTest() {
        assertEquals("Highest Score: 0", score.highestText());
    }
    
    @Test
    public void increaseStringTest() {
        score.increaseLatest();
        assertEquals("Latest Score: 1", score.latestText());
    }
    
    @Test
    public void updateStringTest() {
        score.increaseLatest();
        score.updateHighest();
        assertEquals("Highest Score: 1", score.highestText());
    }
    
    @Test
    public void resetTest() {
        score.increaseLatest();
        score.resetLatest();
        assertEquals("Latest Score: 0", score.latestText());
    }
    
    @Test
    public void writeTest() {
        try {
            Path outFile =  Path.of("score.txt").toAbsolutePath().normalize(); 
            if (Files.exists(outFile)) {
                System.out.printf("The file %s already exists.%n", outFile);
            } else {
                score.write();
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
