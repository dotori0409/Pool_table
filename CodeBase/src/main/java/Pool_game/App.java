package Pool_game;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static
    GameWindow window;
     void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableReader reader = new TableReader();
        reader.read("src/main/resources/config.json");
        PoolTable table = reader.getTable();
        window = new GameWindow(new BallPit(table));
        window.run();
        
        primaryStage.setTitle("Pool Game");
        primaryStage.setScene(window.getScene());
        window.getScene().setFill(table.getColor());
        primaryStage.show();
    }
}

