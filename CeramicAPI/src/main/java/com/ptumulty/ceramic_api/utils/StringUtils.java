package com.ptumulty.ceramic_api.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils
{
    public static String uppercaseFirst(String word)
    {
        word = word.toLowerCase();
        return Character.toString(word.charAt(0)).toUpperCase() + word.substring(1);
    }

    public static List<String> parseMultilineStringToList(String s)
    {
        return Arrays.stream(s.split("\n")).map(String::strip).collect(Collectors.toList());
    }

    public static void removeBlankStringsFromList(List<String> stringList)
    {
        for (int i = 0; i < stringList.size(); i++)
        {
            if (stringList.get(i).isBlank())
            {
                stringList.remove(stringList.get(i));
                i--;
            }
        }
    }
}
