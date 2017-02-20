package org.zezutom.schematic.service.generator.json.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.IntegerGenerator;
import org.zezutom.schematic.service.parser.json.node.IntegerParser;

import static org.junit.Assert.assertTrue;

public class IntegerParserTest extends BaseJsonNodeParserTestCase<Integer, IntegerGenerator, IntegerNode, IntegerParser> {

    @Override
    IntegerGenerator createGenerator() {
        return new IntegerGenerator();
    }

    @Override
    IntegerParser createParser(IntegerGenerator generator) {
        return new IntegerParser(generator);
    }

    @Override
    String getResourceDir() {
        return "integer";
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
}
