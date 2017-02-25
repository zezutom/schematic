package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.NumberGenerator;

public class NumberNodeTest extends NodeTestCase<Number, NumberGenerator, NumberNode> {

    @Override
    NumberNode newInstance(String name, NumberGenerator generator) {
        return new NumberNode(name, generator);
    }

    @Override
    Class<NumberGenerator> getGeneratorClass() {
        return NumberGenerator.class;
    }

    @Override
    Number getTestValue() {
        return 10;
    }
}
