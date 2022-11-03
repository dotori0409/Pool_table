package PoolGame.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import PoolGame.Observer.Score;
import PoolGame.Observer.ScoreUpdater;
import PoolGame.Observer.Updater;
import PoolGame.Observer.Timer.Timer;
import PoolGame.objects.Ball;
import PoolGame.objects.Table;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

/**
 * Removes the all the chosen colored-balls 
 * from the table and increases the score accoring to it
*/
public class Cheat {
    private Score scoreKeeper;
    private Timer timer;
    private Button cheatButton;
    private Button cheatNextButton;
    private int savedScore;
    private boolean doneCheat;
    private String savedTimeDisplay;
    private Updater updater;
    
    /**
     * Initialize variables and set action when pressing button
     * @param balls
     * @param timer
     * @param scorekeeper
     * @param table
    */
   public Cheat(List<Ball> balls, Timer timer, Score scoreKeeper,Table table){
        doneCheat = false;
        savedScore = 0;
        this.timer = timer;
        this.scoreKeeper = scoreKeeper;
        updater = new ScoreUpdater(scoreKeeper);
        cheatButton = new Button("Cheat : Blue");
        cheatButton.setLayoutX(10);
        cheatButton.setLayoutY(table.getyLength()+70);

        cheatNextButton = new Button("Next Cheat");
        cheatNextButton.setLayoutX(110);
        cheatNextButton.setLayoutY(table.getyLength()+70);

        AtomicInteger ballColor = new AtomicInteger(0);
        cheatNextButton.setOnAction(event -> {
            setText(ballColor.get());
            if(ballColor.get()+1 > 7){
                ballColor.set(0);
            } else{
                ballColor.set(ballColor.get()+1);
            }
            
        });

        cheatButton.setOnAction(event -> {
            if (ballColor.get() == 0) {
                doCheat(Paint.valueOf("blue"), balls);
                setText(ballColor.get());
                ballColor.set(1);
                doneCheat = true;

            } else if (ballColor.get() == 1) {
                doCheat(Paint.valueOf("red"), balls);
                setText(ballColor.get());
                ballColor.set(2);
                doneCheat = true;

            } else if (ballColor.get() == 2) {
                doCheat(Paint.valueOf("yellow"), balls);
                setText(ballColor.get());
                ballColor.set(3);
                doneCheat = true;

            } else if (ballColor.get() == 3) {
                doCheat(Paint.valueOf("green"), balls);
                setText(ballColor.get());
                ballColor.set(4);
                doneCheat = true;

            } else if (ballColor.get() == 4) {
                doCheat(Paint.valueOf("brown"), balls);
                setText(ballColor.get());
                ballColor.set(5);

            } else if (ballColor.get() == 5) {
                doCheat(Paint.valueOf("purple"), balls);
                setText(ballColor.get());
                ballColor.set(6);
                doneCheat = true;

            } else if (ballColor.get() == 6) {
                doCheat(Paint.valueOf("orange"), balls);
                setText(ballColor.get());
                ballColor.set(7);
                doneCheat = true;

            } else if (ballColor.get() == 7) {
                doCheat(Paint.valueOf("black"), balls);
                setText(ballColor.get());
                ballColor.set(0);
                doneCheat = true;
            }
        });
   }

   /**
     * sets the text for the cheat button
     * @param num
    */
   public void setText(int num){
        if(num == 0){
            cheatButton.setText("Cheat : Red");
        }else if(num == 1){
            cheatButton.setText("Cheat : Yellow");
        }else if(num == 2){
            cheatButton.setText("Cheat : Green");
        }else if(num == 3){
            cheatButton.setText("Cheat : Brown");
        }else if(num == 4){
            cheatButton.setText("Cheat : Purple");
        }else if(num == 5){
            cheatButton.setText("Cheat : Orange");
        }else if(num == 6){
            cheatButton.setText("Cheat : Black"); 
        }else if(num == 7){
            cheatButton.setText("Cheat : Blue");
        }
   }

   /**
     * To the cheat task of removing balls
     * @param colour
     * @param balls
    */
   public void doCheat(Paint colour, List<Ball> balls) {
        savedScore = scoreKeeper.getScore();
        savedTimeDisplay = timer.getTime();
        List<Ball> toRemove = new ArrayList<>();
        for (Ball ball : balls) {
            if (ball.getColour() == colour) {
                updater.update(String.valueOf(ball.getScore(ball.getColour())+scoreKeeper.getScore()));
                toRemove.add(ball);
            }
        }
        balls.removeAll(toRemove);
    }

    /**
     * Returns the cheatButton variable
     * @return cheatButton
    */
    public Button getCheatButton(){
        return cheatButton;
    }

    /**
     * Returns the boolean doneCheat variable
     * @return doneCheat
    */
    public boolean getDoneCheat(){
        return doneCheat;
    }

    /**
     * Set the doneCheat variable to be false
    */
    public void setDoneCheatFalse(){
        this.doneCheat = false;
    }

    /**
     * Returns the savedScore
     * @return savedScore
    */
    public int getSavedScore(){
        return savedScore;
    }

    /**
     * Returns the savedTimeDisplay varibale
     * @return savedTimeDisplay
    */
    public String getSavedTime(){
        return savedTimeDisplay;
    }

    /**
     * Returns the cheatNextButton variable
     * @return cheatNextButton
    */
    public Button getNextButton(){
        return cheatNextButton;
    }
}
