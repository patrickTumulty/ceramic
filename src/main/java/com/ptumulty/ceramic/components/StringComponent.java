package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.StringModel;
import javafx.scene.control.TextField;

public class StringComponent extends UIComponent<StringModel, TextField>
{
    public StringComponent()
    {
        this(null);
    }

    public StringComponent(StringModel model)
    {
        super(model);
        renderer = new TextField("");
    }

    @Override
    protected void updateModel()
    {
        renderer.setOnAction(event ->
        {
            model.setValue(renderer.getText());
        });
    }

    @Override
    public void attachModel(StringModel model)
    {
        super.attachModel(model);
        renderer.setText(this.model.getValue());
    }

    @Override
    public void valueChanged()
    {
        if (!renderer.getText().equals(this.model.getValue()))
        {
            renderer.setText(this.model.getValue());
        }
    }
}
