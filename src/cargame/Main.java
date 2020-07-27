package cargame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import cargame.EntityType;
import static cargame.EntityType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
        onKey(KeyCode.W, () -> car.translateY(-10));
        onKey(KeyCode.S, () -> car.translateY(10));
        onKey(KeyCode.A, () -> car.translateX(-10));
        onKey(KeyCode.D, () -> car.translateX(10));
    }


    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DrivingFactory());
        initScreenBounds();
        spawn("background");
        initCar();
    }

    private void initCar() {
        car = spawn("car", 300, getAppHeight() - 300);
        //car.xProperty().bind(getInput().mouseXWorldProperty());
        //car.yProperty().bind(getInput().mouseYWorldProperty());
    }

    private void initScreenBounds() {
        entityBuilder().buildScreenBoundsAndAttach(100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
