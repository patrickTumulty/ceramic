package com.ptumulty.ceramic_ui_api.components;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public class ComponentSettingView
{
    private final ScrollPane scrollPane;
    private final GridPane gridPane;

    public ComponentSettingView(List<UIComponent<?, ?>> components)
    {
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.minWidthProperty().bind(scrollPane.widthProperty());
        gridPane.maxWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setContent(gridPane);

        configureColumnAndRowConstraints();

        addComponents(components);
    }

    private void addComponents(List<UIComponent<?, ?>> components)
    {
        int row = 0;

        for (var component : components)
        {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setSpacing(10);

            hBox.getChildren().add(new Indicator(component.modifiedProperty()));

            Label settingLabel = new Label(component.getLabel());
            hBox.getChildren().add(settingLabel);

            settingLabel.setAlignment(Pos.CENTER_RIGHT);
            settingLabel.setTextAlignment(TextAlignment.RIGHT);
            GridPane.setMargin(hBox, new Insets(0, 10, 0, 0));

            gridPane.add(hBox, 0, row);

            Label chevron = new Label();
            var icon = new FontIcon(FontAwesomeSolid.CHEVRON_DOWN);
            icon.getStyleClass().add("control-icon");
            chevron.setGraphic(icon);

            addContextMenu(component, chevron);

            gridPane.add(chevron, 1, row);

            gridPane.add(component.getRenderer(), 2, row);
            GridPane.setMargin(component.getRenderer(), new Insets(0, 0, 0, 10));

            row++;
        }
    }

    private static void addContextMenu(UIComponent<?, ?> component, Label chevron)
    {
        ContextMenu contextMenu = new ContextMenu();
        chevron.setContextMenu(contextMenu);

        MenuItem menuItem = new MenuItem("Restore Default");
        menuItem.setOnAction((event1) -> component.restoreDefault());

        contextMenu.getItems().add(menuItem);
    }

    private void configureColumnAndRowConstraints()
    {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(40);

        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.minWidthProperty().bind(gridPane.widthProperty().divide(2).subtract(20));
        leftColumn.setHalignment(HPos.RIGHT);

        ColumnConstraints middleColumn = new ColumnConstraints();
        middleColumn.minWidthProperty().set(20);
        middleColumn.setHalignment(HPos.CENTER);

        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.minWidthProperty().bind(gridPane.widthProperty().divide(2).subtract(20));
        rightColumn.setHalignment(HPos.LEFT);

        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getColumnConstraints().addAll(leftColumn, middleColumn, rightColumn);
    }

    public ScrollPane getRenderer()
    {
        return scrollPane;
    }

    private static class Indicator extends Circle
    {
        Indicator(BooleanProperty active)
        {
            setStyle("-fx-fill: accent-1");
            setRadius(5);

            setVisible(active.getValue());
            active.addListener((observable, oldValue, newValue) -> setVisible(active.getValue()));
        }
    }
}
