package org.zezutom.schematic.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides random values for basic data types.
 */
public class RandomUtil {

    private static final String RAND_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int MULTIPLIER_MIN = 1;

    private static final int MULTIPLIER_MAX = 10;

    private static final int DEFAULT_STRING_LENGTH = 10;

    private static final int DEFAULT_INT_MAX = 10000;

    private static final int MIN_STRING_LENGTH = 2;

    private RandomUtil() {}

    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Returns a random integer with a min value guarantee
     * @param min           the minimum expected value
     * @param exclusive     true, if the minimum value is inclusive, false otherwise
     * @return  a random integer greater than (or gt or equal to, if inclusive) the expected minimum value
     */
    public static int nextMin(Integer min, Boolean exclusive) {
        if (min == null) return nextInt();

        return ThreadLocalRandom
                .current()
                .nextInt(Boolean.TRUE.equals(exclusive) ? min + 1 : min, DEFAULT_INT_MAX);
    }

    /**
     * Returns a random integer with a max value guarantee
     * @param max           the maximum expected value
     * @param exclusive     true, if the maximum value is inclusive, false otherwise
     * @return  a random integer less than (or gt or equal to, if inclusive) the expected maximum value
     */
    public static int nextMax(Integer max, Boolean exclusive) {
        if (max == null) return nextInt();

        return ThreadLocalRandom
                .current()
                .nextInt(Boolean.TRUE.equals(exclusive) ? max : max + 1);
    }

    /**
     * Generates a random integer
     * @return a random unbounded integer
     */
    public static int nextInt() {
        return nextInt(null);
    }

    /**
     * Generates a random integer within a range, inclusive on either end
     * @param min   min value or null if unbounded
     * @param max   max value or null if unbounded
     * @return  a random integer within the provided boundaries (inclusive)
     */
    private static int nextInt(Integer min, Integer max) {
        return nextInt(min, max, false, false);
    }

    /**
     * Generates a random integer within a range
     * @param min           min value or null if unbounded
     * @param max           max value or null if unbounded
     * @param exclusiveMin  true, if the min value should be exclusive
     * @param exclusiveMax  true, if the max value should be exclusive
     * @return  a random integer within the provided boundaries
     */

    public static int nextInt(Integer min, Integer max, Boolean exclusiveMin, Boolean exclusiveMax) {
        if (min == null || max == null) return nextInt();

        return ThreadLocalRandom
                .current()
                .nextInt(
                        Boolean.TRUE.equals(exclusiveMin) ? min + 1 : min,
                        Boolean.TRUE.equals(exclusiveMax) ? max : max + 1);
    }

    public static int multipleOf(Integer multipleOf) {
        if (multipleOf == null) return nextInt();
        return multipleOf * RandomUtil.nextInt(MULTIPLIER_MIN, MULTIPLIER_MAX);
    }

    public static int nextInt(Integer bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (bound == null) ? random.nextInt() : random.nextInt(bound);
    }

    private static int stringLength(Integer min, Integer max) {
        if (!AppUtil.isValidMin(min)) min = AppUtil.ZERO;
        if (max == null) max = AppUtil.ZERO;

        if (min == AppUtil.ZERO && max.equals(min)) return nextInt(DEFAULT_STRING_LENGTH);

        // If max wasn't provided or is invalid, set to a default value
        if (!AppUtil.isValidRange(min, max)) max = min * (MULTIPLIER_MIN + 1);
        return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random string of a length with a range, inclusive on either end.
     * @param min   min length
     * @param max   max length
     * @return a random string whose length is with the provided boundaries (inclusive)
     */
    public static String nextString(Integer min, Integer max) {
        int length = stringLength(min, max);
        if (length <= 0) length = DEFAULT_STRING_LENGTH;
        if (length < MIN_STRING_LENGTH) length = MIN_STRING_LENGTH;

        StringBuilder sb = new StringBuilder(length);
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(RAND_CHARS.charAt(random.nextInt(RAND_CHARS.length())));
        }
        return sb.toString();
    }

    public static String nextStringFromUUID() {
        return UUID.randomUUID().toString();
    }

}
