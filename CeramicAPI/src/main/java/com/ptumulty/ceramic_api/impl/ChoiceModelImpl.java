package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.ChoiceModel;

import java.util.Collections;
import java.util.List;

public class ChoiceModelImpl<T> extends DefaultValueModel<T> implements ChoiceModel<T>
{
    private final List<T> choices;

    ChoiceModelImpl(T initialValue, List<T> choices)
    {
        super(initialValue);
        this.choices = choices;
    }

    @Override
    public final List<T> getChoiceItems()
    {
        return Collections.unmodifiableList(choices);
    }

    @Override
    public void setValueIndex(int index)
    {
        if (index > -1 && index < choices.size())
        {
            setValue(choices.get(index));
        }
    }

    @Override
    public void addChoice(T item)
    {
        choices.add(item);
    }

    @Override
    public void removeChoice(T item)
    {
        choices.remove(item);
    }
}

