import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Untitled Car Game");
        settings.setVersion("0.0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
