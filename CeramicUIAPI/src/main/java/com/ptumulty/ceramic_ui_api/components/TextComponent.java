package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.StringModel;
import javafx.scene.control.TextArea;

public class TextComponent extends UIComponent<String, StringModel, TextArea>
{
    public TextComponent()
    {
        this(null);
    }

    public TextComponent(StringModel model)
    {
        super(model);
    }

    @Override
    protected void updateModel()
    {
        model.setValue(renderer.getText());
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new TextArea();
        renderer.setOnKeyTyped(event -> updateModel());
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
        if (this.model == null)
        {
            return;
        }

        if (!renderer.getText().equals(this.model.get()))
        {
            renderer.setText(this.model.get());
        }
    }
}
