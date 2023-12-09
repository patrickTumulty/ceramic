package com.ptumulty.ceramic_api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class ValueModel<T> implements Defaultable<T>
{
    private final List<ValueListener<T>> listeners;
    private boolean isModified;
    private Function<T, T> valueModifier;
    protected T value;
    protected T defaultValue;
    protected boolean isSettable;
    protected boolean alwaysNotifyChange;

    public ValueModel(T value)
    {
        this(value, value);
    }

    public ValueModel(T value, T defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
        listeners = new ArrayList<>();
        valueModifier = x -> x;
        isModified = false;
        isSettable = true;
        alwaysNotifyChange = false;
    }

    /**
     * Set this model to always notify a change in the value. This means
     * listeners will be notified even if values are set with the same value
     *
     * @param alwaysNotifyChange always notify change
     */
    public void setAlwaysNotifyChange(boolean alwaysNotifyChange)
    {
        this.alwaysNotifyChange = alwaysNotifyChange;
    }

    public void setValueModifier(Function<T, T> modifier)
    {
        this.valueModifier = modifier;
    }

    @Override
    public boolean isModified()
    {
        return isModified;
    }

    @Override
    public void restoreDefault()
    {
        setValue(defaultValue);
    }

    @Override
    public void setDefault(T defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public T getDefault()
    {
        return defaultValue;
    }

    public T get()
    {
        return value;
    }

    public final void setValue(T value)
    {
        value = valueModifier.apply(value);

        if (!alwaysNotifyChange && (value == this.value))
        {
            return;
        }

        T oldValue = this.value;
        this.value = value;
        isModified = this.value != defaultValue;
        notifyValueListeners(oldValue, this.value);
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    public void addListener(ValueListener<T> listener)
    {
        synchronized (listeners)
        {
            if (!listeners.contains(listener))
            {
                listeners.add(listener);
            }
        }
    }

    public void removeListener(ValueListener<T> listener)
    {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    protected void notifyValueListeners(T previousValue, T newValue)
    {
        synchronized (listeners)
        {
            for (var listener : listeners)
            {
                listener.valueChanged(previousValue, newValue);
            }
        }
    }

    /**
     * Simple listener for listening to value changes. Only fires when the new value is different from the
     * current value.
     */
    public interface ValueListener<T>
    {
        /**
         * Method for handling when the value has changed
         */
        void valueChanged(T previousValue, T newValue);
    }
}
