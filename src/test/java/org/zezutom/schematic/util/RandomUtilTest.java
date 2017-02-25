package org.zezutom.schematic.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RandomUtilTest {

    @Test
    public void nextBoolean() {
        assertVariety(RandomUtil::nextBoolean);
    }

    @Test
    public void nextInt() {
        assertVariety(RandomUtil::nextInt);
    }

    @Test
    public void nextIntWithRange() {
        int min = 0, max = 10;
        assertVariety(() -> RandomUtil.nextInt(min, max, false, false));
        assertVariety(() -> RandomUtil.nextInt(min, max, true, false));
        assertVariety(() -> RandomUtil.nextInt(min, max, false, true));
        assertVariety(() -> RandomUtil.nextInt(min, max, true, true));
    }

    @Test
    public void nextIntWithRangeHavingNullMinOrNullMaxReturnsRandomInteger() {
        int min = 0, max = 10;

        assertNotNull(RandomUtil.nextInt(null, max, false, false));
        assertNotNull(RandomUtil.nextInt(null, max, true, false));
        assertNotNull(RandomUtil.nextInt(null, max, false, true));
        assertNotNull(RandomUtil.nextInt(null, max, true, true));

        assertVariety(() -> RandomUtil.nextInt(null, max, false, false));
        assertVariety(() -> RandomUtil.nextInt(null, max, true, false));
        assertVariety(() -> RandomUtil.nextInt(null, max, false, true));
        assertVariety(() -> RandomUtil.nextInt(null, max, true, true));

        assertNotNull(RandomUtil.nextInt(min, null, false, false));
        assertNotNull(RandomUtil.nextInt(min, null, true, false));
        assertNotNull(RandomUtil.nextInt(min, null, false, true));
        assertNotNull(RandomUtil.nextInt(min, null, true, true));

        assertVariety(() -> RandomUtil.nextInt(min, null, false, false));
        assertVariety(() -> RandomUtil.nextInt(min, null, true, false));
        assertVariety(() -> RandomUtil.nextInt(min, null, false, true));
        assertVariety(() -> RandomUtil.nextInt(min, null, true, true));

        assertNotNull(RandomUtil.nextInt(null, null, false, false));
        assertNotNull(RandomUtil.nextInt(null, null, true, false));
        assertNotNull(RandomUtil.nextInt(null, null, false, true));
        assertNotNull(RandomUtil.nextInt(null, null, true, true));

        assertVariety(() -> RandomUtil.nextInt(null, null, false, false));
        assertVariety(() -> RandomUtil.nextInt(null, null, true, false));
        assertVariety(() -> RandomUtil.nextInt(null, null, false, true));
        assertVariety(() -> RandomUtil.nextInt(null, null, true, true));
    }

    @Test
    public void nextIntWithBound() {
        assertVariety(() -> RandomUtil.nextInt(10));
    }

    @Test
    public void nextString() {
        assertVariety(() -> RandomUtil.nextString(0, 10));
    }

    @Test
    public void nextStringFromUUID() {
        assertVariety(RandomUtil::nextStringFromUUID);
    }

    @Test
    public void nextMin() {
        int min = 10;
        assertVariety(() -> RandomUtil.nextMin(min, false));
        assertVariety(() -> RandomUtil.nextMin(min, true));
    }

    @Test
    public void nextMinOfNullReturnsRandomInteger() {
        assertNotNull(RandomUtil.nextMin(null, false));
        assertNotNull(RandomUtil.nextMin(null, true));
        assertVariety(() -> RandomUtil.nextMin(null, false));
        assertVariety(() -> RandomUtil.nextMin(null, true));
    }

    @Test
    public void nextMax() {
        int max = 10;
        assertVariety(() -> RandomUtil.nextMax(max, false));
        assertVariety(() -> RandomUtil.nextMax(max, true));
    }

    @Test
    public void nextMaxOfNullReturnsRandomInteger() {
        assertNotNull(RandomUtil.nextMax(null, false));
        assertNotNull(RandomUtil.nextMax(null, true));
        assertVariety(() -> RandomUtil.nextMax(null, false));
        assertVariety(() -> RandomUtil.nextMax(null, true));
    }

    @Test
    public void multipleOf() {
        assertVariety(() -> RandomUtil.multipleOf(10));
    }

    @Test
    public void multipleOfNullReturnsRandomInteger() {
        assertNotNull(RandomUtil.multipleOf(null));
        assertVariety(() -> RandomUtil.multipleOf(null));
    }

    private<T> void assertVariety(Supplier<T> supplier) {
        Set<T> randomValues = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            randomValues.add(supplier.get());
        }
        // At least two different values are expected
        assertTrue(randomValues.size() > 1);
    }

}
