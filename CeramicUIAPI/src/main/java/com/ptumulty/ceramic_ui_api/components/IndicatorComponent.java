package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BooleanModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IndicatorComponent extends UIComponent<Boolean, BooleanModel, Circle>
{
    private static final String STYLE_CLASS_ACTIVE = "indicator-active";
    private static final String STYLE_CLASS_INACTIVE = "indicator-inactive";

    public IndicatorComponent(BooleanModel model)
    {
        super(model);
    }

    @Override
    public void valueChanged(Boolean prev, Boolean curr)
    {
        if (model != null)
        {
            renderer.getStyleClass().remove(STYLE_CLASS_ACTIVE);
            renderer.getStyleClass().remove(STYLE_CLASS_INACTIVE);
            renderer.getStyleClass().add(model.get() ? STYLE_CLASS_ACTIVE : STYLE_CLASS_INACTIVE);
        }
    }

    @Override
    protected void updateModel()
    {
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new Circle();
        renderer.getStyleClass().add(STYLE_CLASS_ACTIVE);
        renderer.setRadius(5);
        renderer.setFill(Color.MAGENTA);
    }
}
