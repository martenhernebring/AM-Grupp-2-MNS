package se.mns;

import java.util.TreeMap;

public class Top10 {
	
	//To use lastKey(), I chose type TreeMap, not Map.
	private TreeMap<Integer, String> top10;
	
	public Top10() {
		top10 = new TreeMap<>();
	}
	
	public void add(Integer score, String name) {
		
		top10.put(score, name);
		
		//Delete the 11th record.
		if(top10.size() > 10) {
			top10.remove(top10.lastKey());
		}
	}
	
	public TreeMap<Integer, String> getTop10() {
		return top10;
	}
	

}
