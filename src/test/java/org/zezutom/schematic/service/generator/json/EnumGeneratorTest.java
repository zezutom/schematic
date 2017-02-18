package org.zezutom.schematic.service.generator.json;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EnumGeneratorTest {

    private EnumGenerator generator;

    @Before
    public void before() {
        generator = new EnumGenerator();
    }

    @Test
    public void isEmptyByDefault() {
        assertNull(generator.next());
    }

    @Test
    public void sameType() {
        List<String> expectedValues = Arrays.asList("one", "two", "three", "four", "five");
        assertItems(expectedValues);
        Object value = generator.next();
        assertNotNull(value);
        assertTrue(expectedValues.contains(value.toString()));
    }

    @Test
    public void differentTypes() {
        List<Object> expectedValues = Arrays.asList("one", "two", 3, true, null);
        assertItems(expectedValues);
        assertTrue(expectedValues.contains(generator.next()));
    }

    @Test
    public void explicitNull() {
        assertItems(Collections.singletonList(null));
        assertNull(generator.next());
    }

    private<T> void assertItems(List<T> expectedValues) {
        expectedValues.forEach(generator::addItem);
        List<Object> actualValues = generator.getItems();
        assertNotNull(actualValues);
        assertTrue(actualValues.size() == expectedValues.size());
        actualValues.containsAll(expectedValues);
    }
}
