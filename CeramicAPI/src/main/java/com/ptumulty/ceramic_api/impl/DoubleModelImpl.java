package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.DoubleModel;

public class DoubleModelImpl extends DefaultValueModel<Double> implements DoubleModel
{
    DoubleModelImpl(Double value)
    {
        super(value);
    }
}
