package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.NumberNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.NumberGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumberParserTest extends BaseJsonNodeParserTestCase<Number, NumberGenerator, NumberNode, NumberParser> {

    @Override
    NumberGenerator createGenerator() {
        return new NumberGenerator(TestUtil.mockParserFactory());
    }

    @Override
    NumberParser createParser(NumberGenerator generator) {
        return new NumberParser(generator);
    }

    @Override
    String getResourceDir() {
        return "number";
    }

    @Test
    public void parseRange() {
        parse("min_max.json");
        assertRange();
    }

    @Test
    public void parseExclusiveRange() {
        parse("min_max_exclusive.json");
        assertRange();
        assertTrue(generator.getExclusiveMinimum());
        assertTrue(generator.getExclusiveMaximum());
    }

    @Test
    public void parseMultipleOf() {
        parse("multiple_of.json");
        assertTrue(generator.getMultipleOf() == 10);
    }

    @Test
    public void parseOneOf() {
        parse("one_of.json");
        assertCombinationRule(generator.getCombinationRule(), JsonSchemaCombinationType.ONE_OF);
    }

    @Test
    public void parseAllOf() {
        parse("all_of.json");
        assertCombinationRule(generator.getCombinationRule(), JsonSchemaCombinationType.ALL_OF);
    }

    private void assertRange() {
        assertEquals(0, generator.getMinimum());
        assertEquals(100, generator.getMaximum());
    }

}
