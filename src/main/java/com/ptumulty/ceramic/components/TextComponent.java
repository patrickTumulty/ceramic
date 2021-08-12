package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.StringModel;
import javafx.scene.control.TextArea;

public class TextComponent extends UIComponent<StringModel, TextArea>
{
    public TextComponent()
    {
        this(null);
    }

    public TextComponent(StringModel model)
    {
        super(model);
        renderer = new TextArea();
    }

    @Override
    protected void updateModel()
    {
        renderer.setOnKeyTyped(event ->
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
