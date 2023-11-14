package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BooleanModel;
import org.controlsfx.control.ToggleSwitch;

public class ToggleSwitchComponent extends UIComponent<BooleanModel, ToggleSwitch>
{
    public ToggleSwitchComponent(BooleanModel model)
    {
        super(model);
    }

    @Override
    public void valueChanged()
    {
        renderer.selectedProperty().set(model.get());
    }

    @Override
    protected void updateModel()
    {
        model.setValue(renderer.selectedProperty().getValue());
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new ToggleSwitch();
        renderer.selectedProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }
}
