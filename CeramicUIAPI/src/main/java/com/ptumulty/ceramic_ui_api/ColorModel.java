package com.ptumulty.ceramic_ui_api;


import com.ptumulty.ceramic_api.DefaultValueModel;
import javafx.scene.paint.Color;

public class ColorModel extends DefaultValueModel<Color>
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
