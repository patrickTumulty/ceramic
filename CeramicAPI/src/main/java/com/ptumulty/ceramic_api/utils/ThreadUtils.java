package com.ptumulty.ceramic_api.utils;

import org.jetbrains.annotations.Nullable;

public class ThreadUtils
{
    /**
     * Run runnable in a detached thread
     *
     * @param runnable runnable
     */
    public static void run(Runnable runnable)
    {
        run(null, runnable);
    }

    /**
     * Run runnable in a detached thread
     *
     * @param threadName thread name
     * @param runnable runnable
     */
    public static void run(@Nullable String threadName, Runnable runnable)
    {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        if (threadName != null)
        {
            thread.setName(threadName);
        }
        thread.start();
    }

    /**
     * Thread sleep safely. If interrupt exception is thrown, this method returns immediately
     *
     * @param millis milliseconds to sleep
     */
    public static void safeSleep(long millis)
    {
        try
        {
            Thread.sleep(Math.max(0, millis));
        }
        catch (InterruptedException e)
        {
            // Do Nothing
        }
    }
}
