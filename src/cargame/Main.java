package cargame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import cargame.EntityType;
import static cargame.EntityType.*;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Orientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

public class Main extends GameApplication {

    private Entity car;


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Untitled Car Game");
        settings.setVersion("0.0.1");
    }

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

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

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
        getGameTimer().runAtInterval(() -> spawn("moose", car.getRightX(), car.getBottomY() - 500) , Duration.seconds(3));

    }

    private void initCar() {
        car = spawn("car", 300, getAppHeight() - 300);
    }

    private void initScreenBounds() {
        entityBuilder().buildScreenBoundsAndAttach(100);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(CAR, MOOSE, (car, moose) -> {
            moose.removeFromWorld();
            showMessage("You Died!");
        });
    }

    @Override
    protected void initUI() {
        Text scoreText = getUIFactoryService().newText("", Color.BLACK, 24);
        scoreText.setTranslateX(600);
        scoreText.setTranslateY(100);
        scoreText.textProperty().bind(getip("score").asString("Score: [%d]"));
        addUINode(scoreText);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
