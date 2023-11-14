package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BooleanModel;
import javafx.scene.control.CheckBox;

public class BooleanComponent extends UIComponent<BooleanModel, CheckBox>
{
    public BooleanComponent(BooleanModel model)
    {
        super(model);
    }

    @Override
    protected void updateModel()
    {
        model.setValue(renderer.selectedProperty().getValue());
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new CheckBox();
        renderer.selectedProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }

    @Override
    public void valueChanged()
    {
        renderer.selectedProperty().setValue(model.get());
    }
}
