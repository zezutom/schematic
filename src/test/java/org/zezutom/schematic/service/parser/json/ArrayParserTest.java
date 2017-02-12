package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.ArrayNode;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.service.generator.json.ArrayGenerator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ArrayParserTest extends JsonNodeParserTestCase<List<Object>, ArrayGenerator, ArrayNode> {

    private final ArrayParser parser = new ArrayParser();

    @Override
    String getResourceDir() {
        return "array";
    }

    @Test
    public void basic() {
        assertItems("basic.json");
    }

    @Test
    public void items() {
        assertItems("items.json", JsonDataType.NUMBER);
    }

    @Test
    public void tuple() {
        assertItems("tuple.json", JsonDataType.NUMBER, JsonDataType.STRING);
    }

    @Test
    public void additionalItems() {
        ArrayGenerator generator = getGenerator("additional_items.json");
        assertTrue(generator.getAdditionalItems());
    }

    @Test
    public void uniqueItems() {
        ArrayGenerator generator = getGenerator("unique_items.json");
        assertTrue(generator.getUniqueItems());
    }

    @Test
    public void minMaxItems() {
        ArrayGenerator generator = getGenerator("min_max_items.json");
        assertTrue(generator.getMinItems() == 2);
        assertTrue(generator.getMaxItems() == 3);
    }

    private void assertItems(String fileName, JsonDataType... expectedTypes) {
        ArrayGenerator generator = getGenerator(fileName);
        List<JsonDataType> items = generator.getItems();
        assertNotNull(items);
        if (expectedTypes != null) {
            assertTrue(items.size() == expectedTypes.length);
            assertTrue(items.containsAll(Arrays.asList(expectedTypes)));
        } else {
            assertTrue(items.isEmpty());
        }
    }
}
