package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.BooleanModel;
import javafx.application.Platform;
import javafx.scene.control.ToggleButton;
import org.jetbrains.annotations.Nullable;
import org.kordamp.ikonli.javafx.FontIcon;

public class ToggleButtonComponent extends UIComponent<Boolean, BooleanModel, ToggleButton>
{
    public ToggleButtonComponent(BooleanModel model)
    {
        super(model);
    }

    public ToggleButtonComponent(FontIcon selectedIcon, FontIcon deselectedIcon, @Nullable BooleanModel model)
    {
        super(model);

        renderer.selectedProperty()
                .addListener((observable, oldValue, newValue) -> Platform.runLater(() -> renderer.setGraphic(newValue ?
                                                                                                             selectedIcon :
                                                                                                             deselectedIcon)));
        if (this.model != null)
        {
            renderer.setGraphic(this.model.get() ? selectedIcon : deselectedIcon);
        }
        else
        {
            renderer.setGraphic(selectedIcon);
        }
    }

    @Override
    public void valueChanged(Boolean prev, Boolean curr)
    {
        renderer.selectedProperty().set(curr);
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue(renderer.isSelected());
        }
    }

    @Override
    protected void initializeRenderer()
    {
        renderer = new ToggleButton();
        renderer.selectedProperty().addListener((observable, oldValue, newValue) -> updateModel());
    }
}
