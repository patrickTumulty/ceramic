package com.ptumulty.ceramic.models;

import javafx.scene.paint.Color;

public class ColorModel extends ValueModel<Color>
{
    public ColorModel()
    {
        this(Color.TRANSPARENT);
    }

    ColorModel(Color value)
    {
        super(value);
    }
}
