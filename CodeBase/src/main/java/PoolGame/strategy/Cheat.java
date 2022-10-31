package PoolGame.strategy;

import java.util.ArrayList;
import java.util.List;

import PoolGame.Config;
import PoolGame.objects.Ball;
import PoolGame.objects.Score;
import PoolGame.objects.Table;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class Cheat {
    private final int LOCATIONBUFFER = 70;
    private Score scoreKeeper;
    private Button redCheat = new Button("Red");
    private Button yellowcheat = new Button("Yellow");;
    private Button greenCheat = new Button("Green");;
    private Button brownCheat = new Button("Brown");;
    private Button blueCheat = new Button("Blue");;
    private Button purpleCheat = new Button("Purple");;
    private Button blackCheat = new Button("Black");;
    private Button orangeCheat = new Button("Orange");;
    
   public Cheat(List<Ball> balls, Score scoreKeeper,Table table, Pane pane){
        this.scoreKeeper = scoreKeeper;
        redCheat.setLayoutX(Config.getTableBuffer());
        redCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        pane.getChildren().add(redCheat);

        yellowcheat.setLayoutX(table.getxLength() + 2*LOCATIONBUFFER);
        yellowcheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        pane.getChildren().add(yellowcheat);


        redCheat.setOnAction(e ->{
            task(Paint.valueOf("red"), balls);
        });

        yellowcheat.setOnAction(e ->{
            task(Paint.valueOf("yellow"), balls);
        });

        greenCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        greenCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        greenCheat.setOnAction(e ->{
            task(Paint.valueOf("green"), balls);
        });

        brownCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        brownCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        brownCheat.setOnAction(e ->{
            task(Paint.valueOf("brown"), balls);
        });

        blueCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        blueCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        blueCheat.setOnAction(e ->{
            task(Paint.valueOf("blue"), balls);
        });

        purpleCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        purpleCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        purpleCheat.setOnAction(e ->{
            task(Paint.valueOf("purple"), balls);
        });

        blackCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        blackCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        blackCheat.setOnAction(e ->{
            task(Paint.valueOf("black"), balls);
        });

        orangeCheat.setLayoutX(table.getxLength() + Config.getTableBuffer());
        orangeCheat.setLayoutY(table.getyLength() + LOCATIONBUFFER);
        orangeCheat.setOnAction(e ->{
            task(Paint.valueOf("orange"), balls);
        });
   }

   public void task(Paint colour, List<Ball> balls) {
        List<Ball> toRemove = new ArrayList<>();

        for (Ball ball : balls) {
            if (ball.getColour().equals(colour)) {
                scoreKeeper.addScore(ball.getScore(ball.getColour()));
                toRemove.add(ball);
            }
        }

        balls.removeAll(toRemove);
        for(Ball ball : balls) {
            System.out.println("After :" + ball.getColour());
        }
    }
}
