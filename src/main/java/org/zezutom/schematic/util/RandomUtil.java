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

    private static final int DEFAULT_INT_MAX = 10000;

    static final int MIN_STRING_LENGTH = 2;

    static final int MAX_STRING_LENGTH = 32;

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
        return (bound == null || bound <= 0) ? random.nextInt() : random.nextInt(bound);
    }

    private static int stringLength(Integer min, Integer max) {
        // If the range is invalid, set to a default value
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
        int length = stringLength(adjustStringLength(min), adjustStringLength(max));

        StringBuilder sb = new StringBuilder(length);
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(RAND_CHARS.charAt(random.nextInt(RAND_CHARS.length())));
        }
        return sb.toString();
    }

    /**
     * Generates a string with a minimum length, inclusive. The upper bound is capped by MAX_STRING_LENGTH.
     *
     * @param length    The requested min length
     * @return  A pseudo random string with the requested minimal length, capping applies.
     * @link {RandomUtil#MAX_STRING_LENGTH}
     */
    public static String nextStringWithMinLength(Integer length) {
        return nextString(length, MAX_STRING_LENGTH);
    }

    /**
     * Generates a string using a defined maximum length, inclusive. The upper bound is capped by MAX_STRING_LENGTH.
     *
     * @param length    The requested max length
     * @return  A pseudo random string with the requested maximum length, capping applies.
     * @link {RandomUtil#MAX_STRING_LENGTH}
     */
    public static String nextStringWithMaxLength(Integer length) {
        return nextString(MIN_STRING_LENGTH, length);
    }

    /**
     * Generates a pseudo random string from UUID.
     *
     * @return  A pseudo random UUID turned into a string.
     */
    public static String nextStringFromUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a random integer within a range, inclusive on either end
     * @param min   min value or null if unbounded
     * @param max   max value or null if unbounded
     * @return  a random integer within the provided boundaries (inclusive)
     */
    public static int nextInt(Integer min, Integer max) {
        return nextInt(min, max, false, false);
    }

    /**
     * Adjusts the requested string length to fit within the expected boundaries.
     *
     * @param length    The requested length
     * @return  The original length, if it fits, or min / max allowed length.
     * @link {RandomUtil#MIN_STRING_LENGTH}
     * @link {RandomUtil#MAX_STRING_LENGTH}
     */
    private static int adjustStringLength(Integer length) {
        if (length == null || length < AppUtil.ZERO) return MIN_STRING_LENGTH;
        return (length > MAX_STRING_LENGTH) ? MAX_STRING_LENGTH : length;
    }

}
