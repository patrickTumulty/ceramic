package com.ptumulty.ceramic_api.utils;

import java.util.List;

public class ListUtils
{
    /**
     * Get an item from a list. If index is out of bounds, this method will return null instaed of throwing an
     * index out of bounds exception
     *
     * @param list list
     * @param index index
     * @return value or null
     * @param <T> value type
     */
    public static <T> T getOrNull(List<T> list, int index)
    {
        if (index > -1 && index < list.size())
        {
            return list.get(index);
        }
        return null;
    }
}
