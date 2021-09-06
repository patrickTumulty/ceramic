package com.ptumulty.ceramic.utility;

import java.util.HashSet;
import java.util.Set;

public class SetUtils
{
    /**
     * Get the union of all items between two sets
     *
     * @param a set a
     * @param b set b
     * @param <V> the contained type
     * @return the union of two sets
     */
    public static <V> Set<V> union(Set<V> a, Set<V> b)
    {
        Set<V> mergedSet = new HashSet<>();

        mergedSet.addAll(a);
        mergedSet.addAll(b);

        return mergedSet;
    }

    /**
     * Get the intersection of two sets
     *
     * @param a set a
     * @param b set b
     * @param <V> the contained type
     * @return the intersection of two sets
     */
    public static <V> Set<V> intersection(Set<V> a, Set<V> b)
    {
        Set<V> unionSet = new HashSet<>();

        for (V item : a.size() > b.size() ? a : b)
        {
            if (a.size() > b.size() ? b.contains(item) : a.contains(item))
            {
                unionSet.add(item);
            }
        }

        return unionSet;
    }

    /**
     * Get the symmetric difference of two sets
     *
     * @param a set a
     * @param b set b
     * @param <V> the contained type
     * @return the symmetric difference of two sets
     */
    public static <V> Set<V> symmetricDifference(Set<V> a, Set<V> b)
    {
        Set<V> result = union(a, b);
        result.removeAll(intersection(a, b));
        return result;
    }

    /**
     * Get the difference of sets a and b with respect to a
     *
     * @param a set a
     * @param b set b
     * @param <V> the contained type
     * @return the difference set with respect to a
     */
    public static <V> Set<V> difference(Set<V> a, Set<V> b)
    {
        Set<V> dif = new HashSet<>(a);
        dif.removeAll(b);
        return dif;
    }
}
