package PoolGame;

import PoolGame.Observer.Score;
import PoolGame.Observer.ScoreUpdater;
import PoolGame.Observer.TimeUpdater;
import PoolGame.Observer.Updater;
import PoolGame.Observer.Timer.Timer;
import PoolGame.objects.*;
import PoolGame.strategy.Cheat;
import PoolGame.strategy.undo.BallCareTaker;
import PoolGame.strategy.undo.BallMemento;
import PoolGame.strategy.undo.BallRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Controls the game interface; drawing objects, handling logic and collisions.
 */
public class GameManager {
    private Table table;
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    ArrayList<Pocket> pockets = new ArrayList<Pocket>();
    private Ball cueBall;
    private Line cue;
    private Pane pane;
    private boolean cueSet = false;
    private boolean cueActive = false;
    private boolean winFlag = false;

    private Text text = new Text();
    private int totalScore = 0;
    private Score scoreKeeper = new Score();
    private int savedScore = 0;

    private boolean moving = false;
    private int record = 0;
    private Map<Ball, BallRecord> records = new HashMap<>();
    private BallCareTaker caretaker = new BallCareTaker();

    private Timer timer;
    private String savedTimeDisplay = "Time 00 : 00";
    
    private boolean reset_flag = false;
    private Button undo = new Button("Undo");
    private boolean doneUndo = false;
    private boolean ballRemoved = false;
    private ArrayList<Ball> savedBalls;
   
    private final double TABLEBUFFER = Config.getTableBuffer();
    private final double TABLEEDGE = Config.getTableEdge();
    private final double FORCEFACTOR = 0.1;

    private Cheat cheat;

    private Updater scoreUpdater = new ScoreUpdater(scoreKeeper);
    private Updater timeUpdater;

    private Scene scene;
    private GraphicsContext gc;


    /**
     * Initialises timeline and cycle count.
     */
    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> {
                    try {
                        this.draw();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Builds GameManager properties such as initialising pane, canvas,
     * graphicscontext, and setting events related to clicks.
     * @throws InterruptedException
     */

    public void buildManager() throws InterruptedException {
        pane = new Pane();
        setClickEvents(pane);
        this.scene = new Scene(pane, table.getxLength() + TABLEBUFFER * 2, table.getyLength() + TABLEBUFFER * 2);
        Canvas canvas = new Canvas(table.getxLength() + TABLEBUFFER * 2, table.getyLength() + TABLEBUFFER * 2);
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
        undo.setLayoutX(table.getxLength() - Config.getTableBuffer() * 2 - Config.getTableBuffer()/3);
        undo.setLayoutY(0);
        pane.getChildren().add(undo);
        pane.getChildren().add(text);
        //initiaize timer
        timer = new Timer(pane);
        timeUpdater = new TimeUpdater(timer);
        //initiaize data for record and caretaker
        for(Ball ball: balls){
            savedBalls = new ArrayList<>(balls);
            BallRecord ballRec = new BallRecord(ball);
            ballRec.addRecord(record,new Point2D(ball.getxPos()-Config.getTableBuffer(), ball.getyPos()-Config.getTableBuffer()));
            records.put(ball,ballRec);
            BallMemento memento = new BallMemento(ballRec.getRecord());
            caretaker.addMemento(ball, memento);
        }
        //find cueBall
        for(Ball ball: balls){
            if(ball.isCue()){
                cueBall = ball;
            }
        }
        //initialize cheat
        cheat = new Cheat(balls, timer, scoreKeeper, table);
        pane.getChildren().add(cheat.getCheatButton());
        pane.getChildren().add(cheat.getNextButton());
        //calculate total score
        for(Ball ball: balls){
            totalScore += ball.getScore(ball.getColour());
        }
    }

    /**
     * Draws all relevant items - table, cue, balls, pockets - onto Canvas.
     * Used Exercise 6 as reference.
     * @throws InterruptedException
     */
    private void draw() throws InterruptedException {
        timer.time();
        if(reset_flag){
            timer.resetTime();
            reset_flag = false;
        }
        tick();

        // Fill in background
        gc.setFill(Paint.valueOf("white"));
        gc.fillRect(0, 0, table.getxLength() + TABLEBUFFER * 2, table.getyLength() + TABLEBUFFER * 2);

        // Fill in edges
        gc.setFill(Paint.valueOf("brown"));
        gc.fillRect(TABLEBUFFER - TABLEEDGE, TABLEBUFFER - TABLEEDGE, table.getxLength() + TABLEEDGE * 2,
                table.getyLength() + TABLEEDGE * 2);

        // Fill in Table
        gc.setFill(table.getColour());
        gc.fillRect(TABLEBUFFER, TABLEBUFFER, table.getxLength(), table.getyLength());

        // Fill in Pockets
        for (Pocket pocket : pockets) {
            gc.setFill(Paint.valueOf("black"));
            gc.fillOval(pocket.getxPos() - pocket.getRadius(), pocket.getyPos() - pocket.getRadius(),
                    pocket.getRadius() * 2, pocket.getRadius() * 2);
        }

        // Cue
        if (this.cue != null && cueActive) {
            gc.strokeLine(cue.getStartX(), cue.getStartY(), cue.getEndX(), cue.getEndY());
            gc.setStroke(Color.BROWN);
            gc.setLineWidth(10);
        }
        
        
        //Check if moving is over
        boolean moving_flag = false;
        for (Ball ball : balls) {
            if (ball.isActive()) {
                gc.setFill(ball.getColour());
                gc.fillOval(ball.getxPos() - ball.getRadius(),
                        ball.getyPos() - ball.getRadius(),
                        ball.getRadius() * 2,
                        ball.getRadius() * 2);
            }
            if(moving && (Math.abs(ball.getxVel()) > 0.01 || Math.abs(ball.getyVel()) > 0.01)){
                moving_flag = true;
            }
        }

        //Add info to memento if all balls are at a stop
        if((moving && moving_flag == false)){
            moving = false;
            record++;
            for(Ball ball: balls){
                savedBalls = new ArrayList<>(balls);
                //update records
                BallRecord ballRec = records.get(ball);
                ballRec.addRecord(record,new Point2D(ball.getxPos()- Config.getTableBuffer(), ball.getyPos()- Config.getTableBuffer()));
                records.put(ball,ballRec);
                //update caretaker
                BallMemento memento = caretaker.getMemento(ball);
                memento.getState().put(record,ballRec.getRecord().get(record));
            }
            doneUndo = false;
        }

        // Win
        if (winFlag) {
            gc.setStroke(Paint.valueOf("white"));
            gc.setFont(new Font("Impact", 80));
            gc.strokeText("Win and bye", table.getxLength() / 2 + TABLEBUFFER - 180,
                    table.getyLength() / 2 + TABLEBUFFER);
        }
    }

    /**
     * Updates positions of all balls, handles logic related to collisions.
     * Used Exercise 6 as reference.
     */
    public void tick() {
        String scoreText = "Score: "+ Integer.toString(scoreKeeper.getScore());
        text.setText(scoreText);
        text.setX(table.getxLength()/2);
        text.setY(20);
        text.setFont(Font.font(null, FontWeight.BOLD, 20));

        
        if (scoreKeeper.getScore() == totalScore) {
            winFlag = true;
        }

        for (Ball ball : balls) {
            ball.tick();

            if (ball.isCue() && cueSet) {
                hitBall(ball);
            }

            double width = table.getxLength();
            double height = table.getyLength();

            // Check if ball landed in pocket
            for (Pocket pocket : pockets) {
                if (pocket.isInPocket(ball)) {
                    if (ball.isCue()) {
                        this.reset();
                        reset_flag = true;
                    } else {
                        if (ball.remove()) {
                            scoreUpdater.update(String.valueOf(ball.getScore(ball.getColour())+scoreKeeper.getScore()));
                        } else {
                            // Check if when ball is removed, any other balls are present in its space. (If
                            // another ball is present, blue ball is removed)
                            for (Ball otherBall : balls) {
                                double deltaX = ball.getxPos() - otherBall.getxPos();
                                double deltaY = ball.getyPos() - otherBall.getyPos();
                                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                                if (otherBall != ball && otherBall.isActive() && distance < 10) {
                                    ball.remove();
                                    ballRemoved = true;
                                    savedBalls = new ArrayList<>(balls);
                                    savedTimeDisplay = timer.getTime();
                                    savedScore = scoreKeeper.getScore();
                                }
                            }
                        }
                    }
                    break;
                }
            }

            // Handle the edges (balls don't get a choice here)
            if (ball.getxPos() + ball.getRadius() > width + TABLEBUFFER) {
                ball.setxPos(width - ball.getRadius());
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getxPos() - ball.getRadius() < TABLEBUFFER) {
                ball.setxPos(ball.getRadius());
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getyPos() + ball.getRadius() > height + TABLEBUFFER) {
                ball.setyPos(height - ball.getRadius());
                ball.setyVel(ball.getyVel() * -1);
            }
            if (ball.getyPos() - ball.getRadius() < TABLEBUFFER) {
                ball.setyPos(ball.getRadius());
                ball.setyVel(ball.getyVel() * -1);
            }

            // Apply table friction
            double friction = table.getFriction();
            ball.setxVel(ball.getxVel() * friction);
            ball.setyVel(ball.getyVel() * friction);

            // Check ball collisions
            for (Ball ballB : balls) {
                if (checkCollision(ball, ballB)) {
                    Point2D ballPos = new Point2D(ball.getxPos(), ball.getyPos());
                    Point2D ballBPos = new Point2D(ballB.getxPos(), ballB.getyPos());
                    Point2D ballVel = new Point2D(ball.getxVel(), ball.getyVel());
                    Point2D ballBVel = new Point2D(ballB.getxVel(), ballB.getyVel());
                    Pair<Point2D, Point2D> changes = calculateCollision(ballPos, ballVel, ball.getMass(), ballBPos,
                            ballBVel, ballB.getMass(), false);
                    calculateChanges(changes, ball, ballB);
                }
            }
        }
    }

    //adds prev data to caretaker
    public void revertBalls(Ball ball,int currentRecord) {
        BallRecord ballRecord = records.get(ball);   
        //prev data
        records.put(ball,ballRecord);
        BallMemento memento = ballRecord.revert(2,currentRecord);
        caretaker.addMemento(ball, memento);
    }

    /**
     * Resets the game.
     */
    public void reset() {
        for (Ball ball : balls) {
            ball.reset();
        }

        scoreKeeper.reset();;
    }

    /**
     * @return scene.
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Sets the table of the game.
     * 
     * @param table
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * @return table
     */
    public Table getTable() {
        return this.table;
    }

    /**
     * Sets the balls of the game.
     * 
     * @param balls
     */
    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    /**
     * Sets the pockets of the game.
     * 
     * @param pockets
     */
    public void setPockets(ArrayList<Pocket> pockets) {
        this.pockets = pockets;
    }

    /**
     * Hits the ball with the cue, distance of the cue indicates the strength of the
     * strike.
     * 
     * @param ball
     */
    private void hitBall(Ball ball) {
        double deltaX = ball.getxPos() - cue.getStartX();
        double deltaY = ball.getyPos() - cue.getStartY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Check that start of cue is within cue ball
        if (distance < ball.getRadius()) {
            // Collide ball with cue
            double hitxVel = (cue.getStartX() - cue.getEndX()) * FORCEFACTOR;
            double hityVel = (cue.getStartY() - cue.getEndY()) * FORCEFACTOR;

            ball.setxVel(hitxVel);
            ball.setyVel(hityVel);
        }
        cueSet = false;
    }

    /**
     * Changes values of balls based on collision (if ball is null ignore it)
     * 
     * @param changes
     * @param ballA
     * @param ballB
     */
    private void calculateChanges(Pair<Point2D, Point2D> changes, Ball ballA, Ball ballB) {
        ballA.setxVel(changes.getKey().getX());
        ballA.setyVel(changes.getKey().getY());
        if (ballB != null) {
            ballB.setxVel(changes.getValue().getX());
            ballB.setyVel(changes.getValue().getY());
        }
    }
    

    /**
     * Sets the cue to be drawn on click, and manages cue actions
     * 
     * @param pane
     */
    double saveXStart = 0;
    double saveYStart = 0;
    double saveXEnd = 0;
    double saveYEnd = 0;
    private void setClickEvents(Pane pane) {
        
        pane.setOnMousePressed(event -> {
            // cue = new Line(event.getX(), event.getY(), event.getX(), event.getY());
            cue = CueStickSingleton.getInstance(event);
            cueSet = false;
            cueActive = true;
        });

        pane.setOnMouseDragged(event -> {
            saveXStart = cueBall.getxPos();
            saveYStart = cueBall.getyPos();
            saveXEnd = event.getX();
            saveYEnd = event.getY();
            cue.setStartX(cueBall.getxPos());
            cue.setStartY(cueBall.getyPos());
            cue.setEndX(event.getX());
            cue.setEndY(event.getY());
        });

        pane.setOnMouseReleased(event -> {
            cue.setStartX(saveXStart);
            cue.setStartY(saveYStart);
            cue.setEndX(saveXEnd);
            cue.setEndY(saveYEnd);
            cueSet = true;
            cueActive = true;
            moving = true;
            savedTimeDisplay = timer.getTime();
            savedScore = scoreKeeper.getScore();
        });

        undo.setOnAction(e ->{
            if(!doneUndo){
                if(cheat.getDoneCheat()){
                    timeUpdater.update(cheat.getSavedTime());
                    scoreUpdater.update(String.valueOf(cheat.getSavedScore()));
                    cheat.setDoneCheatFalse();
                    balls = new ArrayList<>(savedBalls);
                } else {
                    if (ballRemoved){
                        balls = new ArrayList<>(savedBalls);
                    }
                    for(Ball ball: balls){
                        if(caretaker.getMemento(ball).getState().size()>1){
                            timeUpdater.update(savedTimeDisplay);
                            scoreUpdater.update(String.valueOf(savedScore));
                            doneUndo = true;
                            revertBalls(ball, record);
                            ball.setxPos(caretaker.getMemento(ball).getState().get(record-1).getX());
                            ball.setyPos(caretaker.getMemento(ball).getState().get(record-1).getY());
                        }
                    }
                    record--;
                }

                if(winFlag){
                    winFlag = false;
                }
            } else {
                System.out.println("You've already undone");
            }        
        });
    }

    /**
     * Checks if two balls are colliding.
     * Used Exercise 6 as reference.
     * 
     * @param ballA
     * @param ballB
     * @return true if colliding, false otherwise
     */
    private boolean checkCollision(Ball ballA, Ball ballB) {
        if (ballA == ballB) {
            return false;
        }

        return Math.abs(ballA.getxPos() - ballB.getxPos()) < ballA.getRadius() + ballB.getRadius() &&
                Math.abs(ballA.getyPos() - ballB.getyPos()) < ballA.getRadius() + ballB.getRadius();
    }

    /**
     * Collision function adapted from assignment, using physics algorithm:
     * http://www.gamasutra.com/view/feature/3015/pool_hall_lessons_fast_accurate_.php?page=3
     *
     * @param positionA The coordinates of the centre of ball A
     * @param velocityA The delta x,y vector of ball A (how much it moves per tick)
     * @param massA     The mass of ball A (for the moment this should always be the
     *                  same as ball B)
     * @param positionB The coordinates of the centre of ball B
     * @param velocityB The delta x,y vector of ball B (how much it moves per tick)
     * @param massB     The mass of ball B (for the moment this should always be the
     *                  same as ball A)
     *
     * @return A Pair in which the first (key) Point2D is the new
     *         delta x,y vector for ball A, and the second (value) Point2D is the
     *         new delta x,y vector for ball B.
     */
    public static Pair<Point2D, Point2D> calculateCollision(Point2D positionA, Point2D velocityA, double massA,
            Point2D positionB, Point2D velocityB, double massB, boolean isCue) {

        // Find the angle of the collision - basically where is ball B relative to ball
        // A. We aren't concerned with
        // distance here, so we reduce it to unit (1) size with normalize() - this
        // allows for arbitrary radii
        Point2D collisionVector = positionA.subtract(positionB);
        collisionVector = collisionVector.normalize();

        // Here we determine how 'direct' or 'glancing' the collision was for each ball
        double vA = collisionVector.dotProduct(velocityA);
        double vB = collisionVector.dotProduct(velocityB);

        // If you don't detect the collision at just the right time, balls might collide
        // again before they leave
        // each others' collision detection area, and bounce twice.
        // This stops these secondary collisions by detecting
        // whether a ball has already begun moving away from its pair, and returns the
        // original velocities
        if (vB <= 0 && vA >= 0 && !isCue) {
            return new Pair<>(velocityA, velocityB);
        }

        // This is the optimisation function described in the gamasutra link. Rather
        // than handling the full quadratic
        // (which as we have discovered allowed for sneaky typos)
        // this is a much simpler - and faster - way of obtaining the same results.
        double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

        // Now we apply that calculated function to the pair of balls to obtain their
        // final velocities
        Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
        Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

        return new Pair<>(velAPrime, velBPrime);
    }

    public Pane getPane(){
        return pane;
    }
}
