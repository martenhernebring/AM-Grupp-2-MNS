package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class ScoreTest {

    Score score = new Score();

    @Test
    public void writeTest() {
        Path path = score.getPath();
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }
            score.write();
            if (!Files.exists(path)) {
                System.err.printf("The file %s does not exist.%n", path);
                fail();
            } else {
                Files.delete(path);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

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
    public void updateTest() throws IOException {
        score.increaseLatest();
        score.updateHighest();
        assertEquals(1, score.getHighest());
        Files.delete(score.getPath());
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
    public void updateStringTest() throws IOException {
        score.increaseLatest();
        score.updateHighest();
        assertEquals("Highest Score: 1", score.highestText());
        Files.delete(score.getPath());
    }

    @Test
    public void resetTest() {
        score.increaseLatest();
        score.resetLatest();
        assertEquals("Latest Score: 0", score.latestText());
    }

}
