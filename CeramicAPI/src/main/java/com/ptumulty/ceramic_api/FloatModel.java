package com.ptumulty.ceramic_api;

public class FloatModel extends ValueModel<Float>
{
    private final int ROUNDING_COEFFICIENT = 1000;

    public FloatModel(Float value)
    {
        super(value);
        setComparator((lhs, rhs) -> Math.round(lhs * ROUNDING_COEFFICIENT) == Math.round(ROUNDING_COEFFICIENT));
    }
}
