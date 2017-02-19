package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.NullNode;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.NullParser;

import javax.lang.model.type.NullType;

@PrototypedService
public class NullGenerator extends BaseSchemaGenerator<NullType, NullNode, NullGenerator, NullParser> {

    @Override
    public NullType next() {
        // TODO meaning of null can be contextual: null, "", -1, ..
        return null;
    }
}
