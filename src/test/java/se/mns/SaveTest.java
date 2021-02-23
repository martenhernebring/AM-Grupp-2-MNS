package se.mns;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        
        @Test
        public void writeTest() {
            try {
                Path outFile =  Path.of("test.txt").toAbsolutePath().normalize(); 
                if (Files.exists(outFile)) {
                    System.err.printf("The file %s already exists.%n", outFile);
                    fail();
                } else {
                    save.write("test.txt");
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
