package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.RunnableModel;
import javafx.scene.control.Button;

public class ButtonComponent extends UIComponent<Runnable, RunnableModel, Button>
{
    public ButtonComponent(String name, RunnableModel runnable)
    {
        super(name, runnable);

        renderer.setText(name);
        renderer.setOnAction(event -> runnable.get().run());
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

    @Override
    public void valueChanged(Runnable previousValue, Runnable newValue)
    {
        renderer.setOnAction(event -> newValue.run());
    }
}
