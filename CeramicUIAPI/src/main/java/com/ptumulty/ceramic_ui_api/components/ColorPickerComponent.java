package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.impl.ColorStringModel;
import com.ptumulty.ceramic_ui_api.utility.ColorUtils;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;

public class ColorPickerComponent extends UIComponent<String, ColorStringModel, ColorPicker>
{
    public ColorPickerComponent(String label, @Nullable ColorStringModel model)
    {
        super(label, model);
    }

    public ColorPickerComponent(ColorStringModel model)
    {
        super(model);
    }

    @Override
    public void valueChanged(String previousValue, String newValue)
    {
        renderer.setValue(Color.valueOf(newValue));
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue(ColorUtils.colorToHex(renderer.getValue()));
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new ColorPicker();
        renderer.valueProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }
}
