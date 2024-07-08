package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.ValueModel.StringModel;

import java.util.regex.Pattern;

public class ColorStringModel extends StringModelImpl implements StringModel
{
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{3}|[A-Fa-f0-9]{6}|[A-Fa-f0-9]{4}|[A-Fa-f0-9]{8})$");

    public ColorStringModel()
    {
        this("#FFFFFF");
    }

    public ColorStringModel(String value)
    {
        super(value);

        setValueModifier(s -> {
            String result;
            if (HEX_COLOR_PATTERN.matcher(s).matches())
            {
                result = s;
            }
            else
            {
                result = get();
            }
            return result;
        });
    }
}
