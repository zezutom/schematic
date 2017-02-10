package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.NumberNode;
import org.zezutom.schematic.service.generator.value.NumberGenerator;

import static org.junit.Assert.*;

public class JsonNumberParserTestJson extends JsonLeafNodeParserTestCase<Number, NumberGenerator, NumberNode> {

    @Override
    String getResourceDir() {
        return "number";
    }

    @Test
    public void basic() {
        NumberGenerator generator = getGenerator("basic.json");
        assertFalse(generator.getExclusiveMaximum());
        assertFalse(generator.getExclusiveMinimum());
        assertNull(generator.getMaximum());
        assertNull(generator.getMinimum());
        assertNull(generator.getMultipleOf());
    }

    @Test
    public void multipleOf() {
        NumberGenerator generator = getGenerator("multiple_of.json");
        assertTrue(generator.getMultipleOf() == 10);
    }

    @Test
    public void range() {
        NumberGenerator generator = getGenerator("min_max.json");
        assertEquals(0, generator.getMinimum());
        assertEquals(100, generator.getMaximum());
        assertFalse(generator.getExclusiveMinimum());
        assertFalse(generator.getExclusiveMaximum());
    }

    @Test
    public void rangeExclusive() {
        NumberGenerator generator = getGenerator("min_max_exclusive.json");
        assertEquals(0, generator.getMinimum());
        assertEquals(100, generator.getMaximum());
        assertTrue(generator.getExclusiveMinimum());
        assertTrue(generator.getExclusiveMaximum());
    }
}
