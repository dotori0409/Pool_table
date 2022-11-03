package PoolGame.Observer.Timer;

/**
 * Involves with the minute, second, and millisecond increase of the timer
 */
public class Counter {
    private int min;
    private int sec;
    private int msec;

    /**
     * Initialize variable
     */
    public Counter() {
        min=0;
        sec=0;
        msec=0; 
    }

    /**
     * Returns the min variable
     * @return min
     */
    public int getMin(){
        return min;
    }

    /**
     * Sets the min variable
     * @param min
     */
    public void setMin(int min){
        this.min = min;
    }

    /**
     * Gets the sec variable
     * @return sec
     */
    public int getSec(){
        return sec;
    }

    /**
     * Sets the sec variable
     * @param sec
     */
    public void setSec(int sec){
        this.sec = sec;
    }

    /**
     * Returns the msec variable
     * @return msec
     */
    public int getMSec(){
        return msec;
    }

    /**
     * Sets the msec variable
     * @param msec
     */
    public void setMSec(int msec){
        this.msec = msec;
    }
}