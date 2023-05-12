package Pool_game;

import javafx.scene.paint.Paint;

public abstract class Ball{
    
    protected double xPos;
    protected double yPos;
    protected double radius;
    protected double xVel;
    protected double yVel;
    protected Paint colour;
    protected double mass;

    public abstract void move();

    public void tick() {
        xPos += xVel;
        yPos += yVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getRadius() {
        return radius;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public Paint getColour() {
        return colour;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }
}

