package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_ui_api.utility.FxUtils;
import javafx.scene.control.Label;


public class LabelComponent<T> extends UIComponent<T, ValueModel<T>, Label>
{
    private ValueToStringConverter converter;
    private String prefix;
    private String suffix;

    public LabelComponent()
    {
        this(null);
    }

    public LabelComponent(ValueModel<T> model)
    {
        super(model);
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        if (model != null)
        {
            valueChanged(model.get(), model.get());
        }
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
        if (model != null)
        {
            valueChanged(model.get(), model.get());
        }
    }

    public String getDisplayedText()
    {
        if (model == null)
        {
            return "";
        }
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
    public void attachModel(ValueModel<T> model)
    {
        super.attachModel(model);
        valueChanged(null, model.get());
    }

    @Override
    public void valueChanged(T prev, T curr)
    {
        FxUtils.run(() -> renderer.setText(getDisplayedText()));
    }

    public interface ValueToStringConverter
    {
        String convert(Object value);
    }
}
