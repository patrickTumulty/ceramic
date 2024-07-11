package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.ValueModel.*;
import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommand;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModelFactory
{
    public static StringModel create(String value)
    {
        return new StringModelImpl(value);
    }

    public static IntegerModel create(int value)
    {
        return new IntegerModelImpl(value);
    }

    public static BooleanModel create(boolean value)
    {
        return new BooleanModelImpl(value);
    }

    public static DoubleModel create(double value)
    {
        return new DoubleModelImpl(value);
    }

    public static FloatModel create(float value)
    {
        return new FloatModelImpl(value);
    }

    public static FloatModel create(float value, boolean alwaysNotify)
    {
        return new FloatModelImpl(value, alwaysNotify);
    }

    public static BoundIntegerModel create(int value, @Nullable Integer lowerBounds, @Nullable Integer upperBounds)
    {
        return new BoundIntegerModelImpl(value, lowerBounds, upperBounds);
    }

    public static <T> ChoiceModel<T> create(T value, List<T> choices)
    {
        return new ChoiceModelImpl<>(value, choices);
    }

    public static KeyboardCommandModel create(KeyboardCommand command, Runnable action)
    {
        return new KeyboardCommandModelImpl(command, action);
    }

    public static <T> ListModel<T> create(List<T> list)
    {
        return new ListModelImpl<>(list);
    }
}
