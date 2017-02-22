package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.ValueGenerator;

import javax.lang.model.type.NullType;

@PrototypedService
public class NullGenerator implements ValueGenerator<NullType> {

    @Override
    public NullType next() {
        // TODO meaning of null can be contextual: null, "", -1, ..
        return null;
    }
}
