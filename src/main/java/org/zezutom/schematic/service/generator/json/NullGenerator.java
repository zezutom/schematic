package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.NullNode;
import org.zezutom.schematic.service.parser.json.NullParser;

import javax.lang.model.type.NullType;

public class NullGenerator extends BaseSchemaGenerator<NullType, NullNode, NullGenerator, NullParser> {

    public NullGenerator(NullParser parser) {
        super(parser);
    }

    @Override
    public NullType next() {
        // TODO meaning of null can be contextual: null, "", -1, ..
        return null;
    }
}
