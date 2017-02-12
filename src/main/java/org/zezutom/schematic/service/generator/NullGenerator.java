package org.zezutom.schematic.service.generator;

import javax.lang.model.type.NullType;

public class NullGenerator implements ValueGenerator<NullType> {

    @Override
    public NullType next() {
        // TODO meaning of null can be contextual: null, "", -1, ..
        return null;
    }
}
