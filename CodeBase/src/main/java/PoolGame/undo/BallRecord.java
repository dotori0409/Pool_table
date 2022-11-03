package PoolGame.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;
import javafx.geometry.Point2D;

/**
 * Originator of the Memento pattern.
 * Produce snapshots of its own state, as well as restore its state from snapshots when needed.
 */
public class BallRecord {
    private Map<Integer, Point2D> records= new HashMap<>(); 
    private Ball ball;

    /**
     * Initialize ball variable
     * @param ball
     */
    public BallRecord(Ball ball) {
        this.ball = ball;
        // this.records = new HashMap<>(); 
    }

    /**
     * Put key and record into records
     * @param key
     * @param record
     */
    public void addRecord(int key, Point2D record) {
        records.put(key, record);
    }

    /**
     * Returns records hashmap
     * @return records
     */
    public Map<Integer, Point2D> getRecord() {
        return records;
    }

    /**
     * Create Deep copy of internal state
     * @return copyRecords
     */
    private Map<Integer, Point2D> copyRecords() { 
        Map<Integer, Point2D> copyRecords = new HashMap<>();
        for(Map.Entry<Integer, Point2D> entry : records.entrySet()) {
            copyRecords.put(entry.getKey(), entry.getValue());
        }
        return copyRecords; 
    }

    /**
     * Create new Memento object of current ball record
     * Revert to a previous state 
     * @param numOfRecords
     * @param currentRecord
     * @return new BallMemento(copyRecords);
     */
    public BallMemento revert(int numOfRecords, int currentRecord) {
        //1. generate Deep copy of current record
        Map<Integer, Point2D> copyRecords = copyRecords();
        //reset ball
        if(numOfRecords>2)copyRecords.remove(currentRecord-2);
        //2. Save the current state inside a memento
        return new BallMemento(copyRecords);
    }
}
