package com.ptumulty.ceramic_api.impl;


import com.ptumulty.ceramic_api.ValueModel.BoundIntegerModel;
import com.ptumulty.ceramic_api.utils.NumUtils;
import org.jetbrains.annotations.Nullable;

public class BoundIntegerModelImpl extends BoundNumberModelImpl<Integer> implements BoundIntegerModel
{
    BoundIntegerModelImpl(Integer value, @Nullable Integer lowerBound, @Nullable Integer upperBound)
    {
        super(value, lowerBound, upperBound);
    }

    @Override
    protected Integer clamp(Integer value)
    {
        return NumUtils.clamp(value, getLowerBounds(), getUpperBounds());
    }

    @Override
    protected Integer getTypeMax()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    protected Integer getTypeMin()
    {
        return Integer.MIN_VALUE;
    }

    @Override
    public void increment()
    {
        setValue(get() + 1);
    }

    @Override
    public void decrement()
    {
        setValue(get() - 1);
    }
}
