package com.pt.mug.Models;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueModel<T>
{
    private List<ValueListener> listeners;
    protected T value;
    protected boolean settable;

    ValueModel(T value)
    {
        this.value = value;
        listeners = new ArrayList<>();
        settable = true;
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
        return settable;
    }

    public void setSettable(boolean settable)
    {
        this.settable = settable;
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
