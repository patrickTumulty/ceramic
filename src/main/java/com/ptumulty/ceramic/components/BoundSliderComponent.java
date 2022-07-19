package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.BoundIntegerModel;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BoundSliderComponent extends UIComponent<BoundIntegerModel, Pane>
{
    private Slider slider;
    private LabelComponent numberLabelComponent;
    private Orientation orientation;
    private int width;
    private int labelWidth;
    private int nodeSpacing;

    public BoundSliderComponent(BoundIntegerModel model)
    {
        super(model);
    }

    public LabelComponent getLabelComponent()
    {
        return numberLabelComponent;
    }

    public Slider getSlider()
    {
        return slider;
    }

    public void setOrientation(Orientation orientation)
    {
        if (this.orientation != orientation)
        {
            this.orientation = orientation;
            Pane layoutRenderer = orientation == Orientation.HORIZONTAL ? new HBox() : new VBox();
            layoutRenderer.getChildren().add(slider);
            layoutRenderer.getChildren().add(numberLabelComponent.getRenderer());

            renderer = layoutRenderer;
            setAlignment(orientation == Orientation.HORIZONTAL ? Pos.CENTER_LEFT : Pos.TOP_CENTER);
            setSpacing(nodeSpacing);
        }
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

    public void setAlignment(Pos position)
    {
        if (renderer instanceof HBox)
        {
            ((HBox) renderer).setAlignment(position);
        }
        else if (renderer instanceof VBox)
        {
            ((VBox) renderer).setAlignment(position);
        }
    }

    public void setSpacing(int spacing)
    {
        this.nodeSpacing = spacing;
        if (renderer instanceof HBox)
        {
            ((HBox) renderer).setSpacing(this.nodeSpacing);
        }
        else if (renderer instanceof VBox)
        {
            ((VBox) renderer).setSpacing(this.nodeSpacing);
        }
    }

    @Override
    protected void updateModel()
    {
        model.setValue((int) slider.getValue());
    }

    @Override
    protected void initializeRenderer()
    {
        nodeSpacing = 0;
        labelWidth = 30;

        slider = new Slider();
        slider.setMajorTickUnit(1);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> updateModel());

        numberLabelComponent = new LabelComponent();

        setOrientation(Orientation.HORIZONTAL);
        setWidth(200);
    }

    @Override
    public void attachModel(BoundIntegerModel model)
    {
        super.attachModel(model);

        valueChanged();
        slider.setMax(model.getUpperBounds());
        slider.setMin(model.getLowerBounds());
        numberLabelComponent.attachModel(model);
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
