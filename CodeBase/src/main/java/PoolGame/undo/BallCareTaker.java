package PoolGame.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;

/**
 * Keeps track of the originator’s history by storing a stack of mementos
 */
public class BallCareTaker {
    private Map<Ball, BallMemento> mementos = new HashMap<>();

    /**
     * Add ball and memento into mementos variable
     * @param ball
     * @param memento
     */
    public void addMemento(Ball ball, BallMemento memento) { 
        mementos.put(ball, memento);
    }

    /**
     * Get the memento of input ball 
     * @param ball
     */
    public BallMemento getMemento(Ball ball) { 
        if (!mementos.containsKey(ball)) { return null; }
        return mementos.get(ball); 
    }
}
