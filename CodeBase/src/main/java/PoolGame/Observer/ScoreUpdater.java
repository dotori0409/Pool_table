package PoolGame.Observer;

public class ScoreUpdater implements Updater{
    private Score scoreKeeper;

    public ScoreUpdater(Score scoreKeeper){
        this.scoreKeeper = scoreKeeper;
        this.scoreKeeper.attach(this);
    }
    @Override
    public void update(String newScore) {
        scoreKeeper.setScore(Integer.parseInt(newScore));
        
    }
}
