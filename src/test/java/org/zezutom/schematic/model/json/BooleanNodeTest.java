package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.BooleanGenerator;

public class BooleanNodeTest extends NodeTestCase<Boolean, BooleanGenerator, BooleanNode> {

    @Override
    BooleanNode newInstance(String name, BooleanGenerator generator) {
        return new BooleanNode(name, generator);
    }

    @Override
    Class<BooleanGenerator> getGeneratorClass() {
        return BooleanGenerator.class;
    }

    @Override
    Boolean getTestValue() {
        return true;
    }
}
