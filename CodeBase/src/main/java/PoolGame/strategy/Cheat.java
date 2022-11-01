package PoolGame.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import PoolGame.objects.Ball;
import PoolGame.objects.Score;
import PoolGame.objects.Table;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class Cheat {
    private final int LOCATIONBUFFER = 70;
    private Score scoreKeeper;
    private Button cheatButton;
    private Button cheatNextButton;
    
   public Cheat(List<Ball> balls, Score scoreKeeper,Table table){
        this.scoreKeeper = scoreKeeper;
        cheatButton = new Button("Cheat : Blue");
        cheatButton.setLayoutX(10);
        cheatButton.setLayoutY(table.getyLength()+LOCATIONBUFFER);

        cheatNextButton = new Button("Next Cheat");
        cheatNextButton.setLayoutX(2*LOCATIONBUFFER-35);
        cheatNextButton.setLayoutY(table.getyLength()+LOCATIONBUFFER);

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
                cheatEvent(Paint.valueOf("blue"), balls);
                setText(ballColor.get());
                ballColor.set(1);

            } else if (ballColor.get() == 1) {
                cheatEvent(Paint.valueOf("red"), balls);
                setText(ballColor.get());
                ballColor.set(2);

            } else if (ballColor.get() == 2) {
                cheatEvent(Paint.valueOf("yellow"), balls);
                setText(ballColor.get());
                ballColor.set(3);

            } else if (ballColor.get() == 3) {
                cheatEvent(Paint.valueOf("green"), balls);
                setText(ballColor.get());
                ballColor.set(4);

            } else if (ballColor.get() == 4) {
                cheatEvent(Paint.valueOf("brown"), balls);
                setText(ballColor.get());
                ballColor.set(5);

            } else if (ballColor.get() == 5) {
                cheatEvent(Paint.valueOf("purple"), balls);
                setText(ballColor.get());
                ballColor.set(6);

            } else if (ballColor.get() == 6) {
                cheatEvent(Paint.valueOf("orange"), balls);
                setText(ballColor.get());
                ballColor.set(7);

            } else if (ballColor.get() == 7) {
                cheatEvent(Paint.valueOf("black"), balls);
                setText(ballColor.get());
                ballColor.set(0);
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

   public void cheatEvent(Paint colour, List<Ball> balls) {
        List<Ball> toRemove = new ArrayList<>();
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

    public Button getNextButton(){
        return cheatNextButton;
    }
}
