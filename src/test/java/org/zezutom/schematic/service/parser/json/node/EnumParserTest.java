package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.service.generator.json.EnumGenerator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EnumParserTest extends BaseJsonNodeParserTestCase<Object, EnumGenerator, EnumNode, EnumParser> {

    @Override
    EnumGenerator createGenerator() {
        return new EnumGenerator();
    }

    @Override
    EnumParser createParser(EnumGenerator generator) {
        return new EnumParser(generator);
    }

    @Override
    String getResourceDir() {
        return "enum";
    }

    @Test
    public void parse() {
        parse("basic.json");
        assertItems("red", "amber", "green");
    }

    @Test
    public void parseDifferentDataTypes() {
        parse("different_types.json");
        assertItems("red", "amber", "green", null, 42);
    }

    private void assertItems(Object... expectedValues) {
        List<Object> items = generator.getItems();
        assertNotNull(items);
        assertTrue(items.size() == expectedValues.length);
        assertTrue(items.containsAll(Arrays.asList(expectedValues)));
    }

}
