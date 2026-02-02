package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.ChoiceModel;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChoiceModelImpl<T> extends DefaultValueModel<T> implements ChoiceModel<T>
{
    private final List<T> choices;
    private final List<ChoiceListener<T>> listeners;
    private @Nullable StringConverter<T> stringConverter;

    public ChoiceModelImpl()
    {
        super(null);
        listeners = new ArrayList<>();
        choices = new ArrayList<>();
    }

    ChoiceModelImpl(T initialValue, List<T> choices)
    {
        super(initialValue);
        listeners = new ArrayList<>();
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
    public void setStringConverter(@Nullable StringConverter<T> stringConverter)
    {
        this.stringConverter = stringConverter;
    }

    @Override
    public Optional<StringConverter<T>> getStringConverter()
    {
        return Optional.ofNullable(stringConverter);
    }

    @Override
    public void addChoice(T item)
    {
        choices.add(item);
        listeners.forEach(l -> l.choiceAdded(item));
        if (get() == null)
        {
            setValue(item);
        }
    }

    @Override
    public void removeChoice(T item)
    {
        choices.remove(item);
        listeners.forEach(l -> l.choiceRemoved(item));
    }

    @Override
    public void addChoiceListener(ChoiceListener<T> listener)
    {
        synchronized (listeners)
        {
            if (!listeners.contains(listener))
            {
                listeners.add(listener);
            }
        }
    }

    @Override
    public void removeChoiceListener(ChoiceListener<T> listener)
    {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }
}

