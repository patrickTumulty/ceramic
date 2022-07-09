package com.ptumulty.ceramic.utility;

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
}
