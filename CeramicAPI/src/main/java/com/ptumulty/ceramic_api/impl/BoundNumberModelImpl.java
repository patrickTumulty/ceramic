package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import org.jetbrains.annotations.Nullable;

public abstract class BoundNumberModelImpl<T extends Number> extends DefaultValueModel<T>
{
    private final T lowerBound;
    private final T upperBound;

    BoundNumberModelImpl(T value, @Nullable T lowerBound, @Nullable T upperBound)
    {
        super(value);

        this.lowerBound = lowerBound != null ? lowerBound : getTypeMin();
        this.upperBound = upperBound != null ? upperBound : getTypeMax();
        setValueModifier(this::clamp);
    }

    protected abstract T clamp(T value);

    protected abstract T getTypeMax();

    protected abstract T getTypeMin();

    /**
     * @return lower bounds
     */
    public T getLowerBounds()
    {
        return lowerBound;
    }

    /**
     * @return upper bounds
     */
    public T getUpperBounds()
    {
        return upperBound;
    }
}
