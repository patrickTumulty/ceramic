package com.ptumulty.ceramic_ui_api;


import com.ptumulty.ceramic_api.ValueModel;
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
