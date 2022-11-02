package PoolGame.Observer;

import PoolGame.objects.Score;

public class ScoreUpdater implements Updater{
    private Score scoreKeeper;

    public ScoreUpdater(Score scoreKeeper){
        this.scoreKeeper = scoreKeeper;
        this.scoreKeeper.attach(this);
    }
    @Override
    public void update(int newScore) {
        scoreKeeper.setScore(newScore);
        
    }
}
