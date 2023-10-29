package com.ptumulty.ceramic_api.utils;

public class ThreadUtils
{
    /**
     * Run runnable in a detached thread
     *
     * @param runnable runnable
     */
    public static void run(Runnable runnable)
    {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Thread sleep safely. If interrupt exception is thrown, this method returns immediately
     *
     * @param millis milliseconds to sleep
     */
    public static void safeSleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            // Do Nothing
        }
    }
}
