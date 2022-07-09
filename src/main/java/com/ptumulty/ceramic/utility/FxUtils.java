package com.ptumulty.ceramic.utility;

import javafx.application.Platform;

public class FxUtils
{
    /**
     * Run on JavaFX UI Thread
     *
     * @param runnable runnable
     */
    public static void run(Runnable runnable)
    {
        Platform.runLater(runnable);
    }
}
