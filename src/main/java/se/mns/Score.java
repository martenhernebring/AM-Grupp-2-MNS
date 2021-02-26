package se.mns;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Score {

    private int latest;
    private int highest;
    private Path path;
    private Top10 top10;
    private boolean done;

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
        done = false;
        savePlayerInTop10();
        if (latest > highest) {
            highest = latest;

        }
        if(done) {
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

    private void read() throws IOException {
        top10 = new Top10();
        if (Files.isReadable(path)) {
            try (var scan = new Scanner(path)) {
                while(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] words = line.split(", score: ", 2); //(, words.length)
                    Player old = new Player(words[0], Integer.parseInt(words[1]));
                    top10.add(old);
                }
            }
        }
    }

    private void savePlayerInTop10() {
        if (top10.isNecessary(latest)) {
            Player player = null;
            while(!done) {
                String name = JOptionPane.showInputDialog("What is your name?");

                try {
                    player = new Player(name, latest);
                    done = true;
                } catch (IllegalArgumentException ex){
                    System.err.println(ex.getMessage());
                }
            }
            
            top10.add(player);
        }
    }

    void write() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {

            List<Player> players = top10.getPlayers();
            for (Player player : players) {
                writer.write(player.toString());
            }
        }
    }

    public List<Player> getTop10() {
        List<Player> p;
        try {
            p = top10.getPlayers();
        } catch (IndexOutOfBoundsException ex) {
            p = null;
        }
        return p;
    }
}
