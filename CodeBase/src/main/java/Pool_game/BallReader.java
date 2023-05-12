package Pool_game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.paint.Paint;

public class BallReader implements ConfigReader{
	private List<Ball> balls = new ArrayList<>();

	public BallReader(){

	}

    public void read(String path){
        JSONParser parser = new JSONParser();
		BallBuilder builder = new BallBuilder();

				BallDirector director = new BallDirector();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the "Balls" section:
			JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

			// reading the "Balls: ball" array:
			JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");

			// reading from the array:
			for (Object obj : jsonBallsBall) {
				JSONObject jsonBall = (JSONObject) obj;
                Paint color = Paint.valueOf((String) jsonBall.get("colour"));
                double xPos = (Double) ((JSONObject) jsonBall.get("position")).get("x");
                double yPos = (Double) ((JSONObject) jsonBall.get("position")).get("y");
                double xVel = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
                double yVel = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");
                double mass = (Double) jsonBall.get("mass");
				
				director.build(builder, color, mass, xPos, yPos, xVel, yVel);
				balls.add(builder.getBuilt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

	public List<Ball> getBalls(){
		return balls;
	}
}
