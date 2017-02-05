package org.zezutom.schematic.util;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides random values for basic data types.
 */
public class RandomUtil {

    private RandomUtil() {}

    public static<T> Optional<T> getRandomValue(Collection<T> values) {
        if (values == null || values.isEmpty()) {
            return Optional.empty();
        }
        return values
                .stream()
                .skip((int) (values.size() * Math.random()))
                .findFirst();
    }

    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(Integer min) {
        if (min == null) {
            return nextInt();
        }
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE - min) + min;
    }

}
