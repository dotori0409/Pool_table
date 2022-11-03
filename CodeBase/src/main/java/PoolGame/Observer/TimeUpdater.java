package PoolGame.Observer;

import PoolGame.Observer.Timer.Timer;

/**
 * Concrete subject of the observer pattern that involves in the time display
 */
public class TimeUpdater implements Updater{
    private Timer timer;

    /**
     * Initialize variables and attaches the timer 
     * @param timer
     */
    public TimeUpdater(Timer timer){
        this.timer= timer;
        timer.attach(this);
    }

     /**
     * Update new time 
     * @param newinfo
     */
    @Override
    public void update(String newinfo) {
        timer.setTime(newinfo);
        
    }
    
}
