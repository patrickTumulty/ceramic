package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BooleanModel;
import javafx.scene.control.CheckBox;
import org.jetbrains.annotations.Nullable;

public class BooleanComponent extends UIComponent<Boolean, BooleanModel, CheckBox>
{
    public BooleanComponent(BooleanModel model)
    {
        super(model);
    }

    public BooleanComponent(@Nullable String label, BooleanModel model)
    {
        super(label, model);
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue(renderer.selectedProperty().getValue());
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new CheckBox();
        renderer.selectedProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }

    @Override
    public void valueChanged(Boolean prev, Boolean curr)
    {
        if (model != null)
        {
            renderer.selectedProperty().setValue(model.get());
        }
    }
}
