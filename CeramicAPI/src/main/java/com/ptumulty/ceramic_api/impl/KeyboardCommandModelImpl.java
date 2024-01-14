package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.KeyboardCommandModel;
import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommand;
import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommandManager;

import java.util.concurrent.CompletableFuture;

public class KeyboardCommandModelImpl extends DefaultValueModel<KeyboardCommand> implements KeyboardCommandModel
{
    private final KeyboardCommandManager commandManager;
    private final Runnable action;

    KeyboardCommandModelImpl(KeyboardCommand value, Runnable action)
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

    @Override
    public CompletableFuture<Void> updateValueWithNextKeyCommand()
    {
        return commandManager.getNextKeyboardCommand().thenAccept(this::setValue);
    }
}
