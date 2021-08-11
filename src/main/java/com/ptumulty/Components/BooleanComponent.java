package com.pt.mug.Components;

import com.pt.mug.Models.BooleanModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void attachModel(BooleanModel model)
    {
        super.attachModel(model);
        renderer.setSelected(this.model.getValue());
    }

    @Override
    public void valueChanged()
    {
        renderer.selectedProperty().setValue(model.getValue());
    }
}
