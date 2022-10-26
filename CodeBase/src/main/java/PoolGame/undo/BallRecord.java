package PoolGame.undo;

import java.util.HashMap;
import java.util.Map;

import PoolGame.objects.Ball;
import javafx.geometry.Point2D;

public class BallRecord {
    private Map<Integer, Point2D> records; 
    private Ball ball;

    public BallRecord(Ball ball) {
        this.ball = ball;
        this.records = new HashMap<>(); 
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
    public Memento revert(int currentRecord) {
        //1. generate Deep copy of current record
        Map<Integer, Point2D> copyRecords = copyRecords();
        
        //reset (has nothing to do with Memento)
        int recordToRemove = currentRecord;
        for(int i = 0; i < currentRecord; i++) {
            System.out.println(recordToRemove-1);
            System.out.println(records.get(recordToRemove-1));
            records.remove(recordToRemove);
            recordToRemove--;
        }

        //2. Save the current state inside a memento
        return new Memento(copyRecords);
    }

    public void restore(Memento memento) {
        Map<Integer, Point2D> state = memento.getState();
        this.records = state;
    }

}
