package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.service.generator.value.BooleanGenerator;

public class JsonSchemaBooleanParserTest extends JsonSchemaLeafNodeParserTestCase<Boolean, BooleanGenerator> {

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
