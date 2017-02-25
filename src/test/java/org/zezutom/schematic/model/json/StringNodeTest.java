package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.StringGenerator;

public class StringNodeTest extends NodeTestCase<String, StringGenerator, StringNode> {

    @Override
    StringNode newInstance(String name, StringGenerator generator) {
        return new StringNode(name, generator);
    }

    @Override
    Class<StringGenerator> getGeneratorClass() {
        return StringGenerator.class;
    }

    @Override
    String getTestValue() {
        return "test";
    }
}
