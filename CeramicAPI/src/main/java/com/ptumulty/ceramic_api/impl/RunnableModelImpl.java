package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.RunnableModel;

public class RunnableModelImpl extends DefaultValueModel<Runnable> implements RunnableModel
{
    public RunnableModelImpl(Runnable value)
    {
        super(value);
        setSaveStringConverter(new SaveStringConverter()
        {
            @Override
            public void fromSaveString(String saveString)
            {

            }

            @Override
            public String toSaveString()
            {
                return "NA";
            }
        });
    }
}
