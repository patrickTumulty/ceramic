package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.ChoiceModel;
import javafx.scene.control.ChoiceBox;

public class ChoiceComponent<T> extends UIComponent<T, ChoiceModel<T>, ChoiceBox<T>>
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

        renderer.getItems().clear();
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
        T object = renderer.getValue();
        if (model != null)
        {
            model.setValue(object);
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new ChoiceBox<>();
    }

    @Override
    public void valueChanged(T prev, T curr)
    {
        if (renderer != null && model != null)
        {
            renderer.setValue(model.get());
        }
    }
}
