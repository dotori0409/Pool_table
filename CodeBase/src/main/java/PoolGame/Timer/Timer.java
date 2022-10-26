package PoolGame.Timer;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Timer {
    private Text timer;
    private Counter count;

    public Timer(Pane pane) throws InterruptedException{
        timer = new Text("Time: 00 : 00");
        timer.setX(40);
        timer.setY(20);
        timer.setFont(Font.font(null, FontWeight.BOLD, 15));
        count = new Counter();
        pane.getChildren().add(timer);
    }

    public void resetTime(){
        count.setMin(0);
        count.setSec(0);
        count.setMSec(0);
    }

    public void time() throws InterruptedException{
        timer.setText("Time: "+ String.format("%02d", count.getMin())+" : "+String.format("%02d", count.getSec())); 
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
}