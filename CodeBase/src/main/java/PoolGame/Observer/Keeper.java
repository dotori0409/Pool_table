package PoolGame.Observer;

public interface Keeper {
    public void attach(Updater updater);
    public void notifyUpdater();
}
