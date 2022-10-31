package PoolGame.strategy.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;

public class BallCareTaker {
    private Map<Ball, BallMemento> mementos = new HashMap<>();

    public void addMemento(Ball ball, BallMemento memento) { 
        mementos.put(ball, memento);
    }

    public BallMemento getMemento(Ball ball) { 
        if (!mementos.containsKey(ball)) { return null; }
        return mementos.get(ball); 
    }
}
