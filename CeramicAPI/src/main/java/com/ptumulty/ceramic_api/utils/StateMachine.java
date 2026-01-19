package com.ptumulty.ceramic_api.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param <T> State
 * @param <V> Event
 */
public class StateMachine<T, V>
{
    private final AtomicReference<T> currentState;
    private final Map<T, Map<V, T>> stateTransitionMap;
    private final List<Listener<T>> listeners;

    public StateMachine(T initialState)
    {
        currentState = new AtomicReference<>(initialState);
        stateTransitionMap = new HashMap<>();
        listeners = new LinkedList<>();
    }

    protected void addStateTransitionMap(T state, Map<V, T> transitionMap)
    {
        stateTransitionMap.put(state, transitionMap);
    }

    public T getCurrentState()
    {
        return currentState.get();
    }

    public Optional<T> handle(V event)
    {
        T prevState, newState;
        List<Listener<T>> listenersCopy;

        synchronized (this)
        {
            var currentStateTransitions = stateTransitionMap.get(currentState.get());
            if (currentStateTransitions == null)
            {
                return Optional.empty();
            }
            newState = currentStateTransitions.get(event);
            if (newState == null)
            {
                return Optional.empty();
            }
            prevState = currentState.get();
            currentState.set(newState);
            synchronized (listeners)
            {
                listenersCopy = new ArrayList<>(listeners);
            }
        }

        listenersCopy.forEach(listener -> listener.stateChanged(prevState, newState));
        return Optional.of(newState);
    }

    public void addListener(Listener<T> listener)
    {
        synchronized (listeners)
        {
            if (!listeners.contains(listener))
            {
                listeners.add(listener);
            }
        }
    }

    public void removeListener(Listener<T> listener)
    {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    public interface Listener<T>
    {
        void stateChanged(T previous, T current);
    }
}
