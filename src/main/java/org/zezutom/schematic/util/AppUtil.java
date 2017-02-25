package org.zezutom.schematic.util;

/**
 * Commonly used app functions.
 */
public class AppUtil {

    static final int ZERO = 0;

    private AppUtil() {}

    public static boolean isValidMin(Integer min) {
        return min != null && min >= ZERO;
    }

    public static boolean isValidMax(Integer max) {
        return max != null && max > ZERO;
    }

    public static boolean isValidRange(Integer min, Integer max) {
        return isValidMin(min) && (max != null && max > min);
    }
}
