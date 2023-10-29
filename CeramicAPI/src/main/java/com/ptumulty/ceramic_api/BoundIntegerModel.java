package com.ptumulty.ceramic_api;


import com.ptumulty.ceramic_api.utils.NumUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class BoundIntegerModel extends IntegerModel
{
    private final Integer lowerBounds;
    private final Integer upperBounds;

    /**
     * Constructor
     *
     * @param value initial value
     * @param lowerBounds lower bounds. If Optional empty lower bounds will be Integer.MIN_VALUE
     * @param upperBounds upper bounds. If Optional empty upper bounds will be Integer.MAX_VALUE
     */
    public BoundIntegerModel(Integer value, @Nullable Integer lowerBounds, @Nullable Integer upperBounds)
    {
        super(value);

        this.lowerBounds = Optional.ofNullable(lowerBounds).orElse(Integer.MIN_VALUE);
        this.upperBounds = Optional.ofNullable(upperBounds).orElse(Integer.MAX_VALUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Integer value)
    {
        if (!Objects.equals(value, this.value))
        {
            this.value = NumUtils.clamp(value, lowerBounds, upperBounds);
            notifyValueListeners();
        }
    }

    /**
     * @return lower bounds
     */
    public Integer getLowerBounds()
    {
        return lowerBounds;
    }

    /**
     * @return upper bounds
     */
    public Integer getUpperBounds()
    {
        return upperBounds;
    }
}
