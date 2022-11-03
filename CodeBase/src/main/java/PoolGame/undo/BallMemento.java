package PoolGame.undo;

import java.util.Map;

import javafx.geometry.Point2D;

/**
 * Involved in the value object that acts as a snapshot of the originator’s state
 */
public class BallMemento {
    private Map<Integer, Point2D> state;

    /**
     * Initialize state variable
     */
    public BallMemento(Map<Integer, Point2D> state) 
    { this.state = state; } 

    /**
     * USed to modify memento data
     */
    public Map<Integer, Point2D> getState() {
        return state;
    }
}
