package com.ptumulty.ceramic.components;


import com.ptumulty.ceramic.models.BooleanModel;
import com.ptumulty.ceramic.models.ValueModel;
import com.ptumulty.ceramic.utility.Disposable;
import javafx.scene.Node;
import javafx.scene.control.Control;

public abstract class UIComponent<T extends ValueModel<?>, V extends Node> implements Disposable, ValueModel.ValueListener
{
    protected T model;
    protected V renderer;

    public UIComponent(T model)
    {
        initializeRenderer();
        if (model != null)
        {
            attachModel(model);
        }
    }

    /**
     * This method is meant to apply the UI components state to a value
     */
    protected abstract void updateModel();

    /**
     * This method is where the renderer should get initialized
     */
    protected abstract void initializeRenderer();

    public void attachModel(T model)
    {
        if (this.model != model)
        {
            this.model = model;
            this.model.addListener(this);
        }
        if (renderer != null)
        {
            valueChanged();
        }
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
