package com.ptumulty.ceramic.components;

import com.ptumulty.ceramic.models.BoundIntegerModel;
import javafx.scene.control.SpinnerValueFactory;

public class BoundIntegerSpinnerComponent extends IntegerSpinnerComponent
{
    /**
     * Constructor
     *
     * @param integerModel bound integer model
     */
    public BoundIntegerSpinnerComponent(BoundIntegerModel integerModel)
    {
        super(integerModel);

        renderer.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(integerModel.getLowerBounds(),
                                                                   integerModel.getUpperBounds(),
                                                                   integerModel.get()));
    }
}
