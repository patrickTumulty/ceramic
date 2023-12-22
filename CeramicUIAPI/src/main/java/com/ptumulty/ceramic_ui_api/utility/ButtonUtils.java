package com.ptumulty.ceramic_ui_api.utility;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

public class ButtonUtils
{
    public static Button createCircleIconButton(FontIcon icon, double radius)
    {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent");
        button.setOnMouseEntered((event) -> button.opacityProperty().set(0.7));
        button.setOnMouseExited((event) -> button.opacityProperty().set(1.0));
        button.setGraphic(icon);
        button.prefWidthProperty().bind(button.heightProperty());
        button.setMinWidth(Region.USE_PREF_SIZE);
        button.setMaxWidth(Region.USE_PREF_SIZE);
        button.setMinHeight(Region.USE_PREF_SIZE);
        button.setMaxHeight(Region.USE_PREF_SIZE);
        button.setShape(new Circle(radius));
        return button;
    }
}
