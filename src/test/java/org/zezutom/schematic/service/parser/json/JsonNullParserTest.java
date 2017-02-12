package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.NullNode;
import org.zezutom.schematic.service.generator.NullGenerator;

import javax.lang.model.type.NullType;

public class JsonNullParserTest extends JsonNodeParserTestCase<NullType, NullGenerator, NullNode> {

    @Override
    String getResourceDir() {
        return "null";
    }

    @Test
    public void basic() {
        // Leave with default tests
        getGenerator("basic.json");
    }

}
