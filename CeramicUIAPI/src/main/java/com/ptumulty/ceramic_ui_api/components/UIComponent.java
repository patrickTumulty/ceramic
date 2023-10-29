package com.ptumulty.ceramic_ui_api.components;


import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_api.utils.Disposable;
import javafx.scene.Node;

import java.util.Optional;

public abstract class UIComponent<T extends ValueModel<?>, V extends Node> implements Disposable, ValueModel.ValueListener
{
    protected Optional<String> label;
    protected T model;
    protected V renderer;

    public UIComponent(T model)
    {
        label = Optional.empty();
        initializeRenderer();
        if (model != null)
        {
            attachModel(model);
        }
    }

    /**
     * @return component label
     */
    public Optional<String> getLabel()
    {
        return label;
    }

    /**
     * Set label
     * @param label component label
     */
    public void setLabel(String label)
    {
        this.label = Optional.of(label);
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
