package Pool_game;

import javafx.geometry.Point2D;
import javafx.util.Pair;


import java.util.ArrayList;
import java.util.List;

public class BallPit {
    private final double height;
    private final double width;
    private final double friction;
    private final double side = 15;
    private final List<Ball> balls = new ArrayList<>();
    private final Pocket[] pockets = new Pocket[6];

    BallPit(double width, double height, double friction) {
        this.height = height;
        this.width = width;
        this.friction = friction;

        //Balls
        String configPath= "src/main/resources/config.json";
        for (String info: ConfigReader.ballInfo(configPath)){
			String color = info.split(",")[0];
            double posX = Double.parseDouble(info.split(",")[1]);
            double posY = Double.parseDouble(info.split(",")[2]);
            double velX = Double.parseDouble(info.split(",")[3]);
            double velY = Double.parseDouble(info.split(",")[4]);
            double mass = Double.parseDouble(info.split(",")[5]);
            
            if(color.equals("white"))
                balls.add(new CueBall(color,posX,posY,velX,velY,mass));
            else
                balls.add(new ColoredBall(color,posX,posY,velX,velY,mass));
        }
        //Pockets
        pockets[0] = new Pocket(0, 0);
        pockets[1] = new Pocket(width/2-side,0);
        pockets[2] = new Pocket(width-2*side,0);
        pockets[3] = new Pocket(0,height-2*side);
        pockets[4] = new Pocket(width/2-side, height-2*side);
        pockets[5] = new Pocket(width-2*side,height-2*side);
    }

    double getHeight() {
        return height;
    }

    double getWidth() {
        return width;
    }

    

    List<Ball> getBalls() {
        return balls;
    }

    Pocket[] getPockets() {
        return pockets;
    }

    
    
}
