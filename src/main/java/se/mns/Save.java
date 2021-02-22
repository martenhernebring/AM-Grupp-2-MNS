package se.mns;

public class Save extends Score{

    public String latest() {
        return "Latest Score: " + Integer.toString(super.getLatest());
    }
    
    public String highest() {
        return "Highest Score: " + Integer.toString(super.getHighest());
    }
    
}
