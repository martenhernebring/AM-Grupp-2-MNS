package se.mns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Top10 {
	
private List<Player> players = new ArrayList<>();
    
    public void add(Player p){
        players.add(p);
       
        //Sort the list by score. Reverse for descending order.
        Collections.sort(players, Collections.reverseOrder());    
        
        //Removes the last(11th) element
        if(players.size()>10){
            players.remove(players.size()-1);
           
        }
    }

    public List<Player> getPlayers(){
        return players;
    }
	

}
