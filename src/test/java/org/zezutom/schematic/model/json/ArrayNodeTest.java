package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.ArrayGenerator;

import java.util.Collections;
import java.util.List;

public class ArrayNodeTest extends NodeTestCase<List<Object>, ArrayGenerator, ArrayNode> {

    @Override
    ArrayNode newInstance(String name, ArrayGenerator generator) {
        return new ArrayNode(name, generator);
    }

    @Override
    Class<ArrayGenerator> getGeneratorClass() {
        return ArrayGenerator.class;
    }

    @Override
    List<Object> getTestValue() {
        return Collections.singletonList("test");
    }
}
