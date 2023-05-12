package Pool_game;

public class Pocket {
    private double x;
    private double y;
    private static double side = Ball.RADIUS * 2;

    public Pocket(double x, double y){    
        this.x = x;
        this.y = y;   
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getSide(){
        return side;
    }
}
