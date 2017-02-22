package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.model.json.BooleanNode;
import org.zezutom.schematic.service.generator.json.BooleanGenerator;

public class BooleanParserTest extends BaseJsonNodeParserTestCase<Boolean, BooleanGenerator, BooleanNode, BooleanParser> {

    @Override
    BooleanGenerator createGenerator() {
        return new BooleanGenerator();
    }

    @Override
    BooleanParser createParser(BooleanGenerator generator) {
        return new BooleanParser(generator);
    }

    @Override
    String getResourceDir() {
        return "boolean";
    }

    @Test
    public void parse() {
        // Leave with a default verification
        parse("basic.json");
    }
}
