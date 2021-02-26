package se.mns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Top10 {

    private List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);

        Collections.sort(players, Collections.reverseOrder());

        if (players.size() > 10) {
            players.remove(last());
        }

    }
    
    public int size() {
        return players.size();
    }

    List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean isNecessary(int latest) {
        if(latest == 0) {
            return false;
        }
        else if (size() < 10) {
            return true;
        } else {
            if(latest > players.get(last()).getScore()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public int last() {
        return players.size() - 1;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Player player : players) {
            sb.append(player.toString());
        }
        return sb.toString();
    }
    
}
