package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.service.generator.json.IntegerGenerator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class IntegerParserTest extends JsonNodeParserTestCase<Integer, IntegerGenerator, IntegerNode> {

    @Override
    String getResourceDir() {
        return "integer";
    }

    @Test
    public void basic() {
        IntegerGenerator generator = getGenerator("basic.json");
        assertNull(generator.getMultipleOf());
    }

    @Test
    public void multipleOf() {
        IntegerGenerator generator = getGenerator("multiple_of.json");
        assertTrue(generator.getMultipleOf() == 10);
    }
}
