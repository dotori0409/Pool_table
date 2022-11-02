package PoolGame.strategy;

import PoolGame.App;
import PoolGame.Config;
import PoolGame.GameManager;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*** Creates the button for the difficulty level*/
public class Level {
    GameManager gameManager;
    Stage primaryStage;
    App app;
    Button buttonEasy;
    Button buttonNormal;
    Button buttonHard;

    /**
     * Intializes variables
     */
    public Level(GameManager gameManager, Stage primaryStage){
        app = new App();
        this.gameManager = gameManager;
        this.primaryStage = primaryStage;
    }

    /**
     * Create buttons from easy, normal, hard
     */
    public void createLevelButtons(){
        buttonEasy = new Button("Easy");
        buttonEasy.setLayoutX(gameManager.getTable().getxLength() - Config.getTableBuffer() - Config.getTableBuffer()/4);
        buttonEasy.setLayoutY(0);
        // gameManager.getPane().getChildren().add(buttonEasy);

        buttonNormal = new Button("Normal");
        buttonNormal.setLayoutX(gameManager.getTable().getxLength() - Config.getTableBuffer()/4);
        buttonNormal.setLayoutY(0);
        // gameManager.getPane().getChildren().add(buttonNormal);

        buttonHard = new Button("Hard");
        buttonHard.setLayoutX(gameManager.getTable().getxLength() + Config.getTableBuffer());
        buttonHard.setLayoutY(0);
        // gameManager.getPane().getChildren().add(buttonHard);

        buttonAction();
    }

    /**
     * Set button actions
     */
    public void buttonAction(){
        buttonEasy.setOnAction(e ->{
            try {
                app.newGame(primaryStage,"src/main/resources/config_easy.json");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        buttonNormal.setOnAction(e ->{
            try {
                app.newGame(primaryStage,"src/main/resources/config_Normal.json");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        buttonHard.setOnAction(e ->{
            try {
                app.newGame(primaryStage,"src/main/resources/config_hard.json");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Returns easy button variable
     * @return buttonEasy
     */
    public Button getEasyButton(){
        return buttonEasy;
    }

    /**
     * Returns normal button variable
     * @return buttonNormal
     */
    public Button getNormalButton(){
        return buttonNormal;
    }

    /**
     * Returns hard button variable
     * @return buttonHard
     */
    public Button getHardButton(){
        return buttonHard;
    }
}
