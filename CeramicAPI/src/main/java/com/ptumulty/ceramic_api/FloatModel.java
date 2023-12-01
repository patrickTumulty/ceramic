package com.ptumulty.ceramic_api;

public class FloatModel extends ValueModel<Float>
{
    public FloatModel(Float value)
    {
        super(value);
    }

    public FloatModel(Float value, boolean alwaysNotify)
    {
       this(value);
       setAlwaysNotifyChange(alwaysNotify);
    }
}
