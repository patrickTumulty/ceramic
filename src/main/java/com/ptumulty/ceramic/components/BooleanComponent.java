package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.BooleanModel;
import javafx.scene.control.CheckBox;

public class BooleanComponent extends UIComponent<BooleanModel, CheckBox>
{
    public BooleanComponent(BooleanModel model)
    {
        super(model);
        renderer = new CheckBox();
    }

    @Override
    protected void updateModel()
    {
        renderer.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            model.setValue(newValue);
        });
    }

    @Override
    public void valueChanged()
    {
        renderer.selectedProperty().setValue(model.getValue());
    }
}
