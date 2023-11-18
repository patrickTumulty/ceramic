package com.ptumulty.ceramic_api.utils;

public class NumUtils
{
    /**
     * Clamp a value between an upper and lower bounds
     *
     * @param value value
     * @param lower lower bounds (inclusive)
     * @param upper upper bounds (inclusive)
     * @return clamped value
     */
    public static int clamp(int value, int lower, int upper)
    {
        return Math.min(Math.max(value, lower), upper);
    }

    public static double clamp(double value, double lower, double upper)
    {
        return Math.min(Math.max(value, lower), upper);
    }
}