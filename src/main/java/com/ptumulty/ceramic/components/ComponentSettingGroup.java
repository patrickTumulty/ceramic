package com.ptumulty.ceramic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


import java.util.List;

public class ComponentSettingGroup
{
    private final BorderPane borderPane;
    private final int LABEL_SPACING = 100;

    public ComponentSettingGroup(String label, List<UIComponent<?, ?>> components)
    {
        borderPane = new BorderPane();

        Label groupLabel = new Label(label);
        borderPane.setTop(groupLabel);
        groupLabel.setStyle("-fx-font-size: larger;");
        BorderPane.setAlignment(groupLabel, Pos.CENTER_LEFT);
        BorderPane.setMargin(groupLabel, new Insets(0, 0, 10, 10));

        VBox vBox = new VBox();
        vBox.minWidthProperty().bind(borderPane.widthProperty());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        for (UIComponent<?, ?> component : components)
        {
            if (component.getLabel().isPresent())
            {
                HBox lineItem = new HBox();
                lineItem.setSpacing(10);
                lineItem.setAlignment(Pos.CENTER_LEFT);
                Label settingLabel = new Label(component.getLabel().get());
                settingLabel.setAlignment(Pos.CENTER_RIGHT);
                settingLabel.setTextAlignment(TextAlignment.RIGHT);
                settingLabel.setMinWidth(LABEL_SPACING);
                lineItem.getChildren().add(settingLabel);
                lineItem.getChildren().add(component.getRenderer());
                vBox.getChildren().add(lineItem);
            }
            else
            {
                vBox.getChildren().add(component.getRenderer());
                VBox.setMargin(component.getRenderer(), new Insets(0, 0, 0, LABEL_SPACING));
            }
        }
        borderPane.setCenter(vBox);
        BorderPane.setAlignment(vBox, Pos.TOP_CENTER);
        BorderPane.setMargin(vBox, new Insets(0, 0, 10, 0));
    }

    public Pane getRenderer()
    {
        return borderPane;
    }
}
