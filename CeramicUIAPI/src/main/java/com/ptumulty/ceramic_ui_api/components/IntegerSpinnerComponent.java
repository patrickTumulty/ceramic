package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.jetbrains.annotations.Nullable;

public class IntegerSpinnerComponent extends UIComponent<Integer, ValueModel<Integer>, Spinner<Integer>>
{
    /**
     * Constructor
     *
     * @param label component label
     * @param model integer spinner model
     */
    public IntegerSpinnerComponent(@Nullable String label, ValueModel<Integer> model)
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
    public void valueChanged(Integer prev, Integer curr)
    {
        if (model != null)
        {
            renderer.getEditor().setText(model.get().toString());
        }
    }
}
