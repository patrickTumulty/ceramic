package com.ptumulty.ceramic.utility;

import java.util.Random;

public class RandomUtils
{
    /**
     * Get a random int.
     *
     * Note: This method instantiates the Random class on every call. This method should be used for convenience when
     * only a few random numbers are needed. Do not use this method when repeated random numbers need to be generated,
     * as this won't be very efficient.
     *
     * @param lower lower bounds
     * @param upper upper bounds
     * @return random int
     */
    public static int getRandomInt(int lower, int upper)
    {
        return new Random().nextInt(lower, upper);
    }
}
