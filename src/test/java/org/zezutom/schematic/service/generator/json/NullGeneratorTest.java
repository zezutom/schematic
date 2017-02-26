package org.zezutom.schematic.service.generator.json;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class NullGeneratorTest {

    private final NullGenerator generator = new NullGenerator();

    @Test
    public void next() {
        assertNull(generator.next());
    }

}
