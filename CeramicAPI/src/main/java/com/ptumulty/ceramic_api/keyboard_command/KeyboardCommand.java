package com.ptumulty.ceramic_api.keyboard_command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public record KeyboardCommand(String keyCodeName, Optional<KeyboardModifier> modifier)
{
    private static final String SAVE_STRING_VALUE_SEPARATOR = ":";
    public static KeyboardCommand UNSET = new KeyboardCommand("UNSET", Optional.empty());

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof KeyboardCommand keyboardCommand)
        {
            return modifier.map(keyboardModifier -> keyCodeName.equals(keyboardCommand.keyCodeName()) &&
                                                    keyboardCommand.modifier.isPresent() &&
                                                    keyboardCommand.modifier.get() == keyboardModifier)
                           .orElseGet(() -> keyCodeName.equals(keyboardCommand.keyCodeName()) &&
                                            keyboardCommand.modifier.isEmpty());
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return modifier.map(keyboardModifier -> Objects.hash(keyCodeName, keyboardModifier))
                       .orElseGet(() -> Objects.hash(keyCodeName));
    }

    public String toSaveString()
    {
        return modifier.map(keyboardModifier -> String.format("%s%s%d",
                                                              keyCodeName,
                                                              SAVE_STRING_VALUE_SEPARATOR,
                                                              keyboardModifier.getId()))
                       .orElse(keyCodeName());
    }

    public Optional<KeyboardCommand> fromSaveString(String saveKey)
    {
        if (saveKey.contains(SAVE_STRING_VALUE_SEPARATOR))
        {
            String[] elements = saveKey.split(SAVE_STRING_VALUE_SEPARATOR);
            var modifier = KeyboardModifier.getFromId(Integer.parseInt(elements[1]));
            if (modifier.isEmpty())
            {
                return Optional.empty();
            }
            return Optional.of(new KeyboardCommand(elements[0], modifier));
        }
        else
        {
            return Optional.of(new KeyboardCommand(saveKey, Optional.empty()));
        }
    }

    @Override
    public String toString()
    {
        if (modifier.isPresent())
        {
            return String.format("%s + %s", modifier.get().getName(), keyCodeName);
        }
        else
        {
            return keyCodeName;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum KeyboardModifier
    {
        ALT(0, "Alt"),
        CONTROL(1, "Cntrl"),
        META(2, "Meta"),
        SHIFT(3, "Shift");

        private final int id;
        private final String name;

        public static Optional<KeyboardModifier> getFromId(int id)
        {
            return Arrays.stream(values()).filter(value -> value.getId() == id).findFirst();
        }
    }
}
