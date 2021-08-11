package com.pt.mug.Utility;

import javafx.scene.Parent;

public class StringUtils
{
    public static String uppercaseFirst(String word)
    {
        word = word.toLowerCase();
        return Character.toString(word.charAt(0)).toUpperCase() + word.substring(1);
    }
}
