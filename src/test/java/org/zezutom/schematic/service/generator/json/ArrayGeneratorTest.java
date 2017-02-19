package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.service.generator.ValueGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ArrayGeneratorTest extends ValueGeneratorTestCase<List<Object>, ArrayGenerator> {

    @Override
    ArrayGenerator newInstance() {
        return new ArrayGenerator();
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
