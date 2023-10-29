package com.ptumulty.ceramic_api.utils;


import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class InstanceLookup
{
    private final Map<Class<?>, List<Object>> instanceMap;

    InstanceLookup()
    {
        instanceMap = new ConcurrentHashMap<>();
    }

    public synchronized void addCapability(Object object)
    {
        if (instanceMap.containsKey(object.getClass()))
        {
            instanceMap.get(object.getClass()).add(object);
        } else
        {
            instanceMap.put(object.getClass(), new ArrayList<>(List.of(object)));
        }
    }

    @Nullable
    private static <T> T castObject(Object object, Class<T> clazz)
    {
        try
        {
            return clazz.cast(object);
        }
        catch (ClassCastException ignored)
        {
            return null;
        }
    }

    public synchronized <T> Optional<T> lookupCapability(Class<T> clazz)
    {
        for (var classKey : instanceMap.keySet())
        {
            if (classKey.isAssignableFrom(clazz))
            {
                var instances = instanceMap.get(classKey);
                return instances.isEmpty() ?
                       Optional.empty() :
                       Optional.ofNullable(castObject(instances.get(0), clazz));
            }
        }
        return Optional.empty();
    }

    public synchronized <T> List<T> lookupAllCapabilities(Class<T> clazz)
    {
        List<T> instances = new ArrayList<>();
        for (var classKey : instanceMap.keySet())
        {
            if (classKey.isAssignableFrom(clazz))
            {
                instanceMap.get(classKey).forEach(instance -> instances.add(castObject(instance, clazz)));
                break;
            }
        }
        return instances;
    }
}
