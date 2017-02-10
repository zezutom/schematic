package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.NullNode;
import org.zezutom.schematic.service.generator.value.NullGenerator;

import javax.lang.model.type.NullType;

public class JsonNullParserTestJson extends JsonLeafNodeParserTestCase<NullType, NullGenerator, NullNode> {

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
