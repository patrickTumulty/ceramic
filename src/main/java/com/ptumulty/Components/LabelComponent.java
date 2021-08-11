package com.pt.mug.Components;

import com.pt.mug.Models.StringModel;
import javafx.scene.control.Label;


public class LabelComponent extends UIComponent<StringModel, Label>
{
    private String prefix;
    private String suffix;

    public LabelComponent()
    {
        this(null);
    }

    public LabelComponent(StringModel model)
    {
        super(model);
        renderer = new Label();
        prefix = "";
        suffix = "";
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public String getDisplayedText()
    {
        return prefix + model.getValue() + suffix;
    }


    @Override
    protected void updateModel()
    {
        /*
         * Do Nothing
         */
    }

    @Override
    public void attachModel(StringModel model)
    {
        super.attachModel(model);
        renderer.setText(getDisplayedText());
    }

    @Override
    public void valueChanged()
    {
        renderer.setText(getDisplayedText());
    }
}
