package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_api.ValueModel.*;
import com.ptumulty.ceramic_api.impl.ColorStringModel;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ComponentFactory
{
    public static Optional<UIComponent<?, ?, ?>> create(@Nullable String label, ValueModel<?> valueModel)
    {
        UIComponent<?, ?, ?> component = null;

        if (valueModel instanceof BooleanModel booleanModel)
        {
            component = new BooleanComponent(label, booleanModel);
        }
        else if (valueModel instanceof BoundIntegerModel boundIntegerModel)
        {
            component = new BoundSliderComponent(label, boundIntegerModel);
        }
        else if (valueModel instanceof KeyboardCommandModel keyboardCommandModel)
        {
            component = new KeyboardCommandComponent(label, keyboardCommandModel);
        }
        else if (valueModel instanceof ColorStringModel colorStringModel)
        {
            component = new ColorPickerComponent(label, colorStringModel);
        }
        else if (valueModel instanceof StringModel stringModel)
        {
            component = new StringComponent(label, stringModel);
        }
        else if (valueModel instanceof ValueModel.ChoiceModel<?> choiceModel)
        {
            component = new ChoiceComponent<>(label, choiceModel);
        }
        else if (valueModel instanceof ValueModel.RunnableModel runnableModel)
        {
            component = new ButtonComponent(label, runnableModel);
        }

        return Optional.ofNullable(component);
    }
}
