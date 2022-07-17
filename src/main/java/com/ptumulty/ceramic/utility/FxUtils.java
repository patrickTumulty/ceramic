package com.ptumulty.ceramic.utility;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

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

    public static void setStaticPaneSize(Pane pane, int width, int height)
    {
        pane.setMinSize(width, height);
        pane.setPrefSize(width, height);
        pane.setMaxSize(width, height);
    }
}
