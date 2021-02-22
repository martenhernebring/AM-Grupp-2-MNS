package se.mns;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SaveTest {

        Save save = new Save();
        
        @Test
        public void latestTest() {
            assertEquals("Latest Score: 0", save.latest());
        }
        
        @Test
        public void highestTest() {
            assertEquals("Highest Score: 0", save.highest());
        }
        
        @Test
        public void increaseTest() {
            save.increase();
            assertEquals("Latest Score: 1", save.latest());
        }
        
        @Test
        public void updateTest() {
            save.increase();
            save.update();
            assertEquals("Highest Score: 1", save.highest());
        }
        
        @Test
        public void resetTest() {
            save.increase();
            save.reset();
            assertEquals("Latest Score: 0", save.latest());
        }

}
