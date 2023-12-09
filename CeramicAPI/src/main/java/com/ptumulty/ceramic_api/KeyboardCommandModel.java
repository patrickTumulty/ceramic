package com.ptumulty.ceramic_api;

import com.ptumulty.ceramic_ui_api.KeyboardCommandManager;

import java.security.Key;
import java.util.concurrent.CompletableFuture;

public class KeyboardCommandModel extends ValueModel<KeyboardCommand>
{
    private final KeyboardCommandManager commandManager;
    private final Runnable action;

    public KeyboardCommandModel(KeyboardCommand value, Runnable action)
    {
        super(value, null);

        commandManager = KeyboardCommandManager.get();

        this.action = action;

        registerKeyCommand(null, value);

        addListener(this::registerKeyCommand);
    }

    private void registerKeyCommand(KeyboardCommand previous, KeyboardCommand current)
    {
        commandManager.unregisterKeyboardCommandAction(previous);
        if (!value.equals(KeyboardCommand.UNSET))
        {
            commandManager.registerKeyboardCommandAction(value, action);
        }
    }

    public CompletableFuture<Void> updateValueWithNextKeyCommand()
    {
        return commandManager.getNextKeyboardCommand().thenAccept(this::setValue);
    }
}
