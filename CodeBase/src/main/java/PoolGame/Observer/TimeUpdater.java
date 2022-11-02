package PoolGame.Observer;

import PoolGame.objects.Timer.Timer;

public class TimeUpdater implements Updater{
    private Timer timer;

    public TimeUpdater(Timer timer){
        this.timer= timer;
        timer.attach(this);
    }

    @Override
    public void update(String newinfo) {
        timer.setTime(newinfo);
        
    }
    
}
