package cargame;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends FXGLMenu {

    private Node mainScreen;
    private Node leaderboardScreen;
    private StackPane customizeScreen;
    private static String selectedCarAsset = "car.png";


    public MainMenu(){

        super(MenuType.MAIN_MENU);
        mainScreen = CreateMainScreen();
        customizeScreen = CreateCustomizeScreen();

        ShowMainMenu();

    }

    private void ShowMainMenu(){

        getContentRoot().getChildren().remove(0);
        ImageView bg = new ImageView("assets/textures/main.png");
        bg.setFitHeight(600.0);
        bg.setFitWidth(800.0);
        getContentRoot().getChildren().add(0,bg);
        getMenuContentRoot().getChildren().clear();
        getMenuContentRoot().getChildren().addAll(mainScreen);
    }

    private void ShowCustomizeScreen(){

        getContentRoot().getChildren().remove(0);
        ImageView bg = new ImageView("assets/textures/customizebackground.jpg");
        bg.setFitHeight(600.0);
        bg.setFitWidth(800.0);
        getContentRoot().getChildren().add(0,bg);
        getMenuContentRoot().getChildren().clear();
        getMenuContentRoot().getChildren().addAll(customizeScreen);

    }

    private void ShowLeaderboardScreen(){

        getContentRoot().getChildren().remove(0);
        ImageView bg = new ImageView("assets/textures/customizebackground.jpg");
        bg.setFitHeight(600.0);
        bg.setFitWidth(800.0);
        getContentRoot().getChildren().add(0,bg);
        getMenuContentRoot().getChildren().clear();
        getMenuContentRoot().getChildren().addAll(leaderboardScreen);

    }

    private Node CreateMainScreen(){

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(800.0,600.0);
        box.setSpacing(20.0);

        MenuButton newGameButton = new MenuButton("New Game");
        box.getChildren().addAll(newGameButton);
        newGameButton.setOnAction((event) -> { fireNewGame();});

        MenuButton customizeButton = new MenuButton("Customize");
        box.getChildren().addAll(customizeButton);
        customizeButton.setOnAction((event) -> { ShowCustomizeScreen();});

        MenuButton exitGameButton = new MenuButton("Exit Game");
        box.getChildren().addAll(exitGameButton);
        exitGameButton.setOnAction( (event) -> FXGL.getGameController().exit());

        return box;
    }

    private StackPane CreateCustomizeScreen(){

        StackPane pane = new StackPane();
        pane.setPrefSize(800.0,600.0);
        pane.setAlignment(Pos.CENTER_RIGHT);

        VBox box = new VBox();
        box.setPrefSize(800.0,600.0);
        box.setSpacing(20.0);
        box.setAlignment(Pos.CENTER_LEFT);

        MenuButton carOneButton = new MenuButton("Car 1");
        carOneButton.setOnAction( (event) -> { SelectCarOne();});
        box.getChildren().add(carOneButton);

        MenuButton carTwoButton = new MenuButton("Car 2");
        carTwoButton.setOnAction( (event) -> { SelectCarTwo();});
        box.getChildren().add(carTwoButton);

        MenuButton carThreeButton = new MenuButton("Car 3");
        carThreeButton.setOnAction( (event) -> { SelectCarThree();});
        box.getChildren().add(carThreeButton);

        MenuButton menuButton = new MenuButton("Main Menu");
        menuButton.setOnAction( (event) -> { ShowMainMenu();});
        box.getChildren().add(menuButton);

        pane.getChildren().add(box);

        ImageView carView = new ImageView("assets/textures/car.png");
        carView.setFitHeight(200);
        carView.setFitWidth(150);
        pane.getChildren().add(carView);

        return pane;

    }

    private void SelectCarOne(){

        customizeScreen.getChildren().remove(1);
        ImageView carView = new ImageView("assets/textures/car.png");
        carView.setFitHeight(200);
        carView.setFitWidth(150);
        customizeScreen.getChildren().add(carView);
        selectedCarAsset = "car.png";
    }

    private void SelectCarTwo(){

        customizeScreen.getChildren().remove(1);
        ImageView carView = new ImageView("assets/textures/car1.png");
        carView.setFitHeight(200);
        carView.setFitWidth(150);
        customizeScreen.getChildren().add(carView);
        selectedCarAsset = "car1.png";
    }

    private void SelectCarThree(){

        customizeScreen.getChildren().remove(1);
        ImageView carView = new ImageView("assets/textures/car3.png");
        carView.setFitHeight(200);
        carView.setFitWidth(150);
        customizeScreen.getChildren().add(carView);
        selectedCarAsset = "car3.png";
    }

    /**
     * a setter method to select which car asset should be used in game
     * @param carAsset, a string value to represent an asset file path
     */
    public void setSelectedCarAsset(String carAsset){
        selectedCarAsset = carAsset;
    }

    /**
     * a getter method to select which car asset should be used in game
     * @return selectedCarAsset, a string value to represent an asset file path
     */
    public static String getSelectedCarAsset(){
        return selectedCarAsset;
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull String s, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createBackground(double v, double v1) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createProfileView(@NotNull String s) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createTitleView(@NotNull String s) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createVersionView(@NotNull String s) {
        return new Button();
    }
}