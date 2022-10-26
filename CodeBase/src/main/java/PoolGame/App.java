package PoolGame;

import PoolGame.config.*;

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

        // START GAME MANAGER
        gameManager.run();
        primaryStage.setTitle("Pool");
        primaryStage.setScene(gameManager.getScene());
        primaryStage.show();
        gameManager.run();

        Button buttonEasy = new Button("Easy");
        buttonEasy.setWrapText(true);
        buttonEasy.setLayoutX(gameManager.getTable().getxLength() - Config.getTableBuffer() - Config.getTableBuffer()/4);
        buttonEasy.setLayoutY(0);
        gameManager.getPane().getChildren().add(buttonEasy);
        buttonEasy.setOnAction(e ->{
            try {
                newGame(primaryStage,"src/main/resources/config_easy.json");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        Button buttonNormal = new Button("Normal");
        buttonNormal.setWrapText(true);
        buttonNormal.setLayoutX(gameManager.getTable().getxLength() - Config.getTableBuffer()/4);
        buttonNormal.setLayoutY(0);
        gameManager.getPane().getChildren().add(buttonNormal);
        buttonNormal.setOnAction(e ->{
            try {
                newGame(primaryStage,"src/main/resources/config_Normal.json");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        Button buttonHard = new Button("Hard");
        buttonHard.setWrapText(true);
        buttonHard.setLayoutX(gameManager.getTable().getxLength() + Config.getTableBuffer());
        buttonHard.setLayoutY(0);
        gameManager.getPane().getChildren().add(buttonHard);
        buttonHard.setOnAction(e ->{
            try {
                newGame(primaryStage,"src/main/resources/config_hard.json");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
}
