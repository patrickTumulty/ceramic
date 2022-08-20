package com.ptumulty.ceramic.utility;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

public class ButtonUtils
{
    public static Button createCircleIconButton(FontIcon icon, double radius)
    {
        Button button = new Button();
        button.setGraphic(icon);
        button.prefWidthProperty().bind(button.heightProperty());
        button.setMinWidth(radius);
        button.setMinHeight(radius);
        button.setShape(new Circle(radius));
        return button;
    }
}
