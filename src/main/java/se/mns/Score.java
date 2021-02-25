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

    public Score() {
        latest = 0;
        highest = 0;
        path = Path.of("highest_score.txt").toAbsolutePath().normalize();
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

    public void save() throws IOException {
        try {
            read();
        } catch (IOException ex) {
            // do nothing
        }
        savePlayerInTop10();
        if (latest > highest) {
            highest = latest;
            
        }
        write();
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

    private void read() throws IOException {
        
        if (Files.isReadable(path)) {
            try (var scan = new Scanner(path)) {
                if (scan.hasNextInt()) {
                    highest = scan.nextInt();
                    return;
                }
            }
        }
    }

    private void savePlayerInTop10() {
        if(top10.isNecessary(latest)) {
            String name = JOptionPane.showInputDialog("What is your name?");
            
            Player player = new Player(name, latest);
            top10.add(player);
            System.out.println(player); // temporary solution
        }
    }

    void write() throws IOException {
        if (Files.isWritable(path)) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {

                /*List<Player> players = top10.getPlayers();
            	for(Player player: players) {
            		writer.write(player.toString());
            	}*/
            	
            	writer.write(Integer.toString(highest));
            	writer.write("\n");
 
            }
        }
    }
}
