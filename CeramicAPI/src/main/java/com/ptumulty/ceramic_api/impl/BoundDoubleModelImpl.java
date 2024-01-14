package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.ValueModel.BoundDoubleModel;
import com.ptumulty.ceramic_api.utils.NumUtils;
import org.jetbrains.annotations.Nullable;

public class BoundDoubleModelImpl extends BoundNumberModelImpl<Double> implements BoundDoubleModel
{
    BoundDoubleModelImpl(Double value,
                                @Nullable Double lowerBound,
                                @Nullable Double upperBound)
    {
        super(value, lowerBound, upperBound);
    }

    @Override
    protected Double clamp(Double value)
    {
        return NumUtils.clamp(value, getLowerBounds(), getUpperBounds());
    }

    @Override
    protected Double getTypeMax()
    {
        return Double.MAX_VALUE;
    }

    @Override
    protected Double getTypeMin()
    {
        return Double.MIN_VALUE;
    }
}
