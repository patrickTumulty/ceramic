package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.IntegerModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.jetbrains.annotations.Nullable;

public class IntegerSpinnerComponent extends UIComponent<IntegerModel, Spinner<Integer>>
{
    /**
     * Constructor
     *
     * @param model integer spinner model
     */
    public IntegerSpinnerComponent(IntegerModel model)
    {
        super(model);
    }

    /**
     * Constructor
     *
     * @param label component label
     * @param model integer spinner model
     */
    public IntegerSpinnerComponent(@Nullable String label, IntegerModel model)
    {
        super(label, model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue(renderer.getValue());
        }
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
        if (model != null)
        {
            renderer.getEditor().setText(model.get().toString());
        }
    }
}
