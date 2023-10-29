package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.BoundIntegerModel;
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
