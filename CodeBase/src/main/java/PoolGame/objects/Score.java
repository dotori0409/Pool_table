package PoolGame.objects;

public class Score {
    int score;

    public Score(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int num){
        this.score += num;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void reset(){
        this.score = 0;
    }
}
