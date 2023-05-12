package Pool_game;

import javafx.scene.paint.Paint;

public class Ball implements Factory{
    public final static double RADIUS = 15;
    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private Paint colour;
    private double mass;

    public Ball() {

    }

    @Override
    public Factory create() {
        return new Ball();
    }
    
    public void tickX(){
        this.xPos += Math.round(xVel);
    }
    
    public void tickY(){
        // double rate = 1;
        // if(xVel != 0 && yVel !=0) rate = Math.abs(xVel/yVel);
        this.yPos += Math.round(yVel);
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

    public void setColour(Paint colour) {
        this.colour = colour;
    }

    public void setMass(double mass) {
        this.mass = mass;
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

    public double getMass() {
        return mass;
    }
}
