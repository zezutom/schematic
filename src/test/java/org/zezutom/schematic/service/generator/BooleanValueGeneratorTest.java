package org.zezutom.schematic.service.generator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BooleanValueGeneratorTest {

    private BooleanGenerator generator;

    @Before
    public void before() {
        generator = new BooleanGenerator();
    }

    @Test
    public void generatedValueIsNotNull() {
        assertNotNull(generator.next());
    }
}
