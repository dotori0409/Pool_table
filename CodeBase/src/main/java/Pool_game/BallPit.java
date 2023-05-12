package Pool_game;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

public class BallPit{
    private Collision collision;
    private BallReader reader;
    private final double height;
    private final double width;
    private final double friction;
    private List<Ball> balls; 

    public BallPit(PoolTable t){
        collision = new Collision();
        reader = new BallReader();  
        reader.read("src/main/resources/config.json");
        balls = reader.getBalls();
        this.height = t.gettableY();
        this.width = t.gettableX();
        this.friction = t.getFriction();
    }

    public List<Ball> resetBalls(){
        reader.read("src/main/resources/config.json");
        balls = reader.getBalls();
        return balls;
    }

    public void tick() {      
        for(Ball ball: reader.getBalls()) {
            //Move in x-direction
            double rate = 1;
            if(ball.getxVel() != 0 && ball.getyVel() !=0) rate = Math.abs(ball.getxVel()/ball.getyVel());
            if(ball.getxVel()!= 0) {
                ball.tickX();

                if(ball.getxVel() > 0) {
                    if (ball.getxVel() - this.friction < this.friction) ball.setxVel(0);
                    else ball.setxVel(ball.getxVel() - this.friction);
                } else {
                    if (ball.getxVel() + this.friction > this.friction) ball.setxVel(0);
                    else ball.setxVel(ball.getxVel() + this.friction);
                }
            }
            //Move in y-direction
            if(ball.getyVel()!= 0) {
                ball.tickY();
                if(ball.getyVel() > 0) {
                    if (ball.getyVel() - this.friction*rate < this.friction*rate) ball.setyVel(0);
                    else ball.setyVel(ball.getyVel() - this.friction*rate);
                } else {
                    if (ball.getyVel() + this.friction*rate > this.friction*rate) ball.setyVel(0);
                    else ball.setyVel(ball.getyVel() + this.friction*rate);
                }
            }

            // Handle the edges
            if (ball.getxPos() > width - Ball.RADIUS) {
                ball.setxPos(width - Ball.RADIUS);
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getxPos() - Ball.RADIUS < 0) {
                ball.setxPos(0 + Ball.RADIUS);
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getyPos()> height - Ball.RADIUS) {
                ball.setyPos(height - Ball.RADIUS);
                ball.setyVel(ball.getyVel() * -1);
            }
            if (ball.getyPos() - Ball.RADIUS < 0) {
                ball.setyPos(0 +  Ball.RADIUS);
                ball.setyVel(ball.getyVel() * -1);
            }

            for(Ball ballB: reader.getBalls()) {
                if(collision.checkCollision(ball, ballB)){
                    Pair<Point2D, Point2D> result = new Pair<>(new Point2D(0, 0), new Point2D(0, 0));

                    Point2D positionA = new Point2D(ball.getxPos(), ball.getyPos());
                    Point2D velocityA = new Point2D(ball.getxVel(), ball.getyVel());
                    Point2D positionB = new Point2D(ballB.getxVel(), ballB.getyVel());
                    Point2D velocityB = new Point2D(ballB.getxVel(), ballB.getyVel());

                    result = collision.calculateCollision(positionA, velocityA, ball.getMass(), positionB, velocityB, ballB.getMass());
                    ball.setxVel(result.getKey().getX());
                    ball.setyVel(result.getKey().getY());
                    ballB.setxVel(result.getValue().getX());
                    ballB.setyVel(result.getValue().getY());
                }
            }
        }
    }

    // public Ball getCueBall(){
    //     for(Ball ball: reader.getBalls()){
    //         if (ball.getColour() == Paint.valueOf("white"))
    //             return ball;
    //     }
    //     return null;
    // }   

    public List<Ball> getBalls(){
		return balls;
	}
}
