package Pool_game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.paint.Paint;

public class TableReader {
	PoolTable pTable;

    public void read(String path){
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Table section:
			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

			Paint color = Paint.valueOf((String) jsonTable.get("colour"));

			// This is a double which should affect the rate at which the balls slow down
			double friction = (Double) jsonTable.get("friction");

			long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
			long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

			Factory table = new PoolTable(color, friction, tableX, tableY);
			pTable = (PoolTable) table.create();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public PoolTable getTable(){
		return pTable;
	}
}
