package se.mns;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

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
    
    public Path getPath() {
        return path;
    }

    public void updateHighest() throws IOException {
        try {
            highest = read();
        } catch (IOException ex) {
            highest = 0;
        }
        if (latest > highest) {
            highest = latest;
            write();
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
            writer.write(Integer.toString(highest));
            writer.write('\n');
        }
    }

    public int read() throws IOException {
        if (!Files.isReadable(path)) {
            return 0;
        }
        try (var scan = new Scanner(path)) {
            return scan.nextInt();
        }
    }
    
    public void savePlayerInTop10() {
        if(latest > highest) {
            String name = JOptionPane.showInputDialog("What is your name?");
            //invisible? BUG
            
            player = new Player(name, latest);
            top10.add(player);
        }  
    }
}
