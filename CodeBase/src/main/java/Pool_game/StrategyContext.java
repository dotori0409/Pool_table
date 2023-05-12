package Pool_game;

public class StrategyContext {
    Strategy strategy;

    public StrategyContext(Strategy strategy){
        this.strategy = strategy;
    }

    public void inPocket(){
        strategy.inPocket();
    }
}
