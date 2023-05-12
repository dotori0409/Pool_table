package Pool_game;

import javafx.scene.paint.Paint;

public class BallBuilder implements Builder{

    Ball ball;
    
    @Override
    public void reset() {
        this.ball = new Ball();
    }

    @Override
    public void initxPos(double xPos) {
        ball.setxPos(xPos);
    }

    @Override
    public void inityPos(double yPos) {
        ball.setyPos(yPos);
        
    }

    @Override
    public void initxVel(double xVel) {
        ball.setxVel(xVel);
    }

    @Override
    public void inityVel(double yVel) {
        ball.setyVel(yVel);
    }

    @Override
    public void initMass(double mass) {
        ball.setMass(mass);
    }

    @Override
    public void initColor(Paint color) {
        ball.setColour(color);
    }

    @Override
    public Ball getBuilt() {
        return ball;
    }
    
}
