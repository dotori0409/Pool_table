package PoolGame.config;

import PoolGame.objects.*;
import PoolGame.GameManager;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** Reads in ball section of JSON. */
public class PocketReader implements Reader {

	/**
	 * Parses the JSON file and builds the balls.
	 * 
	 * @param path        The path to the JSON file.
	 * @param gameManager The game manager.
	 */
	public void parse(String path, GameManager gameManager) {
		JSONParser parser = new JSONParser();
		ArrayList<Pocket> pockets = new ArrayList<Pocket>();

		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the "Balls" section:
			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

			// reading the "Balls: ball" array:
			JSONArray jsonTablepockets = (JSONArray) jsonTable.get("pockets");

			// reading from the array:
			for (Object obj : jsonTablepockets) {
				JSONObject jsonPocket = (JSONObject) obj;

				// the ball position, velocity, mass are all doubles
				Double positionX = (Double) ((JSONObject) jsonPocket.get("position")).get("x");
				Double positionY = (Double) ((JSONObject) jsonPocket.get("position")).get("y");

				Double radius = (Double) jsonPocket.get("radius");

				// Builder code
				// PoolPocketBuilder builder = new PoolPocketBuilder();
				// builder.setxPos(positionX);
				// builder.setyPos(positionY);
				// builder.setRadius(radius);

				// pockets.add(builder.build());
				pockets.add(new Pocket(10, 10,10));
				pockets.add(new Pocket(600 - 10, 10,10));
				pockets.add(new Pocket(600 / 2, 10,10));
				pockets.add(new Pocket(10, 400 - 10,10));
				pockets.add(new Pocket(600 - 10, 400 - 10,10));
				pockets.add(new Pocket(600 / 2, 400 - 10,10));
			}

			gameManager.setPockets(pockets);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}