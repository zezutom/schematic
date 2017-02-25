package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.EnumGenerator;

public class EnumNodeTest extends NodeTestCase<Object, EnumGenerator, EnumNode> {

    @Override
    EnumNode newInstance(String name, EnumGenerator generator) {
        return new EnumNode(name, generator);
    }

    @Override
    Class<EnumGenerator> getGeneratorClass() {
        return EnumGenerator.class;
    }

    @Override
    Object getTestValue() {
        return "one of enum values";
    }
}
