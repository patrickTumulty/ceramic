package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.StringModel;

public class StringModelImpl extends DefaultValueModel<String> implements StringModel
{
    StringModelImpl()
    {
        super("");
    }

    StringModelImpl(String value)
    {
        super(value);
    }
}
