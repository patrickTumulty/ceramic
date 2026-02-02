package com.ptumulty.ceramic_ui_api.components;


import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_api.utils.Disposable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import org.jetbrains.annotations.Nullable;

public abstract class UIComponent<K, T extends ValueModel<K>, V extends Node> implements Disposable,
                                                                                         ValueModel.ValueListener<K>
{
    protected String label;
    protected @Nullable T model;
    protected BooleanProperty modifiedProperty;
    protected ValueModel.ValueListener<K> modifierListener;
    protected V renderer;

    public UIComponent(T model)
    {
        this(null, model);
    }

    public UIComponent(String label, @Nullable T model)
    {
        this.label = label;
        modifiedProperty = new SimpleBooleanProperty(false);
        initializeRenderer();
        if (model != null)
        {
            attachModel(model);
        }
    }

    /**
     * @return component label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Set label
     *
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
        if (model == null)
        {
            return;
        }

        if (this.model != null)
        {
            detachModel();
        }

        if (this.model != model)
        {
            this.model = model;
            this.model.addListener(this);
            modifierListener = (oldValue, newValue) -> modifiedProperty.set(this.model.isModified());
            this.model.addListener(modifierListener);
            modifiedProperty.set(this.model.isModified());
        }

        if (renderer != null)
        {
            valueChanged(model.get(), model.get());
        }
    }

    public BooleanProperty modifiedProperty()
    {
        return modifiedProperty;
    }

    public void restoreDefault()
    {
        if (model != null)
        {
            model.restoreDefault();
        }
    }

    public void detachModel()
    {
        if (model != null)
        {
            model.addListener(modifierListener);
            model.removeListener(this);
        }
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
