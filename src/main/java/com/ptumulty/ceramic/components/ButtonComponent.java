package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.ValueModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonComponent extends UIComponent<ValueModel<?>, Button>
{
    public ButtonComponent(String name, EventHandler<ActionEvent> event)
    {
        super(null);

        renderer.setText(name);
        renderer.setOnAction(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel()
    {
        // Do Nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRenderer()
    {
        renderer = new Button();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void valueChanged()
    {
        // Do Nothing
    }
}
