package cargame;

import javafx.scene.control.Button;

public class MenuButton extends Button {

    String regularButtonStyle = "-fx-background-color:#CC6600; -fx-font-size: 22";
    String hoverButtonStyle = "-fx-background-color:#FF9933; -fx-font-size: 24; -fx-text-fill:#808080";

    public MenuButton( String text){

        super(text);
        setPrefSize(150.0,60.0);
        setStyle(regularButtonStyle);
        setOnMouseEntered( (event) -> { setStyle(hoverButtonStyle);});
        setOnMouseExited( (event) -> { setStyle(regularButtonStyle);});
    }
}
