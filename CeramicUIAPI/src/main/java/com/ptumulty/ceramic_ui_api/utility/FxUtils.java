package com.ptumulty.ceramic_ui_api.utility;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
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

    public static void bindSize(Pane container, Pane child)
    {
        child.minWidthProperty().bind(container.widthProperty());
        child.minHeightProperty().bind(container.heightProperty());
        child.maxWidthProperty().bind(container.widthProperty());
        child.maxHeightProperty().bind(container.heightProperty());
        child.prefWidthProperty().bind(container.widthProperty());
        child.prefHeightProperty().bind(container.heightProperty());
    }

    public static void bindSize(Scene container, Pane child)
    {
        child.minWidthProperty().bind(container.widthProperty());
        child.minHeightProperty().bind(container.heightProperty());
        child.maxWidthProperty().bind(container.widthProperty());
        child.maxHeightProperty().bind(container.heightProperty());
        child.prefWidthProperty().bind(container.widthProperty());
        child.prefHeightProperty().bind(container.heightProperty());
    }
}
