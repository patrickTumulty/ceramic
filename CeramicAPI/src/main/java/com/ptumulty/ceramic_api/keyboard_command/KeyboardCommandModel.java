package com.ptumulty.ceramic_api.keyboard_command;

import com.ptumulty.ceramic_api.ValueModel;

import java.util.concurrent.CompletableFuture;

public class KeyboardCommandModel extends ValueModel<KeyboardCommand>
{
    private final KeyboardCommandManager commandManager;
    private final Runnable action;

    public KeyboardCommandModel(KeyboardCommand value, Runnable action)
    {
        super(value);

        commandManager = KeyboardCommandManager.get();

        this.action = action;

        registerKeyCommand(null, value);

        addListener(this::registerKeyCommand);

        setSaveStringConverter(new SaveStringConverter()
        {
            @Override
            public void fromSaveString(String saveString)
            {
                setValue(KeyboardCommand.fromSaveString(saveString)
                                        .orElse(KeyboardCommand.UNSET));
            }

            @Override
            public String toSaveString()
            {
                return get().toSaveString();
            }
        });
    }

    private void registerKeyCommand(KeyboardCommand previous, KeyboardCommand current)
    {
        commandManager.unregisterKeyboardCommandAction(previous);
        if (current != null && !current.equals(KeyboardCommand.UNSET))
        {
            commandManager.registerKeyboardCommandAction(value, action);
        }
    }

    public CompletableFuture<Void> updateValueWithNextKeyCommand()
    {
        return commandManager.getNextKeyboardCommand().thenAccept(this::setValue);
    }
}
