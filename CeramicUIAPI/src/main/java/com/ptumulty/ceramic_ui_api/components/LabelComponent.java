package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_ui_api.utility.FxUtils;
import javafx.scene.control.Label;


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
        valueChanged();
    }

    @Override
    public void valueChanged()
    {
        FxUtils.run(() -> renderer.setText(getDisplayedText()));
    }

    public interface ValueToStringConverter
    {
        String convert(Object value);
    }
}