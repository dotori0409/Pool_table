package PoolGame.objects;

/** Builds pool balls. */
public class PoolPocketBuilder implements PocketBuilder {
    // Required Parameters
    private double xPosition;
    private double yPosition;
    private double radius;

    @Override
    public void setxPos(double xPosition) {
        this.xPosition = xPosition;
    };

    @Override
    public void setyPos(double yPosition) {
        this.yPosition = yPosition;
    };

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    };

    /**
     * Builds the ball.
     * 
     * @return ball
     */
    public Pocket build() {
        return new Pocket(xPosition, yPosition,radius);
    }
}
