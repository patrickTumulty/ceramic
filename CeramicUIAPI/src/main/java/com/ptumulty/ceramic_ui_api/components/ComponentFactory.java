package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel;
import com.ptumulty.ceramic_api.ValueModel.BooleanModel;
import com.ptumulty.ceramic_api.ValueModel.BoundIntegerModel;
import com.ptumulty.ceramic_api.ValueModel.KeyboardCommandModel;
import com.ptumulty.ceramic_api.ValueModel.StringModel;
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

        return Optional.ofNullable(component);
    }
}
