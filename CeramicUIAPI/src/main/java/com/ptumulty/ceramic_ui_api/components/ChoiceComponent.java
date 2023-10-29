package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ChoiceModel;
import javafx.scene.control.ChoiceBox;

public class ChoiceComponent<T> extends UIComponent<ChoiceModel<T>, ChoiceBox<T>> implements ChoiceModel.ChoiceListener<T>
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
        if (this.model != null)
        {
            model.removeListener((ChoiceModel.ChoiceListener<T>) this);
        }

        super.attachModel(model);

        model.addListener((ChoiceModel.ChoiceListener<T>) this);
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
        model.setValue(object);
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

    @Override
    public void choiceModelChanged(T currentValue)
    {
        if (renderer != null)
        {
            renderer.setValue(currentValue);
        }
    }
}
