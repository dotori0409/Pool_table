package Pool_game;

import javafx.geometry.Point2D;
import javafx.util.Pair;

public class Collision {
    public boolean fallInPocket(Pocket pocket, Ball ball){
        if(Math.abs(pocket.getX()+pocket.getSide() - ball.getxPos()) < 20 && Math.abs(pocket.getY()+pocket.getSide() - ball.getyPos()) < 20){
            return true;
        }
        return false;
    }

    public boolean checkCollision(Ball ballA, Ball ballB){
        return (Math.abs(ballA.getxPos() - ballB.getxPos()) < Ball.RADIUS + Ball.RADIUS &&
                Math.abs(ballA.getyPos() - ballB.getyPos()) < Ball.RADIUS + Ball.RADIUS);
    }

    public Pair<Point2D, Point2D> calculateCollision(Point2D positionA, Point2D velocityA, double massA, Point2D positionB, Point2D velocityB, double massB) {

        Point2D collisionVector = positionA.subtract(positionB);
        collisionVector = collisionVector.normalize();

        double vA = collisionVector.dotProduct(velocityA);
        double vB = collisionVector.dotProduct(velocityB);

        if (vB <= 0 && vA >= 0) {
            return new Pair<>(velocityA, velocityB);
        }

        double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

        Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
        Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

        return new Pair<>(velAPrime, velBPrime);
    }
    
}
