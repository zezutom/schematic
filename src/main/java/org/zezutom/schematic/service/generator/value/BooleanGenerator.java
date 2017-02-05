package org.zezutom.schematic.service.generator.value;

import org.zezutom.schematic.util.RandomUtil;

public class BooleanGenerator implements ValueGenerator<Boolean> {

    @Override
    public Boolean next() {
        return RandomUtil.nextBoolean();
    }
}
