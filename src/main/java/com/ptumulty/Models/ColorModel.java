package com.pt.mug.Models;

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
