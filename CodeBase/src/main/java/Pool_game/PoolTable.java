package Pool_game;

import javafx.scene.paint.Paint;

public class PoolTable implements Factory{
    public final static Pocket[] pockets = new Pocket[6];
    private Paint color;
    private double friction;
    private long tableX;
    private long tableY;

    public PoolTable(Paint color, double friction, long tableX, long tableY) {
        this.color = color;
        this.friction = friction;
        this.tableX = tableX;
        this.tableY = tableY;
        
        pockets[0] = new Pocket(0, 0);
        pockets[1] = new Pocket(tableX/2-Ball.RADIUS,0);
        pockets[2] = new Pocket(tableX-2*Ball.RADIUS,0);
        pockets[3] = new Pocket(0,tableY-2*Ball.RADIUS);
        pockets[4] = new Pocket(tableX/2-Ball.RADIUS, tableY-2*Ball.RADIUS);
        pockets[5] = new Pocket(tableX-2*Ball.RADIUS,tableY-2*Ball.RADIUS);
    }
    
    @Override
    public Factory create() {
        return new PoolTable(color, friction, tableX, tableY);
    }

    public Pocket[] getPockets(){
        return pockets;
    }

    public Paint getColor(){
        return color;
    }

    public double getFriction(){
        return friction;
    }

    public double gettableX(){
        return tableX;
    }

    public double gettableY(){
        return tableY;
    }
}