package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.IntegerModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SpinnerComponent extends UIComponent<IntegerModel, Spinner<Integer>>
{
    public SpinnerComponent(IntegerModel integerModel)
    {
        super(integerModel);
    }

    @Override
    protected void updateModel()
    {
        model.setValue(renderer.getValue());
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new Spinner<>();
        renderer.setEditable(true);
    }

    @Override
    public void valueChanged()
    {
        renderer.getEditor().setText(model.get().toString());
    }
}
