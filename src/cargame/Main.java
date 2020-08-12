package cargame;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import static cargame.EntityType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Orientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

/**
 * the main class for the game. Handles game construction and initialization.
 */
public class Main extends GameApplication {

    private Entity car;

    /**
     * a method to initialize game settings, including window size and main menu.
     * @param settings
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Trans-Canada Caper: Driving NL");
        settings.setVersion("0.1.1");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory(){

            @Override
            public FXGLMenu newMainMenu(){

                return new MainMenu();
            }
        });
    }

    /**
     * a method to bind keyboard input to in-game actions
     */
    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Forward") {
            @Override
            protected void onAction() {
                car.translateY(-8);
                inc("score", +1);
            }
        }, KeyCode.W);
        onKey(KeyCode.S, () -> car.translateY(8));
        onKey(KeyCode.A, () -> car.translateX(-8));
        onKey(KeyCode.D, () -> car.translateX(8));
        onBtnDown(MouseButton.PRIMARY, () -> spawn("moose", car.getRightX(), car.getBottomY() - 500));
    }

    /**
     * a method to initialize the score and health values.
     * @param vars
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("health", 3); //adding health to car
    }

    /**
     * a method to initialize the main game screen, including background, player, and obstacles.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DrivingFactory());
        initScreenBounds();
        Texture t = getAssetLoader().loadTexture("background.png");
        entityBuilder()
                .view(new ScrollingBackgroundView(t.superTexture(t, VerticalDirection.DOWN),
                        Orientation.VERTICAL))
                .buildAndAttach();
        initCar();

        getGameScene().getViewport().setBounds(0, -Integer.MAX_VALUE, getAppWidth(), Integer.MAX_VALUE);
        getGameScene().getViewport().bindToEntity(car, getAppWidth() / 3, getAppHeight() / 2);
        getGameTimer().runAtInterval(() -> spawn("moose", car.getRightX(), car.getBottomY() - 480) , Duration.seconds(3));

        //copying moose spawn code for other entities. durations and X/Y may need to be tweaked
        getGameTimer().runAtInterval(() -> spawn("pothole", car.getRightX(), car.getBottomY() - 480) , Duration.seconds(8));
        getGameTimer().runAtInterval(() -> spawn("pylon", car.getRightX(), car.getBottomY() - 480) , Duration.seconds(6));
        getGameTimer().runAtInterval(() -> spawn("wrench", car.getRightX(), car.getBottomY() - 480) , Duration.seconds(40));
        getGameTimer().runAtInterval(() -> spawn("pointsorb", car.getRightX(), car.getBottomY() - 480) , Duration.seconds(10));
    }

    /**
     * a method to initialize the player's car at certain X and Y values.
     */
    private void initCar() {
        car = spawn("car", 300, getAppHeight() - 300);
    }

    /**
     * a method to initialize the the outer bounds of the play area during the game
     */
    private void initScreenBounds() {
        entityBuilder().buildScreenBoundsAndAttach(100);
    }

    /**
     * a method to handle in game collisions between the player's car and other objects
     */
    @Override
    protected void initPhysics() {
        //car collides with moose. call Game over immediately
        onCollisionBegin(CAR, MOOSE, (car, moose) -> {
            moose.removeFromWorld();
            gameOverScreen();
        });

        //car collides with pothole. remove 1 health
        onCollisionBegin(CAR, POTHOLE, (car, pothole) -> {
            pothole.removeFromWorld();
            updateHealth(-1);
        });

        //car collides with pylon. remove 1 health
        onCollisionBegin(CAR, PYLON, (car, pylon) -> {
            pylon.removeFromWorld();
            updateHealth(-1);
        });

        //car collides with wrench. add 1 health
        onCollisionBegin(CAR, WRENCH, (car, wrench) -> {
            wrench.removeFromWorld();
            updateHealth(1);
        });

        //car collides with pointsorb. add 1000 points
        onCollisionBegin(CAR, POINTSORB, (car, pointsorb) -> {
            pointsorb.removeFromWorld();
            updateScore(1000);
        });
    }

    /**
     * a method to update the health value for the player
     * @param newValue
     */
    //function to update health value
    protected void updateHealth(int newValue){
        if(newValue==1){
            //cap health at 3, give player points if they collect wrench at full health
            if(vars.getValue("health")==3){
                inc("score", +10000)
            }else{
                inc("health", +1)
            }
        }else if(newValue==-1){
            inc("health", -1)
        }

        //if health is less than one call gameOverScreen
        if(vars.getValue("health") < 1){
            gameOverScreen();
        }
    }

    /**
     * a method to initialize the score and health UI for in-game display to the player.
     */
    @Override
    protected void initUI() {
        Text scoreText = getUIFactoryService().newText("", Color.BLACK, 24);
        scoreText.setTranslateX(600);
        scoreText.setTranslateY(100);
        scoreText.textProperty().bind(getip("score").asString("Score: [%d]"));
        addUINode(scoreText);

        //copying code for health. TODO: adjust x and y coordinates
        Text healthText = getUIFactoryService().newText("", Color.BLACK, 24);
        healthText.setTranslateX(600);
        healthText.setTranslateY(80);
        healthText.textProperty().bind(getip("health").asString("Health: [%d]"));
        addUINode(healthText);
    }

    /**
     * a method that handles a call to the game over screen and high score prompt
     * if the player's score should be entered into the high score list.
     */
    //TODO: ADD check for leaderboard in this method?
    private void gameOverScreen(){
        showMessage("You Died!");
        getDialogService().showInputBox("High Score, Enter your name.", (name) -> { ShowRetryPromptUI(); });
        set("score", 0);
    }

    /**
     * a method to allow the player to play again or quit the game
     */
    private void ShowRetryPromptUI(){

        getDialogService().showConfirmationBox("Would you like to play again?", ( playAgain ) -> {
            if( playAgain) {
                FXGL.getGameController().startNewGame();
            } else {
                FXGL.getGameController().exit();
            }
        });
    }

    /**
     * main method call to launch the game.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
