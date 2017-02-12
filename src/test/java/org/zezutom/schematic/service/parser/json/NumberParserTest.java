package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.NumberNode;
import org.zezutom.schematic.model.json.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.NumberGenerator;

import java.util.List;

import static org.junit.Assert.*;

public class NumberParserTest extends JsonNodeParserTestCase<Number, NumberGenerator, NumberNode> {

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
    public void oneOf() {
        NumberGenerator generator = getGenerator("one_of.json");
        assertCombinationRule(
                generator.getCombinationRule(),
                JsonSchemaCombinationType.ONE_OF,
                NumberGenerator::getMultipleOf, 3, 5);
    }

    @Test
    public void allOf() {
        NumberGenerator generator = getGenerator("all_of.json");
        JsonSchemaCombinationRule<NumberGenerator> rule = generator.getCombinationRule();
        assertCombinationRule(
                rule,
                JsonSchemaCombinationType.ALL_OF
        );
        List<NumberGenerator> generators = rule.getGenerators();
        assertEquals(2, generators.get(0).getMinimum());
        assertEquals(3, generators.get(1).getMaximum());
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
