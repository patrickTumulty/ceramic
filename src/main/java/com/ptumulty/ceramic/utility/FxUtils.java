package com.ptumulty.ceramic.utility;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

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

    public static Background backgroundFill(Paint color)
    {
        return new Background(new BackgroundFill(color, null, null));
    }
}
