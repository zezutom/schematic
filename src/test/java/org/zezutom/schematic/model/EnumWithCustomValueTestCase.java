package org.zezutom.schematic.model;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;

public abstract class EnumWithCustomValueTestCase<T extends EnumWithCustomValue> {

    protected abstract T[] values();

    protected abstract Function<String, T> get();

    protected abstract Function<String, T> valueOf();

    protected abstract Function<String, Boolean> contains();

    @Test
    public void getWorksOnAllEnumeratedValues() {
        assertValueOperation((T type) -> assertEquals(type, get().apply(type.getValue())));
    }

    @Test
    public void getReturnsNullOnNullInput() {
        assertNull(get().apply(null));
    }

    @Test
    public void getReturnsNullOInvalidInput() {
        assertNull(get().apply("invalid"));
    }

    @Test
    public void containsWorksOnAllEnumeratedValues() {
        assertValueOperation((T type) -> assertTrue(contains().apply(type.getValue())));
    }

    @Test
    public void containsReturnsFalseOnNullInput() {
        assertFalse(contains().apply(null));
    }

    @Test
    public void containsReturnsFalseOnInvalidInput() {
        assertFalse(contains().apply("invalid"));
    }

    @Test
    public void valueOfWorksOnAllEnumeratedValues() {
        assertValueOperation((T type) -> assertEquals(type, valueOf().apply(type.toString())));
    }

    @Test(expected = NullPointerException.class)
    public void valueOfDoesNotAcceptNullInput() {
        valueOf().apply(null);
        fail("A null value shouldn't be accepted");
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfDoesNotAcceptInvalidInput() {
        valueOf().apply("invalid");
        fail("An invalid value shouldn't be accepted");
    }

    private void assertValueOperation(Consumer<T> consumer) {
        for (T value : values()) {
            consumer.accept(value);
            assertEquals(value, EnumWithCustomValue.get(value.getValue(), values()));
        }
    }
}
