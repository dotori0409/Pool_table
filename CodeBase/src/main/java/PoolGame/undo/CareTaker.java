package PoolGame.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;

public class CareTaker {
    private Map<Ball, Memento> mementos = new HashMap<>();

    public void addMemento(Ball ball, Memento memento) { 
        mementos.put(ball, memento);
    }

    public Memento getMemento(Ball ball) { 
        if (!mementos.containsKey(ball)) { return null; }
        return mementos.get(ball); 
    }
}
