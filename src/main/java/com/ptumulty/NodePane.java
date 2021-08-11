package com.ptumulty;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A JavaFX pane for displaying movable, resizable nodes.
 */
public class NodePane
{
    private final Pane dragPane;

    /**
     * Constructor
     */
    public NodePane()
    {
        this(800, 600);
    }

    /**
     * Constructor
     *
     * @param width The initial width
     * @param height The initial height
     */
    public NodePane(double width, double height)
    {
        dragPane = new Pane();
        dragPane.setPrefSize(width, height);
    }

    /**
     * Set which Node currently has context and bring it to front
     *
     * @param node the ResizableNode to set selected
     */
    public void setSelected(ResizableNode node)
    {
        if (dragPane.getChildren().contains(node))
        {
            node.bringToFront();
        }
    }

    /**
     * @return the NodePane width
     */
    public double getPaneWidth()
    {
        return dragPane.getWidth();
    }

    /**
     * @return the NodePane height
     */
    public double getPaneHeight()
    {
        return dragPane.getHeight();
    }

    /**
     * Set the NodePane background
     *
     * @param background the new background
     */
    public void setBackground(Background background)
    {
        dragPane.setBackground(background);
    }

    /**
     * Add a JavaFX pane to the NodePane with initial XY coordinates
     *
     * @param node new node
     */
    public void addNode(ResizableNode node)
    {
        node.setNodePane(this);
        dragPane.getChildren().add(node);
    }

    /**
     * Remove node from node pane
     *
     * @param node Node to remove
     */
    public void removeNode(ResizableNode node)
    {
        dragPane.getChildren().remove(node);
    }

    /**
     * @return the NodePane render
     */
    public Parent getRenderer()
    {
        return dragPane;
    }
}
