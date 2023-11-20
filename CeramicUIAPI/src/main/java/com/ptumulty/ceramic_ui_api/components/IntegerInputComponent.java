package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.IntegerModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class IntegerInputComponent extends UIComponent<IntegerModel, TextField>
{
    public IntegerInputComponent()
    {
        this(null);
    }

    public IntegerInputComponent(IntegerModel model)
    {
        super(model);
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            if (renderer.getText().isBlank())
            {
                model.setValue(0);
                renderer.setText("0");
                return;
            }

            try
            {
                model.setValue(Integer.parseInt(renderer.getText()));
            }
            catch (NumberFormatException e)
            {
                model.setValue(model.get());
            }
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new TextField();
        UnaryOperator<TextFormatter.Change> integerFilter = change ->
        {
            String controlNewText = change.getControlNewText();
            if (controlNewText.matches("-?([0-9]*)?"))
            {
                return change;
            }
            return null;
        };
        renderer.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
        renderer.setOnAction(event -> updateModel());
    }

    @Override
    public void valueChanged()
    {
        if (model != null)
        {
            renderer.setText(Integer.toString(this.model.get()));
        }
    }
}
