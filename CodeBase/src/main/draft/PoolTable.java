package Pool_game;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PoolTable {
    private final GraphicsContext gc;
    private Scene scene;
    private BallPit model;
    Canvas canvas;
    Pane pane;
    double mouseDragStartX;
    double mouseDragEndX;
    double mouseDragStartY;
    double mouseDragEndY;

    PoolTable(BallPit model) {
        this.model = model;
        pane = new Pane();
        this.scene = new Scene(pane, model.getWidth(), model.getHeight());
        canvas = new Canvas(model.getWidth(), model.getHeight());
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
    }

    Scene getScene() {
        return this.scene;
    }

    void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {

        gc.clearRect(0, 0, model.getWidth(), model.getHeight());

        for(Pocket pocket: model.getPockets()){
            gc.setFill(Paint.valueOf("Black"));
            gc.fillRect(pocket.getxPos(), pocket.getyPos(), pocket.getSide()*2, pocket.getSide()*2);
        }

        for (Ball ball: model.getBalls()) {
            Circle circle = new Circle(ball.getxPos() - ball.getRadius(),
                                       ball.getyPos() - ball.getRadius(),
                                       ball.getRadius());
            circle.setFill(ball.getColour());    
            pane.getChildren().add(circle);    
            if(circle.getFill() == Paint.valueOf("white")){
                circle.setOnMouseDragged(mouseEvent -> {

                });

                circle.setOnMouseReleased(mouseEvent -> {
                    // System.out.print("(" + mouseEvent.getX()+",");
                    // System.out.println(mouseEvent.getY()+")");
                    ball.setxVel(Power.xVelocity(ball, mouseEvent.getX(), mouseEvent.getY()));
                    ball.setyVel(Power.yVelocity(ball, mouseEvent.getX(), mouseEvent.getY()));
                    // model.tick();
                });
            }
        }
    }
}
