package PoolGame.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CueStick {
    private Rectangle cueStick;
    private double x;
    private double y;

    public CueStick(){
        this.x = 10;
        this.y = 200;
        this.cueStick = new Rectangle(x,y,20,100);
        this.cueStick.setFill(Color.SIENNA);
    }

    public Rectangle getCueStick(){
        return cueStick;
    }

    public double getxPos(){
        return cueStick.getX();
    }

    public double getyPos(){
        return cueStick.getY();
    }

    public void setxPos(double x){
        cueStick.setX(x);
    }

    public void setyPos(double y){
        cueStick.setY(y);
    }
}
