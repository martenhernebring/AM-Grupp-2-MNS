package se.mns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Score {
	
	private int latest;
	private int highest;
	
	public Score() {
		latest = 0;
		highest = 0;
	}
	
	public int getLatest() {
        return latest;
    }

    public int getHighest() {
        return highest;
    }

    public void update() {
	    if(latest > highest) {
            highest = latest;
        }
	}
	
	public void increase() {
		latest++;
	}
	
	public void reset() {
		latest = 0;
	}
	
	public String latest() {
        return "Latest Score: " + Integer.toString(getLatest());
    }
    
    public String highest() {
        return "Highest Score: " + Integer.toString(getHighest());
    }
    
    public void write(String fileName) throws IOException {
        Path target = Path.of(fileName).toAbsolutePath().normalize();
        try (BufferedWriter writer = Files.newBufferedWriter(target, StandardOpenOption.CREATE)) {
            writer.write(latest());
            writer.write("\n");
            writer.write(highest());
        }
    }
    
    public String[] read(String fileName) throws IOException {
        Path source = Path.of(fileName);
        if (!Files.isReadable(source)) {
            throw new IllegalArgumentException("The file %s is not readable.%n" + fileName);
        }
        String[] outputLines = new String[2];
        try (BufferedReader reader = Files.newBufferedReader(source)) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                outputLines[index] = line;
                index++;
            }
        }
        return outputLines;
    }
}
