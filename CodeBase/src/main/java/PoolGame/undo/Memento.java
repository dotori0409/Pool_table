package PoolGame.undo;

import java.util.Map;

import javafx.geometry.Point2D;

public class Memento {
    //encapsulation 
    private Map<Integer, Point2D> state;

    //immutable! data only passed in once, via the constructor.
    public Memento(Map<Integer, Point2D> state) 
    { this.state = state; } 

    //USed to modify memento data
    public Map<Integer, Point2D> getState() {
        return state;
    }
}
