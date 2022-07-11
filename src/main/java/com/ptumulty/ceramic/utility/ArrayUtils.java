package com.ptumulty.ceramic.utility;

public class ArrayUtils
{
    public static void swap(Object[] arr, int i, int j)
    {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void roll(Object[] arr, int n)
    {
        if (n == 0 || n == arr.length)
        {
            return;
        }
        Object[] temp = new Object[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        n = n % arr.length;
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = temp[(i + n) % arr.length];
        }
    }

    public static String getArrayString(Object[] arr)
    {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (Object element : arr)
        {
            stringBuilder.append(element).append(" ");
        }
        return stringBuilder.toString();
    }
}
