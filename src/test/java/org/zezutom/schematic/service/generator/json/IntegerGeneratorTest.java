package org.zezutom.schematic.service.generator.json;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IntegerGeneratorTest extends ValueGeneratorTestCase<Integer, IntegerGenerator> {

    @Override
    IntegerGenerator newInstance() {
        return new IntegerGenerator();
    }

    @Test
    public void multipleOf() {
        int multiplier = 10;
        generator.setMultipleOf(multiplier);
        assertTrue(getValue().doubleValue() % multiplier == 0);
    }

}
