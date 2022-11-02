package PoolGame.objects;

import PoolGame.Observer.Updater;

public class Score {
    private Updater scoreUpdate;
    private int score;

    public Score(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void reset(){
        this.score = 0;
    }

    public void attach(Updater scoreUpdate){
        this.scoreUpdate = scoreUpdate;
    }

    public void notifyUpdater(){
        score = getScore();
        scoreUpdate.update(score);
    }
}
