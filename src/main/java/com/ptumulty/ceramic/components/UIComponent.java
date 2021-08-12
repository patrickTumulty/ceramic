package com.ptumulty.ceramic.components;

import com.pt.mug.Utility.Disposable;
import com.ptumulty.ceramic.models.ValueModel;
import javafx.scene.control.Control;

public abstract class UIComponent<T extends ValueModel, V extends Control> implements Disposable, ValueModel.ValueListener
{
    protected T model;
    protected V renderer;

    UIComponent(T model)
    {
        if (model != null)
        {
            attachModel(model);
        }
    }

    /**
     * This method takes a change in the UIComponent to update the value model
     */
    protected abstract void updateModel();

    public void attachModel(T model)
    {
        if (this.model != model)
        {
            this.model = model;
            this.model.addListener(this);
        }
        updateModel();
    }

    public void detachModel()
    {
        this.model.removeListener(this);
    }

    public V getRenderer()
    {
        return renderer;
    }

    @Override
    public void dispose()
    {
        model.removeListener(this);
    }
}
