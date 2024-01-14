package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.FloatModel;

public class FloatModelImpl extends DefaultValueModel<Float> implements FloatModel
{
    FloatModelImpl(Float value)
    {
        super(value);
    }

    FloatModelImpl(Float value, boolean alwaysNotify)
    {
       this(value);
       setAlwaysNotifyChange(alwaysNotify);
    }
}
