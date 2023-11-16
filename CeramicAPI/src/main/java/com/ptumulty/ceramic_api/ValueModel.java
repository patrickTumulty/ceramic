package com.ptumulty.ceramic_api;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueModel<T>
{
    private final List<ValueListener> listeners;
    protected T value;
    protected boolean isSettable;

    public ValueModel(T value)
    {
        this.value = value;
        listeners = new ArrayList<>();
        isSettable = true;
    }

    public T get()
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
        synchronized (listeners)
        {
            if (!listeners.contains(listener))
            {
                listeners.add(listener);
            }
        }
    }

    public void removeListener(ValueListener listener)
    {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    protected void notifyValueListeners()
    {
        synchronized (listeners)
        {
            for (var listener : listeners)
            {
                listener.valueChanged();
            }
        }
    }

    /**
     * Simple listener for listening to value changes. Only fires when the new value is different from the
     * current value.
     */
    public interface ValueListener
    {
        /**
         * Method for handling when the value has changed
         */
        void valueChanged();
    }
}
