package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.BoundIntegerModel;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoundSliderComponent extends UIComponent<BoundIntegerModel, Pane>
{
    private Slider slider;
    private IntegerInputComponent numberEntryComponent;
    private int width;
    private int labelWidth;

    public BoundSliderComponent(BoundIntegerModel model)
    {
        super(model);
    }

    public void setLabelWidth(int width)
    {
        this.labelWidth = width;
        setWidth(this.width);
    }

    public void setWidth(int width)
    {
        this.width = width;
        slider.setPrefWidth(width - labelWidth);
        renderer.setMaxWidth(width);
        renderer.setPrefWidth(width);
    }

    @Override
    protected void updateModel()
    {
        model.setValue((int) slider.getValue());
    }

    @Override
    protected void initializeRenderer()
    {
        labelWidth = 70;

        slider = new Slider();
        slider.setMajorTickUnit(1);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> updateModel());

        numberEntryComponent = new IntegerInputComponent();
        numberEntryComponent.getRenderer().setPrefWidth(labelWidth);
        numberEntryComponent.getRenderer().setAlignment(Pos.CENTER);

        renderer = new AnchorPane();
        StackPane sliderPane = new StackPane(slider);
        sliderPane.prefHeightProperty().bind(renderer.heightProperty());
        StackPane.setAlignment(slider, Pos.CENTER);
        renderer.getChildren().add(sliderPane);
        renderer.getChildren().add(numberEntryComponent.getRenderer());
        AnchorPane.setLeftAnchor(sliderPane, (double) 0);
        AnchorPane.setRightAnchor(numberEntryComponent.getRenderer(), (double) 0);

        slider.prefWidthProperty().bind(renderer.widthProperty().subtract(numberEntryComponent.getRenderer().widthProperty().add(5)));
    }

    @Override
    public void attachModel(BoundIntegerModel model)
    {
        super.attachModel(model);

        valueChanged();
        slider.setMax(model.getUpperBounds());
        slider.setMin(model.getLowerBounds());
        numberEntryComponent.attachModel(model);
    }

    @Override
    public void valueChanged()
    {
        if (slider.getValue() != model.get())
        {
            slider.setValue(model.get());
        }
    }
}
