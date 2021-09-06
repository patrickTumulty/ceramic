package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.ChoiceModel;
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
        renderer.setValue(model.get());
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
    protected void initializeRenderer()
    {
        renderer = new ChoiceBox<>();
    }

    @Override
    public void valueChanged()
    {
        renderer.setValue(model.get());
    }
}
