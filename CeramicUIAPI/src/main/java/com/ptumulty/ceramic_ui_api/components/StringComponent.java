package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.StringModel;
import javafx.scene.control.TextField;

public class StringComponent extends UIComponent<String, StringModel, TextField>
{
    public StringComponent()
    {
        this(null);
    }

    public StringComponent(StringModel model)
    {
        super(model);
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue(renderer.getText());
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new TextField();
        renderer.setOnAction(event -> updateModel());
    }

    @Override
    public void attachModel(StringModel model)
    {
        super.attachModel(model);
        if (this.model != null)
        {
            renderer.setText(this.model.get());
        }
    }

    @Override
    public void valueChanged(String prev, String curr)
    {
        if (model == null)
        {
            return;
        }
        if (!renderer.getText().equals(this.model.get()))
        {
            renderer.setText(this.model.get());
        }
    }
}
