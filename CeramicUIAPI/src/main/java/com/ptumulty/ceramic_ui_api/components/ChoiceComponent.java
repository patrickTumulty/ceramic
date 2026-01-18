package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.ChoiceModel;
import com.ptumulty.ceramic_api.ValueModel.ChoiceModel.ChoiceListener;
import com.ptumulty.ceramic_ui_api.utility.FxUtils;
import javafx.scene.control.ChoiceBox;

public class ChoiceComponent<T> extends UIComponent<T, ChoiceModel<T>, ChoiceBox<T>> implements ChoiceListener<T>
{
    public ChoiceComponent(String label)
    {
        this(label, null);
    }

    public ChoiceComponent(String label, ChoiceModel<T> model)
    {
        super(label, model);
    }

    @Override
    public void attachModel(ChoiceModel<T> model)
    {
        super.attachModel(model);

        if (model != null)
        {
            model.addChoiceListener(this);
            if (model.getStringConverter().isPresent())
            {
                renderer.setConverter(model.getStringConverter().get());
            }
            renderer.getItems().addAll(model.getChoiceItems());
            renderer.setValue(model.get());
        }
    }

    @Override
    public void detachModel()
    {
        super.detachModel();

        renderer.getItems().clear();
        renderer.setConverter(null);
        if (model != null)
        {
            model.removeChoiceListener(this);
        }
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
        renderer.setOnAction(event -> updateModel());
    }

    @Override
    public void valueChanged(T prev, T curr)
    {
        if (renderer != null && model != null)
        {
            FxUtils.run(() -> renderer.setValue(curr));
        }
    }

    @Override
    public void choiceAdded(T item)
    {
        FxUtils.run(() -> renderer.getItems().add(item));
    }

    @Override
    public void choiceRemoved(T item)
    {
        FxUtils.run(() -> renderer.getItems().remove(item));
    }
}
