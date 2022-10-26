package PoolGame.Timer;

public class Counter {
    private int min;
    private int sec;
    private int msec;

    public Counter() {
        min=0;
        sec=0;
        msec=0; 
    }

    public int getMin(){
        return min;
    }

    public void setMin(int min){
        this.min = min;
    }

    public int getSec(){
        return sec;
    }

    public void setSec(int sec){
        this.sec = sec;
    }

    public int getMSec(){
        return msec;
    }

    public void setMSec(int msec){
        this.msec = msec;
    }
}