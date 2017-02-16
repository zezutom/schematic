package org.zezutom.schematic.service.generator.json;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.service.generator.ValueGenerator;

import static org.junit.Assert.assertNotNull;

public abstract class ValueGeneratorTestCase<T, G extends ValueGenerator<T>> {

    G generator;

    abstract G newInstance();

    @Before
    public void before() {
        generator = newInstance();
    }

    @Test
    public void basic() {
        // Leave with default verification
        getValue();
    }

    T getValue() {
        T value = generator.next();
        assertNotNull(value);
        return value;
    }
}
