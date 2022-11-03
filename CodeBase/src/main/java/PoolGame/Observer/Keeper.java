package PoolGame.Observer;

/**
 * Is the Subject interface of the observer pattern
 */
public interface Keeper {

    /**
     * Attach the updater
     * @param updater
     */
    public void attach(Updater updater);
    /**
     * Notifies the updateer the change in object 
     */
    public void notifyUpdater();
}
