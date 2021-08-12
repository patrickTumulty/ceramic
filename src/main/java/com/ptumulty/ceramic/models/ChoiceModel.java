package com.ptumulty.ceramic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChoiceModel<T> extends ValueModel<T>
{
    List<T> choices;
    List<ChoiceListener<T>> listeners;

    public ChoiceModel(T initialValue, List<T> choices)
    {
        super(initialValue);
        this.choices = choices;
        listeners = new ArrayList<>();
    }

    public final List<T> getChoiceItems()
    {
        return Collections.unmodifiableList(choices);
    }

    @Override
    public void setValue(T value)
    {
        if (choices.contains(value))
        {
            super.setValue(value);
        }
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

    public void addListener(ChoiceListener<T> listener)
    {
        listeners.add(listener);
    }

    public void removeListener(ChoiceListener<T> listener)
    {
        listeners.remove(listener);
    }

    public interface ChoiceListener<T>
    {
        void choiceModelChanged(T currentValue);
    }
}

