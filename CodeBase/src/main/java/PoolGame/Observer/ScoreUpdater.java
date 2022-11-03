package PoolGame.Observer;

/**
 * Concrete subject of the observer pattern that involves in the score display
 */
public class ScoreUpdater implements Updater{
    private Score scoreKeeper;

    /**
     * Initialize variables and attaches
     */
    public ScoreUpdater(Score scoreKeeper){
        this.scoreKeeper = scoreKeeper;
        this.scoreKeeper.attach(this);
    }
    /**
     * Update new score 
     * @param newScore
     */
    @Override
    public void update(String newScore) {
        scoreKeeper.setScore(Integer.parseInt(newScore));
        
    }
}
