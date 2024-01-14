package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.BooleanModel;

public class BooleanModelImpl extends DefaultValueModel<Boolean> implements BooleanModel
{
    BooleanModelImpl(Boolean value)
    {
        super(value);
    }

    BooleanModelImpl(Boolean value, Boolean defaultValue)
    {
        super(value, defaultValue);
    }
}
