package com.ptumulty.ceramic_ui_api;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * A pane that can be dragged around with a parent window
 */
public class DragPane extends Pane
{
    private final Pane parent;
    private Bounds parentBounds;
    private double relX;
    private double relY;

    /**
     * Constructor
     *
     * @param parent parent node that this DragPane is placed within
     */
    public DragPane(Pane parent)
    {
        this.parent = parent;

        setLayoutX(0);
        setLayoutY(0);

        setOnMouseDragged(this::moveNodeWithMouse);

        setOnMousePressed(event ->
        {
            if (isManaged())
            {
                setManaged(false); // Let parent manage the node on the initial placement
            }
            updateRelativeXY(event);
        });
    }

    /**
     * Move the node within a parent pane based on a mouse event
     *
     * @param event the mouse event to move the node with
     */
    private void moveNodeWithMouse(MouseEvent event)
    {
        setLayoutX(getDraggedX(event.getSceneX() - parentBounds.getMinX(),
                   widthProperty().get(),
                   parent.widthProperty().get()));
        setLayoutY(getDraggedY(event.getSceneY() - parentBounds.getMinY(),
                   heightProperty().get(),
                   parent.heightProperty().get()));
    }

    /**
     * Get the new layout x coordinate while dragging the node. This method ensures that the node can't be dragged
     * outside the current window.
     *
     * @param eventX mouse event x
     * @param width node width
     * @param parentWidth the right most x coordinate
     * @return the new x coordinate
     */
    private double getDraggedX(double eventX, double width, double parentWidth)
    {
        if (eventX - relX > 0 && eventX - relX + width < parentWidth)
        {
            return eventX - relX;
        }
        else
        {
            return eventX - relX <= 0 ? 0 : parentWidth - width;
        }
    }

    /**
     * Get the new layout y coordinate while dragging the node. This method ensures that the node can't be dragged
     * outside the current window.
     *
     * @param eventY mouse event y coordinate
     * @param height current node height
     * @param parentHeight bottom most y coordinate
     * @return new y coordinate
     */
    private double getDraggedY(double eventY, double height, double parentHeight)
    {
        if (eventY - relY + height < parentHeight && eventY - relY > 0)
        {
            return eventY - relY;
        }
        else
        {
            return eventY - relY <= 0 ? 0 : parentHeight - height;
        }
    }

    /**
     * Update the parent bounds field
     */
    private void updateParentBounds()
    {
        parentBounds = parent.getBoundsInParent();
    }

    /**
     * Update the relative X and Y
     *
     * @param event Current mouse event
     */
    private void updateRelativeXY(MouseEvent event)
    {
        updateParentBounds();
        Point2D local = sceneToLocal(event.getSceneX(), event.getSceneY());
        relX = local.getX();
        relY = local.getY();
    }

}
