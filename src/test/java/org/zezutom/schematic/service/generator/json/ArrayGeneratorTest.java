package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.service.generator.ValueGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayGeneratorTest extends ValueGeneratorTestCase<List<Object>, ArrayGenerator> {

    @Override
    ArrayGenerator newInstance() {
        return new ArrayGenerator(TestUtil.mockParserFactory());
    }

    @Test
    public void next() {
        assertGeneratedValues(false);
    }

    @Test
    public void nextWithUniqueItems() {
        assertGeneratedValues(true);
    }

    @Test
    public void nextWithRange() {
        int min = 2;
        int max = 5;

        generator.setMinItems(min);
        generator.setMaxItems(max);
        generator.addItem(TestUtil.mockGenerator(StringGenerator.class, "test"));

        List<Object> values = generator.next();
        assertNotNull(values);
        assertTrue(values.size() >= min);
        assertTrue(values.size() <= max);
    }

    @Test
    public void nextWithAdditionalItemsAllowed() {
        generator.addItem(TestUtil.mockGenerator(StringGenerator.class, "test"));
        generator.addItem(TestUtil.mockGenerator(StringGenerator.class, "test01"));
        generator.addItem(TestUtil.mockGenerator(NumberGenerator.class, 10));
        generator.setAdditionalItems(true);
        List<Object> values = generator.next();
        assertNotNull(values);
        assertTrue(values.size() > 1);
    }

    @Test
    public void addItem() {
        StringGenerator item = mock(StringGenerator.class);
        generator.addItem(item);
        assertTrue(generator.getItems().contains(item));
    }

    @Test
    public void addItemOnNullInputHasNoEffect() {
        generator.addItem(null);
        assertFalse(generator.getItems().contains(null));
    }

    @Test
    public void nextWithinRangeAdheresToBoundaries() {
        generator.addItem(mockGenerator(StringGenerator.class, "a"));
        generator.addItem(mockGenerator(StringGenerator.class, "b"));

        int min = 1, max = 3;
        generator.setMinItems(min);
        generator.setMaxItems(max);

        int valuesSize = getValidValues().size();
        assertTrue(valuesSize >= min && valuesSize <= max);

    }

    @Test
    public void nextGeneratesAtLeastMinNumberOfValues() {
        // There is a single value producer
        generator.addItem(mockGenerator(StringGenerator.class, "test"));

        // However, at least three values are expected
        int min = 3;
        generator.setMinItems(min);
        assertValueCount(min);
    }

    @Test
    public void nextDoesNotAddAdditionalValuesWhenMinThresholdIsMet() {
        // There is are three value producers
        generator.addItem(mockGenerator(StringGenerator.class, "a"));
        generator.addItem(mockGenerator(StringGenerator.class, "b"));
        generator.addItem(mockGenerator(StringGenerator.class, "c"));

        // Also, there is an expectation for at most one value to be generated
        generator.setMinItems(1);

        // Since the number of generators exceeds the required min, there should be no change
        assertValueCount(generator.getItems().size());
    }

    @Test
    public void nextAdheresToMaxNumberOfValues() {
        // There are three value producers
        generator.addItem(mockGenerator(StringGenerator.class, "test"));
        generator.addItem(mockGenerator(IntegerGenerator.class, 10));
        generator.addItem(mockGenerator(BooleanGenerator.class, true));

        // However, at most two values are expected
        int max = 2;
        generator.setMaxItems(max);

        assertValueCount(max);
    }

    @Test
    public void nextDoesNotReduceNumberOfValuesWhenMaxThresholdIsMet() {
        // There are two value producers
        generator.addItem(mockGenerator(StringGenerator.class, "test"));
        generator.addItem(mockGenerator(IntegerGenerator.class, 10));

        // Also, there is requirement not to exceed three values
        generator.setMaxItems(3);

        // Since the number of generators is less than the required max, there should be no change
        assertNotNull(generator.getItems().size());
    }

    private void assertValueCount(int expectedCount) {
        List<Object> values = getValidValues();
        assertTrue(values.size() == expectedCount);
    }

    private List<Object> getValidValues() {
        return generator
                .next()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private <T, G extends ValueGenerator<T>> G mockGenerator(Class<G> generatorClass, T value) {
        G mock = mock(generatorClass);
        when(mock.next()).thenReturn(value);

        return mock;
    }

    private<T> void assertGeneratedValue(List<Object> values, Class<T> valueClass, T expectedValue, int expectedCount) {
        assertNotNull(values);
        assertTrue(expectedCount == values.stream()
                .filter(Objects::nonNull)
                .filter(x -> valueClass.equals(x.getClass()))
                .filter(expectedValue::equals)
                .count());
    }

    private void assertGeneratedValues(boolean unique) {
        StringGenerator stringGenerator = TestUtil.mockGenerator(StringGenerator.class, "test");
        IntegerGenerator integerGenerator = TestUtil.mockGenerator(IntegerGenerator.class, 10);

        // Add each of the generators multiple times
        List<ValueGenerator> generators = Arrays.asList(
                stringGenerator,
                stringGenerator,
                stringGenerator,
                integerGenerator,
                integerGenerator);
        generators.forEach(generator::addItem);

        // Request the generated values be unique
        Integer expectedStringValueCount, expectedIntegerValueCount;
        if (unique) {
            generator.setUniqueItems(true);
            expectedStringValueCount = expectedIntegerValueCount = 1;
        } else {
            expectedStringValueCount = 3;
            expectedIntegerValueCount = 2;
        }

        // Collect the generated values
        List<Object> values = generator.next();

        assertGeneratedValue(values, String.class, stringGenerator.next(), expectedStringValueCount);
        assertGeneratedValue(values, Integer.class, integerGenerator.next(), expectedIntegerValueCount);
    }
}
