package PoolGame.Observer;

/**
 * Is the concrete observer of the observer pattern
 **/
public class Score implements Keeper{
    private Updater scoreUpdate;
    private int score;

    /**
     * Initialize score variable
     */
    public Score(){
        score = 0;
    }

    /**
     * Returns score
     * @return score
     */
    public int getScore(){
        return score;
    }

    /**
     * Set score to the parameter input
     * @param score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Resets the score
     */
    public void reset(){
        this.score = 0;
    }

    /**
     * Attaches the scoreupdate
     * @param scoreUpdate
     */
    public void attach(Updater scoreUpdate){
        this.scoreUpdate = scoreUpdate;
    }

    /**
     * Notify the updater there was an update
     */
    public void notifyUpdater(){
        score = getScore();
        scoreUpdate.update(String.valueOf(score));
    }
}
