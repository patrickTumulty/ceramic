package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.IntegerModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class IntegerSpinnerComponent extends UIComponent<IntegerModel, Spinner<Integer>>
{
    /**
     * Constructor
     *
     * @param integerModel integer spinner model
     */
    public IntegerSpinnerComponent(IntegerModel integerModel)
    {
        super(integerModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel()
    {
        model.setValue(renderer.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRenderer()
    {
        renderer = new Spinner<>();
        renderer.setEditable(true);
        renderer.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
        renderer.valueProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void valueChanged()
    {
        renderer.getEditor().setText(model.get().toString());
    }
}
