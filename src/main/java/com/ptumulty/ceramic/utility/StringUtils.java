package com.ptumulty.ceramic.utility;

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
}
