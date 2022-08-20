package com.ptumulty.ceramic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FourCornerPane extends BorderPane
{
    private final HBox topLeft;
    private final HBox topRight;
    private final HBox bottomLeft;
    private final HBox bottomRight;
    private final BorderPane top;
    private final BorderPane bottom;
    private int spacing;

    public FourCornerPane()
    {
        spacing = 0;
        top = new BorderPane();
        bottom = new BorderPane();
        topLeft = new HBox();
        topRight = new HBox();
        bottomLeft = new HBox();
        bottomRight = new HBox();

        topLeft.setAlignment(Pos.TOP_LEFT);
        topRight.setAlignment(Pos.TOP_RIGHT);
        bottomLeft.setAlignment(Pos.BOTTOM_LEFT);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);

        setTop(top);
        top.setLeft(topLeft);
        BorderPane.setAlignment(topLeft, Pos.TOP_LEFT);
        top.setRight(topRight);
        BorderPane.setAlignment(topRight, Pos.TOP_RIGHT);

        setBottom(bottom);
        bottom.setLeft(bottomLeft);
        BorderPane.setAlignment(bottomLeft, Pos.BOTTOM_LEFT);
        bottom.setRight(bottomRight);
        BorderPane.setAlignment(bottomRight, Pos.BOTTOM_RIGHT);

        topRight.prefWidthProperty().bind(topLeft.widthProperty());
        topLeft.prefWidthProperty().bind(topRight.widthProperty());
        bottomLeft.prefWidthProperty().bind(bottomRight.widthProperty());
        bottomRight.prefWidthProperty().bind(bottomLeft.widthProperty());
    }

    public void setSpacing(int spacing)
    {
        topLeft.setSpacing(spacing);
        topRight.setSpacing(spacing);
        bottomLeft.setSpacing(spacing);
        bottomRight.setSpacing(spacing);
    }

    public void setInsets(int spacing)
    {
        this.spacing = spacing;
        BorderPane.setMargin(topLeft, new Insets(spacing, 0, 0, spacing));
        BorderPane.setMargin(topRight, new Insets(spacing, spacing, 0, 0));
        BorderPane.setMargin(bottomLeft, new Insets(0, 0, spacing, spacing));
        BorderPane.setMargin(bottomRight, new Insets(0, spacing, spacing, 0));
        setTopMargin();
        setBottomMargin();
    }

    private void setBottomMargin()
    {
        if (bottom.getCenter() != null)
        {
            BorderPane.setMargin(bottom.getCenter(), new Insets(0, 0, spacing, 0));
        }
    }

    private void setTopMargin()
    {
        if (top.getCenter() != null)
        {
            BorderPane.setMargin(top.getCenter(), new Insets(spacing, 0, 0, 0));
        }
    }

    public HBox getTopLeft()
    {
        return topLeft;
    }

    public HBox getTopRight()
    {
        return topRight;
    }

    public HBox getBottomLeft()
    {
        return bottomLeft;
    }

    public HBox getBottomRight()
    {
        return bottomRight;
    }

    public void setTopNode(Node node)
    {
        top.setCenter(node);
        setTopMargin();
    }

    public void setBottomNode(Node node)
    {
        bottom.setCenter(node);
        setBottomMargin();
    }
}
