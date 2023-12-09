package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BoundIntegerModel;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class BoundSliderComponent extends UIComponent<Integer, BoundIntegerModel, HBox>
{
    private static final int DEFAULT_LABEL_WIDTH = 70;
    private Slider slider;
    private IntegerInputComponent numberEntryComponent;

    public BoundSliderComponent(BoundIntegerModel model)
    {
        super(model);
    }

    public BoundSliderComponent(String label, BoundIntegerModel model)
    {
        super(label, model);
    }

    @Override
    protected void updateModel()
    {
        if (model != null)
        {
            model.setValue((int) slider.getValue());
        }
    }

    @Override
    protected void initializeRenderer()
    {
        slider = new Slider();
        slider.setMajorTickUnit(1);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> updateModel());

        numberEntryComponent = new IntegerInputComponent();
        numberEntryComponent.getRenderer().setMinWidth(DEFAULT_LABEL_WIDTH);
        numberEntryComponent.getRenderer().setMaxWidth(DEFAULT_LABEL_WIDTH);
        numberEntryComponent.getRenderer().setPrefWidth(DEFAULT_LABEL_WIDTH);
        numberEntryComponent.getRenderer().setAlignment(Pos.CENTER);

        renderer = new HBox();
        renderer.setSpacing(5);
        renderer.setAlignment(Pos.CENTER);
        renderer.setPrefWidth(500);

        renderer.getChildren().add(slider);
        renderer.getChildren().add(numberEntryComponent.getRenderer());
    }

    @Override
    public void attachModel(BoundIntegerModel model)
    {
        super.attachModel(model);

        valueChanged(null, model.get());
        slider.setMax(model.getUpperBounds());
        slider.setMin(model.getLowerBounds());
        numberEntryComponent.attachModel(model);
    }

    @Override
    public void valueChanged(Integer prev, Integer curr)
    {
        if (model == null)
        {
            return;
        }

        if (slider.getValue() != model.get())
        {
            slider.setValue(model.get());
        }
    }
}
