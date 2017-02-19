package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.util.RandomUtil;

@PrototypedService
public class BooleanGenerator implements ValueGenerator<Boolean> {

    @Override
    public Boolean next() {
        return RandomUtil.nextBoolean();
    }
}
