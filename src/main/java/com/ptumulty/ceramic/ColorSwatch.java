package com.ptumulty.ceramic;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class ColorSwatch extends HBox
{
    private final String title;
    private final Paint color;

    public ColorSwatch(String title, String hex)
    {
        this(title, Color.web(hex));
    }

    public ColorSwatch(String title, Paint color)
    {
        this.title = title;
        this.color = color;

        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10);

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(color);

        Label label = new Label(title);

        getChildren().add(rectangle);
        getChildren().add(label);
    }

    public String getTitle()
    {
        return title;
    }

    public Paint getColor()
    {
        return color;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
