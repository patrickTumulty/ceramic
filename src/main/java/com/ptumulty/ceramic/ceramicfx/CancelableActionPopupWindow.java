package com.ptumulty.ceramic.ceramicfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class CancelableActionPopupWindow
{
    private final String actionLabel;
    private final EventHandler<ActionEvent> actionEvent;
    private final Node contents;
    private Button actionButton;
    private Scene scene;
    private final BorderPane contentContainer;
    private Optional<String> stylesheet;
    private Optional<String> windowTitle;
    private Modality applicationModality;
    private Stage stage;

    public CancelableActionPopupWindow(Node contents, String actionLabel, EventHandler<ActionEvent> actionEvent)
    {
        this.contents = contents;
        this.actionLabel = actionLabel;
        this.actionEvent = actionEvent;
        contentContainer = new BorderPane();
        stylesheet = Optional.empty();
        windowTitle = Optional.empty();
        applicationModality = Modality.APPLICATION_MODAL;

        configureVisuals();
    }

    public void setStylesheet(String stylesheet)
    {
        this.stylesheet = Optional.of(stylesheet);
    }

    public void setWindowTitle(String title)
    {
        windowTitle = Optional.of(title);
    }

    public void setActionButtonID(String id)
    {
        actionButton.setId(id);
    }

    public void setApplicationModality(Modality applicationModality)
    {
        this.applicationModality = applicationModality;
    }

    private void configureVisuals()
    {
        HBox buttonRegion = new HBox();
        buttonRegion.setAlignment(Pos.CENTER_RIGHT);
        buttonRegion.setSpacing(10);
        buttonRegion.setPadding(new Insets(10));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> stage.close());

        actionButton = new Button(actionLabel);
        actionButton.setOnAction(event ->
        {
            actionEvent.handle(event);
            stage.close();
        });

        buttonRegion.getChildren().addAll(List.of(cancelButton, actionButton));

        contentContainer.setCenter(contents);
        contentContainer.setBottom(buttonRegion);
    }

    public void show()
    {
        scene = new Scene(contentContainer);
        stylesheet.ifPresent(s -> scene.getStylesheets().add(s));

        configureStageAndShow();
    }

    private void configureStageAndShow()
    {
        stage = new Stage();
        stage.initModality(applicationModality);
        windowTitle.ifPresent(s -> stage.setTitle(s));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void show(int width, int height)
    {
        scene = new Scene(contentContainer, width, height);
        stylesheet.ifPresent(s -> scene.getStylesheets().add(s));

        configureStageAndShow();
    }
}
