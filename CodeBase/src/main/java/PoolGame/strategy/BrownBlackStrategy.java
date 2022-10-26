package PoolGame.strategy;

public class BrownBlackStrategy extends PocketStrategy{

    public BrownBlackStrategy(){
        this.lives = 3;
    }
    @Override
    public void reset() {
        this.lives = 3;
    }
    
}
