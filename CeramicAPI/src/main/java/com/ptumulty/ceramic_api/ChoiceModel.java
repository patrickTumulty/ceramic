package com.ptumulty.ceramic_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChoiceModel<T> extends ValueModel<T>
{
    private final List<T> choices;

    public ChoiceModel(T initialValue, List<T> choices)
    {
        super(initialValue);
        this.choices = choices;

        setComparator((currentValue, newValue) -> choices.contains(newValue) && currentValue != newValue);
    }

    public final List<T> getChoiceItems()
    {
        return Collections.unmodifiableList(choices);
    }

    public void setValue(int index)
    {
        if (index > -1 && index < choices.size())
        {
            setValue(choices.get(index));
        }
    }

    public void addChoice(T item)
    {
        choices.add(item);
    }

    public void removeChoice(T item)
    {
        choices.remove(item);
    }
}

