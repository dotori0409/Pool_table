package Pool_game;

import javafx.scene.paint.Paint;

public class BallDirector {
    public void build(Builder builder, Paint color, double mass, double xPos, double yPos, double xVel, double yVel){
        builder.reset();
        builder.initColor(color);
        builder.initMass(mass);
        builder.initxPos(xPos);
        builder.inityPos(yPos);
        builder.initxVel(xVel);
        builder.inityVel(yVel);
    }
}
