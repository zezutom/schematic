package org.zezutom.schematic.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppUtilTest {

    @Test
    public void isValidMin() {
        assertTrue(AppUtil.isValidMin(10));
    }

    @Test
    public void isValidMinZeroIsValid() {
        assertTrue(AppUtil.isValidMin(AppUtil.ZERO));
    }

    @Test
    public void isValidMinNullIsInvalid() {
        assertFalse(AppUtil.isValidMin(null));
    }

    @Test
    public void isValidMinNegativeValueIsInvalid() {
        assertFalse(AppUtil.isValidMin(-1));
    }

    @Test
    public void isValidMax() {
        assertTrue(AppUtil.isValidMax(10));
    }

    @Test
    public void isValidMaxZeroIsInvalid() {
        assertFalse(AppUtil.isValidMax(AppUtil.ZERO));
    }

    @Test
    public void isValidMaxNullIsInvalid() {
        assertFalse(AppUtil.isValidMax(null));
    }

    @Test
    public void isValidMaxNegativeValueIsInvalid() {
        assertFalse(AppUtil.isValidMax(-1));
    }

    @Test
    public void isValidRange() {
        assertTrue(AppUtil.isValidRange(AppUtil.ZERO, 10));
    }

    @Test
    public void isValidRangeWithInvalidMinIsInvalid() {
        assertFalse(AppUtil.isValidRange(-1, 10));
    }

    @Test
    public void isValidRangeWithNullMaxIsInvalid() {
        assertFalse(AppUtil.isValidRange(AppUtil.ZERO, null));
    }

    @Test
    public void isValidRangeWithMinGreaterThanMaxIsInvalid() {
        assertFalse(AppUtil.isValidRange(10, AppUtil.ZERO));
    }
}
