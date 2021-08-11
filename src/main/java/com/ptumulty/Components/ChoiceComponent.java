package com.pt.mug.Components;

import com.pt.mug.Models.ChoiceModel;
import javafx.scene.control.ChoiceBox;

public class ChoiceComponent<T> extends UIComponent<ChoiceModel<T>, ChoiceBox<T>>
{
    public ChoiceComponent()
    {
        this(null);
    }
    public ChoiceComponent(ChoiceModel<T> model)
    {
        super(model);
        renderer = new ChoiceBox<>();
    }

    @Override
    public void attachModel(ChoiceModel<T> model)
    {
        super.attachModel(model);

        model.addListener(currentValue ->
        {
            renderer.getItems().clear();
            renderer.getItems().addAll(model.getChoiceItems());
            renderer.setValue(currentValue);
        });

        renderer.getItems().addAll(model.getChoiceItems());
        renderer.setValue(model.getValue());
        renderer.setOnAction(event -> updateModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel()
    {
        model.setValue(renderer.getValue());
    }

    @Override
    public void valueChanged()
    {
        renderer.setValue(model.getValue());
    }
}
