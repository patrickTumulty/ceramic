package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_api.ValueModel.BoundIntegerModel;
import javafx.scene.control.SpinnerValueFactory;
import org.jetbrains.annotations.Nullable;

public class BoundIntegerSpinnerComponent extends IntegerSpinnerComponent
{
    /**
     * Constructor
     *
     * @param model bound integer model
     */
    public BoundIntegerSpinnerComponent(BoundIntegerModel model)
    {
        this(null, model);
    }

    /**
     * Constructor
     *
     * @param label component label
     * @param model integer spinner model
     */
    public BoundIntegerSpinnerComponent(@Nullable String label, BoundIntegerModel model)
    {
        super(label, model);

        renderer.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(model.getLowerBounds(),
                                                                                    model.getUpperBounds(),
                                                                                    model.get()));
    }
}
