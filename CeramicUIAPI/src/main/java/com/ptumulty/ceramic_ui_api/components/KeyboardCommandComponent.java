package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.KeyboardCommandModel;
import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommand;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class KeyboardCommandComponent extends UIComponent<KeyboardCommand, KeyboardCommandModel, GridPane>
{
    private BooleanProperty disabledButtonProperty;
    private TextField textField;
    private FontIcon keyboardIcon;
    private FontIcon clearIcon;

    public KeyboardCommandComponent(KeyboardCommandModel model)
    {
        super(model);
    }

    public KeyboardCommandComponent(String label, @Nullable KeyboardCommandModel model)
    {
        super(label, model);
    }

    @Override
    public void valueChanged(KeyboardCommand previousValue, KeyboardCommand newValue)
    {
        if (model != null)
        {
            Platform.runLater(() -> textField.setText(model.get().toString()));
        }
    }

    @Override
    protected void updateModel()
    {

    }

    public FontIcon getKeyboardIcon()
    {
        return keyboardIcon;
    }

    public FontIcon getTrashIcon()
    {
        return clearIcon;
    }

    @Override
    protected void initializeRenderer()
    {
        disabledButtonProperty = new SimpleBooleanProperty(false);

        renderer = new GridPane();
        renderer.setHgap(5);
        renderer.setAlignment(Pos.CENTER);

        textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(false);
        renderer.add(textField, 0, 0);

        Button keyboardButton = new Button();
        keyboardButton.disableProperty().bind(disabledButtonProperty);
        keyboardIcon = new FontIcon(FontAwesomeSolid.KEYBOARD);
        keyboardIcon.setIconColor(Color.WHITE);
        keyboardButton.setGraphic(keyboardIcon);
        keyboardButton.setOnAction(event -> {
            if (model != null)
            {
                disabledButtonProperty.set(true);
                model.updateValueWithNextKeyCommand()
                     .thenAccept(result -> Platform.runLater(() -> disabledButtonProperty.set(false)));
            }
        });
        renderer.add(keyboardButton, 1, 0);

        clearIcon = new FontIcon(FontAwesomeSolid.TRASH_ALT);
        clearIcon.setIconColor(Color.WHITE);
        Button clearButton = new Button();
        clearButton.disableProperty().bind(disabledButtonProperty);
        clearButton.setGraphic(clearIcon);
        clearButton.setOnAction(event -> {
            if (model != null)
            {
                model.setValue(KeyboardCommand.UNSET);
            }
        });
        renderer.add(clearButton, 2, 0);
    }
}
