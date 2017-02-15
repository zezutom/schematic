package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.service.generator.json.EnumGenerator;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EnumParserTest extends JsonNodeParserTestCase<Object, EnumGenerator, EnumNode> {

    @Override
    String getResourceDir() {
        return "enum";
    }

    @Test
    public void basic() {
        EnumGenerator generator = getGenerator("basic.json");
        assertItems(generator, "red", "amber", "green");
    }

    @Test
    public void differentTypes() {
        EnumGenerator generator = getGenerator("different_types.json");
        assertItems(generator, "red", "amber", "green", null, 42);
    }

    private void assertItems(@NotNull EnumGenerator generator, Object... expectedValues) {
        List<Object> items = generator.getItems();
        assertNotNull(items);
        assertTrue(items.containsAll(Arrays.asList(expectedValues)));
    }
}