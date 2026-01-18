package com.ptumulty.ceramic_api;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class DefaultValueModel<T> implements ValueModel<T>
{
    private final List<ValueListener<T>> listeners;
    private @Nullable SaveStringConverter saveStringConverter;
    private boolean isModified;
    private Function<T, T> valueModifier;
    protected T value;
    protected T defaultValue;
    protected boolean isSettable;
    protected boolean alwaysNotifyChange;

    public DefaultValueModel(T value)
    {
        this(value, value);
    }

    public DefaultValueModel(T value, T defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
        listeners = new ArrayList<>();
        valueModifier = x -> x;
        isModified = false;
        isSettable = true;
        alwaysNotifyChange = false;
        saveStringConverter = null;
    }

    @Override
    public Optional<SaveStringConverter> getSaveStringConverter()
    {
        return Optional.ofNullable(saveStringConverter);
    }

    @Override
    public void setSaveStringConverter(@Nullable SaveStringConverter saveStringConverter)
    {
        this.saveStringConverter = saveStringConverter;
    }

    /**
     * Set this model to always notify a change in the value. This means
     * listeners will be notified even if values are set with the same value
     *
     * @param alwaysNotifyChange always notify change
     */
    @Override
    public void setAlwaysNotifyChange(boolean alwaysNotifyChange)
    {
        this.alwaysNotifyChange = alwaysNotifyChange;
    }

    @Override
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

    @Override
    public T get()
    {
        return value;
    }

    @Override
    public final void setValue(T value)
    {
        value = valueModifier.apply(value);

        if (!alwaysNotifyChange && (Objects.equals(value, this.value)))
        {
            return;
        }

        T oldValue = this.value;
        this.value = value;
        isModified = !this.value.equals(defaultValue);
        notifyValueListeners(oldValue, this.value);
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
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

    @Override
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
}
