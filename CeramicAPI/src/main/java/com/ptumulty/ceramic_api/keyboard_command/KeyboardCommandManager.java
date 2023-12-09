package com.ptumulty.ceramic_api.keyboard_command;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class KeyboardCommandManager
{
    private static KeyboardCommandManager instance;

    private final ReentrantLock lock;
    private @Nullable CompletableFuture<KeyboardCommand> keyboardCommandFuture;
    private final ConcurrentHashMap<KeyboardCommand, Runnable> keyboardCommandActionsMap;
    private final AtomicBoolean keyCommandExecutionThreadRunning;
    private final Thread keyCommandExecutionThread;
    private final BlockingQueue<KeyboardCommand> keyboardCommandQueue;

    private KeyboardCommandManager()
    {
        lock = new ReentrantLock();
        keyboardCommandActionsMap = new ConcurrentHashMap<>();
        keyCommandExecutionThreadRunning = new AtomicBoolean(false);
        keyboardCommandQueue = new LinkedBlockingQueue<>();

        keyCommandExecutionThread = new Thread(this::keyCommandExecutionThreadWorker);
        keyCommandExecutionThread.setDaemon(true);
        keyCommandExecutionThread.setName("KeyCmdThread");
    }

    public synchronized static KeyboardCommandManager get()
    {
        if (instance == null)
        {
            instance = new KeyboardCommandManager();
        }
        return instance;
    }

    private void keyCommandExecutionThreadWorker()
    {
        keyCommandExecutionThreadRunning.set(true);

        while (keyCommandExecutionThreadRunning.get())
        {
            try
            {
                KeyboardCommand command = keyboardCommandQueue.take();
                var action = keyboardCommandActionsMap.get(command);
                if (action != null)
                {
                    action.run();
                }
            }
            catch (InterruptedException ignored)
            {
            }
        }
    }

    public CompletableFuture<KeyboardCommand> getNextKeyboardCommand()
    {
        lock.lock();

        if (keyboardCommandFuture != null)
        {
            try
            {
                // If there is already a pending future, wait for it to complete
                lock.unlock();
                keyboardCommandFuture.get();
            }
            catch (InterruptedException | ExecutionException ignored)
            {
            }
            finally
            {
                lock.lock();
            }
        }

        keyboardCommandFuture = new CompletableFuture<>();

        lock.unlock();

        return keyboardCommandFuture;
    }

    public void handleKeyboardCommand(KeyboardCommand command)
    {
        lock.lock();

        if (keyboardCommandFuture != null)
        {
            keyboardCommandFuture.complete(command);
            keyboardCommandFuture = null;
        }
        else
        {
            keyboardCommandQueue.add(command);
        }

        lock.unlock();
    }

    public void registerKeyboardCommandAction(KeyboardCommand keyboardCommand, Runnable action)
    {
        keyboardCommandActionsMap.put(keyboardCommand, action);
    }

    public void unregisterKeyboardCommandAction(KeyboardCommand keyboardCommand)
    {
        if (keyboardCommand != null)
        {
            keyboardCommandActionsMap.remove(keyboardCommand);
        }
    }

    public void start()
    {
        keyCommandExecutionThread.start();
    }

    public void stop()
    {
       keyCommandExecutionThreadRunning.set(false);
    }
}

