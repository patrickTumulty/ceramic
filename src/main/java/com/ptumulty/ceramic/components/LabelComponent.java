package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.ValueModel;
import javafx.scene.control.Label;

import java.util.function.Consumer;


public class LabelComponent extends UIComponent<ValueModel<?>, Label>
{
    private ValueToStringConverter converter;
    private String prefix;
    private String suffix;

    public LabelComponent()
    {
        this(null);
    }

    public LabelComponent(ValueModel<?> model)
    {
        super(model);
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        valueChanged();
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
        valueChanged();
    }

    public String getDisplayedText()
    {
        return prefix + converter.convert(model.get()) + suffix;
    }

    @Override
    protected void updateModel()
    {
        /*
         * Do Nothing
         */
    }

    @Override
    protected void initializeRenderer()
    {
        converter = Object::toString;
        prefix = "";
        suffix = "";
        renderer = new Label();
    }

    public void setValueStringConverter(ValueToStringConverter converter)
    {
        this.converter = converter;
    }

    @Override
    public void attachModel(ValueModel<?> model)
    {
        super.attachModel(model);
        renderer.setText(getDisplayedText());
    }

    @Override
    public void valueChanged()
    {
        renderer.setText(getDisplayedText());
    }

    public interface ValueToStringConverter
    {
        String convert(Object value);
    }
}
