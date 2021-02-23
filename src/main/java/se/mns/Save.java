package se.mns;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Save extends Score{

    public String latest() {
        return "Latest Score: " + Integer.toString(super.getLatest());
    }
    
    public String highest() {
        return "Highest Score: " + Integer.toString(super.getHighest());
    }
    
    public void write(String fileName) throws IOException {
        Path target = Path.of(fileName).toAbsolutePath().normalize();
        try (BufferedWriter writer = Files.newBufferedWriter(target, StandardOpenOption.CREATE)) {
            writer.write(latest());
            writer.write("\n");
            writer.write(highest());
        }
    }
    
}
