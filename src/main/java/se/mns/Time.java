package se.mns;

import java.util.concurrent.TimeUnit;

public class Time {

    public static void sleepQuarterSecond() {
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }     
    }

}
