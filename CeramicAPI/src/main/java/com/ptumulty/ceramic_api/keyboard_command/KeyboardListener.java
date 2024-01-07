package com.ptumulty.ceramic_api.keyboard_command;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.ptumulty.ceramic_api.keyboard_command.KeyboardCommand.KeyboardModifier;
import com.ptumulty.ceramic_api.utils.JavaUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class KeyboardListener implements NativeKeyListener
{
    private final Set<Integer> modifiersPressed;
    private final Set<Integer> modifierKeyCodes;
    private final Consumer<KeyboardCommand> commandConsumer;

    public KeyboardListener(Consumer<KeyboardCommand> commandConsumer)
    {
        this.commandConsumer = commandConsumer;
        modifiersPressed = ConcurrentHashMap.newKeySet();
        modifierKeyCodes = new HashSet<>(List.of(NativeKeyEvent.VC_SHIFT,
                                                 NativeKeyEvent.VC_CONTROL,
                                                 NativeKeyEvent.VC_ALT,    // Option or Alt Key
                                                 NativeKeyEvent.VC_META)); // Windows or Command Key

        if (JavaUtils.isDebuggerPresent())
        {
            System.out.println("Skipping native hook listener while debugger is present");
            return;
        }

        try
        {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ignored)
        {
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent)
    {
        if (modifierKeyCodes.contains(nativeEvent.getKeyCode()))
        {
            modifiersPressed.add(nativeEvent.getKeyCode());
            return;
        }

        KeyboardModifier modifier = null;

        if (altPressed())
        {
            modifier = KeyboardModifier.ALT;
        } else if (controlPressed())
        {
            modifier = KeyboardModifier.CONTROL;
        } else if (metaPressed())
        {
            modifier = KeyboardModifier.META;
        } else if (shiftPressed())
        {
            modifier = KeyboardModifier.SHIFT;
        }

        commandConsumer.accept(new KeyboardCommand(NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()),
                                                   Optional.ofNullable(modifier)));
    }

    private boolean controlPressed()
    {
        return modifiersPressed.contains(NativeKeyEvent.VC_CONTROL);
    }

    private boolean shiftPressed()
    {
        return modifiersPressed.contains(NativeKeyEvent.VC_SHIFT);
    }

    private boolean altPressed()
    {
        return modifiersPressed.contains(NativeKeyEvent.VC_ALT);
    }

    private boolean metaPressed()
    {
        return modifiersPressed.contains(NativeKeyEvent.VC_META);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent)
    {
        modifiersPressed.remove(nativeEvent.getKeyCode());
    }

    public void dispose()
    {
        GlobalScreen.removeNativeKeyListener(this);
        try
        {
            GlobalScreen.unregisterNativeHook();
        }
        catch (NativeHookException ignored)
        {
        }
    }
}
