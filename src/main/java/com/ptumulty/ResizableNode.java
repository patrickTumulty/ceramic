package com.ptumulty;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * A node to be dragged around and resized within a NodePane
 */
public class ResizableNode extends BorderPane
{
    private final Color CLOSE_BUTTON_HOVER_COLOR = Color.DARKRED;
    private final Color LIGHT_GRAY = Color.LIGHTGRAY;
    private final Color DARKER_GRAY = Color.web("#e3e3e3");
    private final int TOP_BAR_HEIGHT = 15;
    private final int TOP_BAR_BUTTON_WIDTH = 15;
    private final int ICON_SIZE = 10;
    private final int BORDER_WIDTH = 1;
    private final int INSET_WIDTH = 3;
    private final int ADDITIONAL_STATIC_HEIGHT = TOP_BAR_HEIGHT + (BORDER_WIDTH * 3) + (INSET_WIDTH * 2);
    private final int ADDITIONAL_STATIC_WIDTH = (BORDER_WIDTH * 2) + (INSET_WIDTH * 2);

    private final Pane content;

    private StackPane dragRegion;
    private StackPane closeButton;
    private StackPane lockButton;
    private MousePosition mouseHoverPosition;
    private Label topBarLabel;

    private NodePane parent;
    private Bounds parentBounds;

    private List<NodeListener> listeners;

    private double currentX;
    private double currentY;
    private double relX;
    private double relY;
    private double currentWidth;
    private double currentHeight;

    private double previousWidth;
    private double previousHeight;
    private double previousX;
    private double previousY;

    private boolean expanded;
    private boolean mouseOnCloseButton;
    private boolean locked;

    private boolean showPopUpOnClose;
    private boolean expandable;
    private boolean resizable;
    private boolean movable;

    /**
     * Constructor
     *
     * @param content The content to feature within a ResizeableNode
     */
    public ResizableNode(Pane content)
    {
        this(content, 0, 0);
    }

    /**
     * Constructor
     *
     * @param content The content to feature within a ResizeableNode
     * @param initialX The initial X position value. Default is 0
     * @param initialY The initial Y position value. Default is 0
     */
    public ResizableNode(Pane content, double initialX, double initialY)
    {
        this.content = content;

        expanded = false;
        mouseOnCloseButton = false;
        locked = false;
        listeners = new ArrayList<>();

        configureTopBar();

        configureMouseResizeBehavior();

        configureContentInNode(initialX, initialY);

        setDefaultBehaviorFlags();
    }

    /**
     * Set the default behavior flags for this node
     */
    private void setDefaultBehaviorFlags()
    {
        showPopUpOnClose = true;
        expandable = true;
        resizable = true;
        movable = true;
    }

    /**
     * Get the Pane containing the content of this ResizableNode
     *
     * @return the content pane of this node
     */
    public Pane getContent()
    {
        return content;
    }

    /**
     * Bring this Node to the front
     */
    public void bringToFront()
    {
        toFront();
    }

    /**
     * Set the top bar label
     *
     * @param text top bar label text
     */
    public void setTopBarLabel(String text)
    {
        this.topBarLabel.setText(text);
    }

    /**
     * @return true of this node is expandable else false
     */
    public boolean isExpandable()
    {
        return expandable;
    }

    /**
     * Set whether or not this node is expandable
     *
     * @param expandable boolean value whether or not this node is expandable
     */
    public void setExpandable(boolean expandable)
    {
        this.expandable = expandable;
    }

    /**
     * @return true if this node is resizable, else false
     */
    @Override
    public boolean isResizable()
    {
        return resizable;
    }

    /**
     * Set whether or not this node is resizable
     *
     * @param resizable true of resizable else false
     */
    public void setResizable(boolean resizable)
    {
        this.resizable = resizable;
    }

    /**
     * @return whether or not this node is movable
     */
    public boolean isMovable()
    {
        return movable;
    }

    /**
     * Set whether or not this node is movable
     *
     * @param movable set true for movable else false
     */
    public void setMovable(boolean movable)
    {
        this.movable = movable;
    }

    /**
     * Set whether or not to display a popup when a user clicks the close button
     *
     * @param showPopUpOnClose set boolean whether or not to display a pop up on close
     */
    public void setShowPopUpOnClose(boolean showPopUpOnClose)
    {
        this.showPopUpOnClose = showPopUpOnClose;
    }

    /**
     * Set the node pane that this node will be contained in. This method isn't meant to be called by the user. It
     * is called automatically when this ResizableNode is added to a NodePane
     *
     * @param pane the parent NodePane for this node
     */
    public void setNodePane(NodePane pane)
    {
        parent = pane;
        updateParentBounds();
    }

    /**
     * Configure the node position and size based on the content
     *
     * @param initialX The initial X position
     * @param initialY The initial Y position
     */
    private void configureContentInNode(double initialX, double initialY)
    {
        if (content.getMaxHeight() != -1)
        {
            setMaxHeight(content.getMaxHeight() + ADDITIONAL_STATIC_HEIGHT);
        }
        if (content.getMaxWidth() != -1)
        {
            setMaxWidth(content.getMaxWidth());
        }

        double minHeight = content.getMinHeight() != -1 ? content.getMinHeight() : content.getHeight();
        double minWidth = content.getMinWidth() != -1 ? content.getMinWidth() : content.getWidth();

        setMinHeight(minHeight + ADDITIONAL_STATIC_HEIGHT);
        setMinWidth(minWidth + ADDITIONAL_STATIC_WIDTH);
        dragRegion.setMinWidth(minWidth + (INSET_WIDTH * 2) - (TOP_BAR_BUTTON_WIDTH * 2));

        setNodeHeight(minHeight);
        setNodeWidth(minWidth);

        setLayoutX(initialX);
        setLayoutY(initialY);

        setContextEventHandlers();

        backgroundProperty().bind(content.backgroundProperty());

        setCenter(content);
        BorderPane.setMargin(content, new Insets(INSET_WIDTH));
        BorderPane.setAlignment(content, Pos.CENTER);

        setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                    BorderStrokeStyle.SOLID,
                    null,
                    new BorderWidths(BORDER_WIDTH))));
    }

    /**
     * If the content Nodes child nodes already have an onMousePressedEvent set, we will invoke that event and add our
     * own. We add the functionality of bringing the node to the forefront if any part of the content is clicked.
     */
    private void setContextEventHandlers()
    {
        for (Node node : content.getChildren())
        {
            addToOnMousePressed(node, event ->
            {
                bringToFront();
                listeners.forEach(listener -> listener.nodeSelected(this));
            });
        }

        addToOnMousePressed(content, event ->
        {
            bringToFront();
            listeners.forEach(listener -> listener.nodeSelected(this));
        });
    }

    /**
     * Add on to a potentially already exsisting mouse event. This method allows you to add to a mouse event without
     * overwriting a potentially already set onMousePressed event.
     *
     * @param node The node to add the event to
     * @param newEvent the new event
     */
    private void addToOnMousePressed(Node node, EventHandler<MouseEvent> newEvent)
    {
        EventHandler<MouseEvent> existingEvent = (EventHandler<MouseEvent>) node.getOnMousePressed();
        node.setOnMousePressed(event ->
        {
            if (existingEvent != null)
            {
                existingEvent.handle(event);
            }
            newEvent.handle(event);
        });
    }

    /**
     * Configure the mouse behaviors for moving, resizing and closing nodes.
     */
    private void configureMouseResizeBehavior()
    {
        configureMouseCursorContextState();

        configureResizeBehavior();
    }

    /**
     * Set the mouse Top, Bottom, Left, Right state based on its position over the node
     */
    private void configureMouseCursorContextState()
    {
        setOnMouseMoved(event ->
        {
            if (resizable && !mouseOnCloseButton && !locked)
            {
                setMouseCursorOnEdge(event);
            }
        });
    }

    /**
     * Set the cursor based on which edge the mouse is hovered over
     *
     * @param event The mouse event to base the cursor off of
     */
    private void setMouseCursorOnEdge(MouseEvent event)
    {
        if (plusOrMinus(event.getSceneY() - parentBounds.getMinY(), (getLayoutY() + getHeight()), 10))
        {
            mouseHoverPosition = MousePosition.BOTTOM;
            setCursor(Cursor.S_RESIZE);
        }
        else if (plusOrMinus(event.getSceneX() - parentBounds.getMinX(), getLayoutX(), 10))
        {
            mouseHoverPosition = MousePosition.LEFT;
            setCursor(Cursor.W_RESIZE);
        }
        else if (plusOrMinus(event.getSceneX() - parentBounds.getMinX(), (getLayoutX() + getWidth()), 10))
        {
            mouseHoverPosition = MousePosition.RIGHT;
            setCursor(Cursor.E_RESIZE);
        }
        else
        {
            mouseHoverPosition = MousePosition.CENTER;
            setCursor(Cursor.DEFAULT);
        }
    }

    /**
     * Configure the mouse behavior when dragging the top bar of the resizable node.
     */
    private void configureDragRegionMouseBehavior()
    {
        dragRegion.setOnMousePressed(event ->
        {
            updateRelativeXY(event);

            bringToFront();
        });

        dragRegion.setOnMouseReleased(event -> updateParentBounds());

        dragRegion.setOnMouseClicked(event ->
        {
            if (event.getClickCount() == 2)
            {
                onDoubleClick();
            }
        });

        dragRegion.setOnMouseDragged(event ->
        {
            if (movable && !locked)
            {
                moveNodeWithMouse(event);
            }
        });
    }

    /**
     * Move the node within a node pane based on a mouse event
     *
     * @param event the mouse event to move the node with
     */
    private void moveNodeWithMouse(MouseEvent event)
    {
        setLayoutX(getDraggedX(event.getSceneX() - parentBounds.getMinX(),
                                getWidth(),
                                parent.getPaneWidth()));
        setLayoutY(getDraggedY(event.getSceneY() - parentBounds.getMinY(),
                                getHeight(),
                                parent.getPaneHeight()));
    }

    /**
     * Configure the mouse behavior for resizing the node
     */
    private void configureResizeBehavior()
    {
        setOnMousePressed(event ->
        {
            currentX = event.getSceneX();
            currentY = event.getSceneY();
            currentWidth = getWidth();
            currentHeight = getHeight();

            parent.setSelected(this);
        });

        setOnMouseDragged(event ->
        {
            if (resizable && !locked)
            {
                resizeWindowWithMouse(event);
            }
        });
    }

    /**
     * The logic for resizing a node based on a mouse event
     *
     * @param event mouse event to resize the node with
     */
    private void resizeWindowWithMouse(MouseEvent event)
    {
        switch (mouseHoverPosition)
        {
            case TOP:
                double newHeight = -(event.getSceneY() - currentY) + currentHeight;
                if (getMinHeight() <= newHeight)
                {
                    setLayoutY(event.getSceneY());
                }
                setNodeHeight(newHeight);
                break;
            case BOTTOM:
                setNodeHeight((event.getSceneY() - currentY) + currentHeight);
                break;
            case LEFT:
                double newWidth = -(event.getSceneX() - currentX) + currentWidth;
                if (getMinWidth() <= newWidth)
                {
                    setLayoutX(event.getSceneX() - parentBounds.getMinX());
                }
                setNodeWidth(newWidth);
                break;
            case RIGHT:
                setNodeWidth((event.getSceneX() - currentX) + currentWidth);
                break;
        }
    }

    /**
     * Update the relative X and Y
     *
     * @param event Current mouse event
     */
    private void updateRelativeXY(MouseEvent event)
    {
        updateParentBounds();
        Point2D local = dragRegion.sceneToLocal(event.getSceneX(), event.getSceneY());
        relX = local.getX();
        relY = local.getY();
    }

    /**
     * Update the parent bounds field
     */
    private void updateParentBounds()
    {
        parentBounds = parent.getRenderer().getBoundsInParent();
    }

    /**
     * Do on double click
     */
    private void onDoubleClick()
    {
        if (expandable && !locked)
        {
            expandOrRestoreNodeSize();
        }
    }

    /**
     * Either expand to max size or restore to previous size and location depending on whether the node is expanded
     */
    private void expandOrRestoreNodeSize()
    {
        if (expanded)
        {
            restoreExpandedNodeToOriginalSize();
        }
        else
        {
            saveNodePositionAndSize();

            expandNode();
        }
    }

    /**
     * Restore a node to its originial size
     */
    private void restoreExpandedNodeToOriginalSize()
    {
        setLayoutX(previousX);
        setLayoutY(previousY);
        setNodeWidth(previousWidth);
        setNodeHeight(previousHeight);
        expanded = false;
    }

    /**
     * Expand node to max size
     */
    private void expandNode()
    {
        setLayoutX(0);
        setLayoutY(0);
        setNodeHeight(parent.getPaneHeight());
        setNodeWidth(parent.getPaneWidth());
        expanded = true;
    }

    /**
     * Save the current height, width as well as the current x and y coordinates
     */
    private void saveNodePositionAndSize()
    {
        previousWidth = getWidth();
        previousHeight = getHeight();
        previousX = getLayoutX();
        previousY = getLayoutY();
    }

    /**
     * Configure the ResizableNode top bar
     */
    private void configureTopBar()
    {
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setBorder(new Border(new BorderStroke(LIGHT_GRAY,
                                                     BorderStrokeStyle.SOLID, null,
                                                     new BorderWidths(0, 0, 1, 0))));

        configureCloseButton();

        configureLockButton();

        configureDragRegion();

        configureTopBarLabel();

        topBar.getChildren().add(dragRegion);
        topBar.getChildren().add(lockButton);
        topBar.getChildren().add(closeButton);

        setTop(topBar);
    }

    private void configureLockButton()
    {
        FontIcon lockedIcon = new FontIcon(FontAwesomeSolid.LOCK);
        lockedIcon.setIconSize(ICON_SIZE);
        lockedIcon.setIconColor(Color.WHITE);

        FontIcon unlockedIcon = new FontIcon(FontAwesomeSolid.LOCK_OPEN);
        unlockedIcon.setIconSize(ICON_SIZE);
        unlockedIcon.setIconColor(Color.WHITE);

        lockButton = new StackPane(unlockedIcon);
        lockButton.setAlignment(Pos.CENTER);
        lockButton.setBackground(new Background(new BackgroundFill(DARKER_GRAY, null, null)));
        lockButton.setPrefSize(TOP_BAR_BUTTON_WIDTH, TOP_BAR_HEIGHT);
        lockButton.setMinSize(TOP_BAR_BUTTON_WIDTH, TOP_BAR_HEIGHT);
        lockButton.setMaxSize(TOP_BAR_BUTTON_WIDTH, TOP_BAR_HEIGHT);

        configureLockButtonBehavior(lockedIcon, unlockedIcon);
    }

    private void configureLockButtonBehavior(FontIcon lockedIcon, FontIcon unlockedIcon)
    {
        lockButton.setOnMouseClicked(event ->
        {
            if (locked)
            {
                locked = false;
                lockButton.getChildren().remove(lockedIcon);
                lockButton.getChildren().add(unlockedIcon);
            }
            else
            {
                locked = true;
                lockButton.getChildren().remove(unlockedIcon);
                lockButton.getChildren().add(lockedIcon);
            }
        });

        lockButton.setOnMouseEntered(event ->
        {
            lockButton.setBackground(new Background(new BackgroundFill(LIGHT_GRAY, null, null)));
        });
        lockButton.setOnMouseExited(event ->
        {
            lockButton.setBackground(new Background(new BackgroundFill(DARKER_GRAY, null, null)));
        });
    }

    /**
     * Configure the top bar drag region label. Default is blank.
     */
    private void configureTopBarLabel()
    {
        topBarLabel = new Label("");
        topBarLabel.setFont(Font.font(10));
        dragRegion.getChildren().add(topBarLabel);
    }

    /**
     * Configure the top bar drag region
     */
    private void configureDragRegion()
    {
        dragRegion = new StackPane();
        dragRegion.setAlignment(Pos.CENTER);
        dragRegion.setPrefHeight(TOP_BAR_HEIGHT);
        dragRegion.setMinHeight(TOP_BAR_HEIGHT);
        dragRegion.setMaxHeight(TOP_BAR_HEIGHT);
        dragRegion.setBackground(new Background(new BackgroundFill(DARKER_GRAY, null, null)));

        configureDragRegionMouseBehavior();
    }

    /**
     * Configure the close button for this Node
     */
    private void configureCloseButton()
    {
        FontIcon icon = configureCloseIcon();

        closeButton = new StackPane();
        closeButton.setAlignment(Pos.CENTER);
        closeButton.getChildren().add(icon);
        closeButton.setBackground(new Background(new BackgroundFill(DARKER_GRAY, null, null)));

        closeButton.setMinHeight(TOP_BAR_HEIGHT);
        closeButton.setMinWidth(TOP_BAR_BUTTON_WIDTH);
        closeButton.setMaxHeight(TOP_BAR_HEIGHT);
        closeButton.setMaxWidth(TOP_BAR_BUTTON_WIDTH);

        configureCloseButtonBehavior();
    }

    /**
     * Configure the icon displayed on the close button
     *
     * @return the close button icon
     */
    private FontIcon configureCloseIcon()
    {
        FontIcon icon = new FontIcon(FontAwesomeSolid.TIMES);
        icon.setIconColor(Color.WHITE);
        icon.setIconSize(ICON_SIZE);
        return icon;
    }

    /**
     * Return true if input if plus or minus the target
     *
     * @param input Input value
     * @param target target value
     * @param plusOrMinus true if input is plus or minus the target
     * @return Return true if input if plus or minus the target
     */
    private boolean plusOrMinus(double input, double target, int plusOrMinus)
    {
        return target - plusOrMinus < input && input < target + plusOrMinus;
    }

    /**
     * Set the width of the resizeable node
     *
     * @param width new width
     */
    private void setNodeWidth(double width)
    {
        dragRegion.setPrefWidth(width + (INSET_WIDTH * 2) - (TOP_BAR_BUTTON_WIDTH * 2));
        setWidth(width);
        setPrefWidth(width);

    }

    /**
     * Set the height of the resizable node
     *
     * @param height new height
     */
    private void setNodeHeight(double height)
    {
        setHeight(height + ADDITIONAL_STATIC_HEIGHT);
        setPrefHeight(height + ADDITIONAL_STATIC_HEIGHT);
    }

    /**
     * Configure top bar button behavior
     */
    private void configureCloseButtonBehavior()
    {
        closeButton.setOnMouseClicked(event ->
        {
            if (showPopUpOnClose)
            {
                displayPopUp();
            }
            else
            {
                close();
            }
        });

        closeButton.setOnMouseEntered(event ->
        {
            mouseOnCloseButton = true;
            closeButton.setBackground(new Background(new BackgroundFill(CLOSE_BUTTON_HOVER_COLOR, null, null)));
        });

        closeButton.setOnMouseExited(event ->
        {
            mouseOnCloseButton = false;
            closeButton.setBackground(new Background(new BackgroundFill(DARKER_GRAY, null, null)));
        });
    }

    /**
     * Close this node and remove it from the Node Pane
     */
    public void close()
    {
        parent.removeNode(this);
    }

    /**
     * Display a popup to prevent a user from accidentally closing a window
     */
    private void displayPopUp()
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        Label prompt = new Label("Do you wont to close this window?");

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> stage.close());
        cancelButton.setPrefWidth(80);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event ->
        {
            stage.close();
            close();
        });
        closeButton.setPrefWidth(80);

        hBox.getChildren().addAll(List.of(cancelButton, closeButton));

        vBox.getChildren().add(prompt);
        vBox.getChildren().add(hBox);

        Scene scene = new Scene(vBox, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Get the new layout x coordinate while dragging the node. This method ensures that the node can't be dragged
     * outside of the current window.
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
     * outside of the current window.
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

    public void addListener(NodeListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(NodeListener listener)
    {
        listeners.remove(listener);
    }

    /**
     * Simple enum for keeping track of the mouse position over the node
     */
    private enum MousePosition
    {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        CENTER;
    }

    public interface NodeListener
    {
        /**
         * A method called when this node is selected
         * @param node the node that is selected
         */
        void nodeSelected(ResizableNode node);
    }
}
