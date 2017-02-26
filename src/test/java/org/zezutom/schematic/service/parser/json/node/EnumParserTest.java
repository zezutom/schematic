package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.service.generator.json.EnumGenerator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertItems("red", "amber", "green", null, 42, 10.5, true);
    }

    @Test
    public void parseNonEnumNodeReturnsNull() {
        JsonNode jsonNode = mock(JsonNode.class);
        when(jsonNode.get(anyString())).thenReturn(null);
        assertNull(parser.parse(jsonNode));
    }

    private void assertItems(Object... expectedValues) {
        List<Object> items = generator.getItems();
        assertNotNull(items);
        assertTrue(items.size() == expectedValues.length);
        assertTrue(items.containsAll(Arrays.asList(expectedValues)));
    }

}
