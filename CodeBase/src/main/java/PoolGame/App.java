package PoolGame;

import PoolGame.config.*;
import PoolGame.strategy.Level;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/** Main application entry point. */
public class App extends Application {

    /**
     * @param args First argument is the path to the config file
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * Starts the application.
     * 
     * @param primaryStage The primary stage for the application.
     */
    public void start(Stage primaryStage) throws InterruptedException {
        //Set easy mode as default
        newGame(primaryStage,"src/main/resources/config_easy.json");
    }

    /**
     * Restarts a new game with a specified game mode according to the config file
     * 
     * @param primaryStage The primary stage for the application.
     * @param config The file name to read
     */
    public void newGame(Stage primaryStage, String config) throws InterruptedException{

        GameManager gameManager = new GameManager();
        String configPath = config;

        ReaderFactory tableFactory = new TableReaderFactory();
        Reader tableReader = tableFactory.buildReader();
        tableReader.parse(configPath, gameManager);

        ReaderFactory ballFactory = new BallReaderFactory();
        Reader ballReader = ballFactory.buildReader();
        ballReader.parse(configPath, gameManager);

        ReaderFactory pockeFactory = new PocketReaderFactory();
        Reader pocketReader = pockeFactory.buildReader();
        pocketReader.parse(configPath, gameManager);

        gameManager.buildManager();

        //Difficulty level buttons
        Level lvl = new Level(gameManager, primaryStage);
        lvl.createLevelButtons();
        Button buttonEasy = lvl.getEasyButton();
        Button buttonNormal = lvl.getNormalButton();
        Button buttonHard = lvl.getHardButton();
        gameManager.getPane().getChildren().add(buttonEasy);
        gameManager.getPane().getChildren().add(buttonNormal);
        gameManager.getPane().getChildren().add(buttonHard);

        // START GAME MANAGER
        gameManager.run();
        primaryStage.setTitle("Pool");
        primaryStage.setScene(gameManager.getScene());
        primaryStage.show();
        gameManager.run();
    }
}
