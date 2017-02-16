package org.zezutom.schematic.service.generator.json;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NumberGeneratorTest extends ValueGeneratorTestCase<Number, NumberGenerator> {

    @Override
    NumberGenerator newInstance() {
        return new NumberGenerator();
    }

    @Test
    public void min() {
        int min = 10;
        generator.setMinimum(min);
        assertTrue(getValue().intValue() >= min);
    }

    @Test
    public void minExclusive() {
        int min = 10;
        generator.setMinimum(min);
        generator.setExclusiveMinimum(true);
        assertTrue(getValue().intValue() > min);
    }

    @Test
    public void max() {
        int max = 10;
        generator.setMaximum(max);
        assertTrue(getValue().intValue() <= max);
    }

    @Test
    public void maxExclusive() {
        int max = 10;
        generator.setMaximum(max);
        generator.setExclusiveMaximum(true);
        assertTrue(getValue().intValue() < max);
    }

    @Test
    public void multipleOf() {
        int multiplier = 10;
        generator.setMultipleOf(multiplier);
        assertTrue(getValue().doubleValue() % multiplier == 0);
    }

}
