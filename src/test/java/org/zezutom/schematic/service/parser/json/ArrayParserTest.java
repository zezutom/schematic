package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.ArrayGenerator;
import org.zezutom.schematic.service.generator.json.NumberGenerator;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ArrayParserTest extends JsonNodeParserTestCase<List<Object>, ArrayGenerator, ArrayNode> {

    @Override
    String getResourceDir() {
        return "array";
    }

    @Test
    public void basic() {
        assertTrue(getItems("basic.json").isEmpty());
    }

    @Test
    public void items() {
        assertTrue(getItems("items.json")
                .stream()
                .filter(x -> x instanceof NumberGenerator)
                .count() == 1);
    }

    @Test
    public void tuple() {
        List<ValueGenerator> generators = getItems("tuple.json");
        assertTrue(generators
                .stream()
                .filter(x -> x instanceof NumberGenerator)
                .count() == 1);
        assertTrue(generators
                .stream()
                .filter(x -> x instanceof StringGenerator)
                .count() == 3);

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

    private List<ValueGenerator> getItems(String fileName) {
        ArrayGenerator generator = getGenerator(fileName);
        List<ValueGenerator> items = generator.getItems();
        assertNotNull(items);
        return items;
    }
}
