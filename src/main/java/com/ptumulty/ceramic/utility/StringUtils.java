package com.ptumulty.ceramic.utility;

public class StringUtils
{
    public static String uppercaseFirst(String word)
    {
        word = word.toLowerCase();
        return Character.toString(word.charAt(0)).toUpperCase() + word.substring(1);
    }
}
