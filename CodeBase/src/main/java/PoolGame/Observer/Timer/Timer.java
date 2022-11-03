package PoolGame.Observer.Timer;

import PoolGame.Observer.Keeper;
import PoolGame.Observer.Updater;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * Is the Subject interface of the observer pattern
 */
public class Timer implements Keeper{
    private Updater timeUpdater;
    private Text timer;
    private Counter count;

    /**
     * Initilizes vraiables for the Timer
     * @Throws InterruptedException
     */
    public Timer(Pane pane) throws InterruptedException{
        timer = new Text("Time: 00 : 00");
        timer.setX(40);
        timer.setY(20);
        timer.setFont(Font.font(null, FontWeight.BOLD, 15));
        count = new Counter();
        pane.getChildren().add(timer);
    }

    /**
     * Resets the time
     */
    public void resetTime(){
        count.setMin(0);
        count.setSec(0);
        count.setMSec(0);
    }

    /**
     * Increases and displays the time for the Timer
     * @throws InterruptedException
     */
    public void time() throws InterruptedException{
        timer.setText("Time "+ String.format("%02d", count.getMin())+" : "+String.format("%02d", count.getSec())); 
        Thread.sleep(1);
        count.setMSec(count.getMSec()+1);
        if (count.getMSec()==100){
            count.setSec(count.getSec()+1);
            count.setMSec(0);
        }
        if (count.getSec()==59){
            count.setMin(count.getMin()+1);
            count.setSec(0);
        }
    } 

    /**
     * Get the time display text
     * @return time display text
     */
    public String getTime(){
        return timer.getText();
    }

    /**
     * Sets the time to be the saved Time
     * @param savedTime
     */
    public void setTime(String savedTime){
        timer.setText(savedTime);
        count.setMin(Integer.parseInt(savedTime.split(":")[0].split(" ")[1]));
        count.setSec(Integer.parseInt(savedTime.split(": ")[1]));
    }

    /**
     * Attaches the timer updater
     */
    public void attach(Updater timeUpdater){
        this.timeUpdater = timeUpdater;
    }

    /**
     * Notifies the updater an update has been made
     */
    public void notifyUpdater(){
        String time = getTime();
        timeUpdater.update(time);
    }
}