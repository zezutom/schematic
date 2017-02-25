package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.IntegerGenerator;

public class IntegerNodeTest extends NodeTestCase<Integer, IntegerGenerator, IntegerNode> {

    @Override
    IntegerNode newInstance(String name, IntegerGenerator generator) {
        return new IntegerNode(name, generator);
    }

    @Override
    Class<IntegerGenerator> getGeneratorClass() {
        return IntegerGenerator.class;
    }

    @Override
    Integer getTestValue() {
        return 10;
    }
}
