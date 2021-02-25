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

    public boolean addNecessary(int latest) {
        if (size() < 10) {
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
    
}
