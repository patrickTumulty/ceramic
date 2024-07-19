package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.IntegerModel;

public class IntegerModelImpl extends DefaultValueModel<Integer> implements IntegerModel
{
    IntegerModelImpl(Integer value)
    {
        super(value);
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
