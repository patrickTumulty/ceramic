package com.ptumulty.ceramic_api;

import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommand;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface ValueModel<T> extends Defaultable<T>
{
    Optional<SaveStringConverter> getSaveStringConverter();

    void setSaveStringConverter(@Nullable SaveStringConverter saveStringConverter);

    /**
     * Set this model to always notify a change in the value. This means
     * listeners will be notified even if values are set with the same value
     *
     * @param alwaysNotifyChange always notify change
     */
    void setAlwaysNotifyChange(boolean alwaysNotifyChange);

    void setValueModifier(Function<T, T> modifier);

    void setValue(T value);

    T get();

    void addListener(ValueListener<T> listener);

    void removeListener(ValueListener<T> listener);

    /**
     * Simple listener for listening to value changes. Only fires when the new value is different from the
     * current value.
     */
    interface ValueListener<T>
    {
        /**
         * Method for handling when the value has changed
         */
        void valueChanged(T previousValue, T newValue);
    }

    interface SaveStringConverter
    {
        void fromSaveString(String saveString);

        String toSaveString();
    }

    interface IntegerModel extends ValueModel<Integer>
    {
    }

    interface FloatModel extends ValueModel<Float>
    {
    }

    interface DoubleModel extends ValueModel<Double>
    {
    }

    interface BooleanModel extends ValueModel<Boolean>
    {
    }

    interface ListModel<T> extends ValueModel<List<T>>
    {
    }

    interface StringModel extends ValueModel<String>
    {
    }

    interface ChoiceModel<T> extends ValueModel<T>
    {
        void setValueIndex(int index);

        List<T> getChoiceItems();

        void addChoice(T item);

        void removeChoice(T item);
    }

    interface BoundNumberModel<T extends Number> extends ValueModel<T>
    {
        T getUpperBounds();

        T getLowerBounds();
    }

    interface BoundIntegerModel extends BoundNumberModel<Integer>, IntegerModel
    {
    }

    interface BoundDoubleModel extends BoundNumberModel<Double>, DoubleModel
    {
    }

    interface KeyboardCommandModel extends ValueModel<KeyboardCommand>
    {
        CompletableFuture<Void> updateValueWithNextKeyCommand();
    }
}
