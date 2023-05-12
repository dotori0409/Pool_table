package Pool_game;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GameWindow {
    private PoolTable table;
    private TableReader treader;
    private BallPit model;
    private Collision collision;

    private Canvas canvas;
    private Scene scene;
    private Pane pane;
    private Pane root;

    private ArrayList<Circle> circles;
    private List<Ball> allBalls;
    private Circle cueBall;
    private Line notifier;
    private double notifier_x;
    private double notifier_y;
    private final int VEL_LIMIT = 30;
    private boolean cueBallInPocket = false;

    public GameWindow(BallPit model){
        collision = new Collision();
        treader = new TableReader();
        treader.read("src/main/resources/config.json");
        table = treader.getTable();
        this.model = model;
        pane = new Pane();
        root = new Pane();
        pane.getChildren().add(root);
        this.scene = new Scene(pane, table.gettableX(), table.gettableY());
        canvas = new Canvas(table.gettableX(), table.gettableY());
        pane.getChildren().add(canvas);
        initCircles();
    }

    public Scene getScene() {
        return this.scene;
    }

    public void run(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame draw = new KeyFrame(Duration.millis(100),t -> this.draw());
        timeline.getKeyFrames().add(draw);
        timeline.play();
    }

    private void initCircles(){
        circles = new ArrayList<>();
        allBalls = new ArrayList<>(model.getBalls());
        for (Ball ball: allBalls) {
            Circle circle = new Circle(ball.getxPos() - Ball.RADIUS,
                                       ball.getyPos() - Ball.RADIUS,
                                       Ball.RADIUS,
                                       ball.getColour());
            circles.add(circle);
            if(ball.getColour() == Paint.valueOf("white")) cueBall = circle;
        }  
        pane.getChildren().addAll(circles);
    }


    private void gameReset(){
        pane.getChildren().removeAll(circles);
        circles.removeAll(circles);
        allBalls.removeAll(allBalls);

        cueBallInPocket = false;
        StrategyContext sc = new StrategyContext(new CueBallStrategy());
        sc.inPocket(); 
    }

    private void drawPockets(){
        for(Pocket pocket: table.getPockets()){
            Rectangle rectangle = new Rectangle(pocket.getX(), pocket.getY(), pocket.getSide(), pocket.getSide());
            rectangle.setFill(Paint.valueOf("Black"));
            root.getChildren().add(rectangle);    
        }
    }

    private void relocateBalls(){
        for(int i = 0; i < circles.size(); i++){
            circles.get(i).setCenterX(allBalls.get(i).getxPos());
            circles.get(i).setCenterY(allBalls.get(i).getyPos());
            for(Pocket pocket: table.getPockets()){
                if(collision.fallInPocket(pocket, allBalls.get(i))){
                    if(allBalls.get(i) == getCueBall() && getCueBall() != null) cueBallInPocket = true;
                    pane.getChildren().remove(circles.get(i));
                    circles.remove(i);
                    allBalls.remove(allBalls.get(i));
                }
            } 
        }
    }

    private void drawNotifier(){
        cueBall.setOnMousePressed((MouseEvent e) -> {
            notifier = new Line(e.getX(),e.getY(),e.getX(),e.getY());
            notifier.setStroke(Paint.valueOf("black"));
            notifier.setStrokeWidth(3);
            pane.getChildren().add(notifier);
        });

        cueBall.setOnMouseDragged((MouseEvent e) -> {
            notifier.setEndX(e.getX()); 
            notifier.setEndY(e.getY());
        });

        cueBall.setOnMouseReleased((MouseEvent e) -> {
            pane.getChildren().remove(notifier);
            notifier_x = e.getX();
            notifier_y = e.getY();
            hitCueBall();
        });
    }

    private void hitCueBall(){
        System.out.println("Hit!");
        
        double newxVel = getCueBall().getxPos()-notifier_x;
        double newyVel = getCueBall().getyPos()-notifier_y;

        if(Math.abs(newxVel) > VEL_LIMIT) newxVel = newxVel/Math.abs(newxVel) * VEL_LIMIT;
        if(Math.abs(newyVel) > VEL_LIMIT) newyVel = newyVel/Math.abs(newyVel) * VEL_LIMIT;
        
        getCueBall().setxVel(newxVel);
        getCueBall().setyVel(newyVel);
    }

    private void draw() {
        if(cueBallInPocket) {
            gameReset();
            initCircles();
        } else {
            drawPockets();
            relocateBalls();
            drawNotifier();
            model.tick();
        }
        if(allBalls.size() == 1){
            StrategyContext sc = new StrategyContext(new ColoredBallStrategy());
            sc.inPocket(); 
        }
    }

    public Ball getCueBall(){
        for(Ball ball: allBalls){
            if (ball.getColour() == Paint.valueOf("white"))
                return ball;
        }
        return null;
    }
}


