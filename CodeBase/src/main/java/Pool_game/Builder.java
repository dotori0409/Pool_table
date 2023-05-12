package Pool_game;

import javafx.scene.paint.Paint;

public interface Builder {
    public void reset();
    public void initxPos(double xPos);
    public void inityPos(double yPos);
    public void initxVel(double xVel);
    public void inityVel(double yVel);
    public void initMass(double mass);
    public void initColor(Paint color);
    public Ball getBuilt();
}
