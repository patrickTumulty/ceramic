package com.ptumulty.ceramic_ui_api.ceramicfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class CancelableActionPopupWindow
{
    private final String actionLabel;
    private final Action<ActionCanceledException> actionEvent;
    private final Node contents;
    private final BorderPane contentContainer;
    private final Label errorLabel;
    private Button actionButton;
    private Scene scene;
    private Optional<String> stylesheet;
    private Optional<String> windowTitle;
    private Modality applicationModality;
    private Stage stage;

    public CancelableActionPopupWindow(Node contents, String actionLabel, Action<ActionCanceledException> actionEvent)
    {
        this.contents = contents;
        this.actionLabel = actionLabel;
        this.actionEvent = actionEvent;
        contentContainer = new BorderPane();

        errorLabel = new Label();
        errorLabel.setVisible(false);
        errorLabel.setStyle("-fx-text-fill: red");
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
        VBox lowerRegion = new VBox();
        lowerRegion.setSpacing(10);

        lowerRegion.setAlignment(Pos.CENTER_RIGHT);
        lowerRegion.setPadding(new Insets(0,10,10,10));

        HBox buttonRegion = new HBox();
        buttonRegion.setAlignment(Pos.CENTER_RIGHT);
        buttonRegion.setSpacing(10);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> stage.close());

        actionButton = new Button(actionLabel);
        actionButton.setOnAction(event ->
        {
            try
            {
                actionEvent.doAction();
            }
            catch (ActionCanceledException ex)
            {
                errorLabel.setText(ex.getMessage());
                errorLabel.setVisible(true);
                return;
            }
            stage.close();
        });

        buttonRegion.getChildren().addAll(List.of(cancelButton, actionButton));

        lowerRegion.getChildren().addAll(List.of(buttonRegion, errorLabel));

        contentContainer.setCenter(contents);
        contentContainer.setBottom(lowerRegion);
    }

    public void show()
    {
        scene = new Scene(contentContainer);
        stylesheet.ifPresent(s -> scene.getStylesheets().add(s));

        configureStageAndShow();
    }

    public void show(int width, int height)
    {
        scene = new Scene(contentContainer, width, height);
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

    public static class ActionCanceledException extends Exception
    {
        public ActionCanceledException(String message)
        {
            super(message);
        }
    }
}
