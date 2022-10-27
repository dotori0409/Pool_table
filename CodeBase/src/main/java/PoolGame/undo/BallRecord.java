package PoolGame.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;
import javafx.geometry.Point2D;

public class BallRecord {
    private Map<Integer, Point2D> records= new HashMap<>(); 
    private Ball ball;

    public BallRecord(Ball ball) {
        this.ball = ball;
        // this.records = new HashMap<>(); 
    }

    public void addRecord(int key, Point2D record) {
        records.put(key, record);
    }

    public Map<Integer, Point2D> getRecord() {
        return records;
    }

    //create Deep copy of internal state !!! VERY IMPORTANT
    private Map<Integer, Point2D> copyRecords() { 
        Map<Integer, Point2D> copyRecords = new HashMap<>();
        for(Map.Entry<Integer, Point2D> entry : records.entrySet()) {
            copyRecords.put(entry.getKey(), entry.getValue());
        }
        return copyRecords; 
    }

    //Create new Memento object of current ball record
    //revert UOS to a previous state 
    public Memento revert(int numOfRecords, int currentRecord) {
        //1. generate Deep copy of current record
        Map<Integer, Point2D> copyRecords = copyRecords();
        //reset ball
        copyRecords.remove(currentRecord-2);
        //2. Save the current state inside a memento
        return new Memento(copyRecords);
    }
}
