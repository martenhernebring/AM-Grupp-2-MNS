package se.mns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.swing.JOptionPane;

public class Score {
	
	private int latest;
	private int highest;
	private Path path;
    private Top10 top10;
    private Player player;
	
	public Score() {
		latest = 0;
		highest = 0;
		path = Path.of("score.txt").toAbsolutePath().normalize();
		top10 = new Top10();
	}
	
	public int getLatest() {
        return latest;
    }

    public int getHighest() {
        return highest;
    }

    public void updateHighest() {
	    if(latest > highest) {
            highest = latest;
        }
	}
	
	public void increaseLatest() {
		latest++;
	}
	
	public void resetLatest() {
		latest = 0;
	}
	
	public String latestText() {
        return "Latest Score: " + Integer.toString(getLatest());
    }
    
    public String highestText() {
        return "Highest Score: " + Integer.toString(getHighest());
    }
    
    public void write() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            writer.write(latestText());
            writer.write("\n");
            writer.write(highestText());
        }
    }
    
    public void read() throws IOException {
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("The file %s is not readable.%n" + path);
        }
        //String[] outputLines = new String[2];
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + " with index " + i);
                i++;
            }
        }
        //return outputLines;
    }
    
    public void savePlayerInTop10() {
        if(latest > highest) {
            String name = JOptionPane.showInputDialog("What is your name?");
            //invisible?
            
            player = new Player(name, latest);
            top10.add(player);
        }  
      /*Det som händer i top10. Bara för Demo, bör tas bort.
        for(int i = 0; i<top10.getPlayers().size(); i++) {
            System.out.println(String.valueOf(i+1) + ". "+ top10.getPlayers().get(i));
            }
        System.out.println("*******************");*/
    }
}
