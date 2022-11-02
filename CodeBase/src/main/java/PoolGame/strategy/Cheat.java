package PoolGame.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import PoolGame.objects.Ball;
import PoolGame.objects.Score;
import PoolGame.objects.Table;
import PoolGame.objects.Timer.Timer;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class Cheat {
    private Score scoreKeeper;
    private Timer timer;
    private Button cheatButton;
    private Button cheatNextButton;
    private int savedScore;
    private boolean doneCheat;
    private String savedTimeDisplay;
    
   public Cheat(List<Ball> balls, Timer timer, Score scoreKeeper,Table table){
        doneCheat = false;
        savedScore = 0;
        this.timer = timer;
        this.scoreKeeper = scoreKeeper;
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

   public void doCheat(Paint colour, List<Ball> balls) {
        savedScore = scoreKeeper.getScore();
        savedTimeDisplay = timer.getTime();
        List<Ball> toRemove = new ArrayList<>();
        System.out.println(balls.size());
        for (Ball ball : balls) {
            if (ball.getColour() == colour) {
                scoreKeeper.addScore(ball.getScore(ball.getColour()));
                toRemove.add(ball);
            }
        }
        balls.removeAll(toRemove);
    }

    public Button getCheatButton(){
        return cheatButton;
    }

    public boolean getDoneCheat(){
        return doneCheat;
    }

    public void setDoneCheatFalse(){
        this.doneCheat = false;
    }

    public int getSavedScore(){
        return savedScore;
    }

    public String getSavedTime(){
        return savedTimeDisplay;
    }

    public Button getNextButton(){
        return cheatNextButton;
    }
}
