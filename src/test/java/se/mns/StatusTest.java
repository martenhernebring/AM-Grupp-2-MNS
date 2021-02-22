package se.mns;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

public class StatusTest {
    
    Status status = new Status();

    @Test
    void spacePressedTest(){
        status.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(status.isSpacePressed());
    }
    
    @Test
    void spaceReleasedTest(){
        status.keyReleased();
        assertFalse(status.isSpacePressed());
    }
    
    @Test
    void resetTest() {
        status.reset();
        assertTrue(status.isStart());
    }
    
    @Test
    void updateTest() {
        status.reset();
        status.update();
        assertFalse(status.isSpeedUp());
    }
    
    @Test
    void gameOverTest() {
        status.setGameOver(true);
        assertTrue(status.isGameOver());
    }

}
