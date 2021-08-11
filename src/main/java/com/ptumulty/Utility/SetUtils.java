package com.pt.mug.Utility;

import java.util.HashSet;
import java.util.Set;

public class SetUtils
{
    /**
     * Merge two sets into one
     *
     * @param a set a
     * @param b set b
     * @param <V> the contained type
     * @return the merged set of a and b
     */
    public static <V> Set<V> mergeSets(Set<V> a, Set<V> b)
    {
        Set<V> mergedSet = new HashSet<>();
        mergedSet.addAll(a);
        mergedSet.addAll(b);
        return mergedSet;
    }
}
