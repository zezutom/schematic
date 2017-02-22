package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.model.json.NullNode;
import org.zezutom.schematic.service.generator.json.NullGenerator;

import javax.lang.model.type.NullType;

public class NullParserTest extends BaseJsonNodeParserTestCase<NullType, NullGenerator, NullNode, NullParser> {

    @Override
    NullGenerator createGenerator() {
        return new NullGenerator();
    }

    @Override
    NullParser createParser(NullGenerator generator) {
        return new NullParser(generator);
    }

    @Override
    String getResourceDir() {
        return "null";
    }

    @Test
    public void parse() {
        // Leave with a default verification
        parse("basic.json");
    }
}
