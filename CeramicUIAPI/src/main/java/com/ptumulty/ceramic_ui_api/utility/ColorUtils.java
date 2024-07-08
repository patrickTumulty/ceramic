package com.ptumulty.ceramic_ui_api.utility;

import javafx.scene.paint.Color;

public class ColorUtils
{
    public static String colorToHex(Color color)
    {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int alpha = (int) (color.getOpacity() * 255);
        return String.format("#%02X%02X%02X%02X", red, green, blue, alpha);
    }
}
