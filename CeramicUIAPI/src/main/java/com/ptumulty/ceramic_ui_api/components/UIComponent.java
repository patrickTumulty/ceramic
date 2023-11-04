package com.ptumulty.ceramic_ui_api.components;


import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_api.utils.Disposable;
import javafx.scene.Node;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class UIComponent<T extends ValueModel<?>, V extends Node> implements Disposable, ValueModel.ValueListener
{
    protected @Nullable String label;
    protected T model;
    protected V renderer;

    public UIComponent(T model)
    {
        label = null;
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
        return Optional.ofNullable(label);
    }

    /**
     * Set label
     * @param label component label
     */
    public void setLabel(@Nullable String label)
    {
        this.label = label;
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
        model.removeListener(this);
    }

    public V getRenderer()
    {
        return renderer;
    }

    @Override
    public void dispose()
    {
        detachModel();
    }
}
