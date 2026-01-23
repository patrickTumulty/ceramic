package com.ptumulty.ceramic_api.utils;

public class ByteUtils
{
    public static long KILOBYTE = 1000;
    public static long MEGABYTE = 1000 * 1000;
    public static long GIGABYTE = 1000 * 1000 * 1000;
    public static long TERABYTE = 1000 * 1000 * 1000 * 1000;
    public static long KIBIBYTE = 1024;
    public static long MIBIBYTE = 1024 * 1024;
    public static long GIBIBYTE = 1024 * 1024 * 1024;
    public static long TEBIBYTE = 1024L * 1024L * 1024L * 1024L;

    public static String toDataSizeStringB2(long bytes)
    {
        if (bytes < KIBIBYTE)
        {
            return "%d B".formatted(bytes);
        }
        if (bytes < MIBIBYTE)
        {
            return "%.2f KiB".formatted((float) bytes / (float) KIBIBYTE);
        }
        if (bytes < GIBIBYTE)
        {
            return "%.2f MiB".formatted((float) bytes / (float) MIBIBYTE);
        }
        if (bytes < TEBIBYTE)
        {
            return "%.2f GiB".formatted((float) bytes / (float) GIBIBYTE);
        }
        return "%.2f TiB".formatted((float) bytes / (float) TEBIBYTE);
    }

    public static String toDataSizeStringB10(long bytes)
    {
        if (bytes < KILOBYTE)
        {
            return "%d B".formatted(bytes);
        }
        if (bytes < MEGABYTE)
        {
            return "%.2f KB".formatted((float) bytes / (float) KILOBYTE);
        }
        if (bytes < GIGABYTE)
        {
            return "%.2f MB".formatted((float) bytes / (float) MEGABYTE);
        }
        if (bytes < TERABYTE)
        {
            return "%.2f GB".formatted((float) bytes / (float) GIGABYTE);
        }
        return "%.2f TB".formatted((float) bytes / (float) TERABYTE);
    }
}
