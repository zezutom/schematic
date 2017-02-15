package org.zezutom.schematic.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides random values for basic data types.
 */
public class RandomUtil {

    private static final String RAND_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int ZERO = 0;

    private static final int MULTIPLIER = 2;

    private static final int DEFAULT_STRING_LENGTH = 10;

    private static final int MIN_STRING_LENGTH = 2;

    private RandomUtil() {}

    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    private static int nextInt(Integer bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (bound == null) ? random.nextInt() : random.nextInt(bound);
    }

    /**
     * Generates a random integer within a range, inclusive on either end
     * @param min   min value or null if unbounded
     * @param max   max value or null if unbounded
     * @return  a random integer within the provided boundaries (inclusive)
     */
    private static int nextInt(Integer min, Integer max) {
        if (min == null || min < ZERO) min = ZERO;
        if (max == null) max = ZERO;

        if (min == ZERO && max.equals(min)) return nextInt(DEFAULT_STRING_LENGTH);

        // If max wasn't provided or is invalid, set to a default value
        if (max <= ZERO || min > max) max = min * MULTIPLIER;
        return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random string of a length with a range, inclusive on either end.
     * @param min   min length
     * @param max   max length
     * @return a random string whose length is with the provided boundaries (inclusive)
     */
    public static String nextString(Integer min, Integer max) {
        int length = nextInt(min, max);
        if (length <= 0) length = DEFAULT_STRING_LENGTH;
        if (length < MIN_STRING_LENGTH) length = MIN_STRING_LENGTH;

        StringBuilder sb = new StringBuilder(length);
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(RAND_CHARS.charAt(random.nextInt(RAND_CHARS.length())));
        }
        return sb.toString();
    }

}
