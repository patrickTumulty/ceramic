package com.ptumulty.ceramic.models;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueModel<T>
{
    private List<ValueListener> listeners;
    protected T value;
    protected boolean isSettable;

    ValueModel(T value)
    {
        this.value = value;
        listeners = new ArrayList<>();
        isSettable = true;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        if (value != this.value)
        {
            this.value = value;
            notifyValueListeners();
        }
    }

    public boolean isSettable()
    {
        return isSettable;
    }

    public void setIsSettable(boolean isSettable)
    {
        this.isSettable = isSettable;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    public void addListener(ValueListener listener)
    {
        if (!listeners.contains(listener))
        {
            listeners.add(listener);
        }
    }

    public void removeListener(ValueListener listener)
    {
        listeners.remove(listener);
    }

    protected void notifyValueListeners()
    {
        listeners.forEach(ValueListener::valueChanged);
    }

    public interface ValueListener
    {
        /**
         * Method for handling when the value has changed
         */
        void valueChanged();
    }
}
