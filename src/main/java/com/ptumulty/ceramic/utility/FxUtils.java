package com.ptumulty.ceramic.utility;

import javafx.application.Platform;
import javafx.scene.layout.Region;

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

    public static void setStaticRegionSize(Region region, int width, int height)
    {
        region.setMinSize(width, height);
        region.setPrefSize(width, height);
        region.setMaxSize(width, height);
    }
}
