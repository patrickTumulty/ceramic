package com.ptumulty.ceramic_api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class ValueModel<T> implements Defaultable<T>
{
    private final List<ValueListener> listeners;
    private boolean isModified;
    private Comparator<T> comparator;
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
        comparator = (lhs, rhs) -> lhs == rhs;
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

    public void setComparator(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    public T get()
    {
        return value;
    }

    public final void setValue(T value)
    {
        value = valueModifier.apply(value);

        if (alwaysNotifyChange)
        {
            if (comparator.equals(value, this.value))
            {
                return;
            }
        }

        this.value = value;
        isModified = !comparator.equals(this.value, defaultValue);
        notifyValueListeners();
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

    public interface Comparator<T>
    {
        boolean equals(T lhs, T rhs);
    }
}
