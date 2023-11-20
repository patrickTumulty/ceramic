package com.ptumulty.ceramic_api;

public class DoubleModel extends ValueModel<Double>
{
    private final int ROUNDING_COEFFICIENT = 1000;

    public DoubleModel(Double value)
    {
        super(value);
        setComparator((lhs, rhs) -> Math.round(lhs * ROUNDING_COEFFICIENT) == Math.round(ROUNDING_COEFFICIENT));
    }
}
