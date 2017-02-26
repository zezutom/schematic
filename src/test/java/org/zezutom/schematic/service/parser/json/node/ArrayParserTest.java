package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.ArrayGenerator;
import org.zezutom.schematic.service.generator.json.NumberGenerator;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ArrayParserTest extends BaseJsonNodeParserTestCase<List<Object>, ArrayGenerator, ArrayNode, ArrayParser> {

    private final JsonNodeParserFactory parserFactory = TestUtil.mockParserFactory();

    @Override
    ArrayGenerator createGenerator() {
        return new ArrayGenerator(TestUtil.mockParserFactory());
    }

    @Override
    ArrayParser createParser(ArrayGenerator generator) {
        return new ArrayParser(generator, parserFactory);
    }

    @Override
    String getResourceDir() {
        return "array";
    }

    @Test
    public void parse() {
        parse("basic.json");
    }

    @Test
    public void parseEmpty() {
        parse("empty.json");
        List<ValueGenerator> items = generator.getItems();
        assertNotNull(items);
        assertTrue(items.isEmpty());
    }

    @Test
    public void parseNumberSchema() {
        parse("items.json");
        assertItems(NumberGenerator.class, 1);
    }

    @Test
    public void parseMixedSchemas() {
        parse("tuple.json");
        assertItems(NumberGenerator.class, 1);
        assertItems(StringGenerator.class, 3);
    }

    @Test
    public void parseArrayLength() {
        parse("min_max_items.json");
        assertTrue(generator.getMinItems() == 2);
        assertTrue(generator.getMaxItems() == 3);
    }

    @Test
    public void parseUniqueItemsFlag() {
        parse("unique_items.json");
        assertTrue(generator.getUniqueItems());
    }

    @Test
    public void parseAdditionalItemsFlag() {
        parse("additional_items.json");
        assertTrue(generator.getAdditionalItems());
    }

    private<T extends ValueGenerator> void assertItems(Class<T> expectedClass, int expectedCount) {
        List<ValueGenerator> items = generator.getItems();
        assertNotNull(items);
        assertTrue(items.stream().filter(x -> expectedClass.equals(x.getClass())).count() == expectedCount);
    }

}
