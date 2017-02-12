package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.BooleanNode;
import org.zezutom.schematic.service.generator.BooleanGenerator;

public class JsonBooleanParserTest extends JsonNodeParserTestCase<Boolean, BooleanGenerator, BooleanNode> {

    @Override
    String getResourceDir() {
        return "boolean";
    }

    @Test
    public void basic() {
        // Leave with default tests
        getGenerator("basic.json");
    }

}
